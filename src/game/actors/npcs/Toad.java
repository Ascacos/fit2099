package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyItemAction;
import game.items.PowerStar;
import game.items.SuperMushroom;

public class Toad extends Actor {

    /**
     * Constructor.
     */
    public Toad() {
        super("Toad", 'O', 100);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new BuyItemAction(new PowerStar(), 0));
        actions.add(new BuyItemAction(new SuperMushroom(), 0));

        return actions;
    }
}
