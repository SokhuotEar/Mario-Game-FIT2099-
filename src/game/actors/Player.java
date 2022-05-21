package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.reset.ResetAction;
import game.reset.Resettable;
import game.utils.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Player.
 * @author FIT2099, extended by Satya Jhaveri
 * @version 1.0
 */
public class Player extends Actor implements Resettable, IntrinsicWeaponBooster {
	/**
	 * A menu instance to allow the player to select their choice of action
	 */
	private final Menu menu;

	/**
	 * The player's wallet
	 */
	private final Wallet wallet;

	/**
	 * Static list of all player instances
	 */
	private static final List<Player> playerList = new ArrayList<>();

	private int baseAttackDamage;

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
		this.addCapability(Status.HAS_RESET);
		this.addCapability(Status.CAN_BUY_FROM_TOAD);
		this.registerInstance();
		this.wallet = new Wallet();
		this.menu = new Menu();
		playerList.add(this);
		this.baseAttackDamage = 10;
		this.registerIntrinsicWeaponInstance();
	}

	/**
	 * Gets the player's wallet
	 * @return the player's wallet
	 */
	public Wallet getWallet() {
		return this.wallet;
	}


	/**
	 * Checks if an actor is a player
	 * @param actor the actor to check
	 * @return true if the actor is a player, false otherwise
	 */
	public static boolean isInstance(Actor actor) {
		return playerList.contains(actor);
	}

	/**
	 * Gets an actor as a player object (if it is a player). Done to avoid casting.
	 * @param actor an actor to get the player object of
	 * @return a player object if the actor is a player, null otherwise
	 */
	public static Player getInstance(Actor actor) {
		if (isInstance(actor)) {
			for (Player player : playerList) {
				if (player == actor) {
					return player;
				}
			}
		}
		return null;
	}



	/**
	 * Allow the player to choose what to do next
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return an action that the player chose to execute
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// Display player stats:
		display.println(name + ": " + printHp() + "HP, Wallet: $" + wallet.getBalance() + ", Attack Damage: " + this.getWeapon().damage() + "HP");
		if(hasCapability((Status.INVINCIBLE))) {
			String sentence = this + " is INVINCIBLE!";
			display.println(sentence);
		}

		// add the reset action:
		if (this.hasCapability(Status.HAS_RESET)) {
			actions.add(new ResetAction());
		}



		if (hasCapability(Status.FIREATTACK))
		{
			String sentence = this + " can attack with Fire!";
			display.println(sentence);
		}

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Do some damage to the current Actor.
	 * <p>
	 * If the Actor's hitpoints go down to zero, it will be knocked out.
	 *
	 * @param points number of hitpoints to deduct.
	 */
	@Override
	public void hurt(int points) {
		// If the player has the tall status, remove the tall status:
		removeCapability(Status.TALL);  // the if checking is done by the CapabilitySet.removeCapability method
		super.hurt(points);

	}


	/**
	 * gets the display character
	 * @return a character to represent the player on the map
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * Resets the player
	 * @param map The map that the player is on
	 */
	@Override
	public void resetInstance(GameMap map) {
		// heal to full health
		this.resetMaxHp(getMaxHp());

		// remove all statuses
		for (Status status : Status.values()) {
			if (this.hasCapability(status)) {
				this.removeCapability(status);
			}
		}

		//add back hostility and ability to buy from toad:
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.CAN_BUY_FROM_TOAD);

	}

	/**
	 * Increases the base attack damage
	 * @param extraAttack the amount of attack damage to increase by
	 */
	@Override
	public void increaseBaseAttack(int extraAttack) {
		this.baseAttackDamage += extraAttack;
	}

	/**
	 * Gets the current attack damage
	 * @return the current attack damage
	 */
	@Override
	public int getAttackDamage() {
		return this.baseAttackDamage;
	}

	/**
	 * Sets the base attack damage
	 * @param baseDamage the new base attack damage
	 */
	@Override
	public void setBaseAttackDamage(int baseDamage) {
		this.baseAttackDamage = baseDamage;
	}

	/**
	 * Gets the verb of the Player's attack
	 * @return the verb of Player's attack
	 */
	@Override
	public String getVerb() {
		return "punches";
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(this.getAttackDamage(), "punches");
	}
}
