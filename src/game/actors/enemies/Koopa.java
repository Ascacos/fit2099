package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.actions.DestroyShellAction;
import game.actors.Shell;
import game.behaviours.DormantBehaviour;
import game.items.PowerStar;
import game.items.Wrench;

/**
 * A turtle with a very hard shell.
 */
public class Koopa extends Enemy implements Shell {
    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        registerInstance();
    }

    /**
     * Figure out what to do next.
     *
     * This method will first check if the Koopa is in it's dormant state, and if so, will assign it a {@link DormantBehaviour Dormant Behaviour}
     * with a priority of 1. Next, it will iterate over all behaviours and return an appropriate action.
     *
     * @see Enemy#playTurn(ActionList, Action, GameMap, Display)
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        //Check for Reset
        super.playTurn(actions, lastAction, map, display);

        if (isDormant()) {
            this.behaviours.put(1, new DormantBehaviour());
        }

        for(game.behaviours.Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    /**
     * A method to check if a Koopa is in it's dormant state.
     *
     * @return True if and only if the Koopa is dormant
     */
    private boolean isDormant() {
        return this.hasCapability(Status.DORMANT);
    }

    @Override
    public Weapon getWeapon() {
        return getIntrinsicWeapon();
    }

    /**
     * A method to get the Koopa's intrinsic weapon (punch = 30dmg)
     *
     * @return An intrinsic weapon.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }

    /**
     * An override method to see if this Koopa is conscious - but not in a traditional way.
     * Due to Koopa's still technically being 'conscious' at 0 HP (due to their {@link Shell Shell}), this method
     * checks if the Koopa has HP > 0 and if it is dormant
     * @return true if and only if the Koopa has HP < 0 and is no longer dormant (shell destroyed).
     */
    @Override
    public boolean isConscious() {
        if (super.isConscious()) return true;
        else return !isDormant();
    }

    /**
     * An override method to apply damage to a Koopa.
     * This method will add an additional check to see if the Koopa takes lethal damage from the attack and if so,
     * put it in it's dormant state.
     *
     * @param points number of hitpoints to deduct.
     */
    @Override
    public void hurt(int points) {
        super.hurt(points);
        if (!super.isConscious()) {
            this.addCapability(Status.DORMANT);
        }
    }

    /**
     * An override method to determine if a Koopa is in it's dormant state ('D') or not ('K').
     * @return 'D' if dormant, otherwise 'K'.
     */
    @Override
    public char getDisplayChar() {
        if (this.isDormant()) return 'D';
        else return super.getDisplayChar();
    }

    /**
     * A method to get the allowable actions of the Koopa, checking first if it is in it's dormant state.
     *
     * If the Koopa is in it's dormant state, this method will check if the acting actor possesses a {@link game.items.Wrench Wrench}
     * and if so, returns an ActionList containing only a  {@link game.actions.DestroyShellAction DestroyShellAction}.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A list of potential actions the actor can perform.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if (!isDormant()) return super.allowableActions(otherActor, direction, map);

        ActionList actions = new ActionList();

        //FIXME :( instanceof
        for (int i = 0; i < otherActor.getInventory().size(); i++) {
            if (otherActor.getInventory().get(i) instanceof Wrench) {
                actions.add(new DestroyShellAction(this));
            }
        }

        return actions;
    }

    /**
     * A method to destroy the shell of the Koopa
     * When a Koopa's shell is broken, it drops a Power Star item on the ground at it's location.
     * @see PowerStar
     * @param map The game map in which this Koopa presides.
     */
    @Override
    public void destroyShell(GameMap map) {
        map.locationOf(this).addItem(new PowerStar(true));
        map.removeActor(this);
        this.removeCapability(Status.DORMANT);
    }
}
