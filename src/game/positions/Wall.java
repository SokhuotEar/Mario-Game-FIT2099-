package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;
import game.items.Coin;

public class Wall extends HighGround {

	public Wall() {
		super('#');
	}


	@Override
	public int getJumpChanceSuccess(Actor actor) {
		return 80;
	}

	@Override
	public int getFallDamage(Actor actor) {
		return 20;
	}
}
