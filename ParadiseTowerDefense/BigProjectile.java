import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BigProjectile class - A big projectile that is shot out of SingleLaucher towers.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class BigProjectile extends Projectile
{
    /**
     * Constructor for objects of class BigProjectile, calls superclass constructor and initalizes starting values.
     * 
     * @param target        Target of the projectile.
     */
    public BigProjectile(Actor target)
    {
        //Call superclass constructor
        super(target);
        
        //Initializes values for damage and speed
        damage = ((Level)getWorld()).BIGPROJECTILE_DAMAGE;
        speed = ((Level)getWorld()).BIGPROJECTILE_SPEED;
    }
}
