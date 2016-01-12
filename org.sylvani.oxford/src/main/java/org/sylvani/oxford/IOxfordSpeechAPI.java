/**
 *
 */
package org.sylvani.oxford;

import java.io.InputStream;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * POST /query?
 * scenarios=catsearch&appid=f84e364c-ec34-4773-a783-73707bd9a585&locale=en-US&device.os=wp7&version=3.0&format=xml&
 * requestid=1d4b6030-9099-11e0-91e4-0800200c9a66&instanceid=1d4b6030-9099-11e0-91e4-0800200c9a66 HTTP/1.1
 * Host: speech.platform.bing.com/recognize/query
 * Content-Type: audio/wav; samplerate=8000
 *
 * (audio data)
 *
 *
 * @author hkuhn
 *
 */
public interface IOxfordSpeechAPI {

    public static final String CLOUD_URL = "https://speech.platform.bing.com";

    public static final String COULD_URL_AUTH = "https://oxford-speech.cloudapp.net:443/token";

    /**
     * <?php
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
    @POST
    @Path("/issueToken")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public IdentitiyResult login(@FormParam("grant_type") String grantType, @FormParam("client_id") String clientId,
            @FormParam("client_secret") String client_secret, @FormParam("scope") String scope);

    /**
     *
     * scenarios=catsearch&appid=f84e364c-ec34-4773-a783-73707bd9a585&locale=en-US&device.os=wp7&version=3.0&format=xml&
     * requestid=1d4b6030-9099-11e0-91e4-0800200c9a66&instanceid=1d4b6030-9099-11e0-91e4-0800200c9a66
     * https://speech.platform.bing.com/recognize
     *
     * @return
     */
    @Path("/recognize/query")
    @POST
    @Consumes("audio/wav;samplerate=16000")
    @Produces("application/json")
    public Recognition recognize(@QueryParam("scenarios") String scenarios, @QueryParam("appID") String appid,
            @QueryParam("locale") String locale, @QueryParam("device.os") String deviceOs,
            @QueryParam("version") double version, @QueryParam("format") String format,
            @QueryParam("requestid") String requestid, @QueryParam("instanceid") String instanceid,
            @HeaderParam("host") String host, @HeaderParam("Authorization") String authorization,
            @FormDataParam("") InputStream stream);

    /**
     * X-Search-AppId A GUID (hex only, no dashes) An ID that uniquely identifies the client application. This can be
     * Store ID for Apps. If one is not available, this may be user generated per-application.
     * A GUID (hex only, no dashes) An ID that uniquely identifies application instance per-installation.
     * User-Agent Application name Application name is required and less than 255 characters.
     * X-Search-PartnerEventID A GUID (hex only, no dashes) Azure Offer ID is a required parameter.
     *
     * @param format
     * @param host
     * @param authorization
     * @param data
     * @return
     */
    @POST
    @Path("/synthesize")
    @Consumes("text/plain; charset=utf-8")
    public byte[] synthesize(@HeaderParam("X-Microsoft-OutputFormat") String format, @HeaderParam("host") String host,
            @HeaderParam("X-Search-AppId") String appId, @HeaderParam("X-Search-ClientID") String clientId,
            @HeaderParam("X-Search-PartnerEventID") UUID eventId, @HeaderParam("User-Agent") String userAgent,
            @HeaderParam("Authorization") String authorization, String data);
}
