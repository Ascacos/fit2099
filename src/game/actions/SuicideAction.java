package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;

public class SuicideAction extends Action {


    @Override
    public String execute(Actor actor, GameMap map){
        map.removeActor(actor);
        return "Player has reset game.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Reset Game";
    }
}

