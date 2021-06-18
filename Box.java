import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Box Class - A box that holds the image of the tower for the selection menu.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Box extends Actor
{
    //Calss variables/objects
    
    //Image of the box
    private GreenfootImage boxImage;
    
    //Image of the not selected outline
    private GreenfootImage notSelected;
    
    //Image of the selected outline
    private GreenfootImage selected;
    
    //Image of the tower
    private GreenfootImage towerImage;
    
    //Boolean for whether or not the slot is selected
    private boolean isSelected;
    
    /**
     * Constructor for Box class, initializes variables and objects and draws its image.
     * 
     * @param towerImage        Image of the tower
     */
    public Box(GreenfootImage towerImage)
    {
        //Initialize starting values for variables and objects
        notSelected = new GreenfootImage("notselected.png");
        selected = new GreenfootImage("selected.png");
        this.towerImage = towerImage;
        isSelected = false;
        
        //Call method to draw image of the box
        draw();
    }
    
    /**
     * Sets whether or not the box is selected.
     * 
     * @param isSelected        Boolean for whether or not the tile has been selected.
     */
    public void setIsSelected(boolean isSelected)
    {
        //Set isSelected
        this.isSelected = isSelected;
        
        //Call method to draw image of the box
        draw();
    }
    
    /**
     * Draws the image of the box.
     */
    private void draw()
    {
        //If the box is selected
        if (isSelected) {
            //Initialize the image of the box as the selected outline
            boxImage = new GreenfootImage(selected);
        } else { //If the box isn't selected
            //Initialize the image of the box as the not selected outline
            boxImage = new GreenfootImage(notSelected);
        }
        
        //Draw onto it the image of the tower
        boxImage.drawImage(towerImage, 3, 3);
            
        //Set its image
        setImage(boxImage);
    }
    
    /**
     * Returns whether or not the box has been selected.
     * 
     * @return boolean      True if the box is selected, false if not.
     */
    public boolean getIsSelected()
    {
        return isSelected;
    }
}
