package org.sylvani.client;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;

import org.apache.commons.io.FileUtils;

public class AudioTools {

    AudioClient client;
    byte[] test;

    public static void main(String[] args) throws Exception {
        AudioTools sp = new AudioTools();
        sp.testTargetLines();

    }

    public AudioTools() throws IOException {
        client = new AudioClient();
        test = FileUtils.readFileToByteArray(new File("c:/temp/maaa.wav"));
    }

    public static AudioFormat getFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    public void testTargetLines() {

        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        List<Line.Info> availableLines = new ArrayList<Line.Info>();
        for (Mixer.Info mixerInfo : mixers) {

            Mixer m = AudioSystem.getMixer(mixerInfo);

            // System.out.println("Found Mixer: " + mixerInfo.getName() + ";" + mixerInfo.getDescription() + " "
            // + mixerInfo.getVendor());

            // Line.Info[] lines = m.getTargetLineInfo();
            //
            // for (Line.Info li : lines) {
            // if (li.getLineClass() != Port.class) {
            // try {
            // m.open();
            // availableLines.add(li);
            // System.out.println("+: " + li + " (" + m.getMixerInfo());
            // } catch (LineUnavailableException e) {
            // System.out.println("Line unavailable.");
            // }
            // }
            //
            // }

            Line.Info[] lines = m.getSourceLineInfo();

            for (Line.Info li : lines) {
                if (li.getLineClass() == SourceDataLine.class) {
                    try {
                        m.open();
                        availableLines.add(li);
                        System.out.println("-: " + li + " (" + m.getMixerInfo());

                        client.playAudio(new ByteArrayInputStream(test), (SourceDataLine) m.getLine(li));
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } catch (LineUnavailableException e) {
                        System.out.println("Line unavailable.");
                    }
                }
            }
        }

    }
}
