package org.sylvani.audio;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaAudioUtil {

    private Logger logger = LoggerFactory.getLogger(JavaAudioUtil.class);

    public static void main(String[] args) {
        System.out.println("test".hashCode());
    }

    public AudioSystemDetails gatherMixersWithLines() {

        AudioSystemDetails details = new AudioSystemDetails();

        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for (Mixer.Info mixerInfo : mixers) {

            Mixer m = AudioSystem.getMixer(mixerInfo);

            Line.Info[] lines = m.getSourceLineInfo();
            for (Line.Info li : lines) {
                if (li.getLineClass() == SourceDataLine.class) {

                    try {
                        m.open();
                        // System.out.println(" -: " + li + "|" + m.getMixerInfo().getName() + ";"
                        // + m.getMixerInfo().getVendor() + ";" + m.getMixerInfo().getVersion());
                        logger.debug("<" + m.getMixerInfo().getName());
                        details.addOutput(mixerInfo);
                        m.close();

                    } catch (LineUnavailableException e) {
                        System.out.println("Line unavailable.");
                    }
                }
            }

            lines = m.getTargetLineInfo();

            for (Line.Info li : lines) {
                if (li.getLineClass() == TargetDataLine.class) {

                    try {
                        m.open();
                        // System.out.println(" -: " + li + "|" + m.getMixerInfo().getName() + ";"
                        // + m.getMixerInfo().getVendor() + ";" + m.getMixerInfo().getVersion());
                        logger.debug(">" + m.getMixerInfo().getName());
                        details.addInput(mixerInfo);
                        m.close();

                    } catch (LineUnavailableException e) {
                        System.out.println("Line unavailable.");
                    }
                }

            }
        }
        return details;
    }

    public static String getIdForMixer(Mixer.Info info) {
        return "M" + info.getName().hashCode();
    }

    public static AudioFormat convert(javax.sound.sampled.AudioFormat soundFormat) {
        AudioFormat audioFormat = new AudioFormat();

        return audioFormat;
    }

    public static javax.sound.sampled.AudioFormat convert(AudioFormat audioFormat) {
        // audioFormat.getCodec()
        javax.sound.sampled.AudioFormat soundFormat = null;// new javax.sound.sampled.AudioFormat();

        return soundFormat;
    }

    public static javax.sound.sampled.AudioFormat[] convert(AudioFormat[] audioFormats) {
        javax.sound.sampled.AudioFormat[] soundFormats = new javax.sound.sampled.AudioFormat[audioFormats.length];
        for (int i = 0; i < soundFormats.length; i++) {
            soundFormats[i] = convert(audioFormats[i]);
        }
        return soundFormats;
    }

    public static AudioFormat[] convert(javax.sound.sampled.AudioFormat[] soundFormat) {
        AudioFormat[] audioFormats = new AudioFormat[soundFormat.length];
        for (int i = 0; i < audioFormats.length; i++) {
            audioFormats[i] = convert(soundFormat[i]);
        }
        return audioFormats;
    }
}
