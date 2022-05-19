package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.NPC;
import game.items.Key;

import java.util.ArrayList;
import java.util.List;

public class Bowser extends Enemy {
    /**
     * Constructor.
     */
    public Bowser() {
        super("Bowser", 'B', 500, false);
        List<String> lines = new ArrayList<>();
        lines.add("What was that sound? Oh, just a fire.");
        lines.add("Princess Peach! You are formally invited... to the creation of my new kingdom!");
        lines.add("Never gonna let you down!");
        lines.add("Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!");
        this.setLines(lines);
        this.addItemToInventory(Key.getInstance());
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
}
