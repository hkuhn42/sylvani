/**
 *
 */
package org.sylvani.io.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingRegistry;
import org.eclipse.smarthome.core.thing.type.ThingType;
import org.eclipse.smarthome.core.thing.type.ThingTypeRegistry;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.model.sitemap.SitemapProvider;
import org.eclipse.smarthome.ui.items.ItemUIRegistry;

/**
 * @author hkuhn
 *         HAL9000 -> 2001
 *         Vincent -> DAs Schwarze Loch
 *         Kitt -> Night Rider
 *
 *         TODO: take a look at RuleEngineImpl
 *
 *
 */
public class HAL9000 implements ICommandInterpreter {

    private Logger logger = Logger.getLogger(HAL9000.class.getName());

    public HAL9000() {
        System.err.println("hal is in orbit of jupiter");
    }

    protected ThingRegistry thingRegistry;
    protected ThingTypeRegistry thingTypeRegistry;

    private ItemUIRegistry itemUIRegistry;
    private List<SitemapProvider> sitemapProviders = new ArrayList<>();

    @Override
    public String handleCommand(String commandSentence) {
        if (commandSentence == null) {
            return "no command found";
        }

        Command command = OnOffType.OFF;

        // String[] commandSentenceArray = commandSentence.split("\\s+");
        if (commandSentence.endsWith("on")) {
            command = OnOffType.ON;
        } else if (commandSentence.endsWith("on")) {
            command = OnOffType.OFF;
        }

        if (commandSentence.contains("light")) {
            StringBuffer buffer = new StringBuffer();
            if (thingRegistry == null) {
                return "no thing registry available: if you run this outside of openhab2, use another ICommandInterpreter ";
            }
            Collection<Thing> things = thingRegistry.getAll();
            for (Thing thing : things) {
                logger.info("thing " + thing);
                if (thing == null) {
                    continue;
                }
                ThingType thingType = thingTypeRegistry.getThingType(thing.getThingTypeUID());
                logger.info("thingType " + thingType);
                if (thingType == null) {
                    continue;
                }

                if ("hue:LCT001".equalsIgnoreCase(thingType.toString())) {
                    buffer.append(thing.getUID() + " " + thingType.getLabel() + "\n");

                    for (Channel def : thing.getChannels()) {
                        buffer.append("- " + def.getUID() + " " + def.getAcceptedItemType() + "\n");
                        if ("Dimmer".endsWith(def.getAcceptedItemType())) {
                            thing.getHandler().handleCommand(def.getUID(), command);
                            return "ok"; // todo: wait for event on bus
                        }
                    }
                }
            }

        }

        return "unknown";
    }

    protected void setThingRegistry(ThingRegistry thingRegistry) {
        System.out.println("new thingRegistry: " + thingRegistry);
        this.thingRegistry = thingRegistry;
    }

    protected void unsetThingRegistry(ThingRegistry thingRegistry) {
        this.thingRegistry = null;
    }

    protected void setThingTypeRegistry(ThingTypeRegistry thingTypeRegistry) {
        System.out.println("new thingTypeRegistry: " + thingTypeRegistry);
        this.thingTypeRegistry = thingTypeRegistry;
    }

    protected void unsetThingTypeRegistry(ThingTypeRegistry thingTypeRegistry) {
        this.thingTypeRegistry = null;
    }

    public void setItemUIRegistry(ItemUIRegistry itemUIRegistry) {
        this.itemUIRegistry = itemUIRegistry;
    }

    public void unsetItemUIRegistry(ItemUIRegistry itemUIRegistry) {
        this.itemUIRegistry = null;
    }

    public void addSitemapProvider(SitemapProvider provider) {
        sitemapProviders.add(provider);
    }

    public void removeSitemapProvider(SitemapProvider provider) {
        sitemapProviders.remove(provider);
    }

}
