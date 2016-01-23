/**
 *
 */
package org.sylvani.audio;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;

/**
 * Object to store mixers with source and data lines for input and output of audio
 * For reasons not event known to the sun / oracle people sourceLines are for output while targetLines are for input :)
 *
 * @author hkuhn
 *
 */
public class AudioSystemDetails {

    private Map<String, TargetLineInfo> inputs;
    private Map<String, SourceLineInfo> outputs;

    public AudioSystemDetails() {
        inputs = new HashMap<String, TargetLineInfo>(); // with targetLine :)
        outputs = new HashMap<String, SourceLineInfo>(); // with sourceLine :)
    }

    public void addInput(Mixer.Info info, Line.Info lineInfo) {
        String id = JavaAudioUtil.getIdForMixer(info, lineInfo);
        inputs.put(id, new TargetLineInfo(info, lineInfo));
    }

    public void addOutput(Mixer.Info info, Line.Info lineInfo) {
        String id = JavaAudioUtil.getIdForMixer(info, lineInfo);
        outputs.put(id, new SourceLineInfo(info, lineInfo));
    }

    public Collection<TargetLineInfo> getInputs() {
        return inputs.values();
    }

    public Collection<SourceLineInfo> getOutputs() {
        return outputs.values();
    }

    public TargetLineInfo getInputMixer(String id) {
        return inputs.get(id);
    }

    public SourceLineInfo getOutputMixer(String id) {
        return outputs.get(id);
    }

}
