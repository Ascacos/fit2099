package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	/**
	 * Constructor
	 */
	public Floor() {
		super('_');
	}

	/**
	 * A method to check if the actor trying to enter the Floor should be allowed it.
	 * Floor's will only allow actor's with {@link Status#HOSTILE_TO_ENEMY}
	 * @param actor the Actor to check
	 * @return true if and only if the actor is HOSTILE_TO_ENEMY
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
	}
}
