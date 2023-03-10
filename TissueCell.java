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

public class TissueCell extends Cell {
    
    /**
     * Create a new MatureCell.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public TissueCell(Field field, Location location) {
        super(field, location);
        setColor(Color.BLUE);
        setAge(0);
    }

    /**
     * This is how the MatureCell decides if it's alive or not
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);
        incrementAge();
        switchColor();
        if (isAlive()) {
            if (neighbours.size() == 4 && neighbours.size() == 5 && getAge() <= 80) {
                setNextState(true);
            } 
            else {
                setNextState(false);
            }
        }
    }
    
    private void switchColor() {
        if (getAge() >= 40) {
            setColor(Color.GRAY);
        }
    }
}
