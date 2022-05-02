package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ConsumableItem;

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


    @Override
    public String execute(Actor actor, GameMap map) {

        item.consume(actor);
        map.locationOf(actor).removeItem(item);

        return actor + " consumes the " + item;
    }


    @Override
    public String menuDescription(Actor actor) {
        return "Consume the " + item;
    }
}
