import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Explosion Class - A 25-frame animation that plays when a tower or tank runs out of hitpoints, also includes a sound effect
 * if needed.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Explosion extends Actor
{
    //Declare the array of images for each frame of the explosion
    private GreenfootImage[] explosionImages = {
      new GreenfootImage("frame1.png"), new GreenfootImage("frame2.png"), new GreenfootImage("frame3.png"), new GreenfootImage("frame4.png"), new GreenfootImage("frame5.png"), 
      new GreenfootImage("frame6.png"), new GreenfootImage("frame7.png"), new GreenfootImage("frame8.png"), new GreenfootImage("frame9.png"), new GreenfootImage("frame10.png"), 
      new GreenfootImage("frame11.png"), new GreenfootImage("frame12.png"), new GreenfootImage("frame13.png"), new GreenfootImage("frame14.png"), new GreenfootImage("frame15.png"), 
      new GreenfootImage("frame16.png"), new GreenfootImage("frame17.png"), new GreenfootImage("frame18.png"), new GreenfootImage("frame19.png"), new GreenfootImage("frame20.png"), 
      new GreenfootImage("frame21.png"), new GreenfootImage("frame22.png"), new GreenfootImage("frame23.png"), new GreenfootImage("frame24.png"), new GreenfootImage("frame25.png")
    };
    
    //Declare the index to keep track of the current frame of the explosion
    private int explosionIndex;
      
    //Declare the act counter to keep track of the acts to determine when to switch frames
    private int actCount;
    
    //Boolean for whether or not it will play the SFX
    private boolean playSFX;
    
    //GreenfootSound for the explosion
    private GreenfootSound explosion;
    
    /**
     * Constructor for Explosion Class, takes in boolean variable and determines accordingly if there will be a SFX.
     * 
     * @param playSFX           Boolean for whether or not the SFX will play (true - yes, false - no)
     */
    public Explosion(boolean playSFX)
    {
        explosionIndex = 1; //The next image to be show will be the second image in the array
        actCount = 1; //Act counter starts at 1 as the first frame of the explosion is currently shown
        setImage(explosionImages[0]); //Set the current image to the first frame
        
        //If SFX is supposed to play, initialize and play the sound
        if (playSFX) {
            explosion = new GreenfootSound("explosion.wav");
            explosion.setVolume(60);
            explosion.play();
        }
    }
    
    /**
    * Called every act, determines when to change frames and when to display the image of the rubble.
    */
    public void act()
    {
        //If the frame index is 25, set the image to the rubble
        if (explosionIndex == 25) {
            getWorld().removeObject(this);
        } else if (explosionIndex < 25 && actCount % 4 == 0) { //If the frame index is below 25, change frames every 4 acts
            setImage(explosionImages[explosionIndex]); //Set a new frame
            explosionIndex++; //Increment the frame index
        }
        actCount++; //Increment the act counter
    }
}
