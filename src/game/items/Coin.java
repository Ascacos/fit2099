package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actions.AddMoneyAction;

public class Coin extends Item {

    private final int value;

    /***
     * Constructor.
     */
    public Coin(int value) {
        super("Coin", '$', false);
        this.value = value;
        this.addAction(new AddMoneyAction(value));
    }
}
