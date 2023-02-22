import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
 * Fun Fact: MatureCell are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
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
            if (getAge() >= 80) {
                 setNextState(false);
            } 
            else {
                 setNextState(true);
            }
        }
    }
    
    private void switchColor() {
        if (getAge() >= 40) {
            setColor(Color.GRAY);
        }
    }
}
