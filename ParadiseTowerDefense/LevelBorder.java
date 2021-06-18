import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * LevelBorder Class - Used in the MainMenu, displays pictures of each of the levels, as well as their title and reward.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class LevelBorder extends Actor
{
    //Instance variables and objects
    
    //Regular sized image
    private GreenfootImage regular;
    
    //Enlarged image
    private GreenfootImage enlarged;
    
    //Boolean for whether or not it is enlarged
    private boolean isEnlarged;
    
    /**
     * Constructor for LevelBorder class, initializes all variable and objects.
     * 
     * @param levelImage            Image of the level
     * @param title                 Image of the title of the level
     * @param money                 Image of the amount of money that is rewarded after the level
     */
    public LevelBorder(GreenfootImage levelImage, GreenfootImage title, GreenfootImage money) 
    {
        //Draw the regular sized image
        GreenfootImage border = new GreenfootImage("border.png");
        regular = new GreenfootImage(border.getWidth(), border.getHeight());
        levelImage.scale(border.getWidth(), border.getHeight());
        regular.drawImage(levelImage, 0, 0);
        regular.drawImage(border, 0, 0);
        regular.drawImage(title, 0, 0);
        regular.drawImage(money, 0, 0);
        
        //Set the image to the regular image
        setImage(regular);
        
        //Draw the enlarged size image
        enlarged = new GreenfootImage(border.getWidth(), border.getHeight());
        enlarged.drawImage(levelImage, 0, 0);
        enlarged.drawImage(border, 0, 0);
        enlarged.drawImage(title, 0, 0);
        enlarged.drawImage(money, 0, 0);
        enlarged.scale(289, 181);
        
        //Image is not currently enlarged
        isEnlarged = false;
    }
    
    /**
     * Enlarges the image.
     */
    public void enlarge()
    {
        //Set the image to the enlarged version
        setImage(enlarged);
        
        //Image is currently enlarged
        isEnlarged = true;
    }
    
    /**
     * Shrinks the image.
     */
    public void shrink()
    {
        //Set the image to regular size
        setImage(regular);
        
        //Image is not currently enlarged
        isEnlarged = false;
    }
    
    /**
     * Returns whether or not the image is enlarged.
     * 
     * @return boolean              True if image is enlarged, false if not
     */
    public boolean getIsEnlarged()
    {
        return isEnlarged;
    }
}
