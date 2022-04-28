

package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class BuySuperMAction extends BuyItemAction{

    public BuySuperMAction() {
        super(400, new SuperMushroom());
    }
}