/**
 *
 */
package org.sylvani.audio;

import java.io.InputStream;

/**
 * @author hkuhn
 *
 */
public interface AudioSource {

    public String getName();

    public AudioFormat[] getSupportedFormats();

    public InputStream getInputStream() throws AudioException;

    public InputStream getInputStream(AudioFormat format) throws AudioException;

}
