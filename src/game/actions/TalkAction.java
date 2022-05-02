package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.List;
import java.util.Random;

/**
 * A special action to allow two actors to talk to one another.
 */
public class TalkAction extends Action {

    /**
     * The actor that spoke first
     */
    private final Actor origin;
    /**
     * A list of possible responses
     */
    private List<String> messages;
    private final Random rand = new Random();

    /**
     * Constructor.
     *
     * @param origin The actor that spoke first
     * @param messages A list of possible responses.
     */
    public TalkAction(Actor origin, List<String> messages) {
        this.messages = messages;
        this.origin = origin;
    }

    /**
     * Execute this action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a random string from the list of possible responses
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return messages.get(rand.nextInt(messages.size()));
    }

    /**
     * A method to return a descriptive string
     * @see edu.monash.fit2099.engine.actions.Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return A string to display in the game menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks to " + origin;
    }
}
