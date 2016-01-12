package org.sylvani.audio;

/**
 * currently only pcm is supported however
 * http://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
 *
 *
 *
 * @author hkuhn
 *
 */
public enum AudioCodec {

    /**
     * http://wiki.multimedia.cx/?title=PCM#PCM_Types
     */
    PCM_SIGNED

    ,
    /**
     *
     */
    PCM_UNSIGNED

    ,
    /**
     *
     */
    MP3

    ,
    /**
     *
     */
    VORBIS
}
