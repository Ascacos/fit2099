package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Goomba;

import java.util.Random;

public class Sprout extends Ground {

    private final Random rand = new Random();

    public Sprout() {
        super('+');
    }

    @Override
    public void tick(Location location) {
        // 10% chance to summon a Goomba
        if (rand.nextInt(100) < 10) {
            location.addActor(new Goomba());
        }
    }
}
