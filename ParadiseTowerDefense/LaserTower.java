import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * LaserTower Class - Inherits from the Tower superclass, launches lasers at enemies.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class LaserTower extends Tower
{
    //Instance variables
    
    //Keeps tracks of the number of shots for SFX purposes
    private int shotIndex;
    
    /**
     * Constructor for objects of class LaserTower, calls superclass and initializes starting values.
     * 
     * @param tileIndex             Index of the tile the LaserTower will be on
     * @param base                  Base of the tower
     * @param rangeCircle           Circle that displays the range
     * @param level                 Level the LaserTower is in
     */
    public LaserTower(int tileIndex, Base base, Range rangeCircle, Level level)
    {
        //Call superclass constructor
        super(tileIndex, rangeCircle, level);
        
        //Initialize starting values of variables
        
        //Initialize maximum HP
        maxHP = Level.LASERTOWER_HEALTH;
        
        //Set current HP
        currentHP = maxHP;
        
        //Range of the LaserTower
        range = Level.LASERTOWER_RANGE;
        
        //Set reload time
        reloadTime = Level.LASERTOWER_RELOAD;
        
        //Set the base of the LaserTower
        this.base = base;
        
        //Fill the array with the sound effect
        shootingSounds = new GreenfootSound[40];
        for (int i = 0; i < shootingSounds.length; i++) {
            shootingSounds[i] = new GreenfootSound("laser.wav");
            shootingSounds[i].setVolume(65);
        }
        shootingSoundsIndex = 0;
        
        //Start shot index at 0
        shotIndex = 0;
    }
    
    /**
     * Called every act.
     */
    public void act() 
    {
        //Turn towards the nearest enemy
        turn();
        
        //If the user presses on the LaserTower
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
                //Every 25 shots, play a sound effect if it is switched on
                if (shotIndex % 25 == 0 && level.getIsSFX() == 0) {
                    shootingSounds[shootingSoundsIndex].play();
                    shootingSoundsIndex++;
                    if (shootingSoundsIndex >= shootingSounds.length) {
                        shootingSoundsIndex = 0;
                    }
                }
                
                //Increment the shot counter
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
