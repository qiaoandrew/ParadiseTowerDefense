import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util package

/**
 * Wolf Class - A pack of wolves, move quickly do not attack towers.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Wolf extends Enemy
{
    //Instance variables
    
    //Integer for counting acts
    private int actCount;
    
    /**
     * Constructor for Wolf Class, initializes starting values.
     * 
     * @param path          ArrayList of coordinates the Wolf will follow
     * @param level         Level the Wolf is in
     */
    public Wolf(ArrayList<Coordinate> path, Level level)
    {
        //Call superclass constructor
        super(path, level);
        
        //Set speed of the humanoid
        speed = Level.WOLF_SPEED;
        
        //Set max HP of humanoid
        maxHP = Level.WOLF_HEALTH;
        
        //Set current HP of humanoid
        currentHP = maxHP;
        
        //Start act counter at 0
        actCount = 0;
        
        //Initialize player damage
        playerDamage = Level.WOLF_PLAYERDAMAGE;
        
        //Initialize amount of money player gains by defeating it
        defeatMoney = Level.WOLF_MONEY;
    }  
    
    /**
     * Called every act.
     */
    public void act() 
    {
        //Change the image depending on act counter, creates a 7-frame animation
        if (actCount % 100 == 0) {
            setImage("wolf1.png");
        } else if (actCount % 100 == 20) {
            setImage("wolf2.png");
        } else if (actCount % 100 == 40) {
            setImage("wolf3.png");
        } else if (actCount % 100 == 60) {
            setImage("wolf4.png");
        } else if (actCount % 100 == 80) {
            setImage("wolf5.png");
        }
        
        //Call method to move
        move();
        
        //Increment act counter
        actCount++;
    }
}
