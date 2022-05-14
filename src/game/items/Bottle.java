package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.positions.Drinkable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Bottle extends Item implements Consumable{
    private static boolean flag = false;
    //private static Bottle existingBottle = null;
    private static Bottle existingBottle = null;
    private Stack<Drinkable> bottleDrink = new Stack<>();
    private static ActionList actions = new ActionList();
    private Item item;

    /***
     * Constructor.
     */
    private Bottle() {
        super("Bottle", 'b', false);
        System.out.println("ex bottle: " + existingBottle);
        System.out.println("size: " + bottleDrink.size());

        super.addAction(new ConsumeAction(existingBottle));
//        if (flag == true) {
//            System.out.println("");
//            super.addAction(new ConsumeAction((existingBottle)));
//            flag = false;
//        }
    }

    Bottle bottl1;
    Bottle bottl2;

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
        return this;
    }

    @Override
    public void consume(Actor actor) {
        Bottle.getInstance().removeDrink().addPowerUp(actor);

    }
}
