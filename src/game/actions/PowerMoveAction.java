package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.Dirt;
import game.items.Coin;

public class PowerMoveAction extends Action {

    private final Location destination;

    public PowerMoveAction(Location destination) {
        this.destination = destination;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (destination.getGround().hasCapability(Status.TALL)) {
            destination.setGround(new Dirt());
            destination.addItem(new Coin(5));

            map.moveActor(actor, destination);
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
