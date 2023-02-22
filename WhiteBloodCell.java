import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
 * Fun Fact: ColourCell are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @modifiedBy Jolene Yan Wen Lim, Soo Zhi Xin
 * @version 2022.02.28
 */

public class WhiteBloodCell extends Cell {
    
    /**
     * Create a new ColorCell.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public WhiteBloodCell(Field field, Location location) {
        super(field, location);
        setColor(Color.YELLOW);
        setAge(0);
    }

    /**
     * This is how the ColorCell decides if it's alive or not
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        incrementAge();
        switchColor();
        setNextState(false);
        if (isAlive()) {
             if (getAge() >= 30) {
                 setNextState(false);
             }
             else {
                 setNextState(true);
             }
        }
    }
        
    /**
     * This makes the cell change color between generations 
    */
    private void switchColor() {
        if (getAge() %2 == 0) {
            setColor(Color.ORANGE);
        }
        else {
            setColor(Color.YELLOW);
        }
    }
    
}
