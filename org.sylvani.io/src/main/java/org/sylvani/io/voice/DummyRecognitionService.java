/**
 * Copyright (c) 2015-2016 Harald Kuhn
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.sylvani.io.voice;

import java.io.FileOutputStream;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.sylvani.audio.AudioSource;

/**
 * This is a simple test utility which saves the audio to a tmp file and always returns "turn the light on"
 *
 * @author hkuhn
 */
public class DummyRecognitionService implements IRecognitionService {

    /*
     * (non-Javadoc)
     *
     * @see org.sylvani.io.voice.IRecognitionService#recognize(org.sylvani.io.voice.AudioFragment, java.util.Locale)
     */
    @Override
    public String recognize(AudioSource audio, Locale locale) throws Exception {
        IOUtils.copy(audio.getInputStream(null), new FileOutputStream("c:/temp/newData.wav"));
        return "turn the light on";
    }

}
