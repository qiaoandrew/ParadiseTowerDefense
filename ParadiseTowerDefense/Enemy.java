import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util

/**
 * Enemy Superclass - Actors the player has to defend against, move through the map, and damages the player if they reach the end.
 * 
 * @author Andrew Qiao, James Li
 * @version June 2021
 */
public abstract class Enemy extends SuperSmoothMover
{
    //Instance variables and objects
    
    //ArrayList of coordinates for path the enemy will follow
    private ArrayList<Coordinate> path;
    
    //Level the enemy is in
    protected Level level;
    
    //Index of the coordinate the enemy is currently moving towards
    private int pathIndex;
    
    //Speed of the enemy
    protected double speed;
    
    //Maximum HP the enemy will start off with
    protected int maxHP;
    
    //Current HP enemy has
    protected int currentHP;
    
    //Potential tower targets
    private ArrayList<Tower> towers;
    
    //Distance to closest tower
    private double closestTower;
    
    //Range of the enemy
    protected int range;
    
    //Damage done to player if it reaches the end
    protected int playerDamage;
    
    //Amount of money player gets by defeating the enemy
    protected int defeatMoney;
    
    //Health bar
    protected StatBar healthBar;
    
    /**
     * Constructor for objects of Enemy Class, initializes values.
     * 
     * @param path          Path that the enemy will follow.
     * @param level         Level the enemy is in.
     */
    public Enemy(ArrayList<Coordinate> path, Level level)
    {
        //Initialize starting values (See class variables section for explanation)
        this.path = path;
        this.level = level;
        
        pathIndex = 0;
    }
    
    /**
     * Called every act.
     */
    public void act() 
    {
        move();
    }
    
    /**
     * Called when the Enemy is hit with a projectile, deducts from its HP.
     * 
     * @param damage        Amount of damage the projectile does.
     */
    public void hit(int damage)
    {
        //Create a health bar if there is not one already
        if (healthBar == null) {
            healthBar = new StatBar(maxHP, currentHP, this, 70, 8, 40, Color.GREEN, Color.RED, true);
            getWorld().addObject(healthBar, getX(), getY() - 40);
            healthBar.update(currentHP);
        }
        
        //Decrement the HP and update the 
        currentHP -= damage;
        healthBar.update(currentHP);
        
        //If the HP has fallen below 0
        if (currentHP <= 0) {
            if (this instanceof Tank) {
                if (level.getIsSFX() == 0) {
                    getWorld().addObject(new Explosion(true), getX(), getY());
                } else {
                    getWorld().addObject(new Explosion(false), getX(), getY());
                }
            }
            
            //Remove healthbar
            getWorld().removeObject(healthBar);
            
            //Remove the Enemy from the world
            getWorld().removeObject(this);
            
            //Give money to player
            level.addMoney(defeatMoney);
            
            //Remove the Enemy from the level
            level.removeEnemy();
        }
    }
    
    /**
     * Finds and returns the nearest tower to the enemy.
     * 
     * @return Tower        Nearest tower to the enemy.
     */
    protected Tower findTower()
    {
        //Target enemy starts off as null
        Tower targetTower = null;
        
        //ArrayList of enemies in the world
        towers = (ArrayList)getWorld().getObjects(Tower.class);
        
        //Start closest enemy distance as a large number
        closestTower = 1000000;
        
        //Loop through all the enemies
        for (Tower t : towers) {
            //Calculate the distance between the enemy and the tower
            double towerDistance = getDistance(t);
            
            //If the distance is smaller than the range and less than the current closest tower
            if (towerDistance < range && towerDistance < closestTower) {
                //Change the target tower
                targetTower = t;
                
                //Change the closest tower distance
                closestTower = towerDistance;
            }
        }
        
        //Return the closest tower
        return targetTower;
    }
    
    /**
     * Moves the Enemy and acts accordingly.
     */
    protected void move()
    {
        //If the enemy has reached the end
        if (pathIndex >= path.size()) {
            //Remove the object
            getWorld().removeObject(this);
            
            //Remove enemy from the level
            level.removeEnemy();
            
            //Damage the player
            level.takeDamage(playerDamage);
        } else { //If the enemy has not reached the end
            //Turn towards the next point
            turnTowards(path.get(pathIndex).getX(), path.get(pathIndex).getY());
            
            //If the enemy has reached its target point
            if (Math.abs(getX() - path.get(pathIndex).getX()) < 2 && Math.abs(getY() - path.get(pathIndex).getY()) < 2) {
                //Go to the next point
                pathIndex++;
            }
            
            //Move by its speed
            move(speed);
        }
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
