/**
 *
 */
package org.sylvani.io.audio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sylvani.audio.AudioException;
import org.sylvani.audio.AudioOutput;
import org.sylvani.audio.AudioSource;
import org.sylvani.io.audio.impl.AudioUtil;

/**
 * @author hkuhn
 *
 */
public class AudioContextImpl implements AudioContext {

    private Map<String, AudioSource> audioSources;
    private Map<String, AudioOutput> audioOutputs;

    public AudioContextImpl() {
        audioSources = new HashMap<String, AudioSource>();
        audioOutputs = new HashMap<String, AudioOutput>();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.sylvani.io.audio.AudioContext#getSources()
     */
    @Override
    public List<AudioSource> getSources() {
        return new ArrayList<AudioSource>(audioSources.values());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.sylvani.io.audio.AudioContext#getSource(java.lang.String)
     */
    @Override
    public AudioSource getSource(String key) {
        return audioSources.get(key);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.sylvani.io.audio.AudioContext#getOutputs()
     */
    @Override
    public List<AudioOutput> getOutputs() {
        return new ArrayList<AudioOutput>(audioOutputs.values());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.sylvani.io.audio.AudioContext#getOutput(java.lang.String)
     */
    @Override
    public AudioOutput getOutput(String key) {
        return audioOutputs.get(key);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.sylvani.io.audio.AudioContext#registerSource(java.lang.String, org.sylvani.audio.AudioSource)
     */
    @Override
    public void registerSource(String key, AudioSource source) {
        audioSources.put(key, source);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.sylvani.io.audio.AudioContext#registerOutput(java.lang.String, org.sylvani.audio.AudioOutput)
     */
    @Override
    public void registerOutput(String key, AudioOutput output) {
        audioOutputs.put(key, output);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.sylvani.io.audio.AudioContext#relay(org.sylvani.audio.AudioSource, org.sylvani.audio.AudioOutput)
     */
    @Override
    public void relay(AudioSource source, AudioOutput output) throws AudioException {
        AudioUtil.stream(source, output);

    }

}
