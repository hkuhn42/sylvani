/**
 * Copyright (c) 2015-2016 Harald Kuhn
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.sylvani.audio;

import java.io.OutputStream;

/**
 * Definition of an audio output like headphones, a speaker or for a writing to a file / clip
 * Also used by recognition service
 *
 * @author Harald Kuhn (hkuhn42) initial api
 */
public interface AudioOutput {

    /**
     * Returns the human readable name of the source
     *
     * @return the human readable name of the source
     */
    public String getName();

    /**
     * Array containing all supported audio formats this output can process
     *
     * @return
     */
    public AudioFormat[] getSupportedFormats();

    /**
     * An output stream for writing audio data in the default {@link AudioFormat} of this output
     * 
     * @return an {@link OutputStream} 
     * @throws AudioException 
     */
    public OutputStream getOutputStream() throws AudioException;

    /**
     * An output stream for output audio, the format is set to the given format, throws and {@link UnsupportedAudioFormatException} if the given
     * format is not supported
     *
     * @param format the desired format (one of getSupportedFormats) or null for the default format
     * @return an OutputStream to read data from this output
     * @throws AudioException thrown among other reasons if the given format is not supported
     */
    public OutputStream getOutputStream(AudioFormat format) throws AudioException;

    /**
     * Process audio data from the provided {@link AudioSource} throws and {@link AudioException} if matching formats are found
     * 
     * @param source
     * @throws AudioException
     */
    public void stream(AudioSource source) throws AudioException;
    
    /**
     * Returns true if the given {@link AudioSource} can be processes by this output
     * 
     * @param source
     * @return true if the AudioSource can be processes
     */
    public boolean canStream(AudioSource source);
}
