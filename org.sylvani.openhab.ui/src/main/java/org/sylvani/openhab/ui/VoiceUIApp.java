/**
 * Copyright (c) 2015 Harald Kuhn
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.sylvani.openhab.ui;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public class VoiceUIApp {

    public static final String WEBAPP_ALIAS = "/voice";
    private static final Logger logger = LoggerFactory.getLogger(VoiceUIApp.class);

    protected HttpService httpService;

    protected void activate(ComponentContext componentContext) {
        try {
            httpService.registerResources(WEBAPP_ALIAS, "web", null);
            logger.info("Started voice UI at " + WEBAPP_ALIAS);
        } catch (NamespaceException e) {
            logger.error("Error during servlet startup", e);
        }
    }

    protected void deactivate(ComponentContext componentContext) {
        httpService.unregister(WEBAPP_ALIAS);
        logger.info("Stopped voice UI");
    }

    protected void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    protected void unsetHttpService(HttpService httpService) {
        this.httpService = null;
    }

}
