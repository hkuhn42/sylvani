/**
 * Copyright (c) 2015-2016 Harald Kuhn
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.sylvani.io.audio;

import java.util.List;

import org.sylvani.audio.AudioException;
import org.sylvani.audio.AudioOutput;
import org.sylvani.audio.AudioSource;

/**
 * A central registry for audio sources and outputs
 * 
 * @author Harald Kuhn (hkuhn42) initial api
 */
public interface AudioContext {

    public List<AudioSource> getSources();

    public AudioSource getSource(String key);

    public List<AudioOutput> getOutputs();

    public AudioOutput getOutput(String key);

    public void registerSource(String key, AudioSource source);

    public void registerOutput(String key, AudioOutput output);

    public AudioOutput relay(AudioSource source) throws AudioException;
    
    public AudioSource relay(AudioOutput source) throws AudioException;
}
