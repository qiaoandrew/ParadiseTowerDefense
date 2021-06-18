import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util
import java.util.Queue; //Import Queue from java.util
import java.util.LinkedList; //Import LinkedList from java.util

/**
 * Level1 Class - The first level the user will play, gives a simple introduction to the game, inherits from Level Superclass.
 * 
 * @author Andrew Qiao, Ryo Minakami
 * @version June 2021
 */
public class Level1 extends Level
{
    //Instance variables/objects
    
    //2D array for coordinates that enemies move along
    final int[][] enemyPathCoordinates = {{704, 128}, {704, 320}, {512, 320}, {512, 64}, {192, 64}, {192, 448}, {384, 448}, {384, 700}};
    
    //2D array for enemies that will spawn each wave
    final int[][] enemyWaves = {{0, 0}, {0, 0, 1}, {0, 1, 1, 0, 2}, {0, 1, 2, 4, 0, 2, 1, 0}, {0, 0, 1, 2, 4, 0, 2, 1, 2, 4, 0, 0, 3}, {0, 1, 0, 0, 0, 0, 0, 2, 2, 0, 1, 2, 4, 4, 1}, {0, 0, 1, 2, 2, 1, 0, 2, 0, 0, 1, 0, 3, 1, 2, 1, 0, 2, 4}, {4, 2, 2, 1, 2, 3, 1, 4, 1, 0, 1, 3, 4, 2, 2, 1, 4, 2, 0, 3}};
    
    //2D array for time intervals between each enemy spawn (in milliseconds)
    final int[][] enemyIntervals = {{50, 10000}, {2000, 4100, 2050}, {2000, 2100, 2050, 3000, 1200}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050, 2000, 2100, 2050, 3000, 1200}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050, 2000, 2100, 2050, 3000, 1200, 3000, 1200}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050, 2000, 2100, 2050, 3000, 1200, 3000, 1200, 3000, 1200, 3000, 1200}, {2000, 2100, 2050, 3000, 1200, 2000, 2100, 2050, 2000, 2100, 1050, 1000, 1200, 1000, 1200, 1000, 1200, 1000, 1200, 1000}};
    
    //Array for money user gains per wave
    final int[] waveMoney = {200, 200, 250, 300, 300, 300, 400, 400};
    
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
    private Tile tile13;
    private Tile tile14;
    private Tile tile15;
    
    /**
     * Constructor for instances of Class Level1, calls superclass constructor and sets essential values.
     * 
     * @param songIndex             Index of the soundtrack that is currently playing (0 - first song, 1 - second song, 2 - third song, 3 - none)
     * @param isSFX                 If the SFX is playing (0 - yes, 1 - no)
     */
    public Level1(int songIndex, int isSFX)
    {    
        //Call superclass constructor and passes in starting coordinates of the enemy
        super(1050, 128, songIndex, isSFX);
        
        //Initialize the starting x and y values
        startX = 1050;
        startY = 128;
        
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
        addObject(tile1, 96, 96);
        tile2 = new Tile(1);
        addObject(tile2, 96, 288);
        tile3 = new Tile(2);
        addObject(tile3, 96, 480);
        tile4 = new Tile(3);
        addObject(tile4, 288, 160);
        tile5 = new Tile(4);
        addObject(tile5, 288, 352);
        tile6 = new Tile(5);
        addObject(tile6, 288, 544);
        tile7 = new Tile(6);
        addObject(tile7, 416, 160);
        tile8 = new Tile(7);
        addObject(tile8, 480, 416);
        tile9 = new Tile(8);
        addObject(tile9, 480, 608);
        tile10 = new Tile(9);
        addObject(tile10, 608, 96);
        tile11 = new Tile(10);
        addObject(tile11, 608, 224);
        tile12 = new Tile(11);
        addObject(tile12, 672, 416);
        tile13 = new Tile(12);
        addObject(tile13, 736, 32);
        tile14 = new Tile(13);
        addObject(tile14, 864, 224);
        tile15 = new Tile(14);
        addObject(tile15, 928, 32);
        
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
        tiles.add(tile13);
        tiles.add(tile14);
        tiles.add(tile15);
        
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
        //If there is music, stop the music
        if (music != null) {
            music.stop();
        }
        
        //Create a new instance of Level1
        Level1 level1 = new Level1(songIndex, isSFX);
        
        //Set the current world to that new instance
        Greenfoot.setWorld(level1);
    }
    
    /**
     * Called to show the end screen after the level has either been completed or failed.
     * 
     * @param isSuccess             Boolean for if the level has been a success
     */
    protected void showEndScreen(boolean isSuccess)
    {
        //If there is music playing, stop it
        if (music != null) {
            music.stop();
        }
        
        //If the level was a success, add to the user's score (money) and store
        if (isSuccess) {
            if (user != null) {
                user.setScore(user.getScore() + LEVEL1REWARD);
                user.store();
            }
        }
        
        //Create a new instance of the end screen and pass in parameters
        LevelComplete endScreen = new LevelComplete(songIndex, isSFX, 1, isSuccess);
        
        //Set the world to the new end screen
        Greenfoot.setWorld(endScreen);
    }
}
