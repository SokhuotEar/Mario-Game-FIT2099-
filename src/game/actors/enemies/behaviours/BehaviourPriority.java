package game.actors.enemies.behaviours;

/**
 * Enumeration to track priorities of Behaviours
 * @author FIT2099
 * @version 1.0
 */
public enum BehaviourPriority {
    // Order is the highest priority to the lowest priority in descending order:
    ATTACK,  // is the lowest (hence highest priority in tree map)
    FOLLOW,
    WANDERER   // is the highest (hence lowest priority in tree map)
}
