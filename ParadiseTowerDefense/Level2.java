import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Level2 Class - The second level the user will play, inherits from Level Superclass.
 * 
 * @author Andrew Qiao, Ryo Minakami
 * @version June 2021
 */
public class Level2 extends Level
{
    //Instance variables/objects
    
    //2D array for coordinates that enemies move along
    final int[][] enemyPathCoordinates = {{896, 256}, {704, 256}, {704, 512}, {384, 512}, {384, 384}, {256, 384}, {256, 192}, {-50, 192}};
    
    //2D array for enemies that will spawn each wave
    final int[][] enemyWaves = {{0, 0}, {0, 0, 1, 0, 2}, {4, 1, 2, 2, 1, 0}, {0, 1, 1, 0, 2, 4, 1, 3}, {0, 1, 1, 0, 4, 0, 2, 0, 2, 0, 1, 2, 3}, {0, 1, 2, 4, 2, 1, 3, 0, 3, 2, 1, 0, 1, 0, 3}, {0, 0, 2, 4, 3, 2, 1, 4, 2, 0, 0, 0, 2, 0, 0, 2, 1, 4, 3}, {4, 4, 2, 0, 1, 0, 0, 2, 1, 3, 1, 3, 0, 4, 1, 2, 0, 1, 3, 1}};
    
    //2D array for time intervals between each enemy spawn (in milliseconds)
    final int[][] enemyIntervals = {{50, 7000}, {2000, 2100, 2050, 3500, 1900}, {2000, 2100, 2050, 3000, 1200, 1000}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050, 2000, 2100, 2050, 3000, 1200}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050, 2000, 2100, 2050, 3000, 1200, 3000, 1200}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050, 2000, 2100, 2050, 3000, 1200, 3000, 1200, 3000, 1200, 3000, 1200}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050, 2000, 2100, 2050, 3000, 1200, 3000, 1200, 3000, 1200, 3000, 1200, 2000}};
    
    //Array for money user gains per wave
    final int[] waveMoney = {300, 300, 500, 700, 500, 500, 750, 750};
    
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
     * Constructor for instances of Class Level2, calls superclass constructor and sets essential values.
     * 
     * @param songIndex             Which soundtrack, if any, is playing (0 - first, 1 - second, 2 - third, 3 - none)
     * @param isSFX                 Integer for whether or not the SFX is playing (0 - yes, 1 - no)
     */
    public Level2(int songIndex, int isSFX)
    {
        //Calls superclass constructor and sends in starting coordinates of the enemy
        super(896, -25, songIndex, isSFX);
        
        //Set the starting coordinates of the enemies
        startX = 896;
        startY = -25;
        
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
        addObject(tile2, 160, 288);
        tile3 = new Tile(2);
        addObject(tile3, 288, 96);
        tile4 = new Tile(3);
        addObject(tile4, 288, 480);
        tile5 = new Tile(4);
        addObject(tile5, 352, 292);
        tile6 = new Tile(5);
        addObject(tile6, 480, 416);
        tile7 = new Tile(6);
        addObject(tile7, 608, 224);
        tile8 = new Tile(7);
        addObject(tile8, 608, 416);
        tile9 = new Tile(8);
        addObject(tile9, 800, 32);
        tile10 = new Tile(9);
        addObject(tile10, 800, 160);
        tile11 = new Tile(10);
        addObject(tile11, 992, 224);
        tile12 = new Tile(11);
        addObject(tile12, 992, 32);
        
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
        tiles.add(tile12);
        
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
        //If there is music, start looping it
        if (music != null) {
            music.stop();
        }
        
        //Creates new instance of Level2
        Level2 level2 = new Level2(songIndex, isSFX);
        
        //Sets the current world to that new instance
        Greenfoot.setWorld(level2);
    }
    
    /**
     * Called to show the ending screen.
     * 
     * @param isSuccess             Boolean for whether or not the user succeeded
     */
    protected void showEndScreen(boolean isSuccess)
    {
        //If there is music, stop it
        if (music != null) {
            music.stop();
        }
        
        //Give use the reward if they succeed and there is user information and store it
        if (isSuccess) {
            if (user != null) {
                user.setScore(user.getScore() + LEVEL2REWARD);
                user.store();
            }
        }
        
        //Create new instance of the end screen and set the world to that
        LevelComplete endScreen = new LevelComplete(songIndex, isSFX, 2, isSuccess);
        Greenfoot.setWorld(endScreen);
    }
}
