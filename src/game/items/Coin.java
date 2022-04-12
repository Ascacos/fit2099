package game.items;

import edu.monash.fit2099.engine.items.Item;

public class Coin extends Item {

    private final int value;

    /***
     * Constructor.
     */
    public Coin(int value) {
        super("Coin", '$', false);
        this.value = value;
    }
}
