import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Range Class - Range that displays how far the tower can shoot.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Range extends Actor
{
    //Instance variables and objects
    
    //Image of the range
    private GreenfootImage range;
    
    /**
     * Constructor for the range, draws and sets the image.
     * 
     * @param radius            Radius of the range
     */
    public Range(int radius)
    {
        //Create the image
        range = new GreenfootImage(radius * 2, radius * 2);
        
        //Fill it
        range.setColor(Color.BLACK);
        range.fillOval(0, 0, radius * 2, radius * 2);
        
        //Set transparency to 0, so it cannot be seen
        range.setTransparency(0);
        
        //Set the image
        setImage(range);
    }
    
    /**
     * Sets the transparency of the range.
     * 
     * @param transparency          Transparency of the range image
     */
    public void setTransparency(int transparency)
    {
        //Set the transparency
        range.setTransparency(transparency);
        
        //Set the image
        setImage(range);
    }
}
