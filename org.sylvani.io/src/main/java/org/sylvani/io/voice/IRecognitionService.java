/**
 *
 */
package org.sylvani.io.voice;

import java.util.Locale;

import org.sylvani.audio.AudioSource;

/**
 * @author hkuhn
 *
 */
public interface IRecognitionService {

    public String recognize(AudioSource audioSource, Locale locale) throws Exception;

}
