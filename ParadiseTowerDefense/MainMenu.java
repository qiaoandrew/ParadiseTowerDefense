import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * MainMenu World - The Main Menu World where the user can select which level they want to play, as well as the level's name, image, and reward.
 * Includes buttons to change the soundtrack, SFX, and pull up the information screen.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class MainMenu extends World
{
    //Image for each of the levels
    private LevelBorder level1;
    private LevelBorder level2;
    private LevelBorder level3;
    private LevelBorder level4;
    private LevelBorder level5;
    private LevelBorder level6;
    
    //Button to go back to the Start screen
    private Image backButton;
    
    //Timers
    
    //When the user clicks on the home button in the level settings, it automatically brings them to level 5, as it registers as more than one press
    //This timer prevents that from happening
    private SimpleTimer timer;
    
    //When the user clicks on back button on the info screen, it registers as more than one press, thus bringing the user back to the start screen
    //This timer prevents that from happening
    private SimpleTimer timer2;
    
    //Image of the music button
    private Image musicButton;
    
    //Image of the sfx button
    private Image sfxButton;
    
    //Image of the info button
    private Image infoButton;
    
    //Index of which soundtrack is playing (0 - first soundtrack, 1 - second soundtrack, 2 - third soundtrack, 3 - no)
    private int songIndex;
    
    //Whether or not the SFX are playing (0 - yes, 1 - no)
    private int isSFX;
    
    //UserInfo
    private UserInfo user;
    
    //GreenfootSound for the music
    private GreenfootSound music;
    
    //How much money the user has (accumulated from completing levels)
    private int userMoney;
    
    //Text label for the user's money
    private TextLabel moneyLabel;
    
    //Info object about the game that is displayed when the user clicks on the info button
    private Info info;
    
    /**
     * Constructor for MainMenu World, creates the world, initializes each of the buttons, user info, and other vital processes.
     * 
     * @param songIndex         Index of the song that is currently playing (0 - first soundtrack, 1 - second soundtrack, 2 - third soundtrack, 3 - none)
     * @param isSFX             If there is SFX (0 - SFX is playing, 1 - SFX is not playing)
     */
    public MainMenu(int songIndex, int isSFX)
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 640, 1, false);
        
        //Initialize and add each of the images containing the level image and reward to the world
        level1 = new LevelBorder(new GreenfootImage("level1.png"), new GreenfootImage("level1title.png"), new GreenfootImage("level1money.png"));
        addObject(level1, 200, 254);
        
        level2 = new LevelBorder(new GreenfootImage("level2.png"), new GreenfootImage("level2title.png"), new GreenfootImage("level2money.png"));
        addObject(level2, 512, 254);
        
        level3 = new LevelBorder(new GreenfootImage("level3.png"), new GreenfootImage("level3title.png"), new GreenfootImage("level3money.png"));
        addObject(level3, 821, 254);
        
        level4 = new LevelBorder(new GreenfootImage("level4.png"), new GreenfootImage("level4title.png"), new GreenfootImage("level4money.png"));
        addObject(level4, 200, 462);
        
        level5 = new LevelBorder(new GreenfootImage("level5.png"), new GreenfootImage("level5title.png"), new GreenfootImage("level5money.png"));
        addObject(level5, 512, 462);
        
        level6 = new LevelBorder(new GreenfootImage("level6.png"), new GreenfootImage("level6title.png"), new GreenfootImage("level6money.png"));
        addObject(level6, 821, 462);
        
        //Initialize and add image for the button to go back to the Start screen
        backButton = new Image(new GreenfootImage("backbutton.png"));
        addObject(backButton, 40, 40);
        
        //Initialize and add the info button to the world
        infoButton = new Image(new GreenfootImage("infobuttonsmall.png"));
        addObject(infoButton, 805, 590);
        
        //If the storage is available
        if (UserInfo.isStorageAvailable()) {
            //Get the user's info
            user = UserInfo.getMyInfo();
            
            //If there is user info
            if (user != null) {
                //Get the user's values and set them as instance variables
                songIndex = user.getInt(0);
                isSFX = user.getInt(1);
                userMoney = user.getScore();
            } else {
                //Otherwise have default values
                this.songIndex = songIndex;
                this.isSFX = isSFX;
                userMoney = 0;
            }
        } else {
            //Otherwise have default values
            this.songIndex = songIndex;
            this.isSFX = isSFX;
            userMoney = 0;
        }
        
        //Set it to parameter values just in case something goes wrong (like user disconnects from Internet suddenly)
        this.songIndex = songIndex;
        this.isSFX = isSFX;
        
        //Determine which soundtrack if any and which button based on the song index 
        if (songIndex == 0) {
            music = new GreenfootSound("ashesonthefire.mp3");
            musicButton = new Image(new GreenfootImage("music1buttonsmall.png"));
        } else if (songIndex == 1) {
            music = new GreenfootSound("attackontitan.mp3");
            musicButton = new Image(new GreenfootImage("music2buttonsmall.png"));
        } else if (songIndex == 2) {
            music = new GreenfootSound("appleseed.mp3");
            musicButton = new Image(new GreenfootImage("music3buttonsmall.png"));
        } else {
            music = null;
            musicButton = new Image(new GreenfootImage("nomusicbuttonsmall.png"));
        }
        
        //Add the button to the world
        addObject(musicButton, 890, 590);
        
        //Initialize and add the SFX button to the world
        if (isSFX == 0) {
            sfxButton = new Image(new GreenfootImage("sfxbuttonsmall.png"));
        } else {
            sfxButton = new Image(new GreenfootImage("nosfxbuttonsmall.png"));
        }
        
        //Add SFX button to the world
        addObject(sfxButton, 975, 590);
        
        //If there is music, set the volume and play it on loop
        if (music != null) {
            music.setVolume(20);
            music.playLoop();
        }
        
        //If the user information is not null, create a money label and add it to the world
        //Shows the user how much money they have accumulated through playing the game
        if (user != null) {
            moneyLabel = new TextLabel("$" + userMoney, 40, 10, new Color(161, 118, 27), new Color(255, 243, 109));
            addObject(moneyLabel, moneyLabel.getWidth() / 2 + 10, 640 - (moneyLabel.getHeight() / 2 + 10));
        }
        
        //Initialize the information object
        info = new Info();
        
        //Initialize the timer and start it
        //This timer was used as everytime the user would click the home button in each of the level settings it would
        //automatically click into the 5th level, this makes that not happen
        timer = new SimpleTimer();
        timer.mark();
        
        //When the user clicks on back button on the info screen, it registers as more than one press, thus bringing the user back to the start screen
        //This timer prevents that from happening
        timer2 = new SimpleTimer();
        timer2.mark();
    }
    
    /**
     * Called every act, checks if the user is hovering over any of the levels and if the click on anything.
     */
    public void act()
    {
        //Call method to check if the user is hovering over anything
        checkMouse();
        
        //Call method to check if user has clicked anything
        checkClick();
    }
    
    /**
     * Called every method, checks if user clicks on any buttons and acts accordingly.
     */
    private void checkClick()
    {
        //If the user clicks the back button, go back to the Start world
        if (timer2.millisElapsed() > 150 && Greenfoot.mouseClicked(backButton)) {
            //If there is music, stop it
            if (music != null) {
                music.stop();
            }
            
            //Create a new instance of the Start World
            Start start = new Start(songIndex, isSFX, true);
            
            //Set the world to the start world
            Greenfoot.setWorld(start);
        } else if (Greenfoot.mouseClicked(level1)) { //If the user clicks the level1 button
            //If there is music, stop it
            if (music != null) {
                music.stop();
            }
            
            //Create a new instance of the Level1 World
            Level1 level1 = new Level1(songIndex, isSFX);
            
            //Set the world to the level 1 world
            Greenfoot.setWorld(level1);
        } else if (Greenfoot.mouseClicked(level2)) { //If the user clicks on the level2 button
            //If there is music, stop it
            if (music != null) {
                music.stop();
            }
            
            //Create a new instance of the Level2 World
            Level2 level2 = new Level2(songIndex, isSFX);
            
            //Set the world to the level 2 world
            Greenfoot.setWorld(level2);
        } else if (Greenfoot.mouseClicked(level3)) { //If the user clicks on the level3 button
            //If there is music, stop it
            if (music != null) {
                music.stop();
            }
            
            //Create a new instance of the Level3 World
            Level3 level3 = new Level3(songIndex, isSFX);
            
            //Set the world to the level 3 world
            Greenfoot.setWorld(level3);
        } else if (Greenfoot.mouseClicked(level4)) { //If the user clicks on the level4 button
            //If there is music, stop it
            if (music != null) {
                music.stop();
            }
            
            //Create a new instance of the Level4 World
            Level4 level4 = new Level4(songIndex, isSFX);
            
            //Set the world to the level 4 world
            Greenfoot.setWorld(level4);
        } else if (timer.millisElapsed() >= 150 && Greenfoot.mouseClicked(level5)) { //If the user clicks on the level5 button
            //If there is music, stop it
            if (music != null) {
                music.stop();
            }
            
            //Create a new instance of the Level5 World
            Level5 level5 = new Level5(songIndex, isSFX);
            
            //Set the world to the level 5 world
            Greenfoot.setWorld(level5);
        } else if (Greenfoot.mouseClicked(level6)) { //If the user clicks on the level6 button
            //If there is music, stop it
            if (music != null) {
                music.stop();
            }
            
            //Create a new instance of the Level6 World
            Level6 level6 = new Level6(songIndex, isSFX);
            
            //Set the world to the level 6 world
            Greenfoot.setWorld(level6);
        } else if (Greenfoot.mouseClicked(musicButton)) { //If the user clicks on the music button
            //Change to the next song
            nextSong();
        } else if (Greenfoot.mouseClicked(sfxButton)) { //Check if user clicks on the sfx button
            //Change the SFX index from 0 to 1 or from 1 to 0
            isSFX++;
            if (isSFX == 2) {
                isSFX = 0;
            }
            
            //If the user is not null, set the SFX and store it
            if (user != null) {
                user.setInt(1, isSFX);
                user.store();
            }
        } else if (Greenfoot.mouseClicked(infoButton)) { //Check if the user clicks on the information button
            //Add the information object to the world
            addObject(info, 512, 320);
        }
    }
    
    /**
     * Called every method, checks if user hovers over any buttons and acts accordingly
     */
    private void checkMouse()
    {
        //If the mouse is on the screen, check its x and y coordinates and if it is hovering over a level, enlarge it, otherwise shrink it
        MouseInfo m = Greenfoot.getMouseInfo();
        if (m != null) {
            if (m.getX() >= 66 && m.getX() <= 341 && m.getY() >= 168 && m.getY() <= 340) {
                if (!level1.getIsEnlarged()) {
                    level1.enlarge();
                }
            } else {
                if (level1.getIsEnlarged()) {
                    level1.shrink();
                }
            }
            
            if (m.getX() >= 375 && m.getX() <= 650 && m.getY() >= 168 && m.getY() <= 340) {
                if (!level2.getIsEnlarged()) {
                    level2.enlarge();
                }
            } else {
                if (level2.getIsEnlarged()) {
                    level2.shrink();
                }
            }
            
            if (m.getX() >= 684 && m.getX() <= 959 && m.getY() >= 168 && m.getY() <= 340) {
                if (!level3.getIsEnlarged()) {
                    level3.enlarge();
                }
            } else {
                if (level3.getIsEnlarged()) {
                    level3.shrink();
                }
            }
            
            if (m.getX() >= 66 && m.getX() <= 341 && m.getY() >= 376 && m.getY() <= 548) {
                if (!level4.getIsEnlarged()) {
                    level4.enlarge();
                }
            } else {
                if (level4.getIsEnlarged()) {
                    level4.shrink();
                }
            }
            
            if (m.getX() >= 375 && m.getX() <= 650 && m.getY() >= 376 && m.getY() <= 548) {
                if (!level5.getIsEnlarged()) {
                    level5.enlarge();
                }
            } else {
                if (level5.getIsEnlarged()) {
                    level5.shrink();
                }
            }
            
            if (m.getX() >= 684 && m.getX() <= 959 && m.getY() >= 376 && m.getY() <= 548) {
                if (!level6.getIsEnlarged()) {
                    level6.enlarge();
                }
            } else {
                if (level6.getIsEnlarged()) {
                    level6.shrink();
                }
            }
            
            if (m.getX() >= 15 && m.getX() <= 65 && m.getY() >= 15 && m.getY() <= 65) {
                backButton.setImage("backbuttonenlarge.png");
            } else {
                backButton.setImage("backbutton.png");
            }
            
            if (m.getX() >= 775 && m.getX() <= 835 && m.getY() >= 560 && m.getY() <= 620) {
                infoButton.setImage("infobuttonmedium.png");
            } else {
                infoButton.setImage("infobuttonsmall.png");
            }
            
            if (m.getX() >= 860 && m.getX() <= 910 && m.getY() >= 560 && m.getY() <= 620) {
                if (songIndex == 0) {
                    musicButton.setImage("music1buttonmedium.png");
                } else if (songIndex == 1) {
                    musicButton.setImage("music2buttonmedium.png");
                } else if (songIndex == 2) {
                    musicButton.setImage("music3buttonmedium.png");
                } else {
                    musicButton.setImage("nomusicbuttonmedium.png");
                }
            } else {
                if (songIndex == 0) {
                    musicButton.setImage("music1buttonsmall.png");
                } else if (songIndex == 1) {
                    musicButton.setImage("music2buttonsmall.png");
                } else if (songIndex == 2) {
                    musicButton.setImage("music3buttonsmall.png");
                } else {
                    musicButton.setImage("nomusicbuttonsmall.png");
                }
            }
            
            if (m.getX() >= 945 && m.getX() <= 1005 && m.getY() >= 560 && m.getY() <= 620) {
                if (isSFX == 0) {
                    sfxButton.setImage("sfxbuttonmedium.png");
                } else {
                    sfxButton.setImage("nosfxbuttonmedium.png");
                }
            } else {
                if (isSFX == 0) {
                    sfxButton.setImage("sfxbuttonsmall.png");
                } else {
                    sfxButton.setImage("nosfxbuttonsmall.png");
                }
            } 
        }
    }
    
    /**
     * Called to restart the timer that makes sure the back button on the info page does not get double clicked, causing the player
     * to unintentionally return to the Start World.
     */
    public void restartTimer()
    {
        timer2.mark();
    }
    
    /**
     * Changes the soundtrack to the next soundtrack and stores the user's information if needed.
     */
    private void nextSong()
    {
        //Increment the song index
        songIndex++;
        
        //If the song index is over 3, set it to 0
        if (songIndex > 3) {
            songIndex = 0;
        }
        
        //If there is user information set the values and store it
        if (user != null) {
            user.setInt(0, songIndex);
            user.store();
        }
        
        //If there is music and the music is playing
        if (music != null && music.isPlaying()) {
            //Stop the music
            music.stop();
        }
            
        //Set the music based on the isong 
        if (songIndex == 0) {
            music = new GreenfootSound("ashesonthefire.mp3");
        } else if (songIndex == 1) {
            music = new GreenfootSound("attackontitan.mp3");
        } else if (songIndex == 2) {
            music = new GreenfootSound("appleseed.mp3");
        } else {
            music = null;
        }
        
        //If music is not null start playing it
        if (music != null) {
            music.setVolume(20);
            music.playLoop();
        }
    }
}
