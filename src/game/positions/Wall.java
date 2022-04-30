package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;
import game.items.Coin;

public class Wall extends Ground {

	public Wall() {
		super('#');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		if (actor.hasCapability(Status.INVINCIBLE)) {
			int coinValue = 20;
			setDisplayChar(new Coin(coinValue).getDisplayChar());
			return true;
		}
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}