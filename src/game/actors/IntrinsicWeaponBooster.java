package game.actors;

/**
 * Interface that allows Actors to have their base intrinsic weapon damage boosted
 * @author Satya Jhaveri
 * @version 1.0
 */
public interface IntrinsicWeaponBooster {
    /**
     * Increases the base attack damage
     * @param extraAttack the amount of attack damage to increase by
     */
    void increaseBaseAttack(int extraAttack);

    /**
     * Gets the current attack damage
     * @return the current attack damage
     */
    int getAttackDamage();

    /**
     * Sets the base attack damage
     * @param baseDamage the new base attack damage
     */
    void setBaseAttackDamage(int baseDamage);

    /**
     * Gets the verb of the attack
     * @return the verb of attack
     */
    String getVerb();

    /**
     * Registers in instance of this interface with the IntrinsicWeaponBoostedManager manager
     */
    default void registerIntrinsicWeaponInstance() {
        IntrinsicWeaponBoostedManager.addInstance(this);
    }
}
