package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AddMoneyAction;
import game.grounds.Dirt;
import game.reset.Resettable;

import java.util.Random;

public class Coin extends Item implements Resettable {

    private final int value;

    /***
     * Constructor.
     */
    public Coin(int value) {
        super("Coin", '$', false);
        this.value = value;
        this.addAction(new AddMoneyAction(this));
        registerInstance();
    }

    @Override
    public void tick(Location location) {
        if (this.hasCapability(Status.RESETTING)) {
            //Remove all coins on the ground
            location.removeItem(this);
        }
    }

    public int getValue() {
        return this.value;
    }
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESETTING);
    }
}
