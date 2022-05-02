package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.Toad;
import game.actors.enemies.Goomba;
import game.actors.enemies.Koopa;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wrench;
import game.positions.Dirt;
import game.positions.Floor;
import game.positions.Tree;
import game.positions.Wall;

/**
 * The main class for the Mario World game.
 * @author FIT2099, modified by Satya Jhaveri, Klarissa Jutivannadevi, Sok Ear
 * @version 1.0
 *
 */
public class Application {

	/**
	 * Runs the main Mario game
	 * @param args command line args (not needed for current version of application)
	 */
	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			// Adding player:
			Actor mario = new Player("Player", 'm', 100);
			world.addPlayer(mario, gameMap.at(42, 10));

			//Adding SuperMushroom and PowerStar
			gameMap.at(42, 10).addItem(new SuperMushroom());
			gameMap.at(42, 10).addItem(new PowerStar());


			// Adding Toad:
			gameMap.at(43,12).addActor(new Toad());

			world.run();

	}
}
