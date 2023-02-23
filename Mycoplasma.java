import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @modifiedBy Jolene Yan Wen Lim, Soo Zhi Xin
 * @version 2022.02.28
 */

public class Mycoplasma extends Cell {
    private static final double INFECTED_PROBABILITY = 0.04;
    private static final double EXPLOSION_PROBABILITY = 0.02;
    /**
     * Create a new Mycoplasma.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Mycoplasma(Field field, Location location) {
        super(field, location);
        setColor(Color.MAGENTA);
        setAge(0);
        
    }

    /**
     * This is how the Mycoplasma decides if it's alive or not
     */
    public void act() {
         List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
         incrementAge();
         setNextState(false);
         Random rand = Randomizer.getRandom();
         if (rand.nextDouble() <= INFECTED_PROBABILITY) {
             isInfected();
             setColor(Color.GREEN);
             /*if (rand.nextDouble() <= EXPLOSION_PROBABILITY) {
                 for(Cell neighbour: neighbours) {
                 neighbour.setNextState(false);
             }*/
             /*else {
                 if neohgboruign cell alive{
                     
                 }neighbours get infected
             }*/
             }
        else {
             setColor(Color.MAGENTA);
             }
        if (isAlive()) {
             if (neighbours.size() < 2) {
                 setNextState(false);
             }
             else if (neighbours.size() == 2 || neighbours.size() == 3) {
                 setNextState(true);
             }
             else if(neighbours.size() > 3) {
                 setNextState(false);
             }
         } else {
             if (neighbours.size() == 3) {
                 setNextState(true);
             }
         }
    }
}

