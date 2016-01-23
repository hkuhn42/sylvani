/**
 *
 */
package org.sylvani.io.audio.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.sylvani.audio.AudioException;
import org.sylvani.audio.AudioOutput;
import org.sylvani.audio.AudioSource;

/**
 * @author hkuhn
 *
 */
public class AudioUtil {

    /**
     *
     */
    public AudioUtil() {
        // TODO Auto-generated constructor stub
    }

    public static void stream(AudioSource source, AudioOutput output) throws AudioException {

        try (InputStream is = source.getInputStream(); OutputStream os = output.getOutputStream();) {
            IOUtils.copy(is, os);
        } catch (IOException e) {
            throw new AudioException(e);
        }

    }

}
