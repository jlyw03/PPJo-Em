import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @modifiedBy Jolene Yan Wen Lim, Soo Zhi Xin
 * @version 2022.02.28
 */

public class Oxygen extends Cell {
    /**
     * Create a new Oxygen.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Oxygen(Field field, Location location) {
        super(field, location);
        setColor(Color.CYAN);
    }

    /**
     * This is how the Oxygen decides if it's alive or not
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);
        Random rand = Randomizer.getRandom();
        if (isAlive()) {
            incrementAge();
            if(getField().RBCpresent(neighbours)) {
                setNextState(false);
                resetAge();
            }
            else {
                setNextState(true);
            }
        }
        else {
            if (neighbours.size() == 4) {
                setNextState(true);
            }
        }
    }
}


