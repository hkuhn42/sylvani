/**
 *
 */
package org.sylvani.audio;

import java.io.InputStream;

/**
 * Wrapper for a source of audio data
 *
 * @author hkuhn
 */
public interface AudioSource {

    /**
     * Returns the human readable name of the source
     *
     * @return the human readable name of the source
     */
    public String getName();

    /**
     * an array containing all supported audio formats
     *
     * @return
     */
    public AudioFormat[] getSupportedFormats();

    /**
     * An inputstream for reading audio data, the format is the default format
     *
     * @return InputStream for reading audio data
     * @throws AudioException
     */
    public InputStream getInputStream() throws AudioException;

    /**
     * An inputstream for reading audio data, the format is set to the given format
     *
     * @param format the desired format (one of getSupportedFormats) or null for the default format
     * @return InputStream for reading audio data
     * @throws AudioException
     */
    public InputStream getInputStream(AudioFormat format) throws AudioException;

}
