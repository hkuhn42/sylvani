package org.sylvani.io.voice.http;

import java.io.IOException;
import java.util.Locale;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sylvani.audio.AudioFormat;
import org.sylvani.audio.AudioSource;
import org.sylvani.io.VoiceControlContext;
import org.sylvani.io.audio.impl.AudioClip;

/**
 *
 *
 */
@Path("/invokeVoiceCommand")
public class AudioInResource {

    private Logger logger = LoggerFactory.getLogger(AudioInResource.class);

    private static VoiceControlContext context;

    public static void init(VoiceControlContext context) {
        AudioInResource.context = context;
    }

    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces("text/plain")
    public byte[] invokeVoiceCommand(byte[] data, @QueryParam("format") String format,
            @QueryParam("locale") String localeString) {
        Locale locale = new Locale("en", "US");

        String textResponse = "";

        try {
            logger.debug("input data length: " + data.length);

            AudioClip fragment = new AudioClip();
            fragment.setData(data);

            String command = context.getRecognitionService().recognize(fragment, locale);

            logger.debug("command " + command);
            textResponse = context.getCommandInterpreter().handleCommand(command);
            logger.debug("textResponse " + textResponse);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            if (textResponse == null || "error".equalsIgnoreCase(textResponse)) {
                AudioSource response = context.getSyntheziserService().synthesize("error", locale, "default",
                        new AudioFormat());
                return IOUtils.toByteArray(response.getInputStream(null));
            } else if ("ok".equalsIgnoreCase(textResponse)) {
                byte[] response = IOUtils.toByteArray(this.getClass().getResourceAsStream("ok.wav"));
                return response;
            } else {
                AudioSource response1 = context.getSyntheziserService().synthesize(textResponse, locale, "default",
                        new AudioFormat());
                return IOUtils.toByteArray(response1.getInputStream(null));
            }

        } catch (

        Exception e)

        {
            e.printStackTrace();
        }

        return new byte[0];
    }

}