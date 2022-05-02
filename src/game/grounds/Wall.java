package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class representing a Wall
 */
public class Wall extends Ground {

	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
		this.addCapability(Status.TALL);
	}

	/**
	 * A method to check whether an Actor can enter this ground or not.
	 * Walls are not able to be travelled on, so this will always return false.
	 * @param actor the Actor to check
	 * @return False
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * A method to check whether this will block thrown objects
	 * @return True
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
