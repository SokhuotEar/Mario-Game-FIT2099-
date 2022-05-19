package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.enemies.behaviours.BehaviourPriority;
import game.actors.enemies.behaviours.FireAttackBehaviour;
import game.items.Key;

import java.util.ArrayList;
import java.util.List;

public class Bowser extends Enemy {
    private int initialXCoord;
    private int initialYCoord;
    /**
     * Constructor.
     */
    public Bowser(int x, int y) {
        super("Bowser", 'B', 500, false);
        List<String> lines = new ArrayList<>();
        lines.add("What was that sound? Oh, just a fire.");
        lines.add("Princess Peach! You are formally invited... to the creation of my new kingdom!");
        lines.add("Never gonna let you down!");
        lines.add("Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!");
        this.setLines(lines);
        this.addItemToInventory(Key.getInstance());
        this.addBehaviour(BehaviourPriority.FIRE_ATTACK.ordinal(), new FireAttackBehaviour());
        this.initialXCoord = x;
        this.initialYCoord = y;
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
        this.speak(display, this, map);
        return new DoNothingAction();
    }

    /**
     * Creates and returns an intrinsic weapon.
     * <p>
     * By default, the Actor 'punches' for 5 damage. Override this method to create
     * an Actor with more interesting descriptions and/or different damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punches");
    }

    /**
     * Resets the Enemy instance
     *
     * @param map
     */
    @Override
    public void resetInstance(GameMap map) {
        // Move Bowser back to original spot:
        map.removeActor(this);
        map.at(this.initialXCoord, this.initialYCoord).addActor(this);

        // If theres an actor there, move the actor to an adjacent square:
        // TODO:

        // Heal bowser:
        this.resetMaxHp(this.getMaxHp());

        // Remove follow behaviour if it has it:
        this.removeBehaviour(BehaviourPriority.FOLLOW.ordinal());


    }
}
