package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class dedicated for the functionality of SuperMushroom
 *
 * @author Klarissa Jutivannadevi
 */

public class SuperMushroom extends Item implements Consumable {
    private boolean portable;
    private int mushroomHP;
    private int jumpRate;

    /***
     * Constructor.
     *
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        this.portable = true;
    }

    public int increaseHP(){
        this.mushroomHP = 50;
        return this.mushroomHP; 
    }
    
    public void changeBig(Actor player) {
        player.addCapability(Status.TALL);
    }

    @Override
    public void checkLifetime() {

    }

    /*
    public void getAllowableAction() {
        ConsumeAction consumeAction = new ConsumeAction();
        consumeAction.magicalItemConsumed(this);
        addAction(consumeAction);
    }

     */

    /*
    public ConsumeAction getConsumeAction(Actor actor) {
        if(portable)
            return new ConsumeAction(this);
        return null;
    }

     */

    /*
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ActionList().getUnmodifiableActionList();
        System.out.println("udah ada di sini");
        new ActionList().add(new ConsumeAction(this));
        return actions;

    }

     */

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        PickUpItemAction action = new PickUpItemAction(this);
        System.out.println("ini getPickupaction dari supermushroom");
        if(portable) {
            new ActionList().add(new ConsumeAction(this));
            return action;
        }
        return null;
    }

    /*

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if(portable)
            return new PickUpItemAction(this);

        PickUpItemAction actions = new PickUpItemAction(this);
        ActionList actions1 = new ActionList();

        actions1.add(new ConsumeAction((this)));

        return null;
    }

     */

    @Override
    public DropItemAction getDropAction(Actor actor) {
        getConsumeAction(actor);
        return null;
    }


    public ConsumeAction getConsumeAction(Actor actor) {
        if(portable)
            return new ConsumeAction(this);
        return null;
    }






}
