package org.sylvani.io.audio;

import java.util.List;

import org.sylvani.audio.AudioException;
import org.sylvani.audio.AudioOutput;
import org.sylvani.audio.AudioSource;

/**
 *
 * @author hkuhn
 *
 */
public interface AudioContext {

    public List<AudioSource> getSources();

    public AudioSource getSource(String key);

    public List<AudioOutput> getOutputs();

    public AudioOutput getOutput(String key);

    public void registerSource(String key, AudioSource source);

    public void registerOutput(String key, AudioOutput output);

    public void relay(AudioSource source, AudioOutput output) throws AudioException;
}
