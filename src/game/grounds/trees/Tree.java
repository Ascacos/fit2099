package game.grounds.trees;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;

public abstract class Tree extends Ground {

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

    /**
     * A method to get the age of this tree.
     *
     * @return the age of this Tree
     */
    public int getAge() {
        return this.age;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        return new ActionList(new JumpAction(location, direction));
    }

    public void spawnActor(Location location, Actor actor) {
        if (!location.containsAnActor()) {
            location.addActor(actor);
        }
    }
}
