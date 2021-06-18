import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * TextLabel Class
 * 
 * A simple customizable text label that displays text.
 * 
 * @author Andrew Qiao, Ryo Minakami
 * @version June 2021
 */
public class TextLabel extends Actor
{
    //Declare instance variables
    private String text;
    private int fontSize;
    private int padding;
    private int height;
    private int width;
    
    //Declare colors
    private Color foreground;
    private Color background;
    
    //Declare the GreenfootImage for the text label
    private GreenfootImage textLabel;
    
    /**
     * Constructor for the TextLabel class, sets the values for the instance variables and calls update.
     * 
     * @param text              Text that will be displayed on the label
     * @param fontSize          Size of the font of the text
     * @param foreground        Color of the text
     * @param background        Color of the background
     */
    public TextLabel(String text, int fontSize, int padding, Color foreground, Color background)
    {
        //Set values of instance variables
        this.text = text;
        this.fontSize = fontSize;
        this.padding = padding;
        this.foreground = foreground;
        this.background = background;
        this.height = -1;
        this.width = -1;
        
        //Call update to draw all the text label image
        update();
    }
    
    /**
     * Constructor for the TextLabel class, sets the values for the instance variables and calls update.
     * 
     * @param text              Text that will be displayed on the label
     * @param fontSize          Size of the font of the text
     * @param foreground        Color of the text
     * @param background        Color of the background
     */
    public TextLabel(String text, int fontSize, int padding, Color foreground, Color background, int width, int height)
    {
        //Set values of instance variables
        this.text = text;
        this.fontSize = fontSize;
        this.padding = padding;
        this.foreground = foreground;
        this.background = background;
        this.width = width;
        this.height = height;
        
        //Call update to draw all the text label image
        update();
    }
    
    /**
     * Called to draw out the parts of the text label image.
     */
    private void update()
    {
        //Create the image that will contain the text
        GreenfootImage tempText = new GreenfootImage(text, fontSize, foreground, background);
        
        //Create the image for the text label
        if (height != -1 && width != -1) {
            textLabel = new GreenfootImage(width, height);
        } else {
            textLabel = new GreenfootImage(tempText.getWidth() + padding * 2, tempText.getHeight() + padding * 2);
        }
            
        //Draw the text label
        textLabel.setColor(background);
        textLabel.fill();
        textLabel.drawImage(tempText, padding, padding);
        
        //Draw a rectangle around the text label
        textLabel.setColor(foreground);
        textLabel.drawRect(0, 0, textLabel.getWidth() - 1, textLabel.getHeight() - 1);
        
        //Set the image to the text label
        setImage(textLabel);
    }
    
    /**
     * Updates the text in the label.
     * 
     * @param text          Text to be shown on the label
     */
    public void update(String text)
    {
        this.text = text;
        update();
    }
    
    public int getWidth()
    {
        return textLabel.getWidth();
    }
    
    public int getHeight()
    {
        return textLabel.getHeight();
    }
}
