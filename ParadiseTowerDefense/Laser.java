import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Laser Class - Laser shot out from LaserTowers.
 * 
 * @author Andrew Qiao 
 * @version June 2021
 */
public class Laser extends Projectile
{
    /**
     * Constructor for Laser Class.
     * 
     * @param target            Target of the laser
     */
    public Laser(Actor target)
    {
        //Calls superclass constructor
        super(target);
        
        //Initialize values for damage and speed
        damage = ((Level)getWorld()).LASER_DAMAGE;
        speed = ((Level)getWorld()).LASER_SPEED;
    }
}
