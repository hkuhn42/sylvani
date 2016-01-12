/**
 *
 */
package org.sylvani.oxford;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

import org.glassfish.jersey.filter.LoggingFilter;

/**
 * @author hkuhn
 *         {"access_token":
 *         "xxxxx.xxxxx.xxxxx"
 *         ,"token_type":"jwt","expires_in":"600","scope":"speech.platform.bing.com/recognize/query"}
 */
public class TestClientRunner {

    private java.util.Properties props;

    public TestClientRunner() {
        props = new java.util.Properties();
        try {
            props.load(OxfordRecognitionService.class.getResourceAsStream("oxford.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        TestClientRunner runner = new TestClientRunner();
        runner.justCall();
    }

    private void justCall() throws Exception {
        String filename = "C:/Privat/openhab2-master/git/openhab2/addons/io/org.openhab.io.alexa/samples/sampleP16.wav";
        filename = "c:/temp/turnoff.wav";
        try (FileInputStream fStream = new FileInputStream(new File(filename));) {
            OxfordClient client = new OxfordClient();
            client.getServiceClient().register(new LoggingFilter(Logger.getLogger("request"), true));

            String primaryKey = props.getProperty("primaryKey");
            String secondaryKey = props.getProperty("secondaryKey");

            IdentitiyResult result = client.login(primaryKey, secondaryKey);
            System.out.println(result.getAccess_token());

            // if (false) {
            Recognition r = client.recognize(result.getAccess_token(), new Locale("en", "US"), fStream);
            System.out.println(r.getResults().get(0).getName());
            // }

            // byte[] data = client.synthesize(result.getAccess_token(), new Locale("en", "us"), "Turn the light off");
            // FileUtils.writeByteArrayToFile(new File("c:/temp/turnoff.wav"), data);
        }

    }

}
