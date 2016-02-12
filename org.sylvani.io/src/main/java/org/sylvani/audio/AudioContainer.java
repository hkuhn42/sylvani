/**
 * Copyright (c) 2015-2016 Harald Kuhn
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.sylvani.audio;

/**
 * The type of container an audio clips data is wrapped in
 *
 * @author Harald Kuhn (hkuhn42) initial api
 */
public enum AudioContainer {

    /**
     * NONE
     * AudioCodec encoded data without any container header or footer
     *
     * eg MP3 is a non container format
     */
    NONE

    /**
     * Microsofts wave container format
     * http://www.zytrax.com/tech/audio/formats.html#wav-format
     *
     * for a list of codesc supported by WAV see
     * http://www.opennetcf.com/library/sdf/html/60ca47dc-0b9d-2be4-a738-d0080c6fe10c.htm
     * 
     * the riff audio format
     */
    ,WAVE
    

    /**
     * http://www.xiph.org/ogg/
    */ 
    ,OGG   
}