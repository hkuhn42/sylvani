/**
 *
 */
package org.sylvani.oxford;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

import org.glassfish.jersey.filter.LoggingFilter;
import org.sylvani.audio.AudioFormat;
import org.sylvani.audio.AudioSource;
import org.sylvani.io.audio.impl.AudioClip;
import org.sylvani.io.voice.ISyntheziserService;

/**
 * @author hkuhn
 *
 */
public class OxfordSyntheziserService implements ISyntheziserService {

    private java.util.Properties props;

    public OxfordSyntheziserService() {
        props = new java.util.Properties();
        try {
            props.load(OxfordRecognitionService.class.getResourceAsStream("oxford.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AudioSource synthesize(String text, Locale locale, String voice, AudioFormat requestedFormat)
            throws Exception {
        OxfordClient client = new OxfordClient();
        client.getServiceClient().register(new LoggingFilter(Logger.getLogger("request"), true));

        String primaryKey = props.getProperty("primaryKey");
        String secondaryKey = props.getProperty("secondaryKey");

        IdentitiyResult result = client.login(primaryKey, secondaryKey);
        String format = "riff-16khz-16bit-mono-pcm";// "raw-16khz-16bit-mono-pcm"; // "raw-8khz-8bit-mono-mulaw";
        byte[] data = client.synthesize(result.getAccess_token(), locale, text, format);
        AudioClip fragment = new AudioClip();
        fragment.setData(data);
        return fragment;
    }

}
