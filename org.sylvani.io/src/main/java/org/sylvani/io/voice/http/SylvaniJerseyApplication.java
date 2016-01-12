package org.sylvani.io.voice.http;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

/**
 *
 * @author hkuhn
 *
 */
public class SylvaniJerseyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> result = new HashSet<Class<?>>();
        result.add(TextInResource.class);
        result.add(AudioInResource.class);
        return result;
    }

}