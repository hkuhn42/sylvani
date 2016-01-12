package org.openhab.binding.sylvani.audio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.smarthome.core.items.GenericItem;
import org.eclipse.smarthome.core.library.types.NextPreviousType;
import org.eclipse.smarthome.core.library.types.PlayPauseType;
import org.eclipse.smarthome.core.library.types.RewindFastforwardType;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.State;
import org.eclipse.smarthome.core.types.UnDefType;

/**
 * An {@link RecorderItem} allows to control a player, e.g. an audio player.
 *
 * @author Alex Tugarev
 */
public class RecorderItem extends GenericItem {

    private static List<Class<? extends State>> acceptedDataTypes = new ArrayList<Class<? extends State>>();
    private static List<Class<? extends Command>> acceptedCommandTypes = new ArrayList<Class<? extends Command>>();

    static {
        acceptedDataTypes.add(PlayPauseType.class);
        acceptedDataTypes.add(UnDefType.class);

        acceptedCommandTypes.add(PlayPauseType.class);
    }

    public RecorderItem(String name) {
        super(RecorderItemFactory.RECORDER, name);
    }

    /* package */ RecorderItem(String type, String name) {
        super(type, name);
    }

    @Override
    public List<Class<? extends State>> getAcceptedDataTypes() {
        return Collections.unmodifiableList(acceptedDataTypes);
    }

    @Override
    public List<Class<? extends Command>> getAcceptedCommandTypes() {
        return Collections.unmodifiableList(acceptedCommandTypes);
    }

    public void send(PlayPauseType command) {
        internalSend(command);
    }

    public void send(RewindFastforwardType command) {
        internalSend(command);
    }

    public void send(NextPreviousType command) {
        internalSend(command);
    }

}
