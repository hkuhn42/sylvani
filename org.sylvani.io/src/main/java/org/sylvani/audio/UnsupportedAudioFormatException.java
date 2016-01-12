/**
 *
 */
package org.sylvani.audio;

/**
 *
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