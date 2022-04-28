package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public abstract class BuyItemAction extends Action{
        int price;
        Item item;

    public BuyItemAction(int price, Item item) {
        this.item = item;
        this.price = price;
    }

    public String tradingAction(Actor actor){
            //check player has enough money
            boolean isEnough = ((Player) actor).getWallet().getBalance() >= this.price;

            if (isEnough){
                //player spends the money
                ((Player) actor).getWallet().spend(price);
                //player gets the item
                ((Player) actor).addItemToInventory(this.item);


                //prints the following line
                System.out.println(((Player)actor).getInventory());
                return "Purchase is successful";

            }
            else{
                return "You don't have enough coins!";
            }

        }


        @Override
        public String execute(Actor actor, GameMap map) {
            return tradingAction(actor);
        }

        @Override
        public String menuDescription(Actor actor) {

            return "Player buys a Super Mushroom from Toad";
        }
}
