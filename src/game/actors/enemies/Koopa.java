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
import game.behaviours.DormantBehaviour;

public class Koopa extends Enemy {
    /**
     * Constructor.
     *
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        registerInstance();
    }

    /**
     * Figure out what to do next.
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

    private boolean isDormant() {
        return this.hasCapability(Status.DORMANT);
    }

    @Override
    public Weapon getWeapon() {
        return getIntrinsicWeapon();
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }

    @Override
    public boolean isConscious() {
        return !super.isConscious() || isDormant();
    }

    @Override
    public void hurt(int points) {
        System.out.println("Koopa hp: " + printHp());
        super.hurt(points);
        if (!super.isConscious()) {
            this.addCapability(Status.DORMANT);
        }
    }

    @Override
    public char getDisplayChar() {
        if (this.isDormant()) return 'D';
        else return super.getDisplayChar();
    }
}
