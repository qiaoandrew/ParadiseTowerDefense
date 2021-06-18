import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util

/**
 * Tower Class - Superclass to all types of towers, contains vital functions and values.
 * 
 * @author Andrew Qiao, Ryo Minakami, James Li
 * @version June 2021
 */
public abstract class Tower extends Actor
{
    //Instance variables/objects
    
    //ArrayList of enemies currently in the world
    private ArrayList<Enemy> enemies;
    
    //Range of the tower
    protected int range;
    
    //Maximum HP of the tower
    protected int maxHP;
    
    //Current HP of the tower
    protected int currentHP;
    
    //Reload time of the tower
    protected int reloadTime;
    
    //Counts until the next time the tower can shoot
    protected int reloadCounter;
    
    //Distance of closest enemy
    private double closestEnemy;
    
    //Index of the tile tower is being placed on
    protected int tileIndex;
    
    //Health bar
    private StatBar healthBar;
    
    //Base
    protected Base base;
    
    //Circle that displays range of the tower
    protected Range rangeCircle;
    
    //Whether or not the range circle is showing
    protected boolean isRangeShowing;
    
    //Level tower is in
    protected Level level;
    
    //Declare array for sound effect for shooting
    protected GreenfootSound[] shootingSounds;
    
    //Index to keep track of index of sound effect array
    protected int shootingSoundsIndex;
    
    /**
     * Constructor for objects of type Tower, initalizes starting values.
     * 
     * @param tileIndex         Index of the tile the tower is on
     * @param rangeCircle       Circle that displays the range
     */
    public Tower(int tileIndex, Range rangeCircle, Level level)
    {
        //Set starting values of variables
        
        //Counts acts for reloading
        reloadCounter = 0;
        
        //Circle that displays the range of the tower
        this.rangeCircle = rangeCircle;
        
        //Index of the tile the tower is on
        this.tileIndex = tileIndex;
        
        //Range is not currently showing
        isRangeShowing = false;
        
        //Set the level the tower is in
        this.level = level;
        
        //Fill the array with the sound effect
        shootingSounds = new GreenfootSound[40];
        for (int i = 0; i < shootingSounds.length; i++) {
            shootingSounds[i] = new GreenfootSound("fire.wav");
            shootingSounds[i].setVolume(70);
        }
        
        //Index of the array starts at 0
        shootingSoundsIndex = 0;
    }
    
    /**
     * Called every act.
     */
    public void act() 
    {
        //Call method to turn towards nearest enemy
        turn();
        
        //If the user presses on the tower
        if (Greenfoot.mousePressed(this)) {
            //If the range is already showing, hide it
            if (isRangeShowing) {
                rangeCircle.setTransparency(0);
                isRangeShowing = false;
            } else { //If not, show it
                rangeCircle.setTransparency(25);
                isRangeShowing = true;;
            }
        }
    }
    
    /**
     * Called to damage the tower.
     * 
     * @param damage            Amount of the damage done to the tower
     */
    public void hit(int damage)
    {
        //Create a health bar if there is not one already
        if (healthBar == null) {
            if (getY() == 32) {
                healthBar = new StatBar(maxHP, currentHP, this, 70, 8, -40, Color.GREEN, Color.RED, true);
                getWorld().addObject(healthBar, getX(), getY() + 40);
            } else {
                healthBar = new StatBar(maxHP, currentHP, this, 70, 8, 40, Color.GREEN, Color.RED, true);
                getWorld().addObject(healthBar, getX(), getY() - 40);
            }
            healthBar.update(currentHP);
        }
        
        //Decrement the HP and update the 
        currentHP -= damage;
        healthBar.update(currentHP);
        
        //If the hp reaches below 0
        if (currentHP <= 0) {
            if (level.getIsSFX() == 0) {
                getWorld().addObject(new Explosion(true), getX(), getY());
            } else {
                getWorld().addObject(new Explosion(false), getX(), getY());
            }
            
            //Remove healthbar
            getWorld().removeObject(healthBar);
            
            //Remove range circle
            getWorld().removeObject(rangeCircle);
            
            //Set transparency of the tile back to 50
            ((Level)getWorld()).getTiles().get(tileIndex).setTransparency(50);
            
            if (base != null) {
                getWorld().removeObject(base);
            }
            
            //Remove the tower from the world
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Finds an enemy to target and turns towards them and fires after reloading.
     */
    private void turn()
    {
        //Find the nearest enemy within in range
        Enemy targetEnemy = findEnemy();
        
        //If there is an enemy to target
        if (targetEnemy != null) {
            //Turn towards the enemy
            turnTowards(targetEnemy.getX(), targetEnemy.getY());
            
            //If reload has completed
            if (reloadCounter == reloadTime) {
                if (level.getIsSFX() == 0) {
                    shootingSounds[shootingSoundsIndex].play();
                    shootingSoundsIndex++;
                    if (shootingSoundsIndex >= shootingSounds.length) {
                        shootingSoundsIndex = 0;
                    }
                }
                
                //Shoot the enemy
                shoot(targetEnemy);
                
                //Reset the reload counter
                reloadCounter = 0;
            } else { //If reload has not completed
                //Increment the reload counter
                reloadCounter++;
            }
        } else { //If there is not enemy
            //Turn towards the entrance
            turnTowards(((Level)getWorld()).getStartX(), ((Level)getWorld()).getStartY());
        }
    }
    
    /**
     * Called to shoot at an Enemy.
     * 
     * @param a         Actor being targeted.
     */
    protected void shoot(Actor a)
    {
        //Shoot out a different type of projectile based on the type of projectile
        if (this instanceof SingleLauncher) {
            getWorld().addObject(new BigProjectile(a), getX(), getY());
        } else if (this instanceof SingleShooter) {
            getWorld().addObject(new BigBullet(a), getX(), getY());
        } else if (this instanceof Flamethrower) {
            getWorld().addObject(new Fireball(a, false), getX(), getY());
        } else if (this instanceof Archer) {
            getWorld().addObject(new Arrow(a), getX(), getY());
        } else if (this instanceof LaserTower) {
            getWorld().addObject(new Laser(a), getX(), getY());
        } else {
            getWorld().addObject(new Cannonball(a), getX(), getY());
        }
    }
    
    /**
     * Finds the nearest enemy within range.
     * 
     * @return Enemy        Nearest enemy within range.
     */
    protected Enemy findEnemy()
    {
        //Target enemy starts off as null
        Enemy targetEnemy = null;
        
        //ArrayList of enemies in the world
        enemies = (ArrayList)getWorld().getObjects(Enemy.class);
        
        //Start closest enemy distance as a large number
        closestEnemy = 1000000;
        
        //Loop through all the enemies
        for (Enemy e : enemies) {
            //Calculate the distance between the enemy and the tower
            double enemyDistance = getDistance(e);
            
            //If the distance is smaller than the range and less than the current closest enemy
            if (enemyDistance < range && enemyDistance < closestEnemy) {
                //Change the target enemy
                targetEnemy = e;
                
                //Change the closest enemy distance
                closestEnemy = enemyDistance;
            }
        }
        
        //Return the closest enemy
        return targetEnemy;
    }
    
    /**
     * Returns the distance between a troop and another actor.
     * 
     * @return double           Distance between a troop and another actor
     */
    private double getDistance(Actor a)
    {
        //Uses Pythagorean Theorem to find the distance between 2 coordinates
        return Math.sqrt(Math.pow(getX() - a.getX(), 2) + Math.pow(getY() - a.getY(), 2));
    }
}
