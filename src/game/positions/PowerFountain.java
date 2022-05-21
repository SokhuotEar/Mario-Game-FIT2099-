package game.positions;

import game.items.PowerWater;

public class PowerFountain extends Fountain {
    /**
     * Constructor.
     *
     */
    public PowerFountain() {
        super('A', new PowerWater());
    }

    @Override
    public String toString() {
        return "Power Fountain";
    }
}
