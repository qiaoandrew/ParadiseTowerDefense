import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util
import java.lang.IllegalStateException; //Import IllegalStateException from java.util

/**
 * Projectile Superclass - Different types of projectiles inherit from it, includes all vital functions.
 * 
 * @author Andrew Qiao, Ryo Minakami
 * @version June 2021
 */
public abstract class Projectile extends SuperSmoothMover
{
    //Instances variables/objects
    
    //Damage projectile does
    protected int damage;
    
    //Speed of projectile
    protected double speed;
    
    //Target enemy
    private Actor target;
    
    //Enemies potentially being hit
    
    //For multi-target (splash damage)
    protected ArrayList<Enemy> enemies;
    
    //For single target
    protected Enemy enemy;
    
    //Tower potentially being hit
    protected Tower tower;
    
    /**
     * Constructor for objects of class Projectile, initializes x and y coordinates of target.
     */
    public Projectile(Actor target)
    {
        //Initialize target enemy
        this.target = target;
    }
    
    /**
     * Called every act.
     */
    public void act() 
    {
        //If the projectile has not hit anything
        if (!checkHit()) {
            //Turn towards target if it still exists
            if (target != null && target.getWorld() != null) 
            {
                turnTowards(target.getX(), target.getY());
            }
            
            //Move at its speed
            move(speed);
        }
    }    
    
    /**
     * Turns the projectile towards the target when it is added to the world.
     * 
     * @param w             World the projectile is in
     */
    public void addedToWorld(World w)
    {
        turnTowards(target.getX(), target.getY());
    }
    
    /**
     * Checks if the projectile has hit an Enemy or multiple enemies, depending on the type of tower.
     * 
     * @return boolean      Whether or not the projectile has hit an Enemy.
     */
    protected boolean checkHit()
    {
        if (this instanceof BigBullet || this instanceof Arrow || this instanceof Laser) { //For single target projectiles
            //Potential target
            enemy = (Enemy)getOneObjectAtOffset(0, 0, Enemy.class);
            
            //Checks if the targets are on the other alliance and acts accordingly
            if (enemy != null) {
                //Damage the enemy
                enemy.hit(damage);
                
                //Remove the projectile
                getWorld().removeObject(this);
                
                //Return true for hitting something
                return true;
            }
            
            //Did not hit anythign
            return false;
        } else if (this instanceof SmallBullet) { //Tank projectiles, hit towers
            //Potential target
            tower = (Tower)getOneObjectAtOffset(0, 0, Tower.class);
            
            //Checks if the targets are on the other alliance and acts accordingly
            if (tower != null) {
                tower.hit(damage);
                getWorld().removeObject(this);
                return true;
            }
            
            return false;
        } else {    
            //Potential target
            enemies = (ArrayList)getObjectsInRange(5, Enemy.class);
        
            //Checks if the targets are on the other alliance and acts accordingly
            if (enemies.size() > 0) {
                for (Enemy e : enemies) {
                    e.hit(damage);
                }
                getWorld().removeObject(this);
                return true;
            }
            
            return false;
        }
    }
}
