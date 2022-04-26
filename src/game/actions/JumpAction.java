package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class JumpAction extends Action {

    private Location destination;
    private String direction;

    public JumpAction(Location destination, String direction) {
        this.destination = destination;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (destination.containsAnActor()) {
            return actor + " cant go there";
        } else {
            map.moveActor(actor, destination);
        }
        return actor + "jumped " + direction;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Jump to " + direction;
    }
}
