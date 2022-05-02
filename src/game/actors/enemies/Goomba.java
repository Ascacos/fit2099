package game.actors.enemies;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.SuicideAction;
import game.behaviours.Behaviour;

import java.util.Random;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy {

	private final Random rand = new Random();

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		registerInstance();
	}

	/**
	 * Figure out what to do next.
	 * If the Goomba is not committed to being reset, the following will occur in order:
	 * The Goomba has a 10% chance to return a SuicideAction and be removed from the map via a SuicideAction, then
	 * this method will iterate through it's behaviours to find an action to return.
	 * If no action is found, then this method will return a DoNothingAction.
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		//Check for Reset
		Action parentAction = super.playTurn(actions, lastAction, map, display);
		if (parentAction != null){
			return parentAction;
		}

		// In every turn, has a 10% chance to be removed from the map (suicide)
		if (rand.nextInt(101) < 10) {
			return new SuicideAction();
		}

		for (Behaviour Behaviour : behaviours.values()) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

	/**
	 * A method to get the Goomba's weapon
	 * The Goomba does not hold weapon items, so we will always
	 * return it's intrinsic weapon.
	 * @return The weapon of this Goomba (intrinsic weapon)
	 */
	@Override
	public Weapon getWeapon() {
		return getIntrinsicWeapon();
	}

	/**
	 * A method to get the Goomba's intrinsic weapon - a kick that deals 10 damage.
	 * @return The intrinsic weapon of a Goomba (kick)
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "kicks");
	}
}