import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Tile Class - Places where a user can place their towers.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Tile extends Actor
{
    //Class variables and objects
    
    //Tile index (the number in association with its location)
    private int tileIndex;
    
    /**
     * Constructor for Tile Class, sets initial values.
     * 
     * @param tileIndex         Index of the tile
     */
    public Tile(int tileIndex)
    {
        //Set index of the tile
        this.tileIndex = tileIndex;
        
        //Sets the transparency of its image
        getImage().setTransparency(50);
    }
    
    /**
     * Called every act, checks if the user has pressed the tile.
     */
    public void act()
    {
        //If user presses down on the tile, add a tower at the index
        if (Greenfoot.mousePressed(this)) {
            ((Level)getWorld()).addTower(tileIndex);
        }
    }
    
    /**
     * Sets the transparency of the tile.
     * 
     * @param transparency      New transparency of the tile.
     */
    public void setTransparency(int transparency)
    {
        getImage().setTransparency(transparency);
    }
}
