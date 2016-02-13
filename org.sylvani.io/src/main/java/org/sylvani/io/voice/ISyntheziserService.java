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
 * @author hkuhn
 *
 */
public interface ISyntheziserService {

    public AudioSource synthesize(String text, Locale locale, String voice, AudioFormat requestedFormat)
            throws Exception;

}
