package game.grounds.trees;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.actors.enemies.Koopa;
import game.grounds.Dirt;

import java.util.Random;

/**
 * A class representing a mature tree
 *
 * Mature tree's have a 15% chance of spawning a Koopa at their location, a 20% chance of reducing to dirt, and will
 * grow new Sprouts in a random adjacent fertile ground every 5 turns.
 */
public class Mature extends Tree {

    private final Random rand = new Random();
    /**
     * The age of this tree
     */
    private int age;
    /**
     * The amount of turns until this tree grows a new sprout.
     */
    private int growthCycle;

    /**
     *
     * Construct a Mature tree
     */
    public Mature() {
        super('T');
        age = 0;
        registerInstance();
    }

    /**
     *Constructor.
     * @param age The age of the tree
     */
    public Mature(int age) {
        super('T');
        this.age = age;
        registerInstance();
    }

    /**
     * Each tick, a mature tree will age by 1, and it's growth cycle will increment by 1.
     *
     * Then, it will roll to spawn a new Koopa, reduce to dirt, or grow a new tree (if it's growth cycle is ready)
     * @param location The location of the tree.
     */
    @Override
    public void tick(Location location) {
        //Call Parent tick (Check for Reset)
        super.tick(location);

        age++;
        growthCycle++;

        // 15% chance to summon a Koopa
        if (rand.nextInt(101) < 15) {
            spawnActor(location, new Koopa());
        }

        if (rand.nextInt(101) < 20) {
                if (!location.containsAnActor()) location.setGround(new Dirt());
        }

        if (growthCycle >= 5) {

            for (int i = 0; i < location.getExits().size(); i++) {
                Location destination = location.getExits().get(i).getDestination();

                if (!destination.containsAnActor() && destination.getGround().hasCapability(Status.FERTILE)) {
                    destination.setGround(new Sprout());
                    growthCycle = 0;
                    break;
                }
            }
        }
    }

    /**
     * This method will get all allowed actions when nearby this tree.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return An empty ActionList if the actor is on top of the tree, or a JumpAction if it is adjacent to it.
     * @see JumpAction
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (location.containsAnActor() && location.getActor().equals(actor)) { return new ActionList(); }
        return new ActionList(new JumpAction(location, direction, 70, 30));
    }
}
