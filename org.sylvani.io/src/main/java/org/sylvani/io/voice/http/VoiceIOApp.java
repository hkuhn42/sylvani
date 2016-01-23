package org.sylvani.io.voice.http;
/**
 * Copyright (c) 2015 Harald Kuhn
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.servlet.ServletException;

import org.eclipse.smarthome.core.events.Event;
import org.eclipse.smarthome.core.events.EventFilter;
import org.eclipse.smarthome.core.events.EventSubscriber;
import org.eclipse.smarthome.core.items.events.ItemCommandEvent;
import org.eclipse.smarthome.core.items.events.ItemStateEvent;
import org.glassfish.jersey.servlet.ServletContainer;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sylvani.io.VoiceControlContext;
import org.sylvani.io.event.AbstractAudioEvent;
import org.sylvani.io.interpreter.ICommandInterpreter;
import org.sylvani.io.voice.IRecognitionService;
import org.sylvani.io.voice.ISyntheziserService;

/**
 * org.sylvani.openhab.io.voice.http.VoiceIOApp
 */
public class VoiceIOApp implements EventSubscriber {

    public static final String WEBAPP_ALIAS = "/voice/recognition";

    private Logger logger = LoggerFactory.getLogger(VoiceIOApp.class);

    protected HttpService httpService;
    public static final String PATH_SITEMAPS = "sitemaps";

    private VoiceControlContext context = new VoiceControlContext();

    protected void activate(ComponentContext componentContext) {
        try {
            // Hashtable<String, String> props = new Hashtable<String, String>();
            // httpService.registerServlet(WEBAPP_ALIAS, new RecognitionRelayServlet(itemUIRegistry, sitemapProviders),
            // props, httpService.createDefaultHttpContext());

            Hashtable<String, String> restProps = new Hashtable<String, String>();
            restProps.put("javax.ws.rs.Application", SylvaniJerseyApplication.class.getName());

            AudioInResource.init(context);

            ClassLoader myClassLoader = getClass().getClassLoader();
            ClassLoader originalContextClassLoader = Thread.currentThread().getContextClassLoader();
            try {
                Thread.currentThread().setContextClassLoader(myClassLoader);
                httpService.registerServlet("/voice/relay", new ServletContainer(), restProps, null);
            } finally {
                Thread.currentThread().setContextClassLoader(originalContextClassLoader);
            }

            logger.info("Started voice io at " + WEBAPP_ALIAS);
        } catch (NamespaceException e) {
            logger.error("Error during servlet startup", e);
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void deactivate(ComponentContext componentContext) {
        httpService.unregister(WEBAPP_ALIAS);
        logger.info("Stopped voice IO");
    }

    protected void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    protected void unsetHttpService(HttpService httpService) {
        this.httpService = null;
    }

    public void setRecognitionService(IRecognitionService recognitionService) {
        System.out.println("recognitionService set " + recognitionService);
        context.setRecognitionService(recognitionService);
    }

    public void unsetRecognitionService(IRecognitionService recognitionService) {
        context.setRecognitionService(null);
    }

    public void setSyntheziserService(ISyntheziserService syntheziserService) {
        System.out.println("syntheziserService set " + syntheziserService);
        context.setSyntheziserService(syntheziserService);
    }

    public void unsetSyntheziserService(ISyntheziserService syntheziserService) {
        context.setSyntheziserService(null);
    }

    public void setCommandInterpreter(ICommandInterpreter commandInterpreter) {
        System.out.println("commandInterpreter set " + commandInterpreter);
        context.setCommandInterpreter(commandInterpreter);
    }

    // event subscriber

    @Override
    public Set<String> getSubscribedEventTypes() {
        Set<String> types = new HashSet<>(2);
        types.add(ItemCommandEvent.TYPE);
        types.add(ItemStateEvent.TYPE);
        return types;
    }

    @Override
    public EventFilter getEventFilter() {
        return null;
    }

    @Override
    public void receive(Event event) {
        logger.info(event.toString() + " (" + event.getClass() + " " + event.getSource() + " " + event.getPayload()
                + " " + event.getTopic() + " " + event.getTopic());
        if (event instanceof AbstractAudioEvent) {

        }
    }
}
