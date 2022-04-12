package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class AddMoneyAction extends Action {

    private int amount;

    public AddMoneyAction(int amount) {
        this.amount = amount;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // FIXME: this does nothing! how to add money to the player?
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " gets $" + amount;
    }
}
