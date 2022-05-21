package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.WinGameAction;
import game.items.Key;

import java.util.ArrayList;
import java.util.List;

public class PrincessPeach extends NPC{


    /**
     * Constructor.
     *
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 100);
        List<String> lines = new ArrayList<>();
        lines.add("Dear Mario, I'll be waiting for you...");
        lines.add("Never gonna give you up!");
        lines.add("Release me, or I will kick you!");
        this.setLines(lines);
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
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        // TODO: if the other actor has a key, let them unlock peach and end the game:
        if (otherActor.getInventory().contains(Key.getInstance())) {
            actions.add(new WinGameAction());
        }
        return actions;
    }

    @Override
    public String getVerb() {
        return "does nothing to";
    }
}
