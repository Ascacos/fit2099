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

    public Sapling(int age) {
        super('t');
        this.age = age;
    }

    @Override
    public void tick(Location location) {
        age++;

        // 10% chance to summon a Coin
        if (rand.nextInt(100) < 10) {
            //TODO: Summon a coin ($20)
        }
        System.out.println("Sapling age: " + age);
        if (age == 20) {
            if (!location.containsAnActor()) location.setGround(new Mature(age));
        }
    }

    @Override
    public int getAge() {
        return this.age;
    }
}
