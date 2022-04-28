package game.grounds.trees;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.actors.enemies.Koopa;
import game.grounds.Dirt;

import java.util.Random;

public class Mature extends Tree {

    private final Random rand = new Random();
    private int age;
    private int growthCycle;

    /**
     *
     * Construct a Mature tree
     */
    public Mature() {
        super('T');
        age = 0;
    }

    /**
     *
     * @param age The age of the tree
     */
    public Mature(int age) {
        super('T');
        this.age = age;
    }

    @Override
    public void tick(Location location) {
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
                //FIXME: cant use instanceof :(
                // have applied a capability to Dirt (Status.FERTILE) - not sure if it's appropriate use of capabilities.
                if (location.getExits().get(i).getDestination().getGround() instanceof Dirt) {
                    Location destination = location.getExits().get(i).getDestination();
                    if (!destination.containsAnActor() && destination.getGround().hasCapability(Status.FERTILE)) {
                        destination.setGround(new Sprout());
                        growthCycle = 0;
                    }
                }
            }

            int random = rand.nextInt(location.getExits().size());
            location.getExits().get(random).getDestination().setGround(new Sprout());
        }
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (location.containsAnActor() && location.getActor().equals(actor)) { return new ActionList(); }
        return new ActionList(new JumpAction(location, direction, 70, 30));
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public void resetInstance() {

    }
}
