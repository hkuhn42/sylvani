/**
 * Copyright (c) 2015-2016 Harald Kuhn
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.sylvani.io.voice;

import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.sylvani.audio.AudioFormat;
import org.sylvani.audio.AudioSource;
import org.sylvani.io.audio.impl.AudioClip;

/**
 * This is a implementation of {@link ISyntheziserService} that uses pre cached audio data for known textual responses. 
 * Depending on the final architecture this may wrap an actual synthsizer or just throw an exception if the text is not known
 * 
 * TODO
 * - check requested format, locale and voice before returning data
 * - find a better way to store the audio 
 * 
 * @author hkuhn
 */
public class CacheOnlySyntheziserService implements ISyntheziserService {

    @Override
    public AudioSource synthesize(String text, Locale locale, String voice, AudioFormat requestedFormat)
            throws Exception {
        if ("ok".equalsIgnoreCase(text)) {
            byte[] response = IOUtils.toByteArray(this.getClass().getResourceAsStream("ok.wav"));
            return new AudioClip(response);
        }
        return null;
    }

}
