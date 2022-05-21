package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import game.actions.DrinkFromBottleAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Bottle Class that creates a Singleton to create 1 instance of bottle
 * @author Klarissa Jutivannadevi, Satya Jhaveri
 * @version 1.0
 */
public class Bottle extends Item {
    /**
     * Static attribute for Bottle singleton
     */
    private static Bottle existingBottle = null;
    /**
     * Stack to insert the content of the water refilled
     */
    private final Stack<Drinkable> bottleDrink = new Stack<>();

    /**
     * Whether the bottle has been taken by the Player
     */
    private static boolean bottleTaken = false;

    /**
     * Constructor.
     */
    private Bottle() {
        super("Bottle", 'b', false);
    }

    /**
     * Static method used as a Singleton to allow only 1 instance of Bottle
     * @return existingBottle where it is the only instance of Bottle
     */
    public static Bottle getInstance(){
        if (existingBottle == null) {
            existingBottle = new Bottle();
        }
        return existingBottle;
    }

    /**
     * Method used when a TakeBottleAction is executed, sets bottleTaken attribute to true
     * @return the Bottle instance
     */
    public static Bottle takeBottle() {
        bottleTaken = true;
        return getInstance();
    }

    /**
     * Checks if the bottle has been taken by an actor yet
     * @return true if the bottle has been taken, false otherwise
     */
    public static boolean isBottleTaken() {
        return bottleTaken;
    }

    /**
     * Method used to access the private attribute bottleDrink
     * @return bottleDrink which is a stack containing all the refilled drink
     */
    public Stack<Drinkable> getDrink() {
        return this.bottleDrink;

    }

    /**
     * To add the drink to the stack of drinks
     * @param fountainDrink the refilled drink (either HealthFountain or PowerFountain)
     */
    public void addDrink(Drinkable fountainDrink) {
        this.bottleDrink.push(fountainDrink);
    }

    /**
     * Removing the drink that is consumed by the Player
     * @return the consumed (removed) drink
     */
    public Drinkable popDrink() {
        return this.bottleDrink.pop();
    }


    /**
     * To print the stack in the form of string to be displayed in ConsumeAction
     * This is used instead of returning the stack immediately since stack contains
     * a collection of the drinkable object, whereas, this is used to display the
     * name of the drinkable object in the stack.
     * @return the string version of the stack
     */
    public String printContent() {
        String fountainList = "[";
        ArrayList<Drinkable> tempList = new ArrayList<>();

        while (!this.bottleDrink.isEmpty()) {
            tempList.add(this.bottleDrink.pop());
        }

        for (int i = tempList.size() - 1; i > -1; i--) {
            fountainList += tempList.get(i).toString();
            this.bottleDrink.push(tempList.get(i));
            if (i != 0) {
                fountainList += ", ";
            }
        }
        fountainList += "]";
        return fountainList;
    }

    /**
     * Generates DrinkFromBottleActions if the bottle is not empty
     * @return DrinkFromBottleAction if the bottle is not empty
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        if (this.bottleDrink.size() > 0) {
            actions.add(new DrinkFromBottleAction(this));
        }
        return actions;
    }
}
