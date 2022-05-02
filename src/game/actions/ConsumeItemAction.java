package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ConsumableItem;

/**
 * An action to allow for the consumption of items off the ground.
 * Any item that implements the {@link ConsumableItem ConsumableItem} interface will have this action
 * attached to it. In doing so, it can be consumed directly off the ground, without the need to add it to an actor's
 * inventory.
 */
public class ConsumeItemAction extends Action {

    /**
     * The item to be consumed
     */
    private final ConsumableItem item;

    /**
     * Constructor.
     *
     * @param item The item to be consumed.
     */
    public ConsumeItemAction(ConsumableItem item) {
        this.item = item;
    }

    /**
     * Executes this action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of what happened.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        item.consume(actor);
        map.locationOf(actor).removeItem(item);

        return actor + " consumes the " + item;
    }

    /**
     * A method to return a descriptive string
     * @see edu.monash.fit2099.engine.actions.Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return A string to display in the game menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Consume the " + item;
    }
}
