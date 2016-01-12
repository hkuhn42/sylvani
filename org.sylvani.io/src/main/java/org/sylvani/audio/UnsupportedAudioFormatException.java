/**
 *
 */
package org.sylvani.audio;

/**
 * Thrown when a requested format is not supported by a audio source or audiooutput implementation
 * 
 * @author hkuhn
 */
public class UnsupportedAudioFormatException extends AudioException {

    private static final long serialVersionUID = 1L;

    private AudioFormat unsupportedFormat;

    public UnsupportedAudioFormatException(String message, AudioFormat unsupportedFormat, Throwable cause) {
        super(message, cause);
        this.unsupportedFormat = unsupportedFormat;
    }

    public UnsupportedAudioFormatException(String message, AudioFormat unsupportedFormat) {
        this(message, unsupportedFormat, null);
    }

    public AudioFormat getUnsupportedFormat() {
        return unsupportedFormat;
    }
}