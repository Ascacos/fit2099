package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public class PowerStar extends Consumable {

    /**
     * The lifetime of the Power Star.
     */
    private int lifetime;

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public PowerStar(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        lifetime = 10;
    }

    @Override
    public void tick(Location currentLocation) {
        System.out.println(lifetime);
        this.lifetime -= 1;
    }

    @Override
    public void consume(Actor actor) {

    }
}
