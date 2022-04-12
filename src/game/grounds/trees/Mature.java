package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class Mature extends Ground {

    private final Random rand = new Random();
    private int age;

    public Mature() {
        super('T');
        age = 0;
    }

    @Override
    public void tick(Location location) {
        age++;

        // 15% chance to summon a Koopa
        if (rand.nextInt(100) < 15) {
            //TODO: location.addActor(new Koopa());
        }
    }
}
