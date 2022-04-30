package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

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
