package game.reset;

import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that does soft-reset on the instances.
 * @author FIT2099, modified by Klarissa Jutivannadevi
 * @version 1.0
 */
public class ResetManager {
    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private List<Resettable> resettableList;

    /**
     * A singleton reset manager instance
     */
    private static ResetManager instance;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Constructor
     */
    private ResetManager(){
        resettableList = new ArrayList<>();
    }

    /**
     * Reset the game by traversing through all the list
     * By doing this way, it will avoid using `instanceof` all over the place.
     */
    public void run(GameMap map){
        List<Resettable> newResettableList = new ArrayList<>();
        for (int i = 0; i < resettableList.size(); i++) {
            Resettable resettable = resettableList.get(i);
            if (resettable != null) {
                resettable.resetInstance(map);
            }

            // If the resettable is still not null after resetting it, keep it:
            if (resettableList.get(i) != null) {
                newResettableList.add(resettableList.get(i));
            }
        }

        this.resettableList = newResettableList;
    }

    /**
     * Add the Resettable instance to the list
     */
    public void appendResetInstance(Resettable reset){
        resettableList.add(reset);
    }


    /**
     * Remove a Resettable instance from the list
     * @param resettable resettable object
     */
    public void cleanUp(Resettable resettable){
        if (resettableList.contains(resettable)) {
            resettableList.set( resettableList.indexOf(resettable), null);
        }
    }
}
