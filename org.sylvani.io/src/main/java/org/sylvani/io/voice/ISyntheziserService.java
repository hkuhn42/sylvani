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

import org.sylvani.audio.AudioFormat;
import org.sylvani.audio.AudioSource;

/**
 * Definition of a service for synthezising voice output as audio data
 * 
 * @author Harald Kuhn (hkuhn42) initial api
 */
public interface ISyntheziserService {

	/**
	 * Synthezise voice output for the given text in the given locale with the specified voice and format
	 * 
	 * Voice and format info should be optional
	 * 
	 * @param text
	 * @param voice
	 * @param requestedFormat
	 * @return
	 * @throws Exception
	 */
    public AudioSource synthesize(String text, Voice voice, AudioFormat requestedFormat)
            throws Exception;
    
    /**
     * Array containing all supported audio formats this SyntheziserService can produce
     *
     * @return an array of AudioFormat
     */
    public AudioFormat[] getSupportedFormats();
    
    /**
     * Get the default voice for the given locale or null if it is not supported 
     * 
     * @param locale
     * @return
     */
    public Voice getDefaultVoice(Locale locale);

    /**
     * An array containing all supported voices of this SyntheziserService
     * 
     * @return an array of Voice implementations
     */
    public Voice[] getAvailableVoices();

}
