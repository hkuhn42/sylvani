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
     * Aarray containing all supported audio formats
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

    /**
     * Output audio from the provided source
     * 
     * @param source
     * @throws AudioException
     */
    public void stream(AudioSource source) throws AudioException;
}
