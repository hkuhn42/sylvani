/**
 * Copyright (c) 2014-2015 openHAB UG (haftungsbeschraenkt) and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.sylvani.audio;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.smarthome.config.discovery.AbstractDiscoveryService;
import org.eclipse.smarthome.config.discovery.DiscoveryResult;
import org.eclipse.smarthome.config.discovery.DiscoveryResultBuilder;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sylvani.audio.AudioSystemDetails;
import org.sylvani.audio.JavaAudioUtil;

/**
 * The {@link AudioDeviceDiscovery} is responsible for discovering new MAX!
 * Cube LAN gateway devices on the network
 *
 * @author Marcel Verpaalen - Initial contribution
 *
 */
public class AudioDeviceDiscovery extends AbstractDiscoveryService {

    private final static Logger logger = LoggerFactory.getLogger(AudioDeviceDiscovery.class);

    static boolean discoveryRunning = false;

    /** The refresh interval for discovery of MAX! Cubes */
    private long refreshInterval = 600;
    private ScheduledFuture<?> discoveryJob;
    private Runnable discoveryRunnable = new Runnable() {
        @Override
        public void run() {
            discoverDevices();
        }
    };

    public AudioDeviceDiscovery() {
        super(SylvaniAudioBindingConstants.SUPPORTED_DEVICE_THING_TYPES_UIDS, 15, true);
    }

    @Override
    public Set<ThingTypeUID> getSupportedThingTypes() {
        return SylvaniAudioBindingConstants.SUPPORTED_DEVICE_THING_TYPES_UIDS;
    }

    @Override
    public void startScan() {
        logger.debug("Start Audio local Device discovery");
        scheduler.scheduleAtFixedRate(discoveryRunnable, 0, refreshInterval, TimeUnit.SECONDS);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.smarthome.config.discovery.AbstractDiscoveryService#stopBackgroundDiscovery()
     */
    @Override
    protected void stopBackgroundDiscovery() {
        logger.debug("Stop audio background discovery");
        if (discoveryJob != null && !discoveryJob.isCancelled()) {
            discoveryJob.cancel(true);
            discoveryJob = null;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.smarthome.config.discovery.AbstractDiscoveryService#startBackgroundDiscovery()
     */
    @Override
    protected void startBackgroundDiscovery() {
        logger.debug("Start audio background discovery");
        if (discoveryJob == null || discoveryJob.isCancelled()) {
            discoveryJob = scheduler.scheduleAtFixedRate(discoveryRunnable, 0, refreshInterval, TimeUnit.SECONDS);
        }
    }

    private synchronized void discoverDevices() {
        logger.debug("Run audio discovery");

        JavaAudioUtil util = new JavaAudioUtil();
        AudioSystemDetails detail = util.gatherMixersWithLines();
        if (detail.getInputs().size() > 0 || detail.getOutputs().size() > 0) {
            try {
                ThingUID thingUID = new ThingUID(SylvaniAudioBindingConstants.THING_TYPE_AUDIO_DEVICE, "localAudioDevice");

                DiscoveryResult discoveryResult = DiscoveryResultBuilder.create(thingUID)
                        .withLabel(InetAddress.getLocalHost().getHostName()).build();

                thingDiscovered(discoveryResult);
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        logger.debug("Done audio discovery.");
    }

}
