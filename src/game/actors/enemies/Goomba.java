package game.actors.enemies;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.actions.SuicideAction;
import game.actors.enemies.behaviours.WanderBehaviour;

/**
 * A little fungus guy.
 * @author FIT2099, modifed by Satya Jhaveri
 * @version 1.0
 */
public class Goomba extends Enemy {
	/**
	 * The % chance that the Goomba commits suicide every turn
	 */
	private static final int suicidedChance = 10;

	/**
	 * Creates and returns an intrinsic weapon.
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "kick");
	}

	/**
	 * Constructor
	 */
	public Goomba() {
		super("Goomba", 'g', 50,true);
		addBehaviour(10, new WanderBehaviour());
	}

	/**
	 * Figures out what action the Goomba should take next
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return an Action that the Goomba will execute
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// 10% Chance to suicide:
		if (RNG.rng(suicidedChance)) {
			return new SuicideAction();
		}

		return super.playTurn(actions, lastAction, map, display);
	}

}
