package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A behaviour that returns DoNothingAction's
 */
public class DormantBehaviour implements Behaviour {

    /**
     * Determines what action to execute
     * 99.9999% of returning a DoNothingAction()
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return DoNothingAction, always :)
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new DoNothingAction();
    }
}
