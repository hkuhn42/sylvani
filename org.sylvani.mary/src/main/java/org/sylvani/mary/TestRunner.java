/**
 *
 */
package org.sylvani.mary;

import java.io.FileOutputStream;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.sylvani.audio.AudioFormat;
import org.sylvani.audio.AudioSource;

/**
 * @author hkuhn
 */
public class TestRunner {

    public static void main(String[] args) throws Exception {
        MarySyntheziserService mss = new MarySyntheziserService();
        // AudioFragment fragment = mss.synthesize("Ok", new Locale("en", "us"), "wav");
        AudioSource fragment = mss.synthesize("Hallo", new Locale("de"), "default", new AudioFormat());
        IOUtils.copy(fragment.getInputStream(), new FileOutputStream("c:/temp/mary.wav"));
    }

}
