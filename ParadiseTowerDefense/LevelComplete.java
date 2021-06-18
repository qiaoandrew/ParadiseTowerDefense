import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * LevelComplete World - The World that is displayed when the user completes the level. Displays a graphic, buttons, as well as a leaderboard
 * for the user to see and interact with.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class LevelComplete extends World
{
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
    
    //Button for home
    private Image homeButton;
    
    //Button for restart level
    private Image restartButton;
    
    //What level user just came fron
    private int levelIndex;
    
    //Amount of money user has (from finishing levels)
    private int userMoney;
    
    //Label to display the amount of money the user has
    private TextLabel moneyLabel;
    
    //Infomation object that displays info about the game
    private Info info;
    
    //Scoreboard for the user to compare themselves to other players
    private ScoreBoard scoreboard;
    
    /**
     * Constructor for objects of class LevelComplete, initalizes values and displays all relevant objects.
     * 
     * @param songIndex             Soundtrack (if any) that is playing (0 - first soundtrack, 1 - second soundtrack, 2 - third soundtrack, 3 - none)
     * @param isSFX                 If SFX are currently playing (0 - true, 1 - false)
     * @param levelIndex            Level number (1, 2, 3, 4, 5, or 6)
     * @param isSuccess             Boolean for whether the user has succeeded or failed in passing the level
     */
    public LevelComplete(int songIndex, int isSFX, int levelIndex, boolean isSuccess)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 640, 1); 
        
        //Set the background based on whether or not the user has suceeded
        if (isSuccess) {
            setBackground("success.png");
        } else {
            setBackground("failure.png");
        }
        
        //Initialize and add the info button to the world
        infoButton = new Image(new GreenfootImage("infobuttonsmall.png"));
        addObject(infoButton, 805, 590);
        
        //Set the level index (level number)
        this.levelIndex = levelIndex;
        
        //If there is storage space available
        if (UserInfo.isStorageAvailable()) {
            //Get the user's information
            user = UserInfo.getMyInfo();
            
            //If there is user information
            if (user != null) {
                //Get the user's information and set it to the instance variables
                songIndex = user.getInt(0);
                isSFX = user.getInt(1);
                userMoney = user.getScore();
            } else {
                //Otherwise set as parameter values
                this.songIndex = songIndex;
                this.isSFX = isSFX;
                userMoney = 0;
            }
        } else {
            //Otherwise set as parameter values
            this.songIndex = songIndex;
            this.isSFX = isSFX;
            userMoney = 0;
        }
        
        //Set again just in case the user disconnects from Internet
        this.songIndex = songIndex;
        this.isSFX = isSFX;
        
        //Determine the soundtrack (if any) and the image on the button based on the index of the soundtrack
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
        
        //Add the music button to the world
        addObject(musicButton, 890, 590);
        
        //Initialize and add the sfx button to the world
        if (isSFX == 0) {
            sfxButton = new Image(new GreenfootImage("sfxbuttonsmall.png"));
        } else {
            sfxButton = new Image(new GreenfootImage("nosfxbuttonsmall.png"));
        }
        addObject(sfxButton, 975, 590);
        
        //If there is music start looping it
        if (music != null) {
            music.setVolume(20);
            music.playLoop();
        }
        
        //Initialize and add the button for home 
        homeButton = new Image(new GreenfootImage("homebutton.png"));
        addObject(homeButton, 970, 265);
        
        //Initialize and add the restart button
        restartButton = new Image(new GreenfootImage("restartbutton.png"));
        addObject(restartButton, 970, 375);
        
        //If the user information is available, initialize and add a label displaying the accumulated amount of money they have
        if (user != null) {
            moneyLabel = new TextLabel("$" + userMoney, 40, 10, new Color(161, 118, 27), new Color(255, 243, 109));
            addObject(moneyLabel, moneyLabel.getWidth() / 2 + 10, 640 - (moneyLabel.getHeight() / 2 + 10));
        }
        
        //Initialize the information object
        info = new Info();
        
        //Initialize and add the scoreboard
        scoreboard = new ScoreBoard(800, 400);
        addObject(scoreboard, getWidth() / 2, 350);
    }
    
    /**
     * Called every act, checks for if the user drags their mouse over or clicks any buttons.
     */
    public void act()
    {
        //Call method to check if the user is hovering over anything
        checkMouse();
        
        //Call method to check if the user has clicked on any buttons
        checkClick();
    }
    
    /**
     * Checks if the user hovers over any buttons and enlarges accordingly.
     */
    private void checkMouse()
    {
        //Get the information on the mouse
        MouseInfo m = Greenfoot.getMouseInfo();
        
        //If the mouse is on the screen, check if it is hovering over anything and if so enlarge image of the button
        if (m != null) {
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
            
            if (m.getX() >= 925 && m.getX() <= 1015 && m.getY() >= 220 && m.getY() <= 310) {
                homeButton.setImage("homebuttonenlarge.png");
            } else {
                homeButton.setImage("homebutton.png");
            }
            
            if (m.getX() >= 925 && m.getX() <= 1015 && m.getY() >= 330 && m.getY() <= 420) {
                restartButton.setImage("restartbuttonenlarge.png");
            } else {
                restartButton.setImage("restartbutton.png");
            }
        }
    }
    
    /**
     * Called every act, checks if the user has clicked on any buttons and acts accordingly.
     */
    private void checkClick()
    {
        //Check if the user clicks on any buttons
        if (Greenfoot.mouseClicked(musicButton)) { //Go to next song if user clicks on music button
            nextSong();
        } else if (Greenfoot.mouseClicked(sfxButton)) { //Check if user clicks on the sfx button
            isSFX++;
            if (isSFX == 2) {
                isSFX = 0;
            }
            
            //Set the SFX value if ther is user information
            if (user != null) {
                user.setInt(1, isSFX);
                user.store();
            }
        } else if (Greenfoot.mouseClicked(homeButton)) { //If user clicks on the home button
            //Stop the music if it is currently playing
            if (music != null) {
                music.stop();
            }
            
            //Create new instance of Main Menu world passing in the song index and sfx values as paramaters
            MainMenu menu = new MainMenu(songIndex, isSFX);
            Greenfoot.setWorld(menu);
        } else if (Greenfoot.mouseClicked(restartButton)) { //If the user clicks on the restart button
            //Depending on the level index (level number), create new instance of the world and set it as the world
            if (levelIndex == 1) {
                if (music != null) {
                    music.stop();
                }
                
                Level1 level1 = new Level1(songIndex, isSFX);
                Greenfoot.setWorld(level1);
            } else if (levelIndex == 2) {
                if (music != null) {
                    music.stop();
                }
                
                Level2 level2 = new Level2(songIndex, isSFX);
                Greenfoot.setWorld(level2);
            } else if (levelIndex == 3) {
                if (music != null) {
                    music.stop();
                }
                
                Level3 level3 = new Level3(songIndex, isSFX);
                Greenfoot.setWorld(level3);
            } else if (levelIndex == 4) {
                if (music != null) {
                    music.stop();
                }
                
                Level4 level4 = new Level4(songIndex, isSFX);
                Greenfoot.setWorld(level4);
            } else if (levelIndex == 5) {
                if (music != null) {
                    music.stop();
                }
                
                Level5 level5 = new Level5(songIndex, isSFX);
                Greenfoot.setWorld(level5);
            } else {
                if (music != null) {
                    music.stop();
                }
                
                Level6 level6 = new Level6(songIndex, isSFX);
                Greenfoot.setWorld(level6);
            }
        } else if (Greenfoot.mousePressed(infoButton)) { //If the user presses the info button
            //Add information object to the world
            addObject(info, getWidth() / 2, getHeight() / 2);
        }
    }
    
    /**
     * Called to go to the next song, changes the song index and music accordingly.
     */
    private void nextSong()
    {
        //Increase song index, if it is above 3, set it to 0
        songIndex++;
        if (songIndex > 3) {
            songIndex = 0;
        }
        
        //If there is user information, set the values and store it
        if (user != null) {
            user.setInt(0, songIndex);
            user.store();
        }
        
        //If there is music and it is playing stop it
        if (music != null && music.isPlaying()) {
            music.stop();
        }
            
        //Determine the next soundtrack
        if (songIndex == 0) {
            music = new GreenfootSound("ashesonthefire.mp3");
        } else if (songIndex == 1) {
            music = new GreenfootSound("attackontitan.mp3");
        } else if (songIndex == 2) {
            music = new GreenfootSound("appleseed.mp3");
        } else {
            music = null;
        }
        
        //If there is music, start playing it
        if (music != null) {
            music.setVolume(20);
            music.playLoop();
        }
    }
}
