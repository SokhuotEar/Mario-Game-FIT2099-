package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

public interface Drinkable {
    void addPowerUp(Actor actor);

    Ground getFountain();

    int getCapacity();

    void setVolume(int drink);

    String fountainName();
}
