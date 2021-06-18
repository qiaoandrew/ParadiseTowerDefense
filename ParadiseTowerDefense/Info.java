import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Info Class - An object that is added to the world to show the user how to the play the game, also includes a back
 * button to remove it.
 * 
 * @author Andrew Qiao, James Li
 * @version June 2021
 */
public class Info extends Actor
{
    //Instance objects
    
    //The button the user presses to return to the previous screen
    private InfoButton backButton;
    
    /**
     * Constructor for Info Class, initializes button for user to press to go back to previous screen.
     */
    public Info()
    {
        //Initialize images for buttons
        backButton = new InfoButton(new GreenfootImage("backbutton.png"));
    }
    
    /**
     * Called when Info instance is added to world, adds the back button to the world.
     * 
     * @param w         World the instance is in
     */
    public void addedToWorld(World w)
    {
        getWorld().addObject(backButton, 40, 40);
    }
    
    /**
     * Called every act, checks if the user clicks on the button and acts accordingly.
     */
    public void act() 
    {
        //Checks mouse info, if user is hovering over the button, enlarge, otherwise put it to normal size
        MouseInfo m = Greenfoot.getMouseInfo();
        if (m != null && m.getX() >= 15 && m.getX() <= 65 && m.getY() >= 15 && m.getY() <= 65) {
            backButton.setImage("backbuttonenlarge.png");
        } else {
            backButton.setImage("backbutton.png");
        }
        
        //If the user presses the back button, remove the info class
        if (Greenfoot.mousePressed(backButton)) {
            //If the user is going back to the main menu, start the timer to make sure it doesn't double click back
            //to the start world
            if (getWorld() instanceof MainMenu) {
                ((MainMenu)getWorld()).restartTimer();
            }
            
            //Remove the button and the info object
            getWorld().removeObject(backButton);
            getWorld().removeObject(this);
        }
    }    
}
