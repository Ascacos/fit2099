package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeItemAction;

public class PowerStar extends Consumable {

    /**
     * The amount to heal an Actor, when consumed.
     */
    private final int healAmount;
    /**
     * The lifetime of the Power Star.
     */
    private int lifetime;

    /***
     * Constructor.
     */
    public PowerStar() {
        super("Power Star", '*', true);
        this.lifetime = 10;
        this.healAmount = 200;

        this.addAction(new ConsumeItemAction(this));
    }

    @Override
    public void tick(Location currentLocation) {
        System.out.println(lifetime);
        this.lifetime -= 1;

        if (lifetime == 0) {
            currentLocation.removeItem(this);
        }
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        System.out.println(lifetime);
        this.lifetime -= 1;

        if (lifetime == 0) {
            actor.removeItemFromInventory(this);
        }
    }

    @Override
    public void consume(Actor actor) {

        actor.heal(healAmount);
        actor.addCapability(Status.POWER_STAR);
    }
}
