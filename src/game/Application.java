package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.PrincessPeach;
import game.actors.Toad;
import game.actors.enemies.Bowser;
import game.items.*;
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

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Lava());

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


		//Adding fountains
		gameMap.at(36, 10).setGround(new PowerFountain());
		gameMap.at(45, 13).setGround(new HealthFountain());


		// SECOND MAP
		List<String> lavaMap = Arrays.asList(
				"......LLLLLLLLLLLLLLLLLLLLLLLLLLLL........##..........+...............",
				"............+............+..................#.........................",
				"............................................#.........................",
				".............................................##......................+",
				"......LLLLLLLLLLLLLLLLLLLLLLLLLLLLL............#......................",
				"................................................#.....................",
				".................+................................#...................",
				"........LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL..........##...................",
				"................................................##....................",
				".........+....................................####....................",
				"..............................................###.....................",
				".......................................+.......###....................",
				"........LLLLLLLLLLLLLLLLLLLL............+......###....................",
				"........................+........................##.............+.....",
				"...................................................#..................");



		// adding Lava Zone map
		GameMap lavaZoneMap = new GameMap(groundFactory, lavaMap);
		world.addGameMap(lavaZoneMap);

		// Adding Princess:
		lavaZoneMap.at(5, 10).addActor(new PrincessPeach());

		// Adding Bowser:
		lavaZoneMap.at(3, 10).addActor(new Bowser());


		// Adding random warp pipes
		WarpPipe pipe1 = new WarpPipe();
		Location pipe1Location = gameMap.at(43, 8);
		pipe1Location.setGround(pipe1);

		WarpPipe pipe2 = new WarpPipe();
		Location pipe2Location = gameMap.at(42, 15);
		pipe2Location.setGround(pipe2);

		WarpPipe lavaMapPipe = new WarpPipe();
		Location lavaMapPipeLocation = lavaZoneMap.at(0,0);
		lavaMapPipeLocation.setGround(lavaMapPipe);

		// Linking the warp pipes:
		WarpPipe.linkPipes(pipe1, pipe1Location, lavaMapPipe, lavaMapPipeLocation);
		WarpPipe.linkPipes(pipe2, pipe2Location, lavaMapPipe, lavaMapPipeLocation);


		world.run();
	}
}
