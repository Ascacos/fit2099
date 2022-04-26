package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.enemies.Goomba;

import java.util.Random;

public class Sprout extends Tree {

    private final Random rand = new Random();
    private int age;

    public Sprout() {
        super('+');
        age = 0;
    }

    @Override
    public void tick(Location location) {
        age++;

        // 10% chance to summon a Goomba
        if (rand.nextInt(100) < 10) {
            if (location.containsAnActor()) location.addActor(new Goomba());
        }

        //if age = 10, become Sapling
        if (age == 10) {
            location.setGround(new Sapling(age));
        }
    }

    @Override
    public int getAge() {
        return this.age;
    }
}
