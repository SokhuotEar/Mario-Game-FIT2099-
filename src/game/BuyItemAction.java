package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class BuyItemAction extends Action{
        int price;
        Item item;

    public BuyItemAction(Buyable buyable) {
        this.item = buyable.getItem();
        this.price = buyable.getPrice();
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Get the player instance:
        Player player = Player.getInstance(actor);
        if (player == null)
        {
            return null;
        }
        //check player has enough money
        boolean isEnough = player.getWallet().getBalance() >= this.price;

        if (isEnough){
            //player spends the money
            player.getWallet().spend(price);
            //player gets the item
            player.addItemToInventory(this.item);

            return menuDescription(actor);
        }
        else{
            return "You don't have enough coins!";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys a " + item + " from Toad";
    }
}
