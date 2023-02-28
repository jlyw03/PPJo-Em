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

public class Oxygen extends Cell {
    private static final double COMING_ALIVE_PROBABILITY = 0.3; 
    private static final double SET_DEAD_PROBABILITY = 0.3;
    private static final double LIVE_ON_PROBABILITY = 1.0;
    
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
        double randDouble = rand.nextDouble();
        if (isAlive()) {
            incrementAge();
            if(randDouble <= SET_DEAD_PROBABILITY && getField().RBCpresent(neighbours)) {
                setNextState(false);
            }
            else if (randDouble <= LIVE_ON_PROBABILITY) {
                setNextState(true);
            }
        }
        else {
            if (randDouble <= COMING_ALIVE_PROBABILITY) {
                setNextState(true);
            }
            else {
                setNextState(false);
            }
        }
    }
}