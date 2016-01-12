/**
 *
 */
package org.sylvani.io.voice;

import java.util.Locale;

import org.sylvani.audio.AudioFormat;
import org.sylvani.audio.AudioSource;

/**
 * @author hkuhn
 *
 */
public interface ISyntheziserService {

    public AudioSource synthesize(String text, Locale locale, String voice, AudioFormat requestedFormat)
            throws Exception;

}
