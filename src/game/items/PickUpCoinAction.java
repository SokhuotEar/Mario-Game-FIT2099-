package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.items.Coin;

/**
 * Special Action that allows Actors to pick up coins.
 */
public class PickUpCoinAction extends Action {
    /**
     * coin object
     */
    private final Coin coin;

    /**
     * PickUpCoinAction constructor
     * @param coin the coin
     */
    public PickUpCoinAction(Coin coin) {
        this.coin = coin;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (Player.getInstance(actor) != null) {
            Player.getInstance(actor).getWallet().addBalance(coin.getValue());
            map.locationOf(actor).removeItem(coin);
            return menuDescription(actor);
        }
        return null;
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up coin ($" + coin.getValue() + ")";
    }
}
