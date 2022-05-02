package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Shell;

public class DestroyShellAction extends Action {

    private final Shell target;

    public DestroyShellAction(Shell target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        target.destroyShell(map);
        return actor + " destroyed " + target + "'s Shell.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " destroy shell.";
    }
}
