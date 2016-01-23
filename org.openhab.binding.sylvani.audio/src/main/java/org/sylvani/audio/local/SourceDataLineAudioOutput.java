/**
 *
 */
package org.sylvani.audio.local;

import java.io.IOException;
import java.io.OutputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;

import org.sylvani.audio.AudioException;
import org.sylvani.audio.AudioFormat;
import org.sylvani.audio.AudioOutput;
import org.sylvani.audio.AudioSource;
import org.sylvani.audio.JavaAudioUtil;
import org.sylvani.audio.SourceLineInfo;
import org.sylvani.audio.UnsupportedAudioFormatException;
import org.sylvani.io.audio.impl.AudioUtil;

/**
 * TODO:
 * add variant where the source line is fetched for a given format on access, return all formats of a
 *
 * @author hkuhn
 */
public class SourceDataLineAudioOutput implements AudioOutput {
    private SourceDataLine sourceDataLine;
    private Mixer.Info info;

    public SourceDataLineAudioOutput(SourceLineInfo sourceLineInfo) throws AudioException {
        try {
            this.sourceDataLine = (SourceDataLine) AudioSystem.getLine(sourceLineInfo.getLineInfo());
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new AudioException(e);
        }
        this.info = sourceLineInfo.getMixerInfo();
    }

    public SourceDataLineAudioOutput(SourceDataLine sourceDataLine, Mixer.Info info) {
        this.sourceDataLine = sourceDataLine;
        this.info = info;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.sylvani.io.audio.AudioOutput#getOutputStream()
     */
    @Override
    public OutputStream getOutputStream() {
        return new SourceOutputStream();
    }

    @Override
    public String getName() {
        return info.getName();
    }

    @Override
    public AudioFormat[] getSupportedFormats() {
        if (sourceDataLine.isOpen()) {
            return new AudioFormat[] { JavaAudioUtil.convert(sourceDataLine.getFormat()) };
        } else {
            return JavaAudioUtil.convert(((DataLine.Info) sourceDataLine.getLineInfo()).getFormats());
        }
    }

    @Override
    public OutputStream getOutputStream(AudioFormat format) throws UnsupportedAudioFormatException {
        return getOutputStream();
    }

    class SourceOutputStream extends OutputStream {

        @Override
        public void write(int i) throws IOException {
            throw new UnsupportedOperationException("always user write(byte[]9");
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            sourceDataLine.write(b, off, len);
        }
    }

    @Override
    public void stream(AudioSource source) throws AudioException {
        AudioUtil.stream(source, this);
    }
}
