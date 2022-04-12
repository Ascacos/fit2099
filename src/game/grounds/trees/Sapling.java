package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class Sapling extends Ground implements Tree {

    private final Random rand = new Random();
    private int age;

    public Sapling() {
        super('t');
        age = 0;
    }

    @Override
    public void tick(Location location) {
        // 10% chance to summon a Coin
        if (rand.nextInt(100) < 10) {
            //TODO: Summon a coin ($20)
        }
    }

    @Override
    public int getAge() {
        return this.age;
    }
}
