package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.behaviours.Behaviour;
import game.actors.behaviours.SpeakBehaviour;

import java.util.*;

/**
 * This is a class that all non playable actors will extend from. It encapsulates the speaking that all NPCs will do.
 * @author Satya Jhaveri
 * @version 1.0
 */
public abstract class NPC extends Actor implements IntrinsicWeaponBooster{
    /**
     * A special SpeakBehaviour attribute for implementing speaking
     */
    private SpeakBehaviour speakBehaviour;

    /**
     * An ordered collection of behaviours and their priorities
     */
    private Map<Integer, Behaviour> behaviours; // priority, behaviour

    /**
     * The base attack damage that the NPC can inflict
     */
    private int baseAttackDamage;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public NPC(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.speakBehaviour = null;
        this.behaviours = new TreeMap<>();
        this.baseAttackDamage = 0;
        this.registerIntrinsicWeaponInstance();
    }

    /**
     * Sets the lines for the NPC's speaking
     * @param lines A list of string that the NPC can say
     */
    public void setLines(List<String> lines)
    {
        this.speakBehaviour = new SpeakBehaviour(lines);
    }

    /**
     * Adds a new line that the NPC can say
     * @param line a new line that the NPC can say
     */
    public void addLine(String line)
    {
        this.speakBehaviour.addLine(line);
    }

    /**
     * Removes all lines for the NPC speaking
     */
    public void removeLines() {
        this.speakBehaviour = null;
    }

    /**
     * Adds a behaviour to the NPC
     * @param key The priority of the Behaviour being added
     * @param behaviour The behaviour to add
     */
    public void addBehaviour(Integer key, Behaviour behaviour)
    {
        this.behaviours.put(key, behaviour);
    }

    /**
     * Removes a behaviour
     * @param key the priority of the behaviour to remove
     */
    public void removeBehaviour(Integer key)
    {
        this.behaviours.remove(key);
    }

    /**
     * Removes all behaviours
     */
    public void clearBehaviours()
    {
        this.behaviours = new TreeMap<>();
    }

    /**
     * Gets a list of all Behaviours
     * @return a list of the NPCs Behaviours
     */
    public List<Behaviour> getBehaviours()
    {
        return new ArrayList<>(this.behaviours.values());
    }



    // Allows the NPC to speak:

    /**
     * Allows the NPC to speak
     * @param display a display to print the line to
     * @param actor the actor that is speaking
     * @param map the map that the actor is on
     */
    public void speak(Display display, Actor actor, GameMap map)
    {
        if (this.speakBehaviour != null)
        {
            Action speakAction = speakBehaviour.getAction(actor, map);

            // If the getAction method doesn't return null, print its execution to the display:
            if (speakAction != null)
            {
                display.println( speakAction.execute(actor, map));
            }
        }
    }

    /**
     * Gets the current attack damage
     * @return the current attack damage
     */
    @Override
    public int getAttackDamage() {
        return this.baseAttackDamage;
    }

    /**
     * Creates and returns an intrinsic weapon.
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(this.getAttackDamage(), this.getVerb());
    }

    /**
     * Increases the base attack damage
     * @param extraAttack the amount of attack damage to increase by
     */
    @Override
    public void increaseBaseAttack(int extraAttack) {
        this.baseAttackDamage += extraAttack;
    }

    /**
     * Sets the base attack damage
     * @param baseDamage the new base attack damage
     */
    @Override
    public void setBaseAttackDamage(int baseDamage) {
        this.baseAttackDamage = baseDamage;
    }
}
