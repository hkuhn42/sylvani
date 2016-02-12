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
 * An enum representing an audio codec
 * 
 * currently only pcm is supported however
 * http://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
 *
 * @author Harald Kuhn (hkuhn42) initial api
 *
 */
public enum AudioCodec {

    /**
     * PCM Signed
     * 
     * http://wiki.multimedia.cx/?title=PCM#PCM_Types
     */
    PCM_SIGNED

    ,
    /**
     * PCM Unsigned
     * 
     * http://wiki.multimedia.cx/?title=PCM#PCM_Types
     */
    PCM_UNSIGNED

    ,
    /**
     * MP3 Codec
     */
    MP3

    ,
    /**
     * Vorbis Codec
     */
    VORBIS
}
