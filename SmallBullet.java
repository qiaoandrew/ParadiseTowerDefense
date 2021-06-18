import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * SmallBullet class - Two small bullets that are shot out of DoubleShooter towers.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class SmallBullet extends Projectile
{
    /**
     * Constructor for objects of class SmallBullet, calls superclass constructor and initalizes starting values.
     * 
     * @param target        Target of the bullet.
     */
    public SmallBullet(Actor target)
    {
        //Calls superclass constructor
        super(target);
        
        //Initialize values for damage and speed
        damage = ((Level)getWorld()).SMALLBULLET_DAMAGE;
        speed = ((Level)getWorld()).SMALLBULLET_SPEED;
    } 
}
