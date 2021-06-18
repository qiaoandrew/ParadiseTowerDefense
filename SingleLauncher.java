import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * SingleLauncher Class - Inherits from Tower superclass, launches projectiles at enemies.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class SingleLauncher extends Tower
{
   /**
     * Constructor for objects of class SingleLauncher, calls superclass and initializes starting values.
     * 
     * @param tileIndex             Index of the tile the SingleLauncher will be on
     * @param base                  Base of the tower
     * @param rangeCirlce           Circle displaying the range of the tower
     * @param level                 Level the tower is in
     */
    public SingleLauncher(int tileIndex, Base base, Range rangeCircle, Level level)
    {
        //Calls superclass constructor
        super(tileIndex, rangeCircle, level);
        
        //Initialize staring values
        
        //Set the maximum HP
        maxHP = Level.SINGLELAUNCHER_HEALTH;
        
        //Set the current HP
        currentHP = maxHP;
        
        //Set the range of the Single Launcher
        range = Level.SINGLELAUNCHER_RANGE;
        
        //Set the reload time for the Single Launcher
        reloadTime = Level.SINGLELAUNCHER_RELOAD;
        
        //Set the base 
        this.base = base;
        
        //Fill the array with the sound effect
        shootingSounds = new GreenfootSound[40];
        for (int i = 0; i < shootingSounds.length; i++) {
            shootingSounds[i] = new GreenfootSound("launcher.wav");
            shootingSounds[i].setVolume(75);
        }
        
        //Start the sounds index at 0
        shootingSoundsIndex = 0;
    }
    
    /**
     * Called every act.
     */
    public void act() 
    {
        //Turn towards the nearest tower
        turn();
        
        //If the user presses on the single launcher
        if (Greenfoot.mousePressed(this)) {
            //If range is currently showing, hide it
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
                if (level.getIsSFX() == 0) {
                    shootingSounds[shootingSoundsIndex].play();
                    shootingSoundsIndex++;
                    if (shootingSoundsIndex >= shootingSounds.length) {
                        shootingSoundsIndex = 0;
                    }
                }
                
                //Shoot the enemy
                shoot(targetEnemy);
                
                //Change the image to an empty laucher
                setImage("emptysinglelauncher.png");
                
                //Reset the reload counter
                reloadCounter = 0;
            } else { //If reload has not completed
                //Increment the reload counter
                reloadCounter++;
                
                //If reload is complete, set image back to not empty
                if (reloadCounter == 30) {
                    setImage("singlelauncher.png");
                }
            }
        } else { //If there is not enemy
            //Turn towards the entrance
            turnTowards(((Level)getWorld()).getStartX(), ((Level)getWorld()).getStartY());
            
            //Set image back to not empty
            setImage("singlelauncher.png");
        }
    }
}
