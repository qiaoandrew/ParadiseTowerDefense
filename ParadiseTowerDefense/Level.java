import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util
import java.util.Queue; //Import Queue from java.util
import java.util.LinkedList; //Import LinkedList from java.util

/**
 * Level Superclass - All Level classes inherit from it, initializes basic functions of each level, also contains vital methods for their processes.
 * All of the major functions of each level has been added here. Thus, it allows the programmer to create a large amount of levels with relatively 
 * less time consumption.
 * 
 * @author Andrew Qiao, Ryo Minakami
 * @version June 2021
 */
public abstract class Level extends World
{    
    //Final values for the game
    
    //Health
    final static int HUMANOID_HEALTH = 200;
    final static int TANK_HEALTH = 350;
    final static int DRAGON_HEALTH = 325;
    final static int WOLF_HEALTH = 225;
    final static int ROBOT_HEALTH = 300;
    
    final static int SINGLESHOOTER_HEALTH = 500;
    final static int SINGLELAUNCHER_HEALTH = 200;
    final static int FLAMETHROWER_HEALTH = 300;
    final static int ARCHER_HEALTH = 1000;
    final static int CANNON_HEALTH = 700;
    final static int LASERTOWER_HEALTH = 1500;
    
    //Speed
    final static double HUMANOID_SPEED = 1;
    final static double TANK_SPEED = 0.6;
    final static double DRAGON_SPEED = 1.3;
    final static double ROBOT_SPEED = 1.5;
    final static double WOLF_SPEED = 2;
    
    final static double BIGBULLET_SPEED = 7;
    final static double BIGPROJECTILE_SPEED = 3;
    final static double SMALLBULLET_SPEED = 9;
    final static double FIREBALL_SPEED = 5;
    final static double ARROW_SPEED = 4;
    final static double CANNONBALL_SPEED = 4;
    final static double LASER_SPEED = 7;
    
    //Damage
    final static int BIGBULLET_DAMAGE = 40;
    final static int SMALLBULLET_DAMAGE = 15;
    final static int BIGPROJECTILE_DAMAGE = 90;
    final static int FIREBALL_DAMAGE = 3;
    final static int ARROW_DAMAGE = 60;
    final static int CANNONBALL_DAMAGE = 100;
    final static int LASER_DAMAGE = 1;
    
    //Reload speed
    final static int TANK_RELOAD = 60;
    final static int DRAGON_RELOAD = 3;
    final static int SINGLESHOOTER_RELOAD = 70;
    final static int SINGLELAUNCHER_RELOAD = 70;
    final static int FLAMETHROWER_RELOAD = 5;
    final static int ARCHER_RELOAD = 70;
    final static int CANNON_RELOAD = 120;
    final static int LASERTOWER_RELOAD = 1;
    
    //Range
    final static int TANK_RANGE = 128;
    final static int DRAGON_RANGE = 192;
    final static int SINGLESHOOTER_RANGE = 192;
    final static int SINGLELAUNCHER_RANGE = 160;
    final static int FLAMETHROWER_RANGE = 192;
    final static int ARCHER_RANGE = 320;
    final static int CANNON_RANGE = 160;
    final static int LASERTOWER_RANGE = 256;
    
    //Player health
    final static int PLAYER_MAXHEALTH = 100;
    
    //How much damage each enemy does when they pass the final bounds
    final static int HUMANOID_PLAYERDAMAGE = 5;
    final static int ROBOT_PLAYERDAMAGE = 10;
    final static int WOLF_PLAYERDAMAGE = 5;
    final static int DRAGON_PLAYERDAMAGE = 20;
    final static int TANK_PLAYERDAMAGE = 33;
    
    //Cost for each tower
    final static int ARCHER_COST = 600;
    final static int CANNON_COST = 500;
    final static int SINGLELAUNCHER_COST = 400;
    final static int SINGLESHOOTER_COST = 300;
    final static int LASERTOWER_COST = 700;
    final static int FLAMETHROWER_COST = 500;
    
    //Money gained after defeating enemy
    final static int DRAGON_MONEY = 100;
    final static int HUMANOID_MONEY = 60;
    final static int ROBOT_MONEY = 80;
    final static int TANK_MONEY = 90;
    final static int WOLF_MONEY = 40;
    
