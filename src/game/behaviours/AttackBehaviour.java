package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

public class AttackBehaviour implements Behaviour{

    private final Actor target;

    public AttackBehaviour(Actor actor, Actor target) {
        this.target = target;
    }

    // TODO: develop and use it to attack the player automatically.
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination().getActor() == target) {
                System.out.println("attack action");
                return new AttackAction(target, exit.getName());
            }
        }
        return null;
    }
}
