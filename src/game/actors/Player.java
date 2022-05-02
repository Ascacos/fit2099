package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.Status;
import game.bank.Economy;
import game.actions.ResetAction;
import game.bank.WalletManager;
import game.reset.Resettable;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable, Economy {

	private final Menu menu = new Menu();

	private final ActionList defaultActions = new ActionList(new ResetAction());

	private boolean resetCheck = false;
	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		registerInstance();
		addWallet();
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Check to see if the Player has reset the game
		if (!resetCheck) {
			actions.add(defaultActions);
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}


	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.SUPER_MUSHROOM) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * A method to deduct hit points from a Player, while also checking if the player
	 * has any buffs (Super Mushroom) and act accordingly.
	 *
	 * @param points number of hit points to deduct.
	 */
	@Override
	public void hurt(int points) {
		if (this.hasCapability(Status.SUPER_MUSHROOM)) {
			removeCapability(Status.SUPER_MUSHROOM);
		}
		super.hurt(points);
	}

	@Override
	public void resetInstance() {
		resetCheck = true;
		//Heals Mario to Max Hp
		this.heal(this.getMaxHp());
		//Reset Player Status (Super-mushroom and Power star)
		if (this.hasCapability(Status.SUPER_MUSHROOM)) {
			this.removeCapability(Status.SUPER_MUSHROOM);
		}
		if(this.hasCapability(Status.POWER_STAR)){
			this.removeCapability(Status.POWER_STAR);
		}
	}

	@Override
	public void addWallet() {
		WalletManager.getInstance().add(this);
	}
}
