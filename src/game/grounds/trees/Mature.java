package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.Dirt;

import java.util.Random;

public class Mature extends Ground implements Tree {

    private final Random rand = new Random();
    private int age;
    private int growthCycle;

    public Mature() {
        super('T');
        age = 0;
        this.addCapability(Status.TALL);
    }

    public Mature(int age) {
        super('T');
        this.age = age;
        this.addCapability(Status.TALL);
    }

    @Override
    public void tick(Location location) {
        age++;
        growthCycle++;

        // 15% chance to summon a Koopa
        if (rand.nextInt(100) < 15) {
            //TODO: location.addActor(new Koopa());
        }

        if (rand.nextInt(100) < 20) {
            if (!location.containsAnActor()) location.setGround(new Dirt());
        }

        if (growthCycle >= 5) {
            int random = rand.nextInt(location.getExits().size());
            location.getExits().get(random).getDestination().setGround(new Sprout());
            growthCycle = 0;
        }
    }

    @Override
    public int getAge() {
        return this.age;
    }
}
