import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Cannonball Class - Cannonball shot out by Cannons.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Cannonball extends Projectile
{
    /**
     * Constructor for objects of class Cannonball, calls superclass constructor and initalizes starting values.
     * 
     * @param target        Target of the projectile.
     */
    public Cannonball(Actor target)
    {
        //Calls superclass constructor
        super(target);
        
        //Initialize values for damage and speed
        damage = ((Level)getWorld()).CANNONBALL_DAMAGE;
        speed = ((Level)getWorld()).CANNONBALL_SPEED;
    }    
}
