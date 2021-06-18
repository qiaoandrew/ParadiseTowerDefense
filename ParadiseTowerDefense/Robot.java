import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util

/**
 * Robot Class - Similiar to the humanoid, but faster and has more health.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Robot extends Enemy
{
    /**
     * Robot constructor, calls superclass and initializes starting values.
     * 
     * @param path          ArrayList of coordinates the robot will follow
     * @param level         Level the robot is in
     */
    public Robot(ArrayList<Coordinate> path, Level level)
    {
        //Call superclass constructor
        super(path, level);
        
        //Set image based on random number
        int imageType = Greenfoot.getRandomNumber(4);
        if (imageType == 0) {
            setImage("robot1.png");
        } else if (imageType == 1) {
            setImage("robot2.png");
        } else if (imageType == 2) {
            setImage("robot3.png");
        } else {
            setImage("robot4.png");
        }
        
        //Set speed of the humanoid
        speed = Level.ROBOT_SPEED;
        
        //Set max HP of humanoid
        maxHP = Level.ROBOT_HEALTH;
        
        //Set current HP of humanoid
        currentHP = maxHP;
        
        //Initialize player damage
        playerDamage = Level.ROBOT_PLAYERDAMAGE;
        
        //Initialize amount of money player gains by defeating it
        defeatMoney = Level.ROBOT_MONEY;
    }
}
