import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util

/**
 * Tank Class - Inherits from Enemy Superclass, a tank enemy that moves throughout the course and shoots at towers.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Tank extends Enemy
{        
    //Instance variables and objects
    
    //Counts the time until reload
    private int reloadCounter;
    
    //Time for reloading in acts
    private int reloadTime;
    
    //Declare array for sound effect for shooting
    private GreenfootSound[] shootingSounds;
    
    //Index to keep track of index of sound effect array
    private int shootingSoundsIndex;
    
    /**
     * Constructor for Tank Class, calls superclass constructor, sets images, and other variables.
     * 
     * @param path          ArrayList of coordinates the Tank will follow
     * @param level         Level the Tank is in
     */
    public Tank(ArrayList<Coordinate> path, Level level)
    {
        //Call superclass constuctor
        super(path, level);
        
        //Determine which picture to display
        if (Greenfoot.getRandomNumber(2) == 0) {
            setImage("tank1.png");
        } else {
            setImage("tank2.png");
        }
        
        //Set speed of tank
        speed = Level.TANK_SPEED; 
        
        //Set maxHP of tank
        maxHP = Level.TANK_HEALTH;
        
        //Set currentHP
        currentHP = maxHP;
        
        //Set range
        range = Level.TANK_RANGE;
        
        //Set reload time
        reloadTime = Level.TANK_RELOAD;
        
        //Initialize reload counter
        reloadCounter = 0;
        
        //Initialize player damage
        playerDamage = Level.TANK_PLAYERDAMAGE;
        
        //Initialize amount of money player gains by defeating it
        defeatMoney = Level.TANK_MONEY;
        
        //Fill the array with the sound effect
        shootingSounds = new GreenfootSound[40];
        for (int i = 0; i < shootingSounds.length; i++) {
            shootingSounds[i] = new GreenfootSound("fire.wav");
            shootingSounds[i].setVolume(70);
        }
        shootingSoundsIndex = 0;
    }
    
    /**
     * Called every act, finds the nearest tower and determines if to shoot or move.
     */
    public void act() 
    {
        //Find the nearest tower
        Tower towerTarget = findTower();
        
        //If there are none move
        if (towerTarget == null) {
            move();
        } else { //If there is
            //Turn towards the enemy
            turnTowards(towerTarget.getX(), towerTarget.getY());
            
            //If reload has completed
            if (reloadCounter == reloadTime) {
                //Shoot the enemy
                shoot(towerTarget);
                
                //Reset the reload counter
                reloadCounter = 0;
                
                //If SFX are switched on, play the sound
                if (level.getIsSFX() == 0) {
                    shootingSounds[shootingSoundsIndex].play();
                    shootingSoundsIndex++;
                    if (shootingSoundsIndex >= shootingSounds.length) {
                        shootingSoundsIndex = 0;
                    }
                }
            } else { //If reload has not completed
                //Increment the reload counter
                reloadCounter++;
            }
        }
    }
    
    /**
     * Shoots at a tower.
     * 
     * @param a         Tower to be shot at
     */
    private void shoot(Actor a)
    {
        //Create a SmallBullet at the current location aimed at the tower
        getWorld().addObject(new SmallBullet(a), getX(), getY());
    }
}
