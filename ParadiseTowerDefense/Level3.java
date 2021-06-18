import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Level3 Class - The third level the user will play, inherits from Level Superclass.
 * 
 * @author Andrew Qiao, James Li, Brendan Chan
 * @version June 2021
 */
public class Level3 extends Level
{
    //Instance variables/objects
    
    //2D array for coordinates that enemies move along
    final int[][] enemyPathCoordinates = {{800, 224}, {800, 32}, {608, 32}, {608, 544}, {352, 544}, {352, 352}, {224, 352}, {224, -50}};
    
    //2D array for enemies that will spawn each wave
    final int[][] enemyWaves = {{0, 0, 0}, {2, 0, 1}, {0, 1, 0, 0, 2}, {0, 1, 1, 0, 0, 3, 1, 0}, {0, 1, 1, 0, 3, 1, 0, 0, 0, 0, 1, 0, 3}, {0, 1, 0, 0, 3, 1, 0, 0, 2, 0, 1, 0, 2, 0, 0}, {0, 2, 2, 0, 0, 0, 0, 3, 0, 0, 1, 1, 3, 0, 0, 1, 0, 0, 1}, {3, 0, 1, 2, 1, 2, 2, 0, 1, 0, 1, 0, 1, 1, 2, 1, 0, 3, 0, 0}};
    
    //2D array for time intervals between each enemy spawn (in milliseconds)
    final int[][] enemyIntervals = {{50, 5000, 6000}, {2000, 2100, 2050}, {2000, 2100, 2050, 3000, 1200}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050, 2000, 2100, 2050, 3000, 1200}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050, 2000, 2100, 2050, 3000, 1200, 3000, 1200}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050, 2000, 2100, 2050, 3000, 1200, 3000, 1200, 3000, 1200, 3000, 1200}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050, 2000, 2100, 2050, 3000, 1200, 3000, 1200, 3000, 1200, 3000, 1200, 2000}};
    
    //Array for money user gains per wave
    final int[] waveMoney = {200, 200, 200, 300, 300, 300, 4000, 400};
    
    //Tiles where towers will be placed
    private Tile tile1;
    private Tile tile2;
    private Tile tile3;
    private Tile tile4;
    private Tile tile5;
    private Tile tile6;
    private Tile tile7;
    private Tile tile8;
    private Tile tile9;
    private Tile tile10;
    private Tile tile11;
    private Tile tile12;
    
    /**
     * Constructor for instances of Class Level3, calls superclass constructor and sets essential values.
     * 
     * @param songIndex             Which soundtrack, if any, is playing (0 - first, 1 - second, 2 - third, 3 - none)
     * @param isSFX                 Integer for whether or not the SFX is playing (0 - yes, 1 - no)
     */
    public Level3(int songIndex, int isSFX)
    {
        //Calls superclass constructor and sends in starting coordinates of the enemy
        super(1049, 224, songIndex, isSFX);
        
        //Set starting coordinates for the enemies
        startX = 1049;
        startY = 224;
        
        //Add coordinates to ArrayList of coordinates that enemies will pass through
        for (int[] coordinate : enemyPathCoordinates) {
            enemyPath.add(new Coordinate(coordinate[0], coordinate[1]));
        }
        
        //Add enemies that spawn each wave to Queue of enemy waves
        for (int[] enemyWave : enemyWaves) {
            waves.add(enemyWave);
        }
        
        //Add intervals in between each enemy spawn to Queue of intervals
        for (int[] interval : enemyIntervals) {
            intervals.add(interval);
        }
        
        //Add tiles that will be where towers are placed
        tile1 = new Tile(0);
        addObject(tile1, 160, 96);
        tile2 = new Tile(1);
        addObject(tile2, 160, 352);
        tile3 = new Tile(2);
        addObject(tile3, 288, 160);
        tile4 = new Tile(3);
        addObject(tile4, 288, 416);
        tile5 = new Tile(4);
        addObject(tile5, 400, 300);
        tile6 = new Tile(5);
        addObject(tile6, 416, 608);
        tile7 = new Tile(6);
        addObject(tile7, 608, 608);
        tile8 = new Tile(7);
        addObject(tile8, 672, 352);
        tile9 = new Tile(8);
        addObject(tile9, 672, 96);
        tile10 = new Tile(9);
        addObject(tile10, 864, 160);
        tile11 = new Tile(10);
        addObject(tile11, 928, 288);
        
        //Add tiles to ArrayList of tiles
        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        tiles.add(tile4);
        tiles.add(tile5);
        tiles.add(tile6);
        tiles.add(tile7);
        tiles.add(tile8);
        tiles.add(tile9);
        tiles.add(tile10);
        tiles.add(tile11);
        
        //Initialize amount of money per wave
        for (int money : waveMoney) {
            moneyPerWave.add(money);
        }
    }
    
    /**
     * Called when the use clicks the restart button in the settings, restarts the level.
     */
    public void restartLevel()
    {
        //If there is music stop it
        if (music != null) {
            music.stop();
        }
        
        //Create new instance of Level3
        Level3 level3 = new Level3(songIndex, isSFX);
        
        //Set the current world to the new instance of Level3
        Greenfoot.setWorld(level3);
    }
    
    /**
     * Called to show the end screen.
     * 
     * @param isSuccess                     Boolean for whether or not the user has succeeded
     */
    protected void showEndScreen(boolean isSuccess)
    {
        //If the music is not null stop it
        if (music != null) {
            music.stop();
        }
        
        //If the user succeeds, give them the reward and store it
        if (isSuccess) {
            if (user != null) {
                user.setScore(user.getScore() + LEVEL3REWARD);
                user.store();
            }
        }
        
        //Create new instance of the end screen and set the world to that
        LevelComplete endScreen = new LevelComplete(songIndex, isSFX, 3, isSuccess);
        Greenfoot.setWorld(endScreen);
    }
}
