package game.grounds.trees;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.items.Coin;

import java.util.Random;

public class Sapling extends Tree {

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
        if (rand.nextInt(101) < 10) {
            location.addItem(new Coin(20));
        }

        // at age 20, become a mature tree
        if (age == 20) {
            if (!location.containsAnActor()) location.setGround(new Mature(age));
        }
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (location.containsAnActor() && location.getActor().equals(actor)) { return new ActionList(); }
        return new ActionList(new JumpAction(location, direction, 80, 20));
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public void resetInstance() {

    }
}
