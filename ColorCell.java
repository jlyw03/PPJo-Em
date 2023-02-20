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

public class ColorCell extends Cell {

    /**
     * Create a new ColorCell.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public ColorCell(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
     * This is how the ColorCell decides if it's alive or not
     */
    public void act(int generation) {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        switchColor(generation);
        setNextState(false);
         if (isAlive()) {
             if (neighbours.size() < 2) {
                 setNextState(false);
             }
             else if (neighbours.size() > 3) {
                 setNextState(true);
             }
         } else{
            if (neighbours.size() == 3) {
                 setNextState(true);
             }
        }
    }
        
    /**
     * This makes the cell change color between generations 
    */
    public void switchColor(int Counter) 
    {
        if (Counter %2 == 0) {
            setColor(Color.GREEN);
        }
        else
        {
            setColor(Color.RED);
        }
        }
    
}
