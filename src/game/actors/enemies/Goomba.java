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
 */

public class Goomba extends Enemy {
	private static final int sucicideChacnce = 10;
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
		super("Goomba", 'g', 50);
		addBehaviour(10, new WanderBehaviour());
	}



	/**
	 * Figure out what to do next.
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// 10% Chance to suicide:
		if (RNG.rng(sucicideChacnce)) {
			return new SuicideAction();
		}

		for(game.actors.enemies.behaviours.Behaviour Behaviour : getBehaviours().values()) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

}
