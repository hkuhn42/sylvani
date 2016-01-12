package org.sylvani.audio;

import java.io.OutputStream;

public interface AudioOutput {

    public String getName();

    public AudioFormat[] getSupportedFormats();

    public OutputStream getOutputStream() throws AudioException;

    public OutputStream getOutputStream(AudioFormat format) throws AudioException;

}
