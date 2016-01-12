package org.sylvani.sphinx;

import java.io.File;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.sylvani.io.audio.impl.AudioClip;

public class TranscriberDemo {

    public static void main(String[] args) throws Exception {

        SphinxRecognitionService re = new SphinxRecognitionService();
        AudioClip data = new AudioClip();
        byte[] audiodata = FileUtils.readFileToByteArray(new File("c:/temp/10001-90210-01803.wav"));
        data.setData(audiodata);
        re.recognize(data, new Locale("en", "US"));
    }
}