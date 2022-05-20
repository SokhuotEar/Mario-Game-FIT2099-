package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.positions.Drinkable;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Bottle Class that creates a Singleton to create 1 instance of bottle
 * @author Klarissa Jutivannadevi
 * @version 1.0
 */
public class Bottle extends Item implements Consumable{
    /**
     * Static attribute for Bottle singleton
     */
    private static Bottle existingBottle = null;
    /**
     * Stack to insert the content of the water refilled
     */
    private Stack<Drinkable> bottleDrink = new Stack<>();

    /***
     * Constructor of Bottle that will add an action to allow consume bottle.
     */
    private Bottle() {
        super("Bottle", 'b', false);
        super.addAction(new ConsumeAction(this));
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
    public Drinkable removeDrink() {
        return this.bottleDrink.pop();
    }

    /**
     * An override method from Consumable interface to access the Bottle
     * @return Bottle() Item instance in the existingBottle
     */
    @Override
    public Item getItem() {
        return this.existingBottle;
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
            fountainList += tempList.get(i).fountainName();
            this.bottleDrink.push(tempList.get(i));
            if (i != 0) {
                fountainList += ", ";
            }
        }
        fountainList += "]";
        return fountainList;
    }

    /**
     * Method used by the ConsumeAction when the actor decided to consume the drink.
     * @param actor the actor that will consume the consumable
     */
    @Override
    public void consume(Actor actor) {
        Bottle.getInstance().removeDrink().addPowerUp(actor);
    }

}
