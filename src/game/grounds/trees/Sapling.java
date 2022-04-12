package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class Sapling extends Ground {

    private final Random rand = new Random();

    public Sapling() {
        super('t');
    }

    @Override
    public void tick(Location location) {
        // 10% chance to summon a Coin
        if (rand.nextInt(100) < 10) {
            //TODO: Summon a coin ($20)
        }
    }
}
