package game.grounds.trees;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actors.enemies.Goomba;

import java.util.Random;

/**
 * A class representing a sprout (tree)
 * Sprout's have a 10% chance to spawn a Goomba each turn, and will grow into a sapling when it's age hits 10.
 */
public class Sprout extends Tree {

    private final Random rand = new Random();

    public Sprout() {
        super('+');
        age = 0;
        registerInstance();
    }

    /**
     * Each tick, Sprout's age is incremented.
     * Then, it will roll to spawn a Goomba (10% chance)
     * @param location The location of the tree.
     */
    @Override
    public void tick(Location location) {
        //Call Parent tick (Check for Reset)
        super.tick(location);

        age++;

        // 10% chance to summon a Goomba
        if (rand.nextInt(101) <= 10) {
            if (!location.containsAnActor()) location.addActor(new Goomba());
        }

        //if age = 10, become Sapling
        if (age == 10) {
            location.setGround(new Sapling(age));
        }
    }

    /**
     * A method to get the allowable actions when adjacent to a Sprout.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return An empty ActionList if the actor is on the tree, or a JumpAction.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (location.containsAnActor() && location.getActor().equals(actor)) { return new ActionList(); }
        return new ActionList(new JumpAction(location, direction, 90, 10));
    }
}