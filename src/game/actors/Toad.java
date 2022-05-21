package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyItemAction;
import game.actions.SpeakAction;
import game.actions.TakeBottleAction;
import game.items.*;
import game.utils.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Toad class.
 * @author Satya Jhaveri
 * @version 1.0
 */
public class Toad extends NPC {

    /**
     * Constructor.
     */
    public Toad() {
        super("Toad", 'O', 100);
        List<String> lines = new ArrayList<>();
        lines.add("You better get back to finding the Power Stars.");
        lines.add("The Princess is depending on you! You are our only hope.");
        lines.add("Being imprisoned in these walls can drive a fungus crazy :(");
        lines.add("You might need a wrench to smash Koopa's hard shells.");
        this.setLines(lines);
    }

    /**
     * Returns a DoNothingAction, as Toad is a passive, friendly NPC
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
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
        ActionList actions =  new ActionList();

        // Adding actions only if the other actor can buy from Toad:
        if (Player.isInstance(otherActor)) {
            List<String> lines = new ArrayList<>();

            lines.add("The Princess is depending on you! You are our only hope.");
            lines.add("Being imprisoned in these walls can drive a fungus crazy :(");

            // Add wrench line if actor is not holding a wrench
            if (!otherActor.hasCapability(Status.CAN_DESTROY_KOOPA_SHELLS)) {
                lines.add("You might need a wrench to smash Koopa's hard shells.");
            }

            // Add power star line only if actor does not have power star ability
            if (!otherActor.hasCapability(Status.INVINCIBLE)) {
                lines.add("You better get back to finding the Power Stars.");
            }

            Random rand = new Random();
            actions.add(new SpeakAction(lines.get(rand.nextInt(lines.size()))));

            //add the trading actions:
            actions.add(new BuyItemAction(new SuperMushroom()));
            actions.add(new BuyItemAction(new Wrench()));
            actions.add(new BuyItemAction(new PowerStar()));


            if (!Bottle.isBottleTaken()) {
                actions.add(new TakeBottleAction(this));
            }
        }
        return actions;
    }

    /**
     * Gets the verb of the attack
     * @return the verb of attack
     */
    @Override
    public String getVerb() {
        return "does nothing to";
    }
}