    //Reward for passing each level
    final static int LEVEL1REWARD = 500;
    final static int LEVEL2REWARD = 1000;
    final static int LEVEL3REWARD = 1500;
    final static int LEVEL4REWARD = 2000;
    final static int LEVEL5REWARD = 2500;
    final static int LEVEL6REWARD = 3000;
    
    //Instance variables and objects
    
    //Image for the button starting the next wave
    protected Image startWave;
    
    //SimpleTimer for timing intervals between enemy spawns
    private SimpleTimer enemyTimer;
    
    //ArrayList of coordinates that the enemy will move through
    protected ArrayList<Coordinate> enemyPath;
    
    //Boolean for whether or not a wave is currently taking place
    private boolean waveHappening;
    
    //Index for the current wave that is taking place
    private int currentWave;
    
    //Enemies to be spawned within the current wave
    private Queue<Integer> currentEnemies;
    
    //Intervals between enemy spawns in the current wave
    private Queue<Integer> currentIntervals;
    
    //Number of enemies currently in the world
    private int enemyNumber;
    
    //Health the player has
    private int playerHealth;
    
    //Queue for the waves of enemies that will spawn during the level
    protected Queue<int[]> waves;
    
    //Queue for time intervals in between each enemy spawn during each wave
    protected Queue<int[]> intervals;
    
    //x and y coordinates for starting position
    protected int startX;
    protected int startY;
    
    //Image of menu for players to choose towers
    protected Image menu;
    
    //Boxes for towers
    protected Box box1;
    protected Box box2;
    protected Box box3;
    protected Box box4;
    protected Box box5;
    protected Box box6;
    
    //Index for which tower is selected (0 - none, 1 - top left, 2 - top right, 3 - middle left, 4 - middle right, 5 - bottom left, 6 - bottom right)
    protected int selectIndex;
    
    //ArrayList to hold tiles
    protected ArrayList<Tile> tiles;
    
    //TextLabel for player's health
    protected TextLabel playerHealthLabel;
    
    //Amount of money player has
    private int playerMoney;
    
    //TextLabel for player's money
    protected TextLabel playerMoneyLabel;
    
    //How much money the player gains after completing each wave
    protected Queue<Integer> moneyPerWave;
    
    //Images for heart and diamond icons
    protected Image heart;
    protected Image diamond;
    
    //If the start wave button is enabled
    private boolean isWaveButtonEnabled;
    
    //Settings button
    private Image settingsButton;
    
    //Boolean for whether the user is in the settings
    private boolean isInSettings;
    
    //Settings
    private Settings settings;
    
    //Index of which soundtrack is playing (0 - first soundtrack, 1 - second soundtrack, 2 - third soundtrack, 3 - no)
    protected int songIndex;
    
    //Whether or not the SFX are playing (0 - yes, 1 - no)
    protected int isSFX;
    
    //UserInfo
    protected UserInfo user;
    
    //GreenfootSound for the music
    protected GreenfootSound music;
    
    //Images containing stats of each of the towers
    private TowerStats singleshooterStats;
    private TowerStats singlelauncherStats;
    private TowerStats cannonStats;
    private TowerStats flamethrowerStats;
    private TowerStats archerStats;
    private TowerStats laserStats;
    
