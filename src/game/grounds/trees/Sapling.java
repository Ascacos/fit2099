package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.Coin;

import java.util.Random;

public class Sapling extends Tree {

    private final Random rand = new Random();
    private int age;

    public Sapling() {
        super('t');
        age = 0;
        this.addCapability(Status.TALL);
    }

    public Sapling(int age) {
        super('t');
        this.age = age;
        this.addCapability(Status.TALL);
    }

    @Override
    public void tick(Location location) {
        age++;

        // 10% chance to summon a Coin
        if (rand.nextInt(100) < 10) {
            location.addItem(new Coin(20));
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
