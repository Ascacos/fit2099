package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actions.AddMoneyAction;
import game.reset.Resettable;

public class Coin extends Item implements Resettable {

    private final int value;

    /***
     * Constructor.
     */
    public Coin(int value) {
        super("Coin", '$', false);
        this.value = value;
        this.addAction(new AddMoneyAction(value));
    }

    @Override
    public void resetInstance() {

    }
}
