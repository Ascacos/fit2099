package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.items.Coin;

public class AddMoneyAction extends Action {

    private final Coin coin;

    public AddMoneyAction(Coin coin) {
        this.coin = coin;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        //FIXME: we cannot use instanceof in our final submission (WILL LOSE MARKS)
        if (actor instanceof Player) {

            ((Player) actor).addMoney(coin.getValue());

            map.locationOf(actor).removeItem(coin);
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " gets $" + coin.getValue();
    }
}