    /**
     * Constructor for Level Superclass, initializes starting values and adds all important graphics to the world.
     * 
     * @param startX        X-coordinate of where enemies will spawn
     * @param startY        Y-coordinate of where enemies will spawn
     * @param songIndex     Index of the song that is playing (0 - first soundtrack (default), 1 - second soundtrack, 2 - third soundtrack, 3 - none)
     * @param isSFX         If the SFX is playing (0 - yes (default), 1 - no)
     */
    public Level(int startX, int startY, int songIndex, int isSFX)
    {        
        //Create world of 1024x640 pixels, world is also boundless, makes projectiles and enemies leaving the bounds look much smoother
        super(1024, 640, 1, false); 
        
        //Initialize TextButton and add it to the world
        startWave = new Image(new GreenfootImage("startwavebuttonsmall.png"));
        addObject(startWave, 760, getHeight() - 45);
        
        //Initialize SimpleTimer to time when enemies will be spawned
        enemyTimer = new SimpleTimer();        
        
        //Initialize ArrayList for the path of coordinates the enemies will go through
        enemyPath = new ArrayList<Coordinate>();        
        
        //Wave is not currently taking place
        waveHappening = false;        
        
        //Current wave index is 0
        currentWave = 0;        
        
        //Initialize Queue for enemies that spawn
        currentEnemies = new LinkedList<Integer>();
        
        //Initalize Queue for intervals between enemy spawns
        currentIntervals = new LinkedList<Integer>();       
        
        //How many enemies are currently in the world
        enemyNumber = 0;        
        
        //Initialize the amount of health the player starts off with
        playerHealth = 100;        
        
        //Initialize Queue of array of integers to hold which types of enemies will spawn in each wave
        waves = new LinkedList<int[]>();      
        
        //Initialize Queue of array of integers for the milliseconds between each spawn in each wave
        intervals = new LinkedList<int[]>();
        
        //Start wave button is currently enabled
        isWaveButtonEnabled = true;
        
        //Set paint order (which objects will appear on top of which)
        setPaintOrder(InfoButton.class, Info.class, SettingsButton.class, Settings.class, TowerStats.class, Box.class, Image.class, TextLabel.class, Tower.class, Dragon.class, Tank.class, Wolf.class, Enemy.class, Projectile.class, Tile.class, Range.class);
        
        //Set start x and y coordinates
        this.startX = startX;
        this.startY = startY;
        
        //Initialize Menu and add to world
        menu = new Image(new GreenfootImage("menu.png"));
        addObject(menu, 910, 485);
        
        //Initializes Boxes with towers inside and add to world
        box1 = new Box(new GreenfootImage("singleshooter.png"));
        addObject(box1, 870, 405);
        
        box2 = new Box(new GreenfootImage("singlelauncher.png"));
        addObject(box2, 950, 405);
        
        box3 = new Box(new GreenfootImage("fullcannon.png"));
        addObject(box3, 870, 485);
        
        box4 = new Box(new GreenfootImage("flamethrower.png"));
        addObject(box4, 950, 485);
        
        box5 = new Box(new GreenfootImage("archer.png"));
        addObject(box5, 870, 565);
        
        box6 = new Box(new GreenfootImage("lasertowerfull.png"));
        addObject(box6, 950, 565);
        
        //No towers are selected right now
        selectIndex = 0;
        
        //Initialize ArrayList of tiles
        tiles = new ArrayList<Tile>();
        
        //Initialize player's health
        playerHealth = PLAYER_MAXHEALTH;
        
        //Initialize player health text label and add to world
        playerHealthLabel = new TextLabel(String.valueOf(playerHealth), 40, 5, new Color(178, 34, 34), new Color(255, 200, 200));
        addObject(playerHealthLabel, 100, getHeight() - 90);
        
        //Initialize player money text label and add to world
        playerMoney = 500;
        playerMoneyLabel = new TextLabel(String.valueOf(playerMoney), 40, 5, new Color(8, 96, 168), new Color(217, 229, 240));
        addObject(playerMoneyLabel, 100, getHeight() - 32);
        
        //Initialize and add heart and diamond images to the world
        heart = new Image(new GreenfootImage("heart.png"));
        diamond = new Image(new GreenfootImage("diamond.png"));
        addObject(heart, 25, getHeight() - 90);
        addObject(diamond, 25, getHeight() - 32);
        
        //Initialize and add settings button
        settingsButton = new Image(new GreenfootImage("settingsbutton.png"));
        addObject(settingsButton, 35, 35);
        
        //Initialize Queue of money per wave
        moneyPerWave = new LinkedList<Integer>();
        
        //Initialize boolean
        isInSettings = false;
        
        //If storage is available for the user
        if (UserInfo.isStorageAvailable()) {
            //Get the user's information
            user = UserInfo.getMyInfo();
            
            //If it is not null
            if (user != null) {
                //Get the information
                songIndex = user.getInt(0);
                isSFX = user.getInt(1);
            } else {
                //Otherwise set from parameter
                this.songIndex = songIndex;
                this.isSFX = isSFX;
            }
        } else {
            //Otherwise set from paramater
            this.songIndex = songIndex;
            this.isSFX = isSFX;
        }
        
        //Set it once again just in case the user disconnects from Internet
        this.songIndex = songIndex;
        this.isSFX = isSFX;
        
        //Set the type of soundtrack based on the sound index
        if (songIndex == 0) {
            music = new GreenfootSound("ashesonthefire.mp3");
        } else if (songIndex == 1) {
            music = new GreenfootSound("attackontitan.mp3");
        } else if (songIndex == 2) {
            music = new GreenfootSound("appleseed.mp3");
        } else {
            music = null;
        }
        
        //If the music is not null, start playing it
        if (music != null) {
            music.setVolume(20);
            music.playLoop();
        }
        
        //Initialize settings
        settings = new Settings(this);
        
        //Initialize images that will display when user clicks on a tower in the selection menu
        singleshooterStats = new TowerStats(new GreenfootImage("singleshooterstats.png"));
        singlelauncherStats = new TowerStats(new GreenfootImage("singlelauncherstats.png"));
        cannonStats = new TowerStats(new GreenfootImage("cannonstats.png"));
        flamethrowerStats = new TowerStats(new GreenfootImage("flamethrowerstats.png"));
        archerStats = new TowerStats(new GreenfootImage("archerstats.png"));
        laserStats = new TowerStats(new GreenfootImage("lasertowerstats.png"));
    }
    
