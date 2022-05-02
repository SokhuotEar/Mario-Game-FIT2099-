package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 * @author FIT2099, modified by Satya Jhaveri
 * @version 1.0
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    INVINCIBLE,  // use this for when the power star is consumed
    FERTILE,    // use this for when a ground is fertile
    HAS_RESET,  // use this for when an actor has the ability to reset the game
}
