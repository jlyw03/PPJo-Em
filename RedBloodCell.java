import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
 * Fun Fact: RedBloodCell are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @modifiedBy Jolene Yan Wen Lim, Soo Zhi Xin
 * @version 2022.02.28
 */

public class RedBloodCell extends Cell {
    private static final double INFECTED_PROBABILITY = 0.04;
    
    /**
     * Create a new RedBloodCell.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public RedBloodCell(Field field, Location location) {
        super(field, location);
        setColor(Color.RED);
        setAge(0);
    }

    /**
     * This is how the RedBloodCell decides if it's alive or not
     */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        incrementAge();
        setNextState(false);
        Random rand = Randomizer.getRandom();
        if (isAlive()) {
            /*if(!checkHealth()) {
                setColor(Color.MAGENTA);
                if (rand.nextDouble() <= INFECTED_PROBABILITY) {
                    isInfected();
                    setColor(Color.GREEN);
                }
            }
            if (checkHealth() && getAge() >= 10) {
                 setNextState(false);
            }
            else */if (neighbours.size() < 2) {
                 setNextState(false);
            }
            else if (neighbours.size() == 2 || neighbours.size() == 3) {
                 setNextState(true);
            }
            else if(neighbours.size() > 3) {
                 setNextState(false);
            }
        }
        else {
            if (neighbours.size() == 3) {
                setNextState(true);
            }
        }
    }
}


