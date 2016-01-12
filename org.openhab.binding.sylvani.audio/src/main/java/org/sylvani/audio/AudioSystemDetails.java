/**
 *
 */
package org.sylvani.audio;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.Mixer;

/**
 * Object to store mixers with source and data lines for input and output of audio
 * For reasons not event known to the sun / oracle people sourceLines are for output while targetLines are for input :)
 *
 * @author hkuhn
 *
 */
public class AudioSystemDetails {

    private Map<String, Mixer.Info> inputs;
    private Map<String, Mixer.Info> outputs;

    public AudioSystemDetails() {
        inputs = new HashMap<String, Mixer.Info>(); // with targetLine :)
        outputs = new HashMap<String, Mixer.Info>(); // with sourceLine :)
    }

    public void addInput(Mixer.Info info) {
        String id = JavaAudioUtil.getIdForMixer(info);
        inputs.put(id, info);
    }

    public void addOutput(Mixer.Info info) {
        String id = JavaAudioUtil.getIdForMixer(info);
        outputs.put(id, info);
    }

    public Collection<Mixer.Info> getInputs() {
        return inputs.values();
    }

    public Collection<Mixer.Info> getOutputs() {
        return outputs.values();
    }

    public Mixer.Info getInputMixer(String mixerId) {
        return inputs.get(mixerId);
    }

    public Mixer.Info getOutputMixer(String mixerId) {
        return outputs.get(mixerId);
    }
}
