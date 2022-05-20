package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RefillAction;
import game.items.Bottle;

public class HealthFountain extends Ground implements Drinkable{

    private final int HEAL_POINT = 50;
    private final int FULL_CAPACITY = 10;
    private int currentVolume = FULL_CAPACITY;
    private int count;


    /**
     * Constructor.
     *
     */
    public HealthFountain() {
        super('H');
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (actor.getInventory().contains(Bottle.getInstance()) && direction == "") {
            actions.add(new RefillAction(this));
        }
        return actions;
    }

    @Override
    public void addPowerUp(Actor actor) {
        actor.heal(HEAL_POINT);
    }

    @Override
    public Ground getFountain() {
        return this;
    }

    @Override
    public int getCapacity() {
        return this.currentVolume;
    }

    @Override
    public void setVolume(int drink) {
        drink = this.currentVolume;
    }

    @Override
    public String fountainName() {
        return "Health water";
    }

    @Override
    public String printCapacity() {
        return "(" + currentVolume + "/" + FULL_CAPACITY + ")";
    }

    @Override
    public void tick(Location location) {
        if (count == 5) {
            setVolume(FULL_CAPACITY);
            count = 0;
        }
        if (currentVolume <= 0) {
            count++;
        }
    }

}
