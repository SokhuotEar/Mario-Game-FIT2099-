package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class ResetAction extends Action {
    ResetAction() {
        System.out.println("resetactions");
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Reset Game";
    }

    @Override
    public String hotkey() {
        return "r";
    }
}
