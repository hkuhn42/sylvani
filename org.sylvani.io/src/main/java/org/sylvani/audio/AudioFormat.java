/**
 *
 */
package org.sylvani.audio;

/**
 * @author hkuhn
 *
 *         TODO:
 *         i probably need to add endian flag
 */
public class AudioFormat {

    private AudioCodec codec;
    private AudioContainer container;
    /**
     * bit depth (https://en.wikipedia.org/wiki/Audio_bit_depth)
     * or
     * bit rate (https://en.wikipedia.org/wiki/Bit_rate)
     * depending on codec
     */
    private int bits;
    /**
     * sample frequence
     */
    private long frequency;

    public AudioCodec getCodec() {
        return codec;
    }

    public void setCodec(AudioCodec codec) {
        this.codec = codec;
    }

    public AudioContainer getContainer() {
        return container;
    }

    public void setContainer(AudioContainer container) {
        this.container = container;
    }

    public int getBits() {
        return bits;
    }

    public void setBits(int bits) {
        this.bits = bits;
    }

    public long getFrequency() {
        return frequency;
    }

    public void setFrequency(long rate) {
        this.frequency = rate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AudioFormat) {
            AudioFormat format = (AudioFormat) obj;
            if (format.getCodec() != getCodec()) {
                return false;
            }
            if (format.getContainer() != getContainer()) {
                return false;
            }
            if (format.getBits() != getBits()) {
                return false;
            }
            if (format.getFrequency() != getFrequency()) {
                return false;
            }
            return true;
        }
        return super.equals(obj);
    }
}
