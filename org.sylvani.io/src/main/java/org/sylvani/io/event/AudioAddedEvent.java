/**
 *
 */
package org.sylvani.io.event;

import org.sylvani.audio.AudioSource;

/**
 *
 *
 * @author hkuhn
 *
 */
public class AudioAddedEvent extends AbstractAudioEvent {

    /**
     * @param topic
     * @param payload
     * @param source
     */
    public AudioAddedEvent(String topic, String payload, String source) {
        super(topic, payload, source);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return null;
    }

    public AudioSource getAudioSource() {
        return null;
    }
}
