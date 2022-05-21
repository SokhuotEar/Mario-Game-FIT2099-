package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DrinkAction;
import game.actions.RefillAction;
import game.items.Bottle;
import game.items.Drinkable;
import game.utils.Status;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that different types of fountains will extend
 * @author Satya Jhaveri, Klarissa Jutivannadevi
 * @version 1.0
 */
public abstract class Fountain extends Ground {
    /**
     * Static list of all fountains
     */
    private static final List<Fountain> fountainList = new ArrayList<>();

    /**
     * The maximum capacity of drinks that a fountain can supply
     */
    private static final int maxCapacity = 10;

    /**
     * The fountains current capacity
     */
    private int capacity;

    /**
     * A count for the number of turns the fountain has been empty for
     */
    private int emptyCount;

    /**
     * The drink that the fountain can provide
     */
    private final Drinkable drink;


    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of Fountain
     * @param drinkType The Drinkable item that this fountain will supply
     */
    public Fountain(char displayChar, Drinkable drinkType) {
        super(displayChar);
        this.capacity = maxCapacity;
        this.emptyCount = 0;
        this.drink = drinkType;
        fountainList.add(this);
        this.addCapability(Status.DRINKABLE);
    }

    // gets capacity of the fountain

    /**
     * Gets the fountains capacity
     * @return The capacity of the fountain
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Gets the fountain's maximum capacity
     * @return The maximum capacity of the fountain
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Gets a drink from the Fountain
     * @return A Drinkable instance
     */
    public Drinkable getDrink() {
        this.capacity--;
        return this.drink;
    }

    /**
     * Gets an instance of the Fountain
     * @param ground A ground item to get the Fountain instance of
     * @return A fountain
     */
    public static Fountain getInstance(Ground ground) {
        if (fountainList.contains(ground)) {
            for (Fountain f : fountainList) {
                if (f == ground) {
                    return f;
                }
            }
        }
        return null;
    }

    /**
     * Gets the name of the drink
     * @return The name of the drink that this fountain provides
     */
    public String getDrinkName() {
        return this.drink.toString();
    }


    /**
     * Returns drink action and if the actor has a bottle, a fill bottle action
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);

        // Let the actor drink if they
        if (this.capacity > 0 && location.getActor() == actor) {
            actions.add(new DrinkAction(this));

            if (actor.getInventory().contains(Bottle.getInstance())) {
                actions.add(new RefillAction(this));
            }
        }

        return actions;
    }

    /**
     * Method called once every turn
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        if (this.capacity == 0) {
            this.emptyCount++;
            // If the fountain has been empty for 5 turns, refill it to maximum
            if (this.emptyCount == 5) {
                this.emptyCount = 0;
                this.capacity = maxCapacity;
            }
        }
    }

    abstract public String toString();
}
