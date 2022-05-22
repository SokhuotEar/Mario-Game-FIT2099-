package game.utils;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 * @author FIT2099, modified by Satya Jhaveri, Klarissa Jutivannadevi, Sok Ear
 * @version 1.0
 */
public enum Status {
    /**
     * use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
     */
    HOSTILE_TO_ENEMY,

    /**
     * use this status to tell that current instance has "grown".
     */
    TALL,

    /**
     * use this for when the power star is consumed
     */
    INVINCIBLE,

    /**
     * use this for when a ground is fertile
     */
    FERTILE,

    /**
     * use this when an actor can do fireAttack
     */
    FIREATTACK,

    /**
     * use this for when an actor has the ability to reset the game
     */
    HAS_RESET,

    /**
     * use this for when an actor is flying (it can walk over high ground)
     */
    FLYING,

    /**
     * use this for when an actor spawned from a pipe (exclusively used by WarpPipe)
     */
    SPAWNED_FROM_PIPE,

    /**
     * use this when an actor cannot walk on floor tiles
     */
    CANT_ENTER_FLOOR,

    /**
     * use this when an actor cannot walk on lava tiles
     */
    CANT_ENTER_LAVA,

    /**
     * use this for when a ground can generate drink actions
     */
    DRINKABLE,

    /**
     * use this when a weapon can destroy Koopa shells
     */
    CAN_DESTROY_KOOPA_SHELLS,

    /**
     * use this when an actor can buy from toad
     */
    CAN_BUY_FROM_TOAD,

    /**
     * use this when an item gives the ability to free peach
     */
    CAN_FREE_PEACH,

    /**
     * use this when actor does not take damage from fire
     */
    IMMUNE_TO_FIRE,
}
