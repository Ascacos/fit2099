package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AddMoneyAction;
import game.reset.Resettable;

/**
 * A class representing a Coin item.
 */
public class Coin extends Item implements Resettable {

    /**
     * The value of this coin.
     */
    private final int value;

    /***
     * Constructor.
     * @param value The value of the coin.
     */
    public Coin(int value) {
        super("Coin", '$', false);
        this.value = value;
        this.addAction(new AddMoneyAction(this));
        registerInstance();
    }

    /**
     * Allows the Coin to experience the passage of time.
     *
     * @param location The location of the Coin.
     */
    @Override
    public void tick(Location location) {
        if (this.hasCapability(Status.RESETTING)) {
            //Remove all coins on the ground
            location.removeItem(this);
        }
    }

    /**
     * A method to get the value of this coin.
     * @return The value of this coin
     */
    public int getValue() {
        return this.value;
    }

    /**
     * A method to prepare a Coin to reset.
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESETTING);
    }
}
