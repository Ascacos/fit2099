package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Shell;

/**
 * A specialised attack action to allow for a {@link game.actors.Shell Shell} to be destroyed.
 * This action can be applied to any actor's {@link Actor#allowableActions(Actor, String, GameMap) allowable actions},
 * if it implements the Shell interface, to make the Shell destroyable.
 */
public class DestroyShellAction extends Action {

    /**
     * The target shell.
     * @see Shell
     */
    private final Shell target;

    /**
     * Constructor.
     *
     * @param target The target shell.
     */
    public DestroyShellAction(Shell target) {
        this.target = target;
    }

    /**
     * Executes this action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of what happened.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        target.destroyShell(map);
        return actor + " destroyed " + target + "'s Shell.";
    }

    /**
     * A method to return a descriptive string
     * @see edu.monash.fit2099.engine.actions.Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return A string to display in the game menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " destroy shell.";
    }
}
