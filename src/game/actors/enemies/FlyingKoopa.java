package game.actors.enemies;

import game.utils.Status;

/**
 * FlyingKoopa Enemy class
 * @author Satya Jhaveri
 * @version 1.0
 * @see game.actors.enemies.Enemy
 */
public class FlyingKoopa extends Koopa{
    /**
     * Constuctor.
     */
    public FlyingKoopa()
    {
        super("Flying Koopa");
        this.setDisplayChar('F');
        this.resetMaxHp(150);
        this.addCapability(Status.FLYING);
        this.addLine("Pam pam pam!");
    }
}
