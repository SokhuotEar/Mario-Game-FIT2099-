package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.reset.Resettable;
import game.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

	private final Menu menu;
	private int invincibleTurnsLeft;
	private static final int maxInvincibleTurns = 10;
	private final Wallet wallet;
	private static final List<Player> playerList = new ArrayList<>();

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
		invincibleTurnsLeft = 0;
		this.registerInstance();
		this.wallet = new Wallet();
		this.menu = new Menu();
		playerList.add(this);
	}

	public Wallet getWallet() {
		return this.wallet;
	}


	public static boolean isInstance(Actor actor) {
		return playerList.contains(actor);
	}

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


	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// Display player stats:
		display.println(name + ": " + printHp() + "HP, Wallet: $" + wallet.getBalance());  // todo: add money here
		if(hasCapability((Status.INVINCIBLE))) {
			if (invincibleTurnsLeft > 0) {
				String sentence = this + " is INVINCIBLE! - " + invincibleTurnsLeft + " turns remaining";
				invincibleTurnsLeft--;
				display.println(sentence);
				return menu.showMenu(this, actions, display);
			}
			else {
				this.removeCapability(Status.INVINCIBLE);
			}
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
	 * Add a capability to this Player.
	 *
	 * @param capability the Capability to add
	 */
	@Override
	public void addCapability(Enum<?> capability) {
		// Set the number of turns left with invincibility to 10 if the player consumes a power star
		if (capability == Status.INVINCIBLE) {
			invincibleTurnsLeft = maxInvincibleTurns;
		}
		super.addCapability(capability);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	@Override
	public void resetInstance() {
		this.resetMaxHp(getMaxHp());
		for (Status status : Status.values()) {
			if (this.hasCapability(status)) {
				this.removeCapability(status);
			}
		}

	}

}
