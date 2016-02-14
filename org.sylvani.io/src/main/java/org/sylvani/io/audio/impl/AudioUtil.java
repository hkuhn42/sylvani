/**
 * Copyright (c) 2015-2016 Harald Kuhn
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.sylvani.io.audio.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.sylvani.audio.AudioException;
import org.sylvani.audio.AudioOutput;
import org.sylvani.audio.AudioSource;

/**
 * Utility for audio handling
 * 
 * @author Harald Kuhn (hkuhn42) initial api
 */
public class AudioUtil {

    /**
     *
     */
    public AudioUtil() {
        // TODO Auto-generated constructor stub
    }

    public static void stream(AudioSource source, AudioOutput output) throws AudioException {

        try (InputStream is = source.getInputStream(); OutputStream os = output.getOutputStream();) {
            IOUtils.copy(is, os);
        } catch (IOException e) {
            throw new AudioException(e);
        }
    }

}
