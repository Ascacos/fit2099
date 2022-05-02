package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.bank.Wallet;
import game.bank.WalletManager;

/**
 * An action to allow buying of Items.
 * Actor's require a {@link game.bank.Wallet Wallet} in order to make use of this action.
 */
public class BuyItemAction extends Action {

    /**
     * The cost of the item to be purchased
     */
    private final int cost;

    /**
     * The item to be purchased
     * @see Item
     */
    private final Item item;

    /**
     * Constructor
     *
     * @param item The item to be bought
     * @param cost The cost of the item.
     */
    public BuyItemAction(Item item, int cost) {
        this.cost = cost;
        this.item = item;
    }

    /**
     * Executes this action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of what happened.
     * @see edu.monash.fit2099.engine.actions.Action
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        WalletManager wallets = WalletManager.getInstance();
        if (wallets.getWallets().containsKey(actor)) {

            Wallet wallet = wallets.getWallets().get(actor);
            if (wallet.getBalance() >= cost) {
                wallet.takeMoney(cost);
                actor.addItemToInventory(item);
                return actor + " bought " + item;
            } else {
                return actor + " doesn't have enough coins!";
            }
        } else {
            return actor + " doesn't have a wallet!";
        }
    }

    /**
     * A method to return a descriptive string
     * @see edu.monash.fit2099.engine.actions.Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return A string to display in the game menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + item + "($" + cost + ")";
    }
}
