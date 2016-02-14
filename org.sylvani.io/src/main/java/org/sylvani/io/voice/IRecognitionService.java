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
 * Definition of a service for converting audio data with spoken language into text
 * 
 * @author Harald Kuhn (hkuhn42) initial api
 */
public interface IRecognitionService {

	/**
	 * Converts audio data with spoken language into text
	 * 
	 * @param audioSource the source of audio data
	 * @param locale the locale of the text
	 * @return a textual representation of the voice data
	 * @throws Exception
	 */
    public String recognize(AudioSource audioSource, Locale locale) throws Exception;

    /**
     * Array containing all supported audio formats this RecognitionService can process
     *
     * @return and array of AudioFormat
     */
    public AudioFormat[] getSupportedFormats();
}
