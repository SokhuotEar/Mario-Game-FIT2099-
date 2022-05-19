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
    private static Bottle existingBottle = null;
    private Stack<Drinkable> bottleDrink = new Stack<>();

    /***
     * Constructor.
     */
    private Bottle() {
        super("Bottle", 'b', false);
        super.addAction(new ConsumeAction(this));
    }

    public static Bottle getInstance(){
        if (existingBottle == null) {
            existingBottle = new Bottle();
        }
        return existingBottle;
    }



    public Stack<Drinkable> getDrink() {
        return this.bottleDrink;

    }

    public void addDrink(Drinkable fountainDrink) {
        this.bottleDrink.push(fountainDrink);
    }

    public Drinkable removeDrink() {
        return this.bottleDrink.pop();
    }

    @Override
    public Item getItem() {
        return this.existingBottle;
    }

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

    @Override
    public void consume(Actor actor) {
        Bottle.getInstance().removeDrink().addPowerUp(actor);
    }
}
