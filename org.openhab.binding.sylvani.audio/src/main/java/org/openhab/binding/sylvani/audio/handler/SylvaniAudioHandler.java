/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.sylvani.audio.handler;

import static org.openhab.binding.sylvani.audio.SylvaniAudioBindingConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.smarthome.core.library.types.PlayPauseType;
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.thing.binding.builder.ChannelBuilder;
import org.eclipse.smarthome.core.thing.binding.builder.ThingBuilder;
import org.eclipse.smarthome.core.thing.type.ChannelTypeUID;
import org.eclipse.smarthome.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sylvani.audio.AudioException;
import org.sylvani.audio.AudioSystemDetails;
import org.sylvani.audio.JavaAudioUtil;
import org.sylvani.audio.SourceLineInfo;
import org.sylvani.audio.TargetLineInfo;
import org.sylvani.audio.local.SourceDataLineAudioOutput;
import org.sylvani.audio.local.TargetDataLineAudioSource;
import org.sylvani.io.audio.AudioContext;

/**
 * The {@link SylvaniAudioHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 *
 *
 * @author hkuhn - Initial contribution
 */
public class SylvaniAudioHandler extends BaseThingHandler {

    private Logger logger = LoggerFactory.getLogger(SylvaniAudioHandler.class);

    private AudioContext audioContext;

    public SylvaniAudioHandler(Thing thing, AudioContext audioContext) {
        super(thing);
        this.audioContext = audioContext;
        updateChannels();
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        System.out.println("hello " + command);

        ChannelTypeUID channelTypeUID = thing.getChannel(channelUID.getId()).getChannelTypeUID();
        if (channelTypeUID.equals(CHANNEL_TYPE_MICROPHONE)) {
            if (command == PlayPauseType.PLAY) {

            } else if (command == PlayPauseType.PAUSE) {

            }
        } else if (channelUID.getId().equals(CHANNEL_TYPE_SPEAKER)) {
            if (command == PlayPauseType.PLAY) {

            } else if (command == PlayPauseType.PAUSE) {

            }

        }

    }

    private void updateChannels() {
        ThingBuilder thingBuilder = editThing();

        List<Channel> channels = new ArrayList<Channel>();

        AudioSystemDetails details = new JavaAudioUtil().gatherMixersWithLines();
        try {
            for (SourceLineInfo sourceLineInfo : details.getOutputs()) {
                channels.add(buildChannel(sourceLineInfo));
                audioContext.registerOutput(sourceLineInfo.getId(), new SourceDataLineAudioOutput(sourceLineInfo));

            }
            for (TargetLineInfo targetLineInfo : details.getInputs()) {
                channels.add(buildChannel(targetLineInfo));
                audioContext.registerSource(targetLineInfo.getId(), new TargetDataLineAudioSource(targetLineInfo));
            }
        } catch (AudioException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        thingBuilder.withChannels(channels);
        try {
            updateThing(thingBuilder.build());
        } catch (Exception e) {
            System.out.println("updateThing still broken");
        }

    }

    public Channel buildChannel(SourceLineInfo outputMixer) {
        String channelId = outputMixer.getId();
        ChannelUID channelUID = new ChannelUID(thing.getUID(), channelId);
        Channel channel = ChannelBuilder.create(channelUID, outputMixer.getName()).build();
        return channel;
    }

    public Channel buildChannel(TargetLineInfo outputMixer) {
        String channelId = outputMixer.getId();
        ChannelUID channelUID = new ChannelUID(thing.getUID(), channelId);
        Channel channel = ChannelBuilder.create(channelUID, outputMixer.getName()).build();
        return channel;
    }
}
