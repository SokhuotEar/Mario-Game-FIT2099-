package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/** I made this only cuz I need a Koompa class for my part. It does not function **/


public class Koopa extends Enemy {

    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        addBehaviour(10, new WanderBehaviour());
    }          // to be changed

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for(Behaviour Behaviour : getBehaviours().values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
