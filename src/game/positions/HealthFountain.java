package game.positions;

import game.items.HealthWater;

/**
 * Health Fountain Class
 * @author Satya Jhaveri, Klarissa Jutivannadevi
 * @version 1.0
 */
public class HealthFountain extends Fountain {
    /**
     * Constructor.
     */
    public HealthFountain() {
        super('H', new HealthWater());
    }

    @Override
    public String toString() {
        return "Health Fountain";
    }
}
