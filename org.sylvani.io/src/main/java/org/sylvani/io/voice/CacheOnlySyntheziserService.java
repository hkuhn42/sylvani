/**
 *
 */
package org.sylvani.io.voice;

import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.sylvani.audio.AudioFormat;
import org.sylvani.audio.AudioSource;
import org.sylvani.io.audio.impl.AudioClip;

/**
 * @author hkuhn
 *
 */
public class CacheOnlySyntheziserService implements ISyntheziserService {

    @Override
    public AudioSource synthesize(String text, Locale locale, String voice, AudioFormat requestedFormat)
            throws Exception {
        if ("ok".equalsIgnoreCase(text)) {
            byte[] response = IOUtils.toByteArray(this.getClass().getResourceAsStream("ok.wav"));
            return new AudioClip(response);
        }
        return null;
    }

}
