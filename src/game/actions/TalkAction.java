package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.List;
import java.util.Random;

public class TalkAction extends Action {

    private final Actor origin;
    private List<String> messages;
    private final Random rand = new Random();

    public TalkAction(Actor origin, List<String> messages) {
        this.messages = messages;
        this.origin = origin;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return messages.get(rand.nextInt(messages.size()));
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks to " + origin;
    }
}
