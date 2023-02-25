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

public class RedBloodCell extends Cell {
    private int OxygenLevel = 5;
    
    /**
     * Create a new RedBloodCell.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public RedBloodCell(Field field, Location location) {
        super(field, location);
        setColor(Color.RED);
    }

    /**
     * This is how the RedBloodCell decides if it's alive or not
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);
        if (isAlive()) {
            incrementAge();
            if (getField().OxygenPresent(neighbours)){
                OxygenLevel++;
            } else {
                OxygenLevel--;
            }
            
            if (OxygenLevel > 0 && getAge() < 20) {
                setNextState(true);
            }
            else {
                setNextState(false);
                resetAge();
            }
        }
        else {
            if (neighbours.size() == 3) {
                setNextState(true);
            }
        }
    }
}


