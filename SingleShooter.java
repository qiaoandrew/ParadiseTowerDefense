import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * SingleShooter Class - Inherits from the Tower superclass, shoots a single bullet at enemies.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class SingleShooter extends Tower
{
    /**
     * Constructor for objects of class SingleShooter, calls superclass and initializes starting values.
     * 
     * @param tileIndex             Index of the tile the SingleShooter will be on
     * @param base                  Base of the tower
     * @param rangeCircle           Circle displaying the range of the SingleShooter
     * @param level                 Level the tower is in
     */
    public SingleShooter(int tileIndex, Base base, Range rangeCircle, Level level)
    {
        //Calls superclass constructor
        super(tileIndex, rangeCircle, level);
        
        //Initialize starting values
        
        //Set the maximum HP
        maxHP = Level.SINGLESHOOTER_HEALTH;
        
        //Set the current HP
        currentHP = maxHP;
        
        //Set the range
        range = Level.SINGLESHOOTER_RANGE;
        
        //Set the reload time
        reloadTime = Level.SINGLESHOOTER_RELOAD;
        
        //Set the base of the tower
        this.base = base;
    }
}
