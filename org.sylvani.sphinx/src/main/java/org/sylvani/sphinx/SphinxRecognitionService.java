/**
 *
 */
package org.sylvani.sphinx;

import java.io.InputStream;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sylvani.audio.AudioCodec;
import org.sylvani.audio.AudioContainer;
import org.sylvani.audio.AudioFormat;
import org.sylvani.audio.AudioSource;
import org.sylvani.io.voice.IRecognitionService;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

/**
 * @author hkuhn
 */
public class SphinxRecognitionService implements IRecognitionService {

    private Logger logger = LoggerFactory.getLogger(SphinxRecognitionService.class);

    @Override
    public String recognize(AudioSource audio, Locale locale) throws Exception {

        Configuration configuration = new Configuration();
        logger.debug("getSampleRate " + configuration.getSampleRate());
        if (locale.toString().equalsIgnoreCase("en_US")) {

            logger.debug("using " + locale);
            configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
            configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
            configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        } else {
            logger.error("unsupported " + locale);
        }
        StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);

        try (InputStream stream = audio.getInputStream();) {
            stream.skip(44);
            recognizer.startRecognition(stream);

            logger.debug("listing results");

            SpeechResult result;
            while ((result = recognizer.getResult()) != null) {
                logger.debug("Hypothesis: %s\n", result.getHypothesis());
            }
            recognizer.stopRecognition();

            stream.close();
        }
        return "ok";
    }
    
	public AudioFormat[] getSupportedFormats() {
		AudioFormat format = new AudioFormat();
		format.setCodec(AudioCodec.PCM_SIGNED);
		format.setBits(16);
		format.setFrequency(16000);
		format.setContainer(AudioContainer.WAVE);
		return new AudioFormat[] {format};
	}

}
