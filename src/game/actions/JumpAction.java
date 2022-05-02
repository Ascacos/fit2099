package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

import java.util.Random;

/**
 * A special action to allow Actors to jump to high ground.
 * Only actor's with {@link game.Status#HOSTILE_TO_ENEMY HOSTILE_TO_ENEMY} can jump to high grounds.
 * A ground is considered high if it has the {@link game.Status#TALL TALL} capability.
 */
public class JumpAction extends Action {

    private final Random rand = new Random();
    /**
     * The destination to jump to
     * @see Location
     */
    private final Location destination;
    /**
     * The direction to jump to (cardinal)
     */
    private final String direction;
    /**
     * The success rate of the jump.
     */
    private final int successRate;
    /**
     * The amount of damage to take, if the jump fails.
     */
    private final int failDamage;

    /**
     * Constructor.
     *
     * @param destination The location to jump to
     * @param direction The direction to jump in (cardinal diredction), relative to the Actor's location
     * @param successRate The percent chance of the jump succeeding
     * @param failDamage The dmaage the actor will take if the
     */
    public JumpAction(Location destination, String direction, int successRate, int failDamage) {
        this.destination = destination;
        this.direction = direction;
        this.successRate = successRate;
        this.failDamage = failDamage;
    }

    /**
     * Method to determine the outcome of a jump action, taking into account the success rate and
     * fail damage (which varies depending on the ground type)
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of what happened
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (destination.containsAnActor()) {
            return actor + " cant go there";
        } else {
            if (actor.hasCapability(Status.POWER_STAR)) {
                Action powerMove = new PowerMoveAction(destination);
                powerMove.execute(actor, map);
                return null;
            }
            //if the Actor has an active Super Mushroom, jump instantly
            if (actor.hasCapability(Status.SUPER_MUSHROOM)) {
                map.moveActor(actor, destination);
                return actor + " super jumped " + direction;
            }

            // evaluate % chance of successfully jumping
            if (rand.nextInt(101) <= successRate) {
                map.moveActor(actor, destination);
                return actor + " jumped " + direction;
            } else {
                //if the jump fails :( take damage
                actor.hurt(failDamage);
                return actor + " failed to jump and took " + failDamage + " damage";
            }
        }
    }

    /**
     * A method to return a descriptive string
     * @see edu.monash.fit2099.engine.actions.Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return A string to display in the game menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Jump to " + direction;
    }
}
