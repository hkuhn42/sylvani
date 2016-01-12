/**
 *
 */
package org.sylvani.audio;

/**
 * General purpose audio exception
 *
 * @author hkuhn
 */
public class AudioException extends Exception {

    private static final long serialVersionUID = 1L;

    public AudioException() {
        super();
    }

    public AudioException(String message, Throwable cause) {
        super(message, cause);
    }

    public AudioException(String message) {
        super(message);
    }

    public AudioException(Throwable cause) {
        super(cause);
    }
}