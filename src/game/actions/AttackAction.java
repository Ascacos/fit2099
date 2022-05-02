package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Executes an AttackAction.
	 * This method is called any time a basic attack is made.
	 *
	 * On execution, an AttackAction will first check if the Actor executing this Action has a {@link game.Status Power Star}
	 * buff active, if true, will deal damage = Integer.MAX_VALUE. If the Actor does not have a Power Star buff, it will
	 * roll to see if the attack will land (will depend on the return value of {@link Weapon#chanceToHit()}. If the attack
	 * does not miss, it will inflict damage on the target actor - and check if the Actor survived, else drop all of it's
	 * items.
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return A description of what happened.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		if (actor.hasCapability(Status.POWER_STAR)) {
			target.hurt(Integer.MAX_VALUE);

			return actor + " disintegrated " + target;
		}

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		if (!target.isConscious()) {
			ActionList dropActions = new ActionList();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// remove actor
			map.removeActor(target);
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	/**
	 * A method to return a descriptive string
	 * @see edu.monash.fit2099.engine.actions.Action#menuDescription(Actor)
	 * @param actor The actor performing the action.
	 * @return A string to display in the game menu.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
