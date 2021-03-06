package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

/**
 * A behaviour that will return an AttackAction if the target is within reach.
 */
public class AttackBehaviour implements Behaviour{

    /**
     * The actor to attack
     */
    private final Actor target;

    /**
     * Constructor
     * @param actor The attacking actor
     * @param target The target actor
     */
    public AttackBehaviour(Actor actor, Actor target) {
        this.target = target;
    }

    /**
     * A method to get the action to execute.
     *
     * Will check all adjacent exits of the actor's current location, and return an AttackAction on an exit if it
     * contains the target Actor.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return An AttackAction if it finds the target, or null if no target was found.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination().getActor() == target) {
                return new AttackAction(target, exit.getName());
            }
        }
        return null;
    }
}
