package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.bank.WalletManager;
import game.items.Coin;

/**
 * An Action class for adding money to an Actor, through a coin.
 * @see edu.monash.fit2099.engine.actions.Action
 */
public class AddMoneyAction extends Action {

    /**
     * The coin attached to this action.
     * @see game.items.Coin
     */
    private final Coin coin;

    /**
     * Constructor
     * @param coin The coin item attached to this Action
     */
    public AddMoneyAction(Coin coin) {
        this.coin = coin;
    }

    /**
     * Executes this AddMoneyAction.
     *
     * This method will check if the Actor executing the action exists in the {@link game.reset.ResetManager ResetManager}
     * and subsequently determine whether or not this Action will succeed. If the Actor does not have a
     * {@link game.bank.Wallet Wallet}, this method will do nothing on execution. If the Actor does have a Wallet,
     * it's balance will be incremented by the value of the {@link game.items.Coin Coin}.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of what happened on execution.
     */
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

    /**
     * A method to return a descriptive string
     * @see edu.monash.fit2099.engine.actions.Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return A string to display in the game menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " gets $" + coin.getValue();
    }
}
