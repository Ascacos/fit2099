package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeItemAction;

/**
 * A class representing a Power Star item.
 * Power Stars will make an actor temporarily invincible on consumption, but will only remain in the map for 10 turns.
 */
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

    /**
     * This method will check the lifetime remaining on a Power Star that is at some location (not in an Actor's
     * inventory).
     * If it's lifetime is finished, it will be removed from the location.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        System.out.println(lifetime);
        this.lifetime -= 1;

        if (lifetime == 0) {
            currentLocation.removeItem(this);
        }
    }

    /**
     * PowerStar will be removed from the players inventory and the player will lose the PowerStar buff at the same
     * time when the 10 turns is up. That is, when lifetime == 0.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
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

    /**
     * A method to consume the Power Star.
     * This method will check if the Actor has consumed the item from it's own inventory, and if so, toggle it's
     * portability and remove it's consume action (removing the ability to interact with it)
     * Then it will heal the player for the set heal amount, and apply the Power Star buff.
     * @param actor The actor consuming the Power Star.
     */
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
