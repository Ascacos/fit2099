package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeItemAction;

/**
 * An abstract class representing an item that can be consumed.
 */
public abstract class ConsumableItem extends Item {

    /**
     * The action this item will execute
     */
    protected ConsumeItemAction consumeItemAction;

    /**
     * Constructor
     *
     * @param name The name of the consumable
     * @param displayChar The character to display on the map
     * @param portable True if the item can be picked up
     */
    public ConsumableItem(String name, char displayChar, boolean portable){
        super(name, displayChar, portable);
    }

    /**
     * Consume the item.
     */
    public abstract void consume(Actor actor);
}
