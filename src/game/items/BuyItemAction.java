package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;

/**
 * Special Action that allows Actors to buy items.
 *
 * @author Sok Ear, Satya Jhaveri
 * @version 1.0
 */
public class BuyItemAction extends Action{

    /**
     * price of the object
     */
    int price;

    /**
     * Buyable item object
     */
    Buyable buyable;

    /**
     * Get the price of the buyable objec
     * @param buyable an object which is buyable
     */
    public BuyItemAction(Buyable buyable) {
        this.buyable = buyable;
        this.price = buyable.getPrice();
    }

    /**
     * Allow actor to purchase the object if balance in the wallet is enough
     * to purchase it and return the string based on whether actor can buy or not.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing the action that was exectued
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Get the player instance:
        Player player = Player.getInstance(actor);
        if (player == null) {
            return null;
        }
        //check player has enough money
        boolean isEnough = player.getWallet().getBalance() >= this.price;

        if (isEnough) {
            //player spends the money
            player.getWallet().spend(price);
            //player gets the item
            player.addItemToInventory(this.buyable.getItem());

            return menuDescription(actor);
        }
        else {
            return "You don't have enough coins!";
        }
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return string which shows how much are spent on which item
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + buyable + " ($" + price + ")";
    }
}
