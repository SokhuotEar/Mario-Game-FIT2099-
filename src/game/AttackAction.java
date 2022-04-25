package game;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;

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

	@Override
	public String execute(Actor actor, GameMap map) {
		String result;
		if (!target.hasCapability(Status.INVINCIBLE)){  // If the target is not invincible, attack them
			Weapon weapon = actor.getWeapon();
			int damage = weapon.damage();
			if (actor.hasCapability(Status.INVINCIBLE)) { // if the actor(attacker) is invincible, kill target
				while (target.isConscious()) {
					target.hurt(damage);
				}
				result = actor + " is invincible!" ;
			}
			else {  // Otherwise, attack target normally


				if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
					return actor + " misses " + target + ".";
				}

				result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
				target.hurt(damage);

			}
			if (!target.isConscious()) {
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					if (item.getDropAction(actor) != null) {
						dropActions.add(item.getDropAction(actor));
					}
					else {  // if the item cannot be dropped normally (eg power star or super mushroom) add drop action manually
						dropActions.add(new DropItemAction(item));
					}

				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove actor
				map.removeActor(target);
				result += System.lineSeparator() + target + " is killed.";
			}
		}
		else {
			result = target + " is invincible! " + actor + "'s attack did no damage!";
		}


		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