    /**
     * Called every act, runs all the processes for the levels and calls respective methods to perform specific actions.
     */
    public void act()
    { 
        //Call method to check if the user has clicked start wave button
        checkClick();
        
        //Call method to spawn enemies
        spawnEnemies();
        
        //Call method to check if player has clicked on any towers in the menu
        checkSelection();
        
        //Changes image of button if player hovers over it while enabled
        if (isWaveButtonEnabled && Greenfoot.getMouseInfo() != null && Greenfoot.getMouseInfo().getX() >= 725 && Greenfoot.getMouseInfo().getX() <= 795 && Greenfoot.getMouseInfo().getY() >= 560 && Greenfoot.getMouseInfo().getY() <= 630) {
            startWave.setImage("startwavebuttonenlarge.png");
        } else {
            startWave.setImage("startwavebuttonsmall.png");
        }
        
        //If the wave button is not enabled, set the image of the start wave button to a gray color
        if (!isWaveButtonEnabled) {
            startWave.setImage("startwavebuttongray.png");
        }
        
        //Enlarge the settings button if the user is hovering over it, otherwise shrink it
        MouseInfo m = Greenfoot.getMouseInfo();
        if (m != null && m.getX() >= 10 && m.getX() <= 60 && m.getY() >= 10 && m.getY() <= 60) {
            settingsButton.setImage("settingsbuttonenlarge.png");
        } else {
            settingsButton.setImage("settingsbutton.png");
        }
        
        //If the user presses on the settings button, add the settings object to the world
        if (Greenfoot.mousePressed(settingsButton)) {            
            //Add Settings actor
            addObject(settings, 512, 320);
            
            //User is now in the settings
            isInSettings = true;
        }
        
        //If there are no more waves left and a wave is not happening
        if (waves.size() == 0 && !waveHappening) {
            //Call method to show user they succeeded
            showEndScreen(true);
        }
    }
    
    /**
     * Method that is called to return back to the home screen.
     */
    public void goBackHome()
    {
        //If music is playing stop it
        if (music != null) {
            music.stop();
        }
        
        //Create new instance of MainMenu world
        MainMenu menu = new MainMenu(songIndex, isSFX);
        
        //Set the world to the menu
        Greenfoot.setWorld(menu);
    }
    
    /**
     * Returns the SFX index (0 - yes SFX, 1 - no SFX)
     * 
     * @return int          SFX index (0 - yes SFX, 1 - no SFX)
     */
    public int getIsSFX()
    {
        return isSFX;
    }
    
    /**
     * Returns the index of the current soundtrack that is playing.
     * 
     * @return songIndex                Index of soundtrack (0 - first, 1 - second, 2 - third, 0 - none)
     */
    public int getSongIndex()
    {
        return songIndex;
    }
    
    /**
     * Called to change the sound effect settings.
     */
    public void changeIsSFX()
    {
        //Swap the values
        if (isSFX == 0) {
            isSFX = 1;
        } else {
            isSFX = 0;
        }
        
        //If the user information exists, change their settings and store it
        if (user != null) {
            user.setInt(1, isSFX);
            user.store();
        }
    }
    
    /**
     * Called to go to the next song, set the music, and play.
     */
    public void nextSong()
    {
        //Increment the song index, if it is greater than 3, set it back to 0
        songIndex++;
        if (songIndex > 3) {
            songIndex = 0;
        }
        
        //If user information exists, store the value
        if (user != null) {
            user.setInt(0, songIndex);
            user.store();
        }
            
        //If there is music, stop the music
        if (music != null && music.isPlaying()) {
            music.stop();
        }
            
        //Determine and set the music that should be playing
        if (songIndex == 0) {
            music = new GreenfootSound("ashesonthefire.mp3");
        } else if (songIndex == 1) {
            music = new GreenfootSound("attackontitan.mp3");
        } else if (songIndex == 2) {
            music = new GreenfootSound("appleseed.mp3");
        } else {
            music = null;
        }
        
        //If the music is not null, start playing it
        if (music != null) {
            music.setVolume(20);
            music.playLoop();
        }
    }
    
