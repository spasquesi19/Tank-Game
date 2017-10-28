import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Color;
/**
 * Level Screen in between levels
 * 
 * @author Sam Pasquesi/Xor
 * @version 5/7/17
 */
public class LevelScreen extends World
{
    int level;
    /**
     * Constructor for LevelScreen. It creates a randomly colored background and displays text to
     * prompt the user to start the next level
     * 
     */
    public LevelScreen(int level)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.

        super(600, 400, 1); 
        this.level = level;
        GreenfootImage pic = new GreenfootImage(600, 400);
        pic.setColor(new Color(Greenfoot.getRandomNumber(255 + 1), Greenfoot.getRandomNumber(255 + 1), Greenfoot.getRandomNumber(255 + 1)));
        pic.fill();
        setBackground(pic);

        showText("Level " + (level+1), 300,170);
        showText("Press the spacebar to start", 300,230);

    }

    /**
     * waits for user to push the spacebar before starting the next level
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("space")){
            level++;
            Greenfoot.setWorld(new MyWorld(level));
        }
    }
}