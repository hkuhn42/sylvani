/**
 *
 */
package org.sylvani.io.event;

import org.eclipse.smarthome.core.events.AbstractEvent;

/**
 * @author hkuhn
 *
 */
public abstract class AbstractAudioEvent extends AbstractEvent {

    /**
     * @param topic
     * @param payload
     * @param source
     */
    public AbstractAudioEvent(String topic, String payload, String source) {
        super(topic, payload, source);
    }
}