    /**
     * Abstract method for how the level will be restarted if the user chooses so.
     */
    public abstract void restartLevel();
    
    /**
     * Returns the tiles of the level.
     * 
     * @return ArrayList<Tile>      Tiles in the level
     */
    public ArrayList<Tile> getTiles()
    {
        return tiles;
    }
    
    /**
     * Returns the starting x-coordinate of the enemies in the level.
     * 
     * @return int          Starting x-coordinate of enemies in the level
     */
    public int getStartX()
    {
        return startX;
    }
    
    /**
     * Returns the starting y-coordinate of the enemies in the level.
     * 
     * @return int          Starting y-coordinate of enemies in the level
     */
    public int getStartY()
    {
        return startY;
    }
    
    /**
     * When enemies are defeated or the wave is completed, this method is called to give the player money.
     * 
     * @param amount        Amount of money player is receiving
     */
    public void addMoney(int amount) {
        //Add the money to the player's amount
        playerMoney += amount;
        
        //Update the label showing the player's money
        updateMoney();
    }
    
    /**
     * Called when enemies pass the last boundary and damages the player.
     * 
     * @param damage        Amount of damage done to the player.
     */
    public void takeDamage(int damage)
    {
        //Decrease the player's health
        playerHealth -= damage;
        
        //Update the label containing the player's health
        playerHealthLabel.update(String.valueOf(Math.max(playerHealth, 0)));
        
        //If the player loses all their health, show the level failed end screen
        if (playerHealth <= 0) {
            showEndScreen(false);
        }
    }
    
    /**
     * Called to show the different end screen, abstract as the restart button will go back to a different level.
     * 
     * @param isSuccess             Boolean for whether or not the user has succeeded (true - yes, false - no)
     */
    protected abstract void showEndScreen(boolean isSuccess);
    
