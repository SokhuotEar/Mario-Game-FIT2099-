package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.behaviours.BehaviourPriority;
import game.actors.enemies.behaviours.FireAttackBehaviour;
import game.items.Key;
import game.reset.ResetManager;

import java.util.ArrayList;
import java.util.List;

/**
 * The main boss of the game
 * @author Satya Jhaveri
 * @version 1.0
 * @see game.actors.enemies.Enemy
 */
public class Bowser extends Enemy {
    /**
     * The initial position of Bowser
     */
    private Location initialPosition;

    /**
     * Constructor.
     */
    public Bowser() {
        super("Bowser", 'B', 500, false);

        // Initialise monologue lines:
        List<String> lines = new ArrayList<>();
        lines.add("What was that sound? Oh, just a fire.");
        lines.add("Princess Peach! You are formally invited... to the creation of my new kingdom!");
        lines.add("Never gonna let you down!");
        lines.add("Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!");
        this.setLines(lines);

        // Give Bowser a key:
        this.addItemToInventory(Key.getInstance());

        // Give Bowser ability to FireAttack
        this.addBehaviour(BehaviourPriority.ATTACK.ordinal(), new FireAttackBehaviour());
        this.initialPosition = null;
        this.setBaseAttackDamage(80);
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.initialPosition == null) {
            this.initialPosition = map.locationOf(this);
        }
        this.speak(display, this, map);
        return new DoNothingAction();
    }


    /**
     * Resets Bowser
     * @param map The map bowser is currently in
     */
    @Override
    public void resetInstance(GameMap map) {
        map = this.initialPosition.map();

        // If Bowser is not in the map, don't do anything, otherwise, return him to original position:
        if (!map.contains(this)) {
            ResetManager.getInstance().cleanUp(this);
            return;
        }

        // If there's an actor in the initial spot, move the actor to an adjacent square:
        if (this.initialPosition.containsAnActor()) {
            boolean moved = false;

            for (Exit e : this.initialPosition.getExits()) {
                if (!e.getDestination().containsAnActor()) {
                    // If the adjacent location is empty, move the actor there
                    Actor actor = this.initialPosition.getActor();
                    map.removeActor(actor);
                    e.getDestination().addActor(actor);
                    moved = true;
                    break;
                }
            }

            // If no adjacent squares are available either, just remove the actor in the way
            if (!moved) {
                map.removeActor(initialPosition.getActor());
            }
        }

        // Move Bowser back to original spot:
        if (this.initialPosition != null) {
            map.removeActor(this);
            this.initialPosition.addActor(this);
        }

        // Heal bowser:
        this.resetMaxHp(this.getMaxHp());

        // Remove follow behaviour if it has it:
        this.removeBehaviour(BehaviourPriority.FOLLOW.ordinal());
    }

    /**
     * Gets the verb of Bowser's attack
     * @return the verb of Bowser's attack
     */
    @Override
    public String getVerb() {
        return "punches";
    }
}
