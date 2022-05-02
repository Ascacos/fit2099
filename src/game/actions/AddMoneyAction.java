package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.bank.WalletManager;
import game.items.Coin;

public class AddMoneyAction extends Action {

    private final Coin coin;

    public AddMoneyAction(Coin coin) {
        this.coin = coin;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        // get the bank containing all actor's wallets
        WalletManager bank = WalletManager.getInstance();

        // check if the actor has a wallet
        // or, more implicitly, check if the WalletManager has this actor in it's map of actors and their associated
        // wallets.
        if (bank.getWallets().containsKey(actor)) {
           bank.getWallets().get(actor).addMoney(coin.getValue());
           map.locationOf(actor).removeItem(coin);
        }

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " gets $" + coin.getValue();
    }
}
