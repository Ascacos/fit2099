package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.AttackAction;
import game.actions.SuicideAction;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.reset.Resettable;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor implements Resettable {
    /**
     * A map containing an Enemy's behaviours
     * Behaviours with a higher priority will be checked for an action first
     */
    protected final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);

        this.behaviours.put(10, new WanderBehaviour());

    }

    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
            behaviours.put(5, new FollowBehaviour(otherActor));
        }
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(this.hasCapability(Status.RESETTING)){
            return new SuicideAction();
        }
        return null;
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESETTING);
    }
}
