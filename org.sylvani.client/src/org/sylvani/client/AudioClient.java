/**
 *
 */
package org.sylvani.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

/**
 * @author hkuhn
 *
 */
public class AudioClient {

    private String serverUrl;
    private String captureFolder;
    private boolean captureToFolder = true;
    private boolean sendToServer = true;

    private boolean running;
    private ByteArrayOutputStream out;
    private ByteArrayOutputStream wavout;

    public AudioClient() {
        this("http://haraldm3800:8080/voice/relay/invokeVoiceCommand", "c:/temp");
    }

    public AudioClient(String serverUrl, String captureFolder) {
        this.serverUrl = serverUrl;
        this.captureFolder = captureFolder;
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        AudioClient sp = new AudioClient();
        File file = new File("c:/temp/turnon.wav");
        byte[] audioData = FileUtils.readFileToByteArray(file);

        byte[] response = sp.invokeVoiceCommand(audioData);

        FileUtils.writeByteArrayToFile(new File("c:/temp/ok.wav"), response);

    }

    public byte[] invokeVoiceCommand(byte[] audioData) throws IOException {
        Content content = Request.Post(serverUrl).bodyByteArray(audioData).execute().returnContent();
        return content.asBytes();
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getCaptureFolder() {
        return captureFolder;
    }

    public void setCaptureFolder(String captureFolder) {
        this.captureFolder = captureFolder;
    }

    public boolean isCaptureToFolder() {
        return captureToFolder;
    }

    public void setCaptureToFolder(boolean captureToFolder) {
        this.captureToFolder = captureToFolder;
    }

    public boolean isSendToServer() {
        return sendToServer;
    }

    public void setSendToServer(boolean sendToServer) {
        this.sendToServer = sendToServer;
    }

    public void playAudio() {
        playAudio(out.toByteArray());
    }

    public void playAudio(InputStream inputStream) throws LineUnavailableException {

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
            playAudio(audioStream);
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void playAudio(InputStream inputStream, SourceDataLine line) throws LineUnavailableException {

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
            playAudio(audioStream, line);
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void playAudio(byte[] audio) {
        InputStream input = new ByteArrayInputStream(audio);
        AudioFormat format = AudioTools.getFormat();
        AudioInputStream ais = new AudioInputStream(input, format, audio.length / format.getFrameSize());
        playAudio(ais);
    }

    public void playAudio(byte[] audio, Line.Info line) throws LineUnavailableException {
        InputStream input = new ByteArrayInputStream(audio);
        AudioFormat format = AudioTools.getFormat();
        AudioInputStream ais = new AudioInputStream(input, format, audio.length / format.getFrameSize());
        playAudio(ais, line);
    }

    public void playAudio(final AudioInputStream ais) {
        try {
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, ais.getFormat());

            playAudio(ais, info);
        } catch (LineUnavailableException e) {
            System.err.println("Line unavailable: " + e);
            System.exit(-4);
        }
    }

    public void playAudio(final AudioInputStream ais, Line.Info info) throws LineUnavailableException {
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
        playAudio(ais, line);
    }

    public void playAudio(final AudioInputStream ais, final SourceDataLine line) throws LineUnavailableException {
        line.open(ais.getFormat());

        System.out.println(line.getLineInfo());

        line.start();

        Runnable runner = new Runnable() {
            int bufferSize = (int) ais.getFormat().getSampleRate() * ais.getFormat().getFrameSize();
            byte buffer[] = new byte[bufferSize];

            @Override
            public void run() {
                try {
                    int count;
                    while ((count = ais.read(buffer, 0, buffer.length)) != -1) {
                        if (count > 0) {
                            line.write(buffer, 0, count);
                        }
                    }
                    line.drain();
                    line.close();
                } catch (IOException e) {
                    System.err.println("I/O problems: " + e);
                    System.exit(-3);
                }
            }
        };
        Thread playThread = new Thread(runner);
        playThread.start();
    }

    public void captureAudio() {
        try {
            final AudioFormat format = AudioTools.getFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            final TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();
            Runnable runner = new Runnable() {
                int bufferSize = (int) format.getSampleRate() * format.getFrameSize();
                byte buffer[] = new byte[bufferSize];

                @Override
                public void run() {
                    out = new ByteArrayOutputStream();
                    running = true;
                    try {
                        while (running) {
                            int count = line.read(buffer, 0, buffer.length);
                            if (count > 0) {
                                out.write(buffer, 0, count);
                            }
                        }
                        out.close();
                        line.close();

                        wavout = new ByteArrayOutputStream();
                        byte[] byteArray = out.toByteArray();
                        AudioInputStream ais = new AudioInputStream(new ByteArrayInputStream(byteArray), format,
                                byteArray.length / format.getFrameSize());
                        AudioSystem.write(ais, AudioFileFormat.Type.WAVE, wavout);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
                        String timestamp = sdf.format(new Timestamp(System.currentTimeMillis()));

                        FileUtils.writeByteArrayToFile(new File(getCaptureFolder(), "data" + timestamp + ".wav"),
                                wavout.toByteArray());

                        byte[] response = invokeVoiceCommand(wavout.toByteArray());
                        playAudio(new ByteArrayInputStream(response));

                    } catch (IOException e) {
                        System.err.println("I/O problems: " + e);
                        System.exit(-1);
                    } catch (LineUnavailableException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            Thread captureThread = new Thread(runner);
            captureThread.start();
        } catch (LineUnavailableException e) {
            System.err.println("Line unavailable: " + e);
            System.exit(-2);
        }
    }

    public void finished() {
        running = false;
    }

    /**
     * test method for local audio
     * TODO add to ui to test local audio
     */
    public void streamDefaults() {
        AudioFormat format = new AudioFormat(16000, 8, 1, true, true);

        DataLine.Info targetInfo = new DataLine.Info(TargetDataLine.class, format);
        DataLine.Info sourceInfo = new DataLine.Info(SourceDataLine.class, format);

        try {
            TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
            targetLine.open(format);
            targetLine.start();

            SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(sourceInfo);
            sourceLine.open(format);
            sourceLine.start();

            int numBytesRead;
            byte[] targetData = new byte[targetLine.getBufferSize() / 5];

            while (true) {
                numBytesRead = targetLine.read(targetData, 0, targetData.length);

                if (numBytesRead == -1) {
                    break;
                }

                sourceLine.write(targetData, 0, numBytesRead);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
