package game.grounds.trees;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.actors.enemies.Goomba;
import game.grounds.Dirt;
import game.reset.Resettable;

import java.util.Random;

public abstract class Tree extends Ground implements Resettable {

    private int age;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.age = 0;
        this.addCapability(Status.TALL);
    }

    @Override
    public void tick(Location location) {
        if (this.hasCapability(Status.RESETTING)){
            Random randnum = new Random();
            //50% Chance for Tree to turn back into Dirt
            int generatedNum = randnum.nextInt(101);

            if (generatedNum < 50) {
                location.setGround(new Dirt());
            }
        }
    }

    /**
     * A method to get the age of this tree.
     *
     * @return the age of this Tree
     */
    public int getAge() {
        return this.age;
    }

    public void spawnActor(Location location, Actor actor) {
        if (!location.containsAnActor()) {
            location.addActor(actor);
        }
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESETTING);
    }
}
