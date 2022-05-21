package game.actors;

public interface IntrinsicWeaponBooster {
    public void increaseBaseAttack(int extraAttack);
    public int getAttackDamage();
    public void setBaseAttackDamage(int baseDamage);
    public String getVerb();

    public default void registerIntrinsicWeaponInstance() {
        InstrinsicWeaponBoostedManager.addInstance(this);
    }
}