    /**
     * Checks if any of the boxes on the menu have been selected.
     */
    private void checkSelection()
    {
        //When any of the boxes are clicked, the box will reverse its selection
        //It will also make other boxes not selected and update the index of the selected box accordingly
        if (Greenfoot.mouseClicked(box1)) {
            box1.setIsSelected(!box1.getIsSelected());
            box2.setIsSelected(false);
            box3.setIsSelected(false);
            box4.setIsSelected(false);
            box5.setIsSelected(false);
            box6.setIsSelected(false);
            if (box1.getIsSelected()) {
                selectIndex = 1;
            } else {
                selectIndex = 0;
            }
        } else if (Greenfoot.mouseClicked(box2)) {
            box2.setIsSelected(!box2.getIsSelected());
            box1.setIsSelected(false);
            box3.setIsSelected(false);
            box4.setIsSelected(false);
            box5.setIsSelected(false);
            box6.setIsSelected(false);
            if (box2.getIsSelected()) {
                selectIndex = 2;
            } else {
                selectIndex = 0;
            }
        } else if (Greenfoot.mouseClicked(box3)) {
            box3.setIsSelected(!box3.getIsSelected());
            box1.setIsSelected(false);
            box2.setIsSelected(false);
            box4.setIsSelected(false);
            box5.setIsSelected(false);
            box6.setIsSelected(false);
            if (box3.getIsSelected()) {
                selectIndex = 3;
            } else {
                selectIndex = 0;
            }
        } else if (Greenfoot.mouseClicked(box4)) {
            box4.setIsSelected(!box4.getIsSelected());
            box1.setIsSelected(false);
            box2.setIsSelected(false);
            box3.setIsSelected(false);
            box5.setIsSelected(false);
            box6.setIsSelected(false);
            if (box4.getIsSelected()) {
                selectIndex = 4;
            } else {
                selectIndex = 0;
            }
        } else if (Greenfoot.mouseClicked(box5)) {
            box5.setIsSelected(!box5.getIsSelected());
            box1.setIsSelected(false);
            box2.setIsSelected(false);
            box3.setIsSelected(false);
            box4.setIsSelected(false);
            box6.setIsSelected(false);
            if (box5.getIsSelected()) {
                selectIndex = 5;
            } else {
                selectIndex = 0;
            }
        } else if (Greenfoot.mouseClicked(box6)) {
            box6.setIsSelected(!box6.getIsSelected());
            box1.setIsSelected(false);
            box2.setIsSelected(false);
            box3.setIsSelected(false);
            box4.setIsSelected(false);
            box5.setIsSelected(false);
            if (box6.getIsSelected()) {
                selectIndex = 6;
            } else {
                selectIndex = 0;
            }
        } else if (Greenfoot.mouseClicked(this) || Greenfoot.mouseClicked(menu) || Greenfoot.mouseClicked(startWave) || Greenfoot.mouseClicked(singleshooterStats) || Greenfoot.mouseClicked(singlelauncherStats) || Greenfoot.mouseClicked(cannonStats) || Greenfoot.mouseClicked(flamethrowerStats) || Greenfoot.mouseClicked(archerStats) || Greenfoot.mouseClicked(laserStats)) {
            box1.setIsSelected(false);
            box2.setIsSelected(false);
            box3.setIsSelected(false);
            box4.setIsSelected(false);
            box5.setIsSelected(false);
            box6.setIsSelected(false);
            selectIndex = 0;
        }
        
        //Check if any of the boxes are selected and if so add relative money stats for the tower, and if not remove it
        if (box1.getIsSelected()) {
            addObject(singleshooterStats, box1.getX(), box1.getY());
        } else {
            removeObject(singleshooterStats);
        }
        
        if (box2.getIsSelected()) {
            addObject(singlelauncherStats, box2.getX(), box2.getY());
        } else {
            removeObject(singlelauncherStats);
        }
        
        if (box3.getIsSelected()) {
            addObject(cannonStats, box3.getX(), box3.getY());
        } else {
            removeObject(cannonStats);
        }
        
        if (box4.getIsSelected()) {
            addObject(flamethrowerStats, box4.getX(), box4.getY());
        } else {
            removeObject(flamethrowerStats);
        }
        
        if (box5.getIsSelected()) {
            addObject(archerStats, box5.getX(), box5.getY());
        } else {
            removeObject(archerStats);
        }
        
        if (box6.getIsSelected()) {
            addObject(laserStats, box6.getX(), box6.getY());
        } else {
            removeObject(laserStats);
        }
    }
    
