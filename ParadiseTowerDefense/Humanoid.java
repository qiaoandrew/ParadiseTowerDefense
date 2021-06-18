import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util

/**
 * Humanoid Class - Inherits from Enemy Superclass, a humanoid enemy that moves through the course.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Humanoid extends Enemy
{
    /**
     * Constructor for Humanoid Actors.
     * 
     * @param path      Path that the humanoid will follow
     * @param level     Level that the humanoid is in
     */
    public Humanoid(ArrayList<Coordinate> path, Level level)
    {
        //Call superclass constructor
        super(path, level);
        
        //Determine which image based on a random number
        int imageType = Greenfoot.getRandomNumber(3);
        if (imageType == 0) {
            setImage("humanoid1.png");
        } else if (imageType == 1) {
            setImage("humanoid2.png");
        } else {
            setImage("humanoid3.png");
        }
        
        //Set speed of the humanoid
        speed = Level.HUMANOID_SPEED;
        
        //Set max HP of humanoid
        maxHP = Level.HUMANOID_HEALTH;
        
        //Set current HP of humanoid
        currentHP = maxHP;
        
        //Initialize player damage
        playerDamage = Level.HUMANOID_PLAYERDAMAGE;
        
        //Initialize amount of money player gains by defeating it
        defeatMoney = Level.HUMANOID_MONEY;
    }
}
