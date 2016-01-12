/**
 *
 */
package org.sylvani.oxford;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

import org.glassfish.jersey.filter.LoggingFilter;
import org.sylvani.audio.AudioSource;
import org.sylvani.io.voice.IRecognitionService;

/**
 * @author hkuhn
 *
 */
public class OxfordRecognitionService implements IRecognitionService {

    private java.util.Properties props;

    public OxfordRecognitionService() {
        props = new java.util.Properties();
        try {
            props.load(OxfordRecognitionService.class.getResourceAsStream("/org/sylvani/oxford/oxford.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.sylvani.openhab.io.voice.IRecognitionService#recognize(org.sylvani.openhab.io.voice.AudioFragment,
     * java.util.Locale)
     */
    @Override
    public String recognize(AudioSource audio, Locale locale) throws Exception {

        OxfordClient client = new OxfordClient();
        client.getServiceClient().register(new LoggingFilter(Logger.getLogger("request"), true));

        String primaryKey = props.getProperty("primaryKey");
        String secondaryKey = props.getProperty("secondaryKey");

        IdentitiyResult result = client.login(primaryKey, secondaryKey);

        Recognition r = client.recognize(result.getAccess_token(), locale, audio.getInputStream());
        if (!"error".equalsIgnoreCase(r.getHeader().getStatus())) {
            String command = r.getResults().get(0).getName();
            return command;
        }
        return "error";
    }

}
