import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Another form of life.
 * 
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @modifiedBy Jolene Yan Wen Lim, Soo Zhi Xin
 * @version 2022.02.28
 */
 
public class TissueCell extends Cell {
    private static final double RECOVER_PROBABILITY = 0.2;
    
    /**
     * Create a new TissueCell.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
     
    public TissueCell(Field field, Location location) {
        super(field, location);
        setColor(Color.BLUE);
    }

    /**
     * This is how the TissueCell decides if it's alive or not
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        setNextState(false);
        Random rand = Randomizer.getRandom();
        if (isAlive()) {
            incrementAge();
            switchColor();
            if(checkHealth()) {
                if (getAge() < 20) {
                    setNextState(true);
                } 
                else if (getField().WBCpresent(neighbours) && rand.nextDouble() <= RECOVER_PROBABILITY) {
                    recoverInfected();
                    setColor(Color.BLUE);
                    setNextState(true);
                }
                else {
                    setNextState(false);
                }
            }
            else {
                if (getField().infectedPresent(neighbours)) {
                    isInfected();
                    setColor(Color.GREEN);
                    if (getAge() < 20) {
                        setNextState(true);
                    } else {
                        setNextState(false);
                    }
                }
            }
        }
        else {
            if (neighbours.size() == 4) {
                 setNextState(true);
            }
        }
    }
    
    /**
     * Checks the age of this cell and set its color to gray if it is aged 40 or over
     */
    private void switchColor() {
        if (getAge() >= 40) {
            setColor(Color.DARK_GRAY);
        }
    }
}
