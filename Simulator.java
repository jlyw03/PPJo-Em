import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * A Life (Game of Life) simulator, first described by British mathematician
 * John Horton Conway in 1970.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06 (1)
 */

public class Simulator {
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 100;

    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;

    // The probability that a Mycoplasma is alive
    private static final double MYCOPLASMA_ALIVE_PROB = 0.02;
    
    // The probability that a ColourCell is alive 
    private static final double WHITEBLOODCELL_ALIVE_PROB = 0.05;
    
    // The probability that a MatureCell is alive
    private static final double TISSUECELL_ALIVE_PROB = 0.08;

    // List of cells in the field.
    private List<Cell> cells;

    // The current state of the field.
    private Field field;

    // The current generation of the simulation.
    private int generation;

    // A graphical view of the simulation.
    private SimulatorView view;

    /**
     * Execute simulation
     */
    public static void main(String[] args) {
      Simulator sim = new Simulator();
      sim.simulate(100);
    }

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        cells = new ArrayList<>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);

        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 generations).
     */
    public void runLongSimulation() {
        simulate(4000);
    }

    /**
     * Run the simulation from its current state for the given number of
     * generations.  Stop before the given number of generations if the
     * simulation ceases to be viable.
     * @param numGenerations The number of generations to run for.
     */
    public void simulate(int numGenerations) {
        for (int gen = 1; gen <= numGenerations && view.isViable(field); gen++) {
            simOneGeneration();
            delay(500);   // comment out to run simulation faster
        }
    }

    /**
     * Run the simulation from its current state for a single generation.
     * Iterate over the whole field updating the state of each life form.
     */
    public void simOneGeneration() {
        generation++;
         for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Location location = new Location(row, col);
                Cell cell = field.getObjectAt(row, col);
                if (cell != null) {
                        cell.act();
                        cell.updateState();
                } 
                else {
                    int numOfNeighbours = field.getLivingNeighbours(location).size();
                    determineCell(numOfNeighbours, location);
                }
            }
        }
            
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Cell cell = field.getObjectAt(row, col);
            }
        }

        view.showStatus(generation, field);
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        generation = 0;
        cells.clear();
        populate();

        // Show the starting state in the view.
        view.showStatus(generation, field);
    }

    /**
     * Randomly populate the field live/dead life forms
     */
    private void populate() {
      Random rand = Randomizer.getRandom();
      field.clear();
      for (int row = 0; row < field.getDepth(); row++) {
        for (int col = 0; col < field.getWidth(); col++) {
          Location location = new Location(row, col);
          if (rand.nextDouble() <= MYCOPLASMA_ALIVE_PROB) {
            Mycoplasma myco = new Mycoplasma(field, location);
            cells.add(myco);
          }
          else if (rand.nextDouble() <= WHITEBLOODCELL_ALIVE_PROB){
            WhiteBloodCell color = new WhiteBloodCell(field, location);
            cells.add(color);
          }
          else if (rand.nextDouble() <= TISSUECELL_ALIVE_PROB){
            TissueCell mature = new TissueCell(field, location);
            cells.add(mature);
          }
          else{
            Mycoplasma dead = new Mycoplasma(field, location);
            dead.setDead();
            cells.add(dead);
          }
        }
      }
    }

    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    private void delay(int millisec) {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }
    
     /**
     * Checks if conditions of the cell to determine which cell should come
     * alive in this location
     * @param Location location
     * @return Cell type
     */
    private void determineCell(int numOfNeighbours, Location location) { 
        if (numOfNeighbours == 3) {
            new Mycoplasma(field, location);
        } else if (numOfNeighbours == 5) {
            new WhiteBloodCell(field, location);
        } else if (numOfNeighbours == 4) {
            new TissueCell(field, location);
        }
    }

}
