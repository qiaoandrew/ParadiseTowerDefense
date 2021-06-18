import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Base class - Image that is displayed under towers, does not move while the top of the tower, thus creates
 * a cool effect.
 * 
 * @author Andrew Qiao
 * @version June 2021
 */
public class Base extends Actor
{
    /**
     * Constructor for Base class, sets its image.
     * 
     * @param baseType      Integer telling which type of base it will be
     */
    public Base(int baseType)
    {
        //Set the image of the base based on the parameter.
        if (baseType == 0) {
            setImage("launcherbase.png");
        } else if (baseType == 1) {
            setImage("cannonbase.png");
        } else if (baseType == 2) {
            setImage("lasertowerbase.png");
        }
    }
}
