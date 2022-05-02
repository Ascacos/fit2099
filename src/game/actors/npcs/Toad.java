package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.BuyItemAction;
import game.actions.TalkAction;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wrench;

import java.util.ArrayList;
import java.util.List;

public class Toad extends Actor {

    /**
     * Constructor.
     */
    public Toad() {
        super("Toad", 'O', 100);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new BuyItemAction(new PowerStar(false), 0));
        actions.add(new BuyItemAction(new SuperMushroom(false), 0));

        actions.add(new TalkAction(this, generateMessages(otherActor)));
        return actions;
    }

    private List<String> generateMessages(Actor actor) {
        List<String> messages = new ArrayList<>();
        boolean hasWrench = false;
        for (int i = 0; i < actor.getInventory().size(); i++) {
            if (actor.getInventory().get(i) instanceof Wrench) {
                hasWrench = true;
            }
        }
        if (!hasWrench) { messages.add("You might need a wrench to smash Koopa's hard shells."); }

        if (!actor.hasCapability(Status.POWER_STAR)) {
            messages.add("You better get back to finding the Power Stars.");
        }

        messages.add("The Princess is depending on you! You are our only hope.");
        messages.add("Being imprisoned in these walls can drive a fungus crazy :(");

        return messages;
    }
}
