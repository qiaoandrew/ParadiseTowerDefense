import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Flamethrower Class - Inherits from the Tower superclass, shoots fireballs at enemies.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Flamethrower extends Tower
{
    //Instance variables
    
    //Keeps tracks of how many shots have been launched for SFX purposes
    private int shotIndex;
    
    /**
     * Constructor for objects of class Flamethrower, calls superclass and initializes starting values.
     * 
     * @param tileIndex             Index of the tile the Flamethrower will be on
     * @param base                  Base of the tower
     * @param rangeCircle           Circle that displays the range
     * @param level                 Initialize level the Flamethrower is in
     */
    public Flamethrower(int tileIndex, Base base, Range rangeCircle, Level level)
    {
        //Call superclass constructor
        super(tileIndex, rangeCircle, level);
        
        //Initialize starting values
        
        //Initialize maximum HP
        maxHP = Level.FLAMETHROWER_HEALTH;
        
        //Initialize current HP
        currentHP = maxHP;
        
        //Initialize range of the Flamethrower
        range = Level.FLAMETHROWER_RANGE;
        
        //Initialize reload time
        reloadTime = Level.FLAMETHROWER_RELOAD;
        
        //Set the base
        this.base = base;
        
        //Fill the array with the sound effect
        shootingSounds = new GreenfootSound[40];
        for (int i = 0; i < shootingSounds.length; i++) {
            shootingSounds[i] = new GreenfootSound("fireball.wav");
            shootingSounds[i].setVolume(60);
        }
        shootingSoundsIndex = 0;
        
        //Set the shot index at 0
        shotIndex = 0;
    }
    
    /**
     * Called every act.
     */
    public void act() 
    {
        //Turn towards the nearest enemy
        turn();
        
        //If the user clicks on the flamethrower
        if (Greenfoot.mousePressed(this)) {
            //If the range is already showing, hide it
            if (isRangeShowing) {
                rangeCircle.setTransparency(0);
                isRangeShowing = false;
            } else { //Otherwise show it
                rangeCircle.setTransparency(25);
                isRangeShowing = true;;
            }
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
                //Every 10 shots, SFX will play if SFX is switched on
                if (shotIndex % 10 == 0 && level.getIsSFX() == 0) {
                    shootingSounds[shootingSoundsIndex].play();
                    shootingSoundsIndex++;
                    if (shootingSoundsIndex >= shootingSounds.length) {
                        shootingSoundsIndex = 0;
                    }
                }
                
                //Increment the shot index
                shotIndex++;
                
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
}
