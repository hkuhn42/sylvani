/**
 * Copyright (c) 2015 Harald Kuhn
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.sylvani.oxford;

import java.io.InputStream;
import java.util.Locale;
import java.util.UUID;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;

import com.eclipsesource.jaxrs.provider.gson.GsonProvider;

/**
 * Cloud Client wrapper
 *
 * @author hkuhn
 */
public class OxfordClient {

    private Client client;
    public static final String deviceId = "2f019190-83c2-46a4-8b90-33795d96a773";

    public OxfordClient() {
        ClientConfig conf = new ClientConfig();
        client = ClientBuilder.newClient(conf);
        client.register(new GsonProvider<Object>());
    }

    public Client getServiceClient() {
        return client;
    }

    /**
     * * <?php
     * $AccessTokenUri = "https://oxford-speech.cloudapp.net:443/token/issueToken";
     *
     * // Note: Sign up at http://www.projectoxford.ai to get a subscription key.
     * // Search for Speech APIs from Azure Marketplace.
     * // Use the subscription key as Client secret below.
     * $clientId = "your client id";
     * $clientSecret = "your subscription key";
     * $sttHost = "https://speech.platform.bing.com";
     *
     * $data = array('grant_type' => 'client_credentials', 'client_id' => $clientId,
     * 'client_secret' => $clientSecret, 'scope' => $sttHost);
     * $data = http_build_query($data);
     *
     * // use key 'http' even if you send the request to https://...
     * $options = array(
     * 'http' => array(
     * 'header' => "Content-type: application/x-www-form-urlencoded\r\n" .
     * "content-length: ".strlen($data)."\r\n",
     * 'method' => 'POST',
     * 'content' => $data,
     * ),
     * );
     *
     * @return
     */
    public IdentitiyResult login(String appId, String appSecret) {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        WebTarget target = client.target(IOxfordSpeechAPI.COULD_URL_AUTH);

        // create a new client proxy for the BooksResource
        IOxfordSpeechAPI oxfordCloud = WebResourceFactory.newResource(IOxfordSpeechAPI.class, target);

        String host = "speech.platform.bing.com/recognize/query";

        IdentitiyResult response = oxfordCloud.login("client_credentials", appId, appSecret, host);

        return response;
    }

    public Recognition recognize(String token, Locale locale, InputStream stream) throws Exception {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        WebTarget target = client.target(IOxfordSpeechAPI.CLOUD_URL);
        String localeString = locale.getLanguage() + "-" + locale.getCountry();
        // create a new client proxy for the BooksResource
        IOxfordSpeechAPI oxfordCloud = WebResourceFactory.newResource(IOxfordSpeechAPI.class, target);

        String uuid = UUID.randomUUID().toString();
        String host = "speech.platform.bing.com/recognize/query";

        String authHeader = "Bearer " + token;

        Recognition response = oxfordCloud.recognize("ulm", deviceId, localeString, "windows 8", 3.0, "json", uuid,
                "D4D52672-91D7-4C74-8AD8-42B1D98141A5", host, authHeader, stream);

        return response;
    }

    public byte[] synthesize(String token, Locale locale, String text, String format) {
        String localeString = locale.getLanguage() + "-" + locale.getCountry();
        text = "<speak version='1.0' xml:lang='" + localeString + "'><voice xml:lang='" + localeString
                + "' xml:gender='Female' name='Microsoft Server Speech Text to Speech Voice (" + localeString
                + ", ZiraRUS)'>" + text + "</voice></speak>";
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        WebTarget target = client.target(IOxfordSpeechAPI.CLOUD_URL);

        // create a new client proxy for the BooksResource
        IOxfordSpeechAPI oxfordCloud = WebResourceFactory.newResource(IOxfordSpeechAPI.class, target);

        String host = "speech.platform.bing.com/synthesize";

        String appId = "D4D5267291D74C748AD842B1D98141A5";
        String clientId = "1596c1ab00c840be8406cf815d0441ce";

        String authHeader = "Bearer " + token;

        return oxfordCloud.synthesize(format, host, appId, clientId, UUID.randomUUID(), "Sylvani", authHeader, text);
    }
}
