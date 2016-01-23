/**
 *
 */
package org.sylvani.audio;

import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;

/**
 * @author hkuhn
 *
 */
public class TargetLineInfo {

    private String id;
    private String name;

    private Line.Info lineInfo;
    private Mixer.Info mixerInfo;

    public TargetLineInfo(Mixer.Info mixerInfo, Line.Info lineInfo) {
        this.mixerInfo = mixerInfo;
        this.lineInfo = lineInfo;
        name = mixerInfo.getName();
        id = JavaAudioUtil.getIdForMixer(mixerInfo, lineInfo);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Line.Info getLineInfo() {
        return lineInfo;
    }

    public Mixer.Info getMixerInfo() {
        return mixerInfo;
    }

}
