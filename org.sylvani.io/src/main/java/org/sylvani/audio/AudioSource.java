/**
 * Copyright (c) 2015-2016 Harald Kuhn
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.sylvani.audio;

import java.io.InputStream;

/**
 * Wrapper for a source of audio data
 *
 *  @author Harald Kuhn (hkuhn42) initial api
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
     * Gives access to an InputStream for reading audio data, the format is the default format
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

    /**
     * Load data from this AudioSource to the given {@link AudioOutput} 
     *
     * @param output
     * @throws AudioException
     */
    public void stream(AudioOutput output) throws AudioException;
    
    /**
     * Returns true if this AudioSource can stream to the given {@link AudioOutput}
     * false otherwise
     * 
     * @param source
     * @return true if the AudioSource can be processes
     */
    public boolean canStream(AudioOutput source);
}
