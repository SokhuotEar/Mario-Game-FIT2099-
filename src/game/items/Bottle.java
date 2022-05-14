package game.items;

import edu.monash.fit2099.engine.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Bottle extends Item {

    private static Bottle existingBottle = null;
    private Stack<String> bottleDrink = new Stack<>();
    private boolean containBottle = false;

    /***
     * Constructor.
     */
    public Bottle() {
        super("Bottle", 'b', false);
    }

    public static Bottle getInstance(){
        if (existingBottle == null) {
            existingBottle = new Bottle();

        }
        return existingBottle;
    }

    public Stack<String> printDrink() {
        return bottleDrink;

    }

    public void addDrink(String fountainDrink) {
        bottleDrink.push(fountainDrink);
    }

    public boolean getBottleStatus() {
        return containBottle;
    }

    public void setBottleStatus(boolean status) {
        this.containBottle = status;
    }
}
