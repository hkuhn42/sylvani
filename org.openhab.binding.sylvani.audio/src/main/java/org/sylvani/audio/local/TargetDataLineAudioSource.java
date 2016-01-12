/**
 *
 */
package org.sylvani.audio.local;

import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

import org.sylvani.audio.AudioException;
import org.sylvani.audio.AudioFormat;
import org.sylvani.audio.AudioSource;
import org.sylvani.audio.JavaAudioUtil;

/**
 * @author hkuhn
 *
 */
public class TargetDataLineAudioSource implements AudioSource {

    private TargetDataLine targetDataLine;
    private Mixer.Info info;

    public TargetDataLineAudioSource(TargetDataLine targetDataLine, Mixer.Info info) {
        this.targetDataLine = targetDataLine;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.sylvani.io.audio.AudioSource#getInputStream()
     */
    @Override
    public InputStream getInputStream() {
        return new AudioInputStream(targetDataLine);
    }

    @Override
    public String getName() {
        return info.getName();
    }

    @Override
    public AudioFormat[] getSupportedFormats() {
        if (targetDataLine.isOpen()) {
            return new AudioFormat[] { JavaAudioUtil.convert(targetDataLine.getFormat()) };
        } else {
            return JavaAudioUtil.convert(((DataLine.Info) targetDataLine.getLineInfo()).getFormats());
        }
    }

    @Override
    public InputStream getInputStream(AudioFormat format) throws AudioException {
        try {
            targetDataLine.open(JavaAudioUtil.convert(format));
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new AudioException("", e);
        }
        return getInputStream();
    }

}
