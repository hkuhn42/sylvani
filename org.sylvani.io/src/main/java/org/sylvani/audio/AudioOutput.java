package org.sylvani.audio;

import java.io.OutputStream;

/**
 * Definition of an audio ouput like headphones, a speaker or for a writing to a file / clip
 * Also used by recognition service
 *
 * @author hkuhn
 *
 */
public interface AudioOutput {

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
     * An output stream for output audio, the format is the default format
     *
     * @return
     * @throws AudioException
     */
    public OutputStream getOutputStream() throws AudioException;

    /**
     * An output stream for output audio, the format is set to the given format
     *
     * @param format the desired format (one of getSupportedFormats) or null for the default format
     * @return
     * @throws AudioException
     */
    public OutputStream getOutputStream(AudioFormat format) throws AudioException;

}
