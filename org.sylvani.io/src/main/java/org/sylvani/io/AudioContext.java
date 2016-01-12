package org.sylvani.io;

import java.util.List;

import org.eclipse.smarthome.core.common.registry.Registry;
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

    public List<AudioOutput> getOutputs();

    public void registerSource(AudioSource source);

    public void registerOutput(AudioOutput output);

    public void relay(AudioSource source, AudioOutput output) throws AudioException;

    public Registry<AudioSource, String> getSourceRegistry();

    public Registry<AudioOutput, String> geOutputRegistry();
}
