package game.actors.enemies;

import game.utils.Status;

public class FlyingKoopa extends Koopa{
    public FlyingKoopa()
    {
        super();
        this.setDisplayChar('F');
        this.resetMaxHp(150);
        this.addCapability(Status.FLYING);
        this.addLine("Pam pam pam!");
    }

    @Override
    public String toString() {
        return "Flying Koopa";
    }
}
