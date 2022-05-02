package game.grounds.trees;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.items.Coin;

import java.util.Random;

/**
 * A class representing a sapling (tree)
 * Saplings have a 10% chance of spawning a coin ($20) each tick, and will grow into a Mature tree when it's age
 * hits 20.
 */
public class Sapling extends Tree {

    private final Random rand = new Random();


    public Sapling() {
        super('t');
        age = 0;
        registerInstance();
    }

    public Sapling(int age) {
        super('t');
        this.age = age;
        registerInstance();
    }

    /**
     * Each tick, the sapling's age is incremented by 1.
     * Then, it rolls to spawn a coin (10% chance)
     * @param location The location of the tree.
     */
    @Override
    public void tick(Location location) {
        //Call Parent tick (Check for Reset)
        super.tick(location);

        age++;

        // 10% chance to summon a Coin
        if (rand.nextInt(101) < 10) {
            location.addItem(new Coin(20));
        }

        // at age 20, become a mature tree
        if (age == 20) {
            if (!location.containsAnActor()) location.setGround(new Mature(age));
        }
    }

    /**
     * A method to get the allowable actions when adjacent to a Sapling.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return An empty ActionList if the actor is on the tree, or a JumpAction.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (location.containsAnActor() && location.getActor().equals(actor)) { return new ActionList(); }
        return new ActionList(new JumpAction(location, direction, 80, 20));
    }
}
