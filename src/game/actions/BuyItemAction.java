package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class BuyItemAction extends Action {

    /**
     * The cost of the item to be purchased
     */
    private final int cost;

    /**
     * The item to be purchased
     */
    private final Item item;

    public BuyItemAction(Item item, int cost) {
        this.cost = cost;
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(item);

        return actor + " bought " + item + " for " + cost;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + item;
    }
}
