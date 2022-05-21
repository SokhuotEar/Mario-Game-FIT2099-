package game.positions;

import game.items.PowerWater;

/**
 * Power Fountain Class
 * @author Satya Jhaveri, Klarissa Jutivannadevi
 * @version 1.0
 */
public class PowerFountain extends Fountain {
    /**
     * Constructor.
     */
    public PowerFountain() {
        super('A', new PowerWater());
    }

    @Override
    public String toString() {
        return "Power Fountain";
    }
}
