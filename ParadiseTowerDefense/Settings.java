import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Settings Class - Actor that is displayed when the user clicks on the settings button within each level, has buttons
 * that the user can use to do various actions.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Settings extends Actor
{
    //Images for various buttons in settings
    
    //Button for resuming the game
    private SettingsButton resumeButton;
    
    //Button for restarting the game
    private SettingsButton restartButton;
    
    //Button for switching the music
    private SettingsButton musicButton;
    
    //Button for switching on or off the SFX
    private SettingsButton sfxButton;
    
    //Button for going back to the MainMenus
    private SettingsButton homeButton;
    
    //Button to display information about the game
    private SettingsButton infoButton;
    
    //Index of the soundtrack (0 - first soundtrack, 1 - second, 2 - third, 3 - none)
    private int songIndex;
    
    //If the SFX is playing (0 - yes, 1 - no)
    private int isSFX;
    
    //Level the settings instance is in
    private Level level;
    
    //Info object that displays information about how the game works
    private Info info;
    
    /**
     * Constructor for Settings Class, initializes each of the buttons.
     * 
     * @param level         Level the Settings object is in
     */
    public Settings(Level level)
    {
        //Initialize images for buttons
        resumeButton = new SettingsButton(new GreenfootImage("resumebutton.png"));
        restartButton = new SettingsButton(new GreenfootImage("restartbutton.png"));
        
        //Set level to the instance variable
        this.level = level;
        
        //Get the index of the song that is playing
        songIndex = level.getSongIndex();
        
        //Determine which button to show accordingly
        if (songIndex == 0) {
            musicButton = new SettingsButton(new GreenfootImage("music1button.png"));
        } else if (songIndex == 1) {
            musicButton = new SettingsButton(new GreenfootImage("music2button.png"));
        } else if (songIndex == 2) {
            musicButton = new SettingsButton(new GreenfootImage("music3button.png"));
        } else {
            musicButton = new SettingsButton(new GreenfootImage("nomusicbutton.png"));
        }
        
        //Get the number for whether or not the SFX is playing
        isSFX = level.getIsSFX();
        
        //Determine the image for the SFX button accordingly
        if (isSFX == 0) {
            sfxButton = new SettingsButton(new GreenfootImage("sfxbutton.png"));
        } else {
            sfxButton = new SettingsButton(new GreenfootImage("nosfxbutton.png"));
        }
        
        //Initialize the home button
        homeButton = new SettingsButton(new GreenfootImage("homebutton.png"));
        
        //Initialize the info button
        infoButton = new SettingsButton(new GreenfootImage("infobutton.png"));
        
        //Initialize the information object
        info = new Info();
    }
    
    /**
     * Called when the Settings instance is added to the world, adds all the buttons to the world.
     * 
     * @param w             World the Settings object is in
     */
    public void addedToWorld(World w)
    {
        //Add buttons to the world
        level.addObject(resumeButton, 292, 275);
        level.addObject(restartButton, 438, 275);
        level.addObject(musicButton, 584, 275);
        level.addObject(sfxButton, 730, 275);
        level.addObject(homeButton, 438, 421);
        level.addObject(infoButton, 584, 421);
    }
    
    /**
     * Called every act, checks if the user hovers or clicks on the button and acts accordingly.
     */
    public void act()
    {
        //Check the coordinates of the user's mouse, enlarge the button if the user hovers over it
        MouseInfo m = Greenfoot.getMouseInfo();
        if (m != null) {
            if (m.getX() >= 247 && m.getX() <= 337 && m.getY() >= 223 && m.getY() <= 327) {
                resumeButton.setImage(new GreenfootImage("resumebuttonenlarge.png"));
            } else {
                resumeButton.setImage(new GreenfootImage("resumebutton.png"));
            }
            
            if (m.getX() >= 393 && m.getX() <= 483 && m.getY() >= 223 && m.getY() <= 327) {
                restartButton.setImage(new GreenfootImage("restartbuttonenlarge.png"));
            } else {
                restartButton.setImage(new GreenfootImage("restartbutton.png"));
            }
            
            if (m.getX() >= 539 && m.getX() <= 629 && m.getY() >= 223 && m.getY() <= 327) {
                if (songIndex == 0) {
                    musicButton.setImage("music1buttonenlarge.png");
                } else if (songIndex == 1) {
                    musicButton.setImage(new GreenfootImage("music2buttonenlarge.png"));
                } else if (songIndex == 2) {
                    musicButton.setImage(new GreenfootImage("music3buttonenlarge.png"));
                } else {
                    musicButton.setImage(new GreenfootImage("nomusicbuttonenlarge.png"));
                }
            } else {
                if (songIndex == 0) {
                    musicButton.setImage(new GreenfootImage("music1button.png"));
                } else if (songIndex == 1) {
                    musicButton .setImage(new GreenfootImage("music2button.png"));
                } else if (songIndex == 2) {
                    musicButton.setImage(new GreenfootImage("music3button.png"));
                } else {
                    musicButton.setImage(new GreenfootImage("nomusicbutton.png"));
                }
            }
            
            if (m.getX() >= 685 && m.getX() <= 775 && m.getY() >= 223 && m.getY() <= 327) {
                if (isSFX == 0) {
                    sfxButton.setImage(new GreenfootImage("sfxbuttonenlarge.png"));
                } else {
                    sfxButton.setImage(new GreenfootImage("nosfxbuttonenlarge.png"));
                }
            } else {
                if (isSFX == 0) {
                    sfxButton.setImage(new GreenfootImage("sfxbutton.png"));
                } else {
                    sfxButton.setImage(new GreenfootImage("nosfxbutton.png"));
                }
            }
            
            if (m.getX() >= 393 && m.getX() <= 483 && m.getY() >= 376 && m.getY() <= 466) {
                homeButton.setImage(new GreenfootImage("homebuttonenlarge.png"));
            } else {
                homeButton.setImage(new GreenfootImage("homebutton.png"));
            }
            
            if (m.getX() >= 539 && m.getX() <= 629 && m.getY() >= 376 && m.getY() <= 466) {
                infoButton.setImage(new GreenfootImage("infobuttonenlarge.png"));
            } else {
                infoButton.setImage(new GreenfootImage("infobutton.png"));
            }
        }
        
        //If the user presses the resume button remove all the objects
        if (Greenfoot.mousePressed(resumeButton)) {
            level.removeObject(resumeButton);
            level.removeObject(restartButton);
            level.removeObject(musicButton);
            level.removeObject(sfxButton);
            level.removeObject(homeButton);
            level.removeObject(infoButton);
            level.removeObject(this);
        } else if (Greenfoot.mousePressed(restartButton)) { //If the user presses the restart button, call method to restart the level
            level.restartLevel();
        } else if (Greenfoot.mousePressed(homeButton)) { //If the user presses the home button, call method to go back to home screen
            level.goBackHome();
        } else if (Greenfoot.mousePressed(musicButton)) { //If the user presses the music button
            //Call method to go to next song
            level.nextSong();
            
            //Get the new song index
            songIndex = level.getSongIndex();
        } else if (Greenfoot.mousePressed(sfxButton)) { //If the user presses the SFX button
            //Call method to change the SFX
            level.changeIsSFX();
            
            //Call method to get the new SFX value
            isSFX = level.getIsSFX();
        } else if (Greenfoot.mouseClicked(infoButton)) { //If the user presses the info button, add the info object to the world
            getWorld().addObject(info, 512, 320);
        }
    }
}
