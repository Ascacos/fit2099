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

	/**
	 * A menu.
	 */
	private final Menu menu = new Menu();

	/**
	 * An ActionList of default actions the player may use.
	 */
	private final ActionList defaultActions = new ActionList(new ResetAction());

	/**
	 * A boolean flag to check if the player has reset the game.
	 */
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

	/**
	 * Determine what action to execute
	 *
	 * This method will first check if a reset has been executed previously and if not, adds the player's default actions.
	 * Then, a menu will be shown to the player where the user can input their choice of action to execute.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return The action to execute.
	 */
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

	/**
	 * A method to get the display character of the Player.
	 * This method checks if the player has an active SUPER_MUSHROOM buff, and if so, converts it's display character
	 * to uppercase
	 * @return The character to display on the game map.
	 */
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

	/**
	 * A method to reset the player instance.
	 *
	 * This method is called when the player executes a game reset.
	 * It will heal the player to max health and remove any active buffs.
	 */
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

	/**
	 * A method to add an associated wallet to a player.
	 * @see WalletManager
	 */
	@Override
	public void addWallet() {
		WalletManager.getInstance().add(this);
	}
}
