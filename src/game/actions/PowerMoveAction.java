package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.Dirt;
import game.items.Coin;

/**
 * A specialised move action to allow an actor to move to any high ground, without the need to {@link game.actions.JumpAction}
 * jump. Guaranteed jump!
 */
public class PowerMoveAction extends Action {

    /**
     * The location to move to.
     */
    private final Location destination;

    /**
     * Constructor.
     *
     * @param destination The location to jump to.
     */
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

    /**
     * A method to return a descriptive string
     * @see edu.monash.fit2099.engine.actions.Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return A string to display in the game menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
