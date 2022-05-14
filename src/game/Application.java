package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actions.TeleportAction;
import game.actors.Player;
import game.actors.Toad;
import game.actors.enemies.Goomba;
import game.actors.enemies.Koopa;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.WarpPipe;
import game.items.Wrench;
import game.positions.*;

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

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Lava());

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
		Actor mario = new Player("Player", 'm', 1000);
		world.addPlayer(mario, gameMap.at(42, 10));

		//Adding SuperMushroom and PowerStar
		gameMap.at(42, 10).addItem(new SuperMushroom());
		gameMap.at(42, 10).addItem(new PowerStar());


		// Adding Toad:
		gameMap.at(43,12).addActor(new Toad());

		// Adding random warp pipes
		WarpPipe pipe1 = new WarpPipe();
		WarpPipe pipe2 = new WarpPipe();
		WarpPipe pipe3 = new WarpPipe();
		gameMap.at(41,14).addItem(pipe1);
		gameMap.at(43,14).addItem(pipe2);
		gameMap.at(45,04).addItem(pipe3);

		//Adding fountains
		gameMap.at(36, 10).setGround(new HealthFountain());
		gameMap.at(45, 13).setGround(new PowerFountain());



		// SECOND MAP
		List<String> lavaMap = Arrays.asList(
				"......LLLLLLLLLLLLLLLLLLLLLLLLLLLL........##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"......LLLLLLLLLLLLLLLLLLLLLLLLLLLLL............#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........LLLLLLLLLLLLLLLLLLLL............+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");



		// adding Lava Zone map
		GameMap lavaZoneMap = new GameMap(groundFactory, lavaMap);
		world.addGameMap(lavaZoneMap);

		pipe1.addPipeAction(new TeleportAction(lavaZoneMap.at(0,0)));
		pipe2.addPipeAction(new TeleportAction(lavaZoneMap.at(0,0)));
		pipe3.addPipeAction(new TeleportAction(lavaZoneMap.at(0,0)));


		world.run();
	}
}
