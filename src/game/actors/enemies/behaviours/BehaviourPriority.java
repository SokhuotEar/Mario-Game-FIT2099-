package game.actors.enemies.behaviours;

/**
 * Enumeration to track priorities of Behaviours
 * @author FIT2099
 * @version 1.0
 */
public enum BehaviourPriority {
    // Order is the highest priority to the lowest priority in descending order:
    DRINK,  // is the highest priority
    ATTACK,
    FOLLOW,
    WANDERER,  // is the  lowest priority
}
