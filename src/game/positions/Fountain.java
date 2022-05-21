package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DrinkAction;
import game.actions.RefillAction;
import game.items.Bottle;
import game.items.Drinkable;

import java.util.ArrayList;
import java.util.List;

public abstract class Fountain extends Ground {
    private static List<Fountain> fountainList = new ArrayList<>();
    private static int maxCapacity = 10;
    private int capacity;
    private int emptyCount;  // nubmer of turns it's been empty for
    private Drinkable drink;


    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of Fountain
     */
    public Fountain(char displayChar, Drinkable drinkType) {
        super(displayChar);
        this.capacity = maxCapacity;
        this.emptyCount = 0;
        this.drink = drinkType;
        fountainList.add(this);
    }

    // gets capacity of the fountain
    public int getCapacity() {
        return this.capacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Drinkable getDrink() {
        this.capacity--;
        return this.drink;
    }

    public static boolean isInstance(Ground ground) {
        return fountainList.contains(ground);
    }

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
     *
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
