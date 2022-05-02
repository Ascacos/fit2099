package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A specialised action to allow an actor to perish on it's own accord.
 */
public class SuicideAction extends Action {

    /**
     * Execute this action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of what happened.
     */
    @Override
    public String execute(Actor actor, GameMap map){
        map.removeActor(actor);
        return actor.toString() + " has died.";
    }

    /**
     * A method to return a descriptive string
     * @see edu.monash.fit2099.engine.actions.Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return A string to display in the game menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset Game";
    }
}

