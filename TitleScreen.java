import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Color;

/**
 * Title Screen before the start of the game
 * 
 * @author Sam Pasquesi/Xor
 * @version 5/7/17
 */
public class TitleScreen extends World
{
    int level;
    /**
     * Constructor for TitleScreen. It creates a randomly colored background and displays text to
     * prompt the user to start the game
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        
        super(600, 400, 1); 
        level = 0;
        GreenfootImage pic = new GreenfootImage(600, 400);
        pic.setColor(new Color(Greenfoot.getRandomNumber(255 + 1), Greenfoot.getRandomNumber(255 + 1), Greenfoot.getRandomNumber(255 + 1)));
        pic.fill();
        setBackground(pic);
        if(level == 0)
            showText("Press the spacebar to start", 300,200);
    }

    /**
     * Waits for user to push the spacebar before starting the game
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("space")){
            level++;
            Greenfoot.setWorld(new MyWorld(level));
        }
    }
}