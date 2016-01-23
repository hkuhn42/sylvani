/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.sylvani.audio.internal;

import static org.openhab.binding.sylvani.audio.SylvaniAudioBindingConstants.THING_TYPE_AUDIO_DEVICE;

import java.util.Collections;
import java.util.Set;

import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.openhab.binding.sylvani.audio.handler.SylvaniAudioHandler;
import org.sylvani.io.audio.AudioContext;

/**
 * The {@link SylvaniAudioHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author hkuhn - Initial contribution
 */
public class SylvaniAudioHandlerFactory extends BaseThingHandlerFactory {

    private final static Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections.singleton(THING_TYPE_AUDIO_DEVICE);

    private AudioContext audioContext;

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected ThingHandler createHandler(Thing thing) {

        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(THING_TYPE_AUDIO_DEVICE)) {
            return new SylvaniAudioHandler(thing, audioContext);
        }

        return null;
    }

    public void setAudioContext(AudioContext audioContext) {
        this.audioContext = audioContext;
    }

    public void unsetAudioContext(AudioContext audioContext) {
        this.audioContext = null;
    }
}
