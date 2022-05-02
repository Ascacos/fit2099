package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
import game.actions.ConsumeItemAction;

/**
 * A class representing a Super Mushroom consumable.
 * Super Mushrooms will give an actor perfect jumps when consumed.
 */
public class SuperMushroom extends ConsumableItem {

    /***
     * Constructor.
     */
    public SuperMushroom(boolean portable) {
        super("Super Mushroom", '^', true);
        this.addAction(new ConsumeItemAction(this));
    }

    /**
     * Consumes the Super Mushroom
     * Increases the actor's HP by 50, and gives the SUPER_MUSHROOM status buff.
     * @param actor The actor to consume the mushroom
     */
    @Override
    public void consume(Actor actor) {

        // if the actor is consuming this item from their inventory
        if (actor.getInventory().contains(this)) {
            actor.removeItemFromInventory(this);
        }

        actor.increaseMaxHp(50);
        actor.addCapability(Status.SUPER_MUSHROOM);
    }
}
