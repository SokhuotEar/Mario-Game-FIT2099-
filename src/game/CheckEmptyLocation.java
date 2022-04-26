package game;

import edu.monash.fit2099.engine.positions.Location;

public class CheckEmptyLocation {

    /*public Boolean leftEmpty(Location location) {
        if (location.x() - 1 < location.map().getXRange().min()) {
            if (!(location.map().at(location.x() + 1, location.y()).getGround() instanceof Dirt)) {
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    public Boolean rightEmpty(Location location) {
        if (location.x() + 1 < location.map().getXRange().max()) {
            if (location.map().at(location.x() - 1, location.y()).getGround() instanceof Dirt) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    public Boolean upEmpty(Location location) {
        if (location.y()-1 < location.map().getYRange().max()) {
            if (location.map().at(location.x() + 1, location.y()).getGround() instanceof Dirt) {
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    public Boolean downEmpty(Location location) {
        if (location.y()+1 < location.map().getYRange().max()) {
            if (location.map().at(location.x(), location.y() + 1).getGround() instanceof Dirt) {
                return true;
            } else
                return false;
        }
        else
            return false;
    } */

    public Boolean leftEmpty(Location location) {
        if (location.x() - 1 >= location.map().getXRange().min()) {
            return true;
        }
        else
            return false;
    }

    public Boolean rightEmpty(Location location) {
        if (location.x() + 1 <= location.map().getXRange().max()) {
            return true;
        } else
            return false;
    }

    public Boolean upEmpty(Location location) {
        if (location.y()-1 >= location.map().getYRange().min()) {
            return  true;
        }
        else
            return false;
    }

    public Boolean downEmpty(Location location) {
        if (location.y()+1 <= location.map().getYRange().max()) {
            return true;
        }
        else
            return false;
    }




}
