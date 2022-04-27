package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;

public class AddMoneyAction extends Action {

    private final int amount;

    public AddMoneyAction(int amount) {
        this.amount = amount;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        //FIXME: we cannot use instanceof in our final submission (WILL LOSE MARKS)
        if (actor instanceof Player) {

            ((Player) actor).addMoney(this.amount);

            //TODO: How to delete coin after being picked up?
            // this line is removing *all* items, not just the coin that was picked up
            map.at(map.locationOf(actor).x(), map.locationOf(actor).y()).getItems().clear();
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " gets $" + amount;
    }
}
