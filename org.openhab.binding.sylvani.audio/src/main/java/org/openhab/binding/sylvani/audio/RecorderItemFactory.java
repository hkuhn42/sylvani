package org.openhab.binding.sylvani.audio;

import org.eclipse.smarthome.core.items.GenericItem;
import org.eclipse.smarthome.core.items.ItemFactory;

/**
 *
 * {@link ItemFactory}-Implementation for recorder item type
 *
 * @author HKuhn
 */
public class RecorderItemFactory implements org.eclipse.smarthome.core.items.ItemFactory {

    public static final String RECORDER = "Recorder";

    /**
     * @{inheritDoc}
     */
    @Override
    public GenericItem createItem(String itemTypeName, String itemName) {
        if (itemTypeName.equals(RECORDER)) {
            return null;
        } else {
            return null;
        }
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public String[] getSupportedItemTypes() {
        return new String[] { RECORDER };
    }

}