    /**
     * Adds a tower to the relevant tile if the player has enough money.
     * 
     * @param tileIndex         Index of the tile where the tower is being placed.
     */
    public void addTower(int tileIndex)
    {
        //Select index is the tower that is currently selected in the meny
        //For each index, if the player has enough money, the tower will be placed and the player's money will be decreased accordingly
        if (selectIndex == 1) {
            if (playerMoney >= SINGLESHOOTER_COST) {
                playerMoney -= SINGLESHOOTER_COST;
                Range r = new Range(SINGLESHOOTER_RANGE);
                addObject(r, tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                Base b = new Base(0);
                addObject(b, tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                addObject(new SingleShooter(tileIndex, b, r, this), tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                tiles.get(tileIndex).setTransparency(0);
            }
        } else if (selectIndex == 2) {
            if (playerMoney >= SINGLELAUNCHER_COST) {
                playerMoney -= SINGLELAUNCHER_COST;
                Range r = new Range(SINGLELAUNCHER_RANGE);
                addObject(r, tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                Base b = new Base(0);
                addObject(b, tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                addObject(new SingleLauncher(tileIndex, b, r, this), tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                tiles.get(tileIndex).setTransparency(0);
            }
        } else if (selectIndex == 3) {
            if (playerMoney >= CANNON_COST) {
                playerMoney -= CANNON_COST;
                Range r = new Range(CANNON_RANGE);
                addObject(r, tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                Base b = new Base(1);
                addObject(b, tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                addObject(new Cannon(tileIndex, b, r, this), tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                tiles.get(tileIndex).setTransparency(0);
            }
        } else if (selectIndex == 4) {
            if (playerMoney >= FLAMETHROWER_COST) {
                playerMoney -= FLAMETHROWER_COST;
                Range r = new Range(FLAMETHROWER_RANGE);
                addObject(r, tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                Base b = new Base(0);
                addObject(b, tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                addObject(new Flamethrower(tileIndex, b, r, this), tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                tiles.get(tileIndex).setTransparency(0);
            }
        } else if (selectIndex == 5) {
            if (playerMoney >= ARCHER_COST) {
                playerMoney -= ARCHER_COST;
                Range r = new Range(ARCHER_RANGE);
                addObject(r, tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                addObject(new Archer(tileIndex, r, this), tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                tiles.get(tileIndex).setTransparency(0);
            }
        } else if (selectIndex == 6) {
            if (playerMoney >= LASERTOWER_COST) {
                playerMoney -= LASERTOWER_COST;
                Range r = new Range(LASERTOWER_RANGE);
                addObject(r, tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                Base b = new Base(2);
                addObject(b, tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                addObject(new LaserTower(tileIndex, b, r, this), tiles.get(tileIndex).getX(), tiles.get(tileIndex).getY());
                tiles.get(tileIndex).setTransparency(0);
            }
        }
        
        //Updates the amount of money the player has
        updateMoney();
    }
    
    /**
     * Decrements the number of enemies in the world by 1.
     */
    public void removeEnemy()
    {
        //Decrease number of enemies by 1
        enemyNumber--;
    }
    
    /**
     * Updates the amount of money the user has.
     */
    private void updateMoney()
    {
        //Update the label that contains the amount of money the player has
        playerMoneyLabel.update(String.valueOf(playerMoney));
    }
    
    /**
     * Checks if the start wave button has been clicked. 
     */
    private void checkClick()
    {
        //Continue if the user has pressed the button, there is no wave happening, and the button is not disabled
        if (Greenfoot.mousePressed(startWave) && !waveHappening && !startWave.getImage().equals(new GreenfootImage("startwavebuttongray.png")) && isWaveButtonEnabled) {
            
            //If there are still waves left to be created
            if (waves.size() > 0) {
                //Call method to create next wave
                createWave();
                
                //Button is disabled
                isWaveButtonEnabled = false;
                
                //Wave is now happening
                waveHappening = true;
            }
        }
    }
    
    /**
     * Creates the next wave of enemies.
     */
    private void createWave()
    {
        //Increment the current wave number
        currentWave++;
        
        //Take the first array of integers from the waves and intervals
        int[] enemiesTemp = waves.poll();
        int[] intervalsTemp = intervals.poll();
        
        //Add each enemy to the current enemies to be spawned
        for (int enemy : enemiesTemp) {
            currentEnemies.add(enemy);
        }
        
        //Add each interval to the current intervals in between spawns
        for (int interval : intervalsTemp) {
            currentIntervals.add(interval);
        }
        
        //Start the timer
        enemyTimer.mark();
    }
    
    /**
     * Makes decisions on whether or not to spawn enemies.
     */
    private void spawnEnemies()
    {
        //If the Queues of enemies and intervals are not empty
        if (currentEnemies.size() > 0 && currentIntervals.size() > 0) {
            
            //If the time interval between times has passed
            if (enemyTimer.millisElapsed() >= currentIntervals.peek()) {
                
                //Determine which enemy to spawn based on integer (0 - humanoid, 1 - tank, 2 - robot, 3 - dragon, 4 - wolf)
                int enemyType = currentEnemies.poll();
                if (enemyType == 0) {
                    Humanoid h = new Humanoid(enemyPath, this);
                    addObject(h, startX, startY);
                    enemyNumber++;
                } else if (enemyType == 1) {
                    Tank t = new Tank(enemyPath, this);
                    addObject(t, startX, startY);
                    enemyNumber++;
                } else if (enemyType == 2) {
                    Robot r = new Robot(enemyPath, this);
                    addObject(r, startX, startY);
                    enemyNumber++;
                } else if (enemyType == 3) {
                    Dragon d = new Dragon(enemyPath, this);
                    addObject(d, startX, startY);
                    enemyNumber++;
                } else if (enemyType == 4) {
                    Wolf w = new Wolf(enemyPath, this);
                    addObject(w, startX, startY);
                    enemyNumber++;
                }
                
                //Remove the interval at the beginning of the Queue
                currentIntervals.poll();
                
                //Start the timer
                enemyTimer.mark();
            }
        } else if (enemyNumber == 0) { //If there are no enemies left
            //Enable the button
            startWave.setImage("startwavebuttonsmall.png");
            isWaveButtonEnabled = true;
            
            //Give user the money for the wave
            if (waveHappening == true) {
                addMoney(moneyPerWave.poll());
            }
            
            //Wave is not currently happening
            waveHappening = false;
        }
    }
}
