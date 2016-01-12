/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.sylvani.audio.handler;

import static org.openhab.binding.sylvani.audio.SylvaniAudioBindingConstants.CHANNEL_MICROPHONE;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Mixer;

import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.thing.binding.builder.ChannelBuilder;
import org.eclipse.smarthome.core.thing.binding.builder.ThingBuilder;
import org.eclipse.smarthome.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sylvani.audio.AudioSystemDetails;
import org.sylvani.audio.JavaAudioUtil;

/**
 * The {@link SylvaniAudioHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author hkuhn - Initial contribution
 */
public class SylvaniAudioHandler extends BaseThingHandler {

    private Logger logger = LoggerFactory.getLogger(SylvaniAudioHandler.class);

    public SylvaniAudioHandler(Thing thing) {
        super(thing);
        updateChannels();
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        if (channelUID.getId().equals(CHANNEL_MICROPHONE)) {
        }
    }

    private void updateChannels() {
        ThingBuilder thingBuilder = editThing();

        List<Channel> channels = new ArrayList<Channel>();

        AudioSystemDetails details = new JavaAudioUtil().gatherMixersWithLines();
        for (Mixer.Info outputMixer : details.getOutputs()) {
            channels.add(buildChannel(outputMixer));
        }
        for (Mixer.Info inputMixer : details.getInputs()) {
            channels.add(buildChannel(inputMixer));
        }

        thingBuilder.withChannels(channels);
        updateThing(thingBuilder.build());
    }

    public Channel buildChannel(Mixer.Info outputMixer) {
        String channelId = JavaAudioUtil.getIdForMixer(outputMixer);
        ChannelUID channelUID = new ChannelUID(thing.getUID(), channelId);
        Channel channel = ChannelBuilder.create(channelUID, outputMixer.getName()).build();
        return channel;
    }

}
