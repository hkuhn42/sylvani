package org.sylvani.io.voice.http;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sylvani.io.VoiceControlContext;

/**
 *
 */
@Path("/invokeTextCommand")
public class TextInResource {

    private static VoiceControlContext context;

    public static void init(VoiceControlContext context) {
        TextInResource.context = context;
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces("text/plain")
    public String invokeTextCommand(String command) {
        try {
            System.out.println("command " + command);

            System.out.println(command);
            context.getCommandInterpreter().handleCommand(command);

            return "ok";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "not ok";
    }
}