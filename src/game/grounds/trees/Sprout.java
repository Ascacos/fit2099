package game.grounds.trees;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
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
        if (rand.nextInt(101) <= 10) {
            if (!location.containsAnActor()) location.addActor(new Goomba());
        }

        //if age = 10, become Sapling
        if (age == 10) {
            location.setGround(new Sapling(age));
        }
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (location.containsAnActor() && location.getActor().equals(actor)) { return new ActionList(); }
        return new ActionList(new JumpAction(location, direction, 90, 10));
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public void resetInstance() {

    }
}