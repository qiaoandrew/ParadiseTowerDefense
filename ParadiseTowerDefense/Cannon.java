import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Cannon Class - Inherits from the Tower superclass, shoots cannonballs at enemies.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Cannon extends Tower
{    
    /**
     * Constructor for objects of class Cannon, calls superclass and initializes starting values.
     * 
     * @param tileIndex             Index of the tile the Cannon will be on
     * @param base                  Base of the tower
     * @param rangeCircle           Circle that displays the range
     * @param level                 Level the Cannon is in
     */
    public Cannon(int tileIndex, Base base, Range rangeCircle, Level level)
    {
        //Call superclass constructor
        super(tileIndex, rangeCircle, level);
        
        //Initialize starting values
        
        //Initialize maximum HP
        maxHP = Level.CANNON_HEALTH;
        
        //Initialize current HP
        currentHP = maxHP;
        
        //Initialize range
        range = Level.CANNON_RANGE;
        
        //Initialize the reload time
        reloadTime = Level.CANNON_RELOAD;
        
        //Set the base
        this.base = base;
    }
}
