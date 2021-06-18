import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Image Class - An image that is an actor, making it easier to manipulate (adding or removing it from the world)
 * and which you can set the transparency of.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Image extends Actor
{
    /**
     * Constructor of Image class, sets the image.
     * 
     * @param image         Image that will be set
     */
    public Image(GreenfootImage image) 
    {
        //Set the image
        setImage(image);
    }
    
    /**
     * Set the transparency of the image.
     * 
     * @param transparency          Transparency of the image
     */
    public void setTransparency(int transparency)
    {
        getImage().setTransparency(transparency);
    }
}
