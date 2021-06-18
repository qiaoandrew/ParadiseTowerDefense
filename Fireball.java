import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util;

/**
 * Fireball Class - Fireball that is shot out of Dragons and Flamethrowers.
 * 
 * @author Andrew Qiao 
 * @version June 2021
 */
public class Fireball extends Projectile
{
    //Instance variables and objects
    private boolean isDragon;
    
    /**
     * Constructor for objects of class Fireball, calls superclass constructor and initalizes starting values.
     * 
     * @param target        Target of the projectile
     * @param isDragon      True if Fireball is being shot out of dragon, false if not
     */
    public Fireball(Actor target, boolean isDragon)
    {
        //Call superclass constructor
        super(target);
        
        //Initializes starting values
        damage = ((Level)getWorld()).FIREBALL_DAMAGE;
        speed = ((Level)getWorld()).FIREBALL_SPEED;
        
        //Initialize isDragon boolean
        this.isDragon = isDragon;
    }   
    
    /**
     * Checks if the Fireball has hit anything.
     * 
     * @return boolean          True if the fireball has hit something, false if not.
     */
    protected boolean checkHit()
    {
        //Check for seperate objects depending on what is shooting the Fireball
        if (isDragon) {
            //Potential tower
            tower = (Tower)getOneObjectAtOffset(0, 0, Tower.class);
            
            //If there is a tower, damage it, remove this object and return true
            if (tower != null) {
                tower.hit(damage);
                getWorld().removeObject(this);
                return true;
            }
            
            //Otherwise return false
            return false;
        } else {
            //Potential target
            enemies = (ArrayList)getObjectsAtOffset(16, 16, Enemy.class);
        
            //If there is a enemy, damage it, remove this object and return true
            if (enemies.size() > 0) {
                for (Enemy e : enemies) {
                    e.hit(damage);
                }
                getWorld().removeObject(this);
                return true;
            }
            
            //Otherwise return false
            return false;
        }
    }
}
