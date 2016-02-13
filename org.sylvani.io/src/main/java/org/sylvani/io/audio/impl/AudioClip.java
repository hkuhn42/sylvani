/**
 * Copyright (c) 2015-2016 Harald Kuhn
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.sylvani.io.audio.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.sylvani.audio.AudioException;
import org.sylvani.audio.AudioFormat;
import org.sylvani.audio.AudioOutput;
import org.sylvani.audio.AudioSource;
import org.sylvani.audio.UnsupportedAudioFormatException;

/**
 * A cached piece of audio data
 *
 * This is a temp implementation for development
 *
 * In general this is a very bad way to handle binary data, the audio data should be stored somewhere if its size
 * exceeds a certain limit, something like TempFileItem of apache commons fileupload comes to mind
 *
 * @author Harald Kuhn (hkuhn42) initial api
 */
public class AudioClip implements Serializable, AudioSource, AudioOutput {

    private static final long serialVersionUID = 1L;

    private byte[] data;
    private AudioFormat format;
    private String name;

    public AudioClip() {
        this(null, null, null);
    }

    public AudioClip(byte[] data) {
        this(null, data, null);
    }

    public AudioClip(String name, byte[] data, AudioFormat format) {
        super();
        this.data = data;
        this.format = format;
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setFormat(AudioFormat format) {
        this.format = format;
    }

    @Override
    public OutputStream getOutputStream() {
        return new ByteArrayOutputStream() {
            @Override
            public void flush() throws IOException {
                super.flush();
                data = toByteArray();
            }
        };
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(data);
    }

    @Override
    public OutputStream getOutputStream(AudioFormat format) throws UnsupportedAudioFormatException {
        setFormat(format);
        return getOutputStream(format);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public AudioFormat[] getSupportedFormats() {
        // TODO Auto-generated method stub
        return new AudioFormat[] { format };
    }

    @Override
    public InputStream getInputStream(AudioFormat requestedFormat) throws UnsupportedAudioFormatException {
        if (format == null || format.equals(requestedFormat)) {
            return getInputStream();
        }
        throw new UnsupportedAudioFormatException(format + " is not supported", requestedFormat);
    }

    @Override
    public void stream(AudioSource source) throws AudioException {
        AudioUtil.stream(source, this);
    }

    @Override
    public void stream(AudioOutput output) throws AudioException {
        AudioUtil.stream(this, output);
    }

}
