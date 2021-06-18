import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Archer Class - Inherits from the Tower superclass, launches an arrow at enemies.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Archer extends Tower
{    
    /**
     * Constructor for objects of class Archer, calls superclass and initializes starting values.
     * 
     * @param tileIndex             Index of the tile the Archer will be on
     * @param rangeCircle           Circle that displays the range
     * @param level                 Level the Archer is in
     */
    public Archer(int tileIndex, Range rangeCircle, Level level)
    {
        //Call superclass constructor
        super(tileIndex, rangeCircle, level);
        
        //Initialize starting values
        
        //Initialize maximum HP
        maxHP = Level.ARCHER_HEALTH;
        
        //Initialize current HP
        currentHP = maxHP;
        
        //Initialize range of the archer
        range = Level.ARCHER_RANGE;
        
        //Initialize reload time
        reloadTime = Level.ARCHER_RELOAD;
        
        //Fill the array with the sound effect
        shootingSounds = new GreenfootSound[40];
        for (int i = 0; i < shootingSounds.length; i++) {
            shootingSounds[i] = new GreenfootSound("arrow.wav");
            shootingSounds[i].setVolume(65);
        }
        shootingSoundsIndex = 0;
    }
    
    /**
     * Called every act.
     */
    public void act() 
    {
        //Call method to turn towards nearest enemy
        turn();
        
        //When the user clicks on the tower
        if (Greenfoot.mousePressed(this)) {
            //If range is already showing, hide it
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
                setImage("emptyarcher.png");
                
                //Reset the reload counter
                reloadCounter = 0;
            } else { //If reload has not completed
                //Increment the reload counter
                reloadCounter++;
                
                //Change image back to regular archer (not empty) 30 acts after shooting
                if (reloadCounter == 30) {
                    setImage("archer.png");
                }
            }
        } else { //If there is not enemy
            //Turn towards the entrance
            turnTowards(((Level)getWorld()).getStartX(), ((Level)getWorld()).getStartY());
            
            //Set image to regular archer (not empty)
            setImage("archer.png");
        }
    }
}
