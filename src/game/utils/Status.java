package game.utils;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 * @author FIT2099, modified by Satya Jhaveri, Klarissa Jutivannadevi, Sok Ear
 * @version 1.0
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    INVINCIBLE,  // use this for when the power star is consumed
    FERTILE,    // use this for when a ground is fertile
    FIREATTACK, // use this when an actor can do fireAttack
    HAS_RESET,  // use this for when an actor has the ability to reset the game
    FLYING,     // use this for when an actor is flying (it can walk over high ground)
    SPAWNED_FROM_PIPE, // use this for when an actor spawned from a pipe (exclusively used by WarpPipe)
    CANT_ENTER_FLOOR,   // use this when an actor cannot walk on floor tiles
    CANT_ENTER_LAVA,  // use this when an actor cannot walk on lava tiles
    DRINKABLE,  // use this for when a ground can geenrate drink actions
    CAN_DESTROY_KOOPA_SHELLS,  // use this when a weapon can destroy Koopa shells
    CAN_BUY_FROM_TOAD,  // use this when an actor can buy from toad
    CAN_FREE_PEACH,  // use this when an item gives teh ability to free peach
    IMMUNE_TO_FIRE, // use this when actor does not take damage from fire
}
