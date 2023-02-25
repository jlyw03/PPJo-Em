import java.awt.Color;
import java.util.List;

/**
 * A class representing the shared characteristics of all forms of life
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06 (1)
 */

public abstract class Cell {
    // Whether the cell is alive or not.
    private boolean alive;

    // Whether the cell will be alive in the next generation.
    private boolean nextAlive;

    // The cell's field.
    private Field field;

    // The cell's position in the field.
    private Location location;

    // The cell's color
    private Color color = Color.white;
    
    //The cell's age 
    private int age = 0;
    
    //The cell's disease state
    private boolean diseased;


    /**
     * Create a new cell at location in field.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Cell(Field field, Location location) {
        alive = true;
        diseased = false;
        nextAlive = false;
        this.field = field;
        setLocation(location);
    }

    /**
     * Make this cell act - that is: the cell decides it's status in the
     * next generation.
     */
    abstract public void act();

    /**
     * Check whether the cell is alive or not.
     * @return true if the cell is still alive.
     */
    protected boolean isAlive() {
        return alive;
    }

    /**
     * Indicate that the cell is no longer alive.
     */
    protected void setDead() {
        alive = false;
    }

    /**
     * Indicate that the cell will be alive or dead in the next generation.
     */
    public void setNextState(boolean value) {
      nextAlive = value;
    }

    /**
     * Changes the state of the cell
     */
    public void updateState() {
      alive = nextAlive;
    }

    /**
     * Changes the color of the cell
     */
    public void setColor(Color col) {
      color = col;
    }

    /**
     * Returns the cell's color
     */
    public Color getColor() {
      return color;
    }

    /**
     * Return the cell's location.
     * @return The cell's location.
     */
    protected Location getLocation() {
        return location;
    }

    /**
     * Place the cell at the new location in the given field.
     * @param location The cell's location.
     */
    protected void setLocation(Location location) {
        this.location = location;
        field.place(this, location);
    }

    /**
     * Return the cell's field.
     * @return The cell's field.
     */
    protected Field getField() {
        return field;
    }
    
    /**
     * Return the cell's age 
     * @return The cell's age
     */
    protected int getAge()
    {
        return age; 
    }
    
    /**
     * Sets the cell's age to 0
     * 
     * @param int Age
     */
    protected void resetAge()
    {
        age = 0; 
    }
    
    /**
     * Increases the cell's age by 1
     */
    protected void incrementAge()
    {
        age++; 
    }
    
    /**
     * Makes cells infected
     */
    protected void isInfected()
    {
        diseased = true;
    }
    
    /**
     * Return's cell's infected state
     */
    protected boolean checkHealth()
    {
        return diseased;
    } 
}
