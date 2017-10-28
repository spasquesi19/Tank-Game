import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Color;
/**
 * Screen that shows up when the user loses the game
 * 
 * @author Sam Pasquesi/Xor
 * @version 5/7/17
 */
public class Loser extends World
{
    /**
     * Constructor for objects of class Loser. Displays text to show that the user has lost.
     * Also displays a randomly colored background
     * 
     */
    public Loser()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.

        super(600, 400, 1); 
        GreenfootImage pic = new GreenfootImage(600, 400);
        pic.setColor(new Color(Greenfoot.getRandomNumber(255 + 1), Greenfoot.getRandomNumber(255 + 1), Greenfoot.getRandomNumber(255 + 1)));
        pic.fill();
        setBackground(pic);
        showText("You lose:(", 300,170);
        showText("Press the spacebar to retry", 300,230);
    }

    /**
     * waits for the user to push the spacebar before starting a new game
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("space")){
            Greenfoot.setWorld(new MyWorld(1));
        }
    }
}