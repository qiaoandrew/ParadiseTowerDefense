import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList; //Import ArrayList from java.util

/**
 * Dragon Class - Inherits from the Enemy superclass, an enemy that shoots fireballs.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Dragon extends Enemy
{
    //Instance variables and objects
    
    //Counts the time for reloading
    private int reloadCounter;
    
    //Time for reloading
    private int reloadTime;
    
    //Counts the acts
    private int actCount;
    
    //Declare array for sound effect for shooting
    private GreenfootSound[] fireballSounds;
    
    //Index to keep track of index of sound effect array
    private int fireballSoundsIndex;
    
    //Declare sound effect for dragon's death
    private GreenfootSound dragonDeath;
    
    //Keeps track of how man shots the dragon has done, and plays a SFX 20 shots, so it does not sound weird due to the dragon's high fire rate
    private int shotIndex;
    
    /**
     * Constructor for Dragon Class - Calls superclass and initializes values.
     * 
     * @param path      ArrayList of coordinates that the dragon will follow
     * @param level     Level the Dragon is in
     */
    public Dragon(ArrayList<Coordinate> path, Level level)
    {
        //Call superclass constructor
        super(path, level);
        
        //Set speed of the humanoid
        speed = Level.DRAGON_SPEED;
        
        //Set max HP of humanoid
        maxHP = Level.DRAGON_HEALTH;
        
        //Set current HP of humanoid
        currentHP = maxHP;
        
        //Set range
        range = Level.DRAGON_RANGE;
        
        //Set reload time
        reloadTime = Level.DRAGON_RELOAD;
        
        //Initialize reload counter
        reloadCounter = 0;
        
        //Initialize player damage
        playerDamage = Level.DRAGON_PLAYERDAMAGE;
        
        //Initialize amount of money player gains by defeating it
        defeatMoney = Level.DRAGON_MONEY;
        
        //Fill the array with the sound effect
        fireballSounds = new GreenfootSound[40];
        for (int i = 0; i < fireballSounds.length; i++) {
            fireballSounds[i] = new GreenfootSound("fireball.wav");
            fireballSounds[i].setVolume(60);
        }
        fireballSoundsIndex = 0;
        
        //Initialize the sound effect for the dragon's death
        dragonDeath = new GreenfootSound("dragondeath.wav");
        dragonDeath.setVolume(80);
        
        //Initialize the shot index at 0
        shotIndex = 0;
    }
    
    /**
     * Called every act, changes the image of the dragon if needed, and shoots at towers if applicable.
     */
    public void act() 
    {
        //Change the image depending on act counter, creates a 7-frame animation
        if (actCount % 140 == 0) {
            setImage("dragon1.png");
        } else if (actCount % 140 == 20) {
            setImage("dragon2.png");
        } else if (actCount % 140 == 40) {
            setImage("dragon3.png");
        } else if (actCount % 140 == 60) {
            setImage("dragon4.png");
        } else if (actCount % 140 == 80) {
            setImage("dragon5.png");
        } else if (actCount % 140 == 100) {
            setImage("dragon6.png");
        } else if (actCount % 140 == 120) {
            setImage("dragon7.png");
        }
        
        //Find the nearest tower
        Tower towerTarget = findTower();
        
        //If there is not target, then move
        if (towerTarget == null) {
            move();
        } else {
            //Turn towards the enemy
            turnTowards(towerTarget.getX(), towerTarget.getY());
            
            //If reload has completed
            if (reloadCounter == reloadTime) {
                //Shoot the enemy
                shoot(towerTarget);
                
                shotIndex++;
                
                //Reset the reload counter
                reloadCounter = 0;
                
                //Every 20 shots, a SFX will be played
                if (shotIndex % 20 == 0 && level.getIsSFX() == 0) {
                    fireballSounds[fireballSoundsIndex].play();
                    fireballSoundsIndex++;
                    if (fireballSoundsIndex > 20) {
                        fireballSoundsIndex = 0;
                    }
                }
            } else { //If reload has not completed
                //Increment the reload counter
                reloadCounter++;
            }
        }
        
        //Increment act counter
        actCount++;
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
            if (level.getIsSFX() == 0) {
                //Play sound effect
                dragonDeath.play();
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
     * Shoots a fireball at tower.
     * 
     * @param a     Tower to shoot at.
     */
    private void shoot(Actor a)
    {
        //Creates new fireball aimed at the tower at the current location
        getWorld().addObject(new Fireball(a, true), getX(), getY());
    }
}
