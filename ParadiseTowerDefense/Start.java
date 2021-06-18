import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Start World - The World that greets the user, includes fascinating images for the background as well as the title,
 * draws users in. Through buttons, allows users to view an info page to get to know the game, change the soundtrack
 * or disable it, disable or enable the SFX, and start the game.
 * 
 * <h1>How to play:</h1>
 * <ol>
 *      <li>Start the scenario, click the "Start" button to go to the main menu</li>
 *      <li>Click on the music button (music logo) to change the track or turn it off</li>
 *      <li>Click on the SFX button (speaker logo) to switch it on or off</li>
 *      <li>In the main menu, click on any of the levels to go to them</li>
 *      <li>Click on any of the boxes with towers to select and click on a tile to place it there</li>
 *      <li>Click on the tower to show the range, click again to hide it</li>
 *      <li>Click on the blue start button on the bottom right to start the wave</li>
 *      <li>Put towers throughout the map to defend against the enemies that move throughout the map</li>
 *      <li>Make sure your lives do not run out, if you do, you lose</li>
 *      <li>If all the waves complete and you succeed, you will be awarded the score</li>
 *      <li>Click on the home button to go back to the main menu or restart the button</li>
 * </ol>
 * 
 * <br>
 * 
 * <h1>Features to look out for!</h1>
 * <ul>
 *      <li>6 different levels, with different maps the user can choose to play</li>
 *      <li>6 different towers the users can choose to place on any available tile</li>
 *      <li>5 different enemies that will be spawned throughout the game</li>
 *      <li>7 different types of projectiles for each tower and the tank</li>
 *      <li>Start screen to greet the user</li>
 *      <li>MainMenu screen including selection for each of the 6 levels</li>
 *      <li>LevelComplete screen that displays when the level finishes, displays different graphics based on success or failure</li>
 *      <li>Scoreboard that also displays on the LevelComplete screen to show the players who have accumulated the most money from completing levels</li>
 *      <li>Settings button in Levels for users to resume, restart, go to home, look at information, switch soundtrack and SFX</li>
 *      <li>Buttons enlarge when users hover over them, shrink back to regular size when not</li>
 *      <li>User gains score (money) for completing levels, shown on a global leaderboard in the end screen</li>
 *      <li>Menu graphic where users can click and place towers</li>
 *      <li>When users click on boxes containing towers a graphic pops up displaying price of the tower</li>
 *      <li>Clicking on the tower displays its range</li>
 *      <li>Buttons on the bottom right of Start, MainMenu and LevelComplete screen to control soundtrack (music symbol) and whether or not SFX will play (speaker symbol)</li>
 *      <li>Button on bottom right of Start, MainMenu and LevelComplete to show an information graphic</li>
 *      <li>User's selection of music is saved from world to world and after the user plays</li>
 *      <li>3 different animations for explosions, dragons flying, wolves running</li>
 *      <li>Various sound effects and soundtracks (see below)</li>
 *      <li>Lots of graphics (see below)</li>
 * </ul>
 * 
 * <br>
 * 
 * <h1>Requirements:</h1>
 * <h2>2D Arrays</h2>
 * <ul>
 *      <li>Each level has a 2D array of integers for the coordinates the enemies have to move to</li>
 *      <li>Also has a 2D array of integers for the types of enemies that will spawn each round</li>
 *      <li>Another 2D array of integers for intervals (in milliseconds) between which enemies will spawn</li>
 *      <li>These 2D arrays are extremely important for our game's interactions as they are the basis of how enemies move, as well as where users can place their towers</li>
 *      <li>Also, they determine which enemies will spawn and at what intervals</li>
 * </ul>
 * <br>
 * 
 * <h2>The Grid</h2>
 * <ul>
 *      <li>Maps for each of the levels were created using tile art that was pieced together into 16 tile by 10 tile formations</li>
 *      <li>Structure of the map forms a 17 by 11 grid, hence 16 by 10 tiles
 *      <li>Towers can only be placed on tiles, which is a square enclosed by grid lines</li>
 *      <li>Enemies move along the grid lines, between the tiles</li>
 * </ul>
 * <br>
 * 
 * <h2>UserInfo Class</h2>
 * <ul>
 *      <li>Saves the cumulative score of the user, which increases when the user completes a level</li>
 *      <li>User is allowed to choose between 1 of 3 soundtracks or none, saved in UserInfo</li>
 *      <li>User can turn on or off SFX, saved in UserInfo</li>
 *      <li>Scoreboard is displayed in the LevelComplete screen that shows which user has the highest score based on their UserInfo</li>
 * </ul>
 * <br>
 * 
 * <h2>ArrayList, Stacks, and Queues</h2>
 * <ul>
 *      <li>Queue is populated with waves (arrays of integers for enemy type) to determine which enemy will spawn next</li>
 *      <li>Queue is populated with intervals (array of integers for spawn interval) to determine how much time until next spawn</li>
 *      <li>Queue of integers for the current wave of enemies (integers for enemy type)</li>
 *      <li>Queue of integers for the intervals between the spawns of the current wave of enemies (milliseconds)</li>
 *      <li>ArrayList to hold the tiles (where users can place their towers)</li>
 *      <li>Queue of integers for the money given after the completion of each wave</li>
 *      <li>Enemies have an ArrayList of coordinates they move towards</li>
 *      <li>To determine which tower to shoot at, Dragon and Tank enemies get an ArrayList of all towers in the world, and use an algorithm to figure out which one is closest</li>
 *      <li>Projectiles have an ArrayList of enemies if splash damage needs to be done</li>
 *      <li>Towers calculate an ArrayList of enemies and determine which is the closest to fire at</li>
 * </ul>
 * <br>
 * 
 * <h2>Graphics and Sound</h2>
 * <ul>
 *      <li>Soundtracks</li>
 *          <ul>
 *              <li>User has the option of choosing 1 of 3 different soundtracks, or turning the soundtrack off</li>
 *          </ul>
 *      <li>Sound effects</li>
 *          <ul>
 *              <li>Towers losing all their health (explosion SFX)</li>
 *              <li>Tanks losing all health (explosion SFX)</li>
 *              <li>Cannon, SingleShooter and Tanks have basic firing sound effect</li>
 *              <li>Dragon and Flamethrower have a fireball sound effect</li>
 *              <li>Sound effect for dragon when they are defeated</li>
 *              <li>Laser sound effect for LaserTower shooting</li>
 *              <li>Arrow shooting sound effect for Archer</li>
 *              <li>Projectile firing sound effect for SingleLauncher</li>
 *              <li>User has the option between choosing to turn on or off the SFX</li>
 *          </ul>
 *      <li>Graphics</li>
 *          <ul>
 *              <li>Each map was created by ourselves using tile art that was pieced together (16 tiles by 10 tiles)</li>
 *              <li>Flamethrower was created by ourselves, other towers and projectiles were taken from online (check credits)</li>
 *              <li>Graphics for buttons that enlarge and shrink were taken from Bloons Tower Defense 5, then edited for our purposes</li>
 *              <li>Information graphic was created by ourselves</li>
 *              <li>Titles were created by ourselves</li>
 *              <li>Menu where users select towers was created by ourselves</li>
 *              <li>End screen for success and failure was created by ourselves</li>
 *              <li>Borders for the levels in the main menu were created by ourselves</li>
 *              <li>Explosion animation that plays when towers or tanks lose all their health</li>
 *              <li>Wolf animation when they run</li>
 *              <li>Dragon animation for flying</li>
 *          </ul>
 * </ul>
 * <br>
 * 
 * <h2>Greenfoot Gallery</h2>
 * <p><strong>Uploaded!</strong></p>
 * 
 * <h2>Credits</h2>
 * <ul>
 *      <li>Coordinate Class from Mr. Cohen</li>
 *      <li>StatBar Class from Mr. Cohen</li>
 *      <li>UserInfo utilization and Scoreboard inspiration from Mr. Cohen</li>
 *      <li>Scoreboard Class from Neil Brown</li>
 *      <li>SimpleTimer Class from Neil Brown</li>
 *      <li>Button images from Bloons Tower Defense 5</li>
 *      <li>Tile art used for maps from Kenney 2D Assets</li>
 *      <li>Towers and projectiles from Kenney 2D Assets and Argidev</li>
 *      <li>Soundtrack 1: Ashes on the Fire by Kohta Yamamoto</li>
 *      <li>Soundtrack 2: Attack on Titan by Hiroyuki Sawano</li>
 *      <li>Soundtrack 3: AppleSeed - Instrumental by Hiroyuki Sawano</li>
 *      <li>Explosion SFX by ActionVFX</li>
 *      <li>Dragon defeat SFX by Supercell</li>
 *      <li>Arrow firing by Sound Library</li>
 * </ul>
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Start extends World
{
    //Instance variables and objects
    
    //Image for the title
    private Image title;
    
    //Image of the start button
    private Image startButton;
    
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
    
    //Amount of money the user has (from completing levels)
    private int userMoney;
    
    //Displays the amount of money the user has (from completing levels)
    private TextLabel moneyLabel;
    
    //Info object about the game that is displayed when the user clicks on the info button
    private Info info;
    
    //Counts the acts (for button transparency)
    private int actCount;
    
    //If the user is logged in
    private boolean isLoggedIn;
    
    /**
     * Starting constructor for the Start World, only called at the beginning, calls other constructor.
     */
    public Start()
    {
        //Default values for the soundtrack and sfx, not coming from main menu
        this(0, 0, false);
    }
    
    /**
     * Constructor for the Start World, initializes all objects and adds them to the world.
     * 
     * @param songIndex             What soundtrack is currently playing if any (0 - first soundtrack, 1 - second soundtrack, 2 - third soundtrack, 3 - none)
     * @param isSFX                 If sound effects are being played (0 - yes, 1 - no)
     * @param fromMainMenu        Boolean for if the user is coming from the MainMenu screen
     */
    public Start(int songIndex, int isSFX, boolean fromMainMenu)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 640, 1);
        
        //Initialize the image for the title and add it to the world
        title = new Image(new GreenfootImage("title.png"));
        addObject(title, getWidth() / 2, 225);
        
        //Initialize the image for the start button and add it to the world, starts off as transparent
        startButton = new Image(new GreenfootImage("startbutton.png"));
        startButton.setTransparency(0);
        addObject(startButton, getWidth() / 2, 450);
        
        //Initialize and add the info button to the world
        infoButton = new Image(new GreenfootImage("infobuttonsmall.png"));
        addObject(infoButton, 805, 590);
        
        //Check if there is storage for the user
        if (UserInfo.isStorageAvailable()) {
            //If there is get the user's info
            user = UserInfo.getMyInfo();
            
            if (user != null) {
                //If the user has info take their values and save them
                songIndex = user.getInt(0);
                isSFX = user.getInt(1);
                userMoney = user.getScore();
            } else {
                //Otherwise set it to the values that were passed in through parameters
                this.songIndex = songIndex;
                this.isSFX = isSFX;
                userMoney = 0;
            }
        } else {
            //Otherwise set it to the values that were passed in through parameters
            this.songIndex = songIndex;
            this.isSFX = isSFX;
            userMoney = 0;
        }
        
        //Set it to parameter values just in case something goes wrong (like user disconnects from Internet suddenly)
        this.songIndex = songIndex;
        this.isSFX = isSFX;
        
        //Based on the index of the song, set the current music and the image of the music button
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
        
        //Initialize the SFX button based on if there is SFX or not
        if (isSFX == 0) {
            sfxButton = new Image(new GreenfootImage("sfxbuttonsmall.png"));
        } else {
            sfxButton = new Image(new GreenfootImage("nosfxbuttonsmall.png"));
        }
        
        //Add the SFX button to the world
        addObject(sfxButton, 975, 590);
        
        //If the user has info stored
        if (user != null) {
            //Initialize a label that displays how much money the user has
            moneyLabel = new TextLabel("$" + userMoney, 40, 10, new Color(161, 118, 27), new Color(255, 243, 109));
            
            //Add the object to the world
            addObject(moneyLabel, moneyLabel.getWidth() / 2 + 10, 640 - (moneyLabel.getHeight() / 2 + 10));
        }
        
        //Initialize the info screen that shows up
        info = new Info();
        
        //Initialize the act counter (for button transparency)
        actCount = 0;
        
        //If the user is coming from the main menu, start their music
        //For users just starting up the game, they will use the started() method to avoid the music playing before the scenario runs
        if (fromMainMenu) {
            if (music != null) {
                music.setVolume(20);
                music.playLoop();
            }
        }
    }
    
    /**
     * Called every act, changes the transparency of the button if necessary, checks if the user interacts with any buttons.
     */
    public void act()
    {
        //Set the transparency of the button based on the time that has passed
        startButton.setTransparency(Math.min((int)Math.round(actCount / 180.0 * 255.0), 255));
        
        //Increment act counter
        actCount++;
        
        //Call method to check the user's mouse position
        checkMouse();
        
        //Call method to check if user clicks anything
        checkClick();
    }
    
    /**
     * Checks if the user clicks on any buttons, and acts accordingly.
     */
    private void checkClick()
    {
        //If the user has clicked the start button bring them to the next world
        if (Greenfoot.mouseClicked(startButton)) {
            //If there is music, stop it
            if (music != null) {
                music.stop();
            }
            
            //Create new instance of MainMenu world
            MainMenu menu = new MainMenu(songIndex, isSFX);
            
            //Set the world to that new instance
            Greenfoot.setWorld(menu);
        } else if (Greenfoot.mouseClicked(musicButton)) {
            //If the user clicks the music button, go to the next song
            nextSong();
        } else if (Greenfoot.mouseClicked(sfxButton)) { //Check if user clicks on the sfx button
            //Switch the SFX from 0 to 1 or from 1 to 0
            isSFX++;
            if (isSFX == 2) {
                isSFX = 0;
            }
            
            //If the user information is available, set and store their new SFX value
            if (user != null) {
                user.setInt(1, isSFX);
                user.store();
            }
        } else if (Greenfoot.mouseClicked(infoButton)) {
            //If the user clicks on the info add the information button to the world
            addObject(info, 512, 320);
        }
    }
    
    /**
     * Called every act, checks if the user hovers over any buttons, and if so, enlarges them.
     */
    private void checkMouse()
    {
        //Check if the user is hovering over any of the buttons and enlarge them if they are, otherwise shrink them
        MouseInfo m = Greenfoot.getMouseInfo();
        if (m != null) {
            if (m.getX() >= 400 && m.getX() <= 610 && m.getY() >= 400 && m.getY() <= 500) {
                startButton.setImage("startbuttonselect.png");
            } else {
                startButton.setImage("startbutton.png");
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
     * Called to change to the next song and store the value for the user.
     */
    private void nextSong()
    {
        //Increment the song index, if it is greater than 3, change it back to 0
        songIndex++;
        if (songIndex > 3) {
            songIndex = 0;
        }
        
        //If the user is not null, set the value of the song index and store it
        if (user != null) {
            user.setInt(0, songIndex);
            user.store();
        }
        
        //If the music is not null and the music is playing stop it
        if (music != null && music.isPlaying()) {
            music.stop();
        }
            
        //Determine which is the new music that is playing
        if (songIndex == 0) {
            music = new GreenfootSound("ashesonthefire.mp3");
        } else if (songIndex == 1) {
            music = new GreenfootSound("attackontitan.mp3");
        } else if (songIndex == 2) {
            music = new GreenfootSound("appleseed.mp3");
        } else {
            music = null;
        }
        
        //If the music is not null, starting playing it
        if (music != null) {
            music.setVolume(20);
            music.playLoop();
        }
    }
    
    /**
     * Method that is called when the program starts, plays the song. This was used so that the music wouldn't start before the user clicked run.
     */
    public void started()
    {
        //If the music is not null, start it.
        if (music != null) {
            music.setVolume(20);
            music.playLoop();
        }
    }
}
