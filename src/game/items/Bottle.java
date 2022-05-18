package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actors.Player;
import game.positions.Drinkable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Bottle extends Item implements Consumable{
    private static boolean flag = false;
    private static Bottle existingBottle = null;
    private Stack<Drinkable> bottleDrink = new Stack<>();
    private static ActionList actions = new ActionList();
    private Item item;

    /***
     * Constructor.
     */
    private Bottle() {
        super("Bottle", 'b', false);
        super.addAction(new ConsumeAction(this));
    }

    public static Bottle getInstance(){
        if (existingBottle == null) {
            flag = true;
            existingBottle = new Bottle();
        }
        return existingBottle;
    }



    public Stack<Drinkable> getDrink() {
        return bottleDrink;

    }

    public void addDrink(Drinkable fountainDrink) {
        bottleDrink.push(fountainDrink);
    }

    public Drinkable removeDrink() {
        return bottleDrink.pop();
    }

    @Override
    public Item getItem() {
        return Bottle.getInstance();
    }

    public String printContent(Stack<Drinkable> drinkableStack) {
        String fountainList = "";
        Drinkable drinks = drinkableStack.peek();
        drinkableStack.pop();
        printContent(drinkableStack);
        fountainList = drinks.fountainName() + ", ";
        drinkableStack.push(drinks);
        return fountainList;
    }

    @Override
    public void consume(Actor actor) {
        Bottle.getInstance().removeDrink().addPowerUp(actor);

    }
}
