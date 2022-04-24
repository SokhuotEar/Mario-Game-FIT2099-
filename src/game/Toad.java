package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Character.toLowerCase;

public class Toad extends Actor {

    /**
     * Constructor.
     *
     */
    public Toad() {
        super("Toad", 'O', 100);
    }

    /**
     * Returns a DoNothingAction, as Toad is a passive, friendly NPC
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return new DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
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
        ActionList actions =  new ActionList();

        // Adding the SpeakAction:
        if (otherActor.getDisplayChar() == 'm' || otherActor.getDisplayChar() == 'M') {  // Check actor is player TODO: fix this
            List<String> lines = new ArrayList<>();

            lines.add("The Princess is depending on you! You are our only hope.");
            lines.add("Being imprisoned in these walls can drive a fungus crazy :(");

            // TODO: Add wrench line if actor is not holding a wrench (wrench not implemented yet)
            if (true) {
                lines.add("You might need a wrench to smash Koopa's hard shells.");
            }

            // Add power star line only if actor does not have power star ability
            if (!otherActor.hasCapability(Status.INVINCIBLE)) {
                lines.add("You better get back to finding the Power Stars.");
            }
            Random rand = new Random();
            actions.add(new SpeakAction(lines.get(rand.nextInt(lines.size()))));
        }


        // TODO: Add the trading action:

        return actions;
    }

}
