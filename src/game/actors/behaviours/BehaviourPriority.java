package game.actors.behaviours;

/**
 * Enumeration to track priorities of Behaviours
 * @author FIT2099, modified Satya Jhaveri, Klarissa Jutivannadevi
 * @version 1.0
 */
public enum BehaviourPriority {
    // Order is the highest priority to the lowest priority in descending order:
    /**
     * Priority for DrinkBehaviour
     */
    DRINK,  // is the highest priority

    /**
     * Priority for AttackBehaviour
     */
    ATTACK,

    /**
     * Priority for FollowBehaviour
     */
    FOLLOW,

    /**
     * Priority for WanderBehaviour
     */
    WANDERER,  // is the  lowest priority
}
