package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeItemAction;

public class PowerStar extends ConsumableItem {

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
    public PowerStar(boolean portable) {
        super("Power Star", '*', portable);
        this.lifetime = 10;
        this.healAmount = 200;
        consumeItemAction = new ConsumeItemAction(this);
        this.addAction(consumeItemAction);
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
            if (actor.hasCapability(Status.POWER_STAR)) {
                actor.removeCapability(Status.POWER_STAR);
            }
        }
    }

    @Override
    public void consume(Actor actor) {

        // if the actor is consuming this item from their inventory
        if (actor.getInventory().contains(this)) {
            this.lifetime = 10;
            this.togglePortability();
            this.removeAction(consumeItemAction);
        }


        actor.heal(healAmount);
        actor.addCapability(Status.POWER_STAR);
    }
}
