import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Arrow Class - Arrow shot out by Archers.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Arrow extends Projectile
{
    /**
     * Constructor for Arrow Class, calls superclass and initializes starting values.
     * 
     * @param target            Target actor of the Arrow
     */
    public Arrow(Actor target)
    {
        //Calls superclass constructor
        super(target);
        
        //Initialize values for damage and speed
        damage = ((Level)getWorld()).ARROW_DAMAGE;
        speed = ((Level)getWorld()).ARROW_SPEED;
    }    
}
