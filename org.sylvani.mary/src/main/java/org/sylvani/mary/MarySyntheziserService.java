/**
 *
 */
package org.sylvani.mary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.sylvani.audio.AudioException;
import org.sylvani.audio.AudioFormat;
import org.sylvani.audio.AudioSource;
import org.sylvani.io.audio.impl.AudioClip;
import org.sylvani.io.voice.ISyntheziserService;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.BufferedDoubleDataSource;
import marytts.util.data.audio.DDSAudioInputStream;
import marytts.util.data.audio.MaryAudioUtils;

/**
 * This is a voice syntheziser service based on mary tts
 * seen
 * http://mary.dfki.de/index.html
 *
 * it uses mary interface:
 * https://github.com/marytts/marytts/wiki/MaryInterface
 *
 * Currently only german and us english are supported. For additional locales voices need to be installed. Be aware that
 * these vocie files are very big!
 *
 * for adding voices see
 * https://github.com/marytts/marytts/issues/262
 *
 * @author hkuhn
 */
public class MarySyntheziserService implements ISyntheziserService {

    @Override
    public AudioSource synthesize(String text, Locale locale, String voice, AudioFormat requestedFormat)
            throws AudioException {
        AudioClip fragment;
        try {
            MaryInterface marytts = new LocalMaryInterface();
            marytts.setLocale(locale);
            AudioInputStream audio = marytts.generateAudio(text);

            DDSAudioInputStream outputAudio = new DDSAudioInputStream(
                    new BufferedDoubleDataSource(MaryAudioUtils.getSamplesAsDoubleArray(audio)), audio.getFormat());
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            AudioSystem.write(outputAudio, AudioFileFormat.Type.WAVE, bStream);

            fragment = new AudioClip();
            fragment.setFormat(requestedFormat);
            fragment.setData(bStream.toByteArray());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new AudioException(e);
        } catch (MaryConfigurationException e) {
            e.printStackTrace();
            throw new AudioException(e);
        } catch (SynthesisException e) {
            e.printStackTrace();
            throw new AudioException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AudioException(e);
        }

        return fragment;
    }
}