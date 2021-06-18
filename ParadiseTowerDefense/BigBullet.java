import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BigBullet class - A big bullet that is shot out of SingleShooter towers.
 * 
 * @author Ryo Minakami
 * @version June 2021
 */
public class BigBullet extends Projectile
{
    /**
     * Constructor for objects of class BigBullet, calls superclass constructor and initalizes starting values.
     * 
     * @param target        Target of the bullet.
     */
    public BigBullet(Actor target)
    {
        //Calls superclass constructor
        super(target);
        
        //Initialize values for damage and speed
        damage = ((Level)getWorld()).BIGBULLET_DAMAGE;
        speed = ((Level)getWorld()).BIGBULLET_SPEED;
    }
}
