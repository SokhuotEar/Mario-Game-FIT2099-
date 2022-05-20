package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.items.Consumable;
import game.positions.Drinkable;

public class RefillAction extends Action {

    Drinkable drinkable;

    public RefillAction(Drinkable drinkableFountain) {
        this.drinkable = drinkableFountain;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Bottle.getInstance().addDrink(this.drinkable);
        drinkable.setVolume(drinkable.getCapacity() - 2);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " refill " + drinkable.fountainName() + " " + drinkable.printCapacity();
    }

}
