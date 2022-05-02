package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;

/**
 * A specialise action that can be performed by the player, once per game, to 'soft reset' the game.
 */
public class ResetAction extends Action {

    /**
     * Gives this action a custom hotkey "r"
     * @return The hotkey, 'r'
     */
    @Override
    public String hotkey(){return "r";}

    /**
     * Executes the action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of what happened.
     */
    @Override
    public String execute(Actor actor, GameMap map){
        ResetManager.getInstance().run();
        return "Player has reset game.";
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
