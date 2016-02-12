/**
 * Copyright (c) 2015-2016 Harald Kuhn
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.sylvani.audio;

/**
 * General purpose audio exception
 *
 * @author Harald Kuhn (hkuhn42) initial api
 */
public class AudioException extends Exception {

    private static final long serialVersionUID = 1L;

    public AudioException() {
        super();
    }

    public AudioException(String message, Throwable cause) {
        super(message, cause);
    }

    public AudioException(String message) {
        super(message);
    }

    public AudioException(Throwable cause) {
        super(cause);
    }
}