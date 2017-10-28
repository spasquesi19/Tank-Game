import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Object that is placed around walls so that the wall follower has a path
 * 
 * @author Sam Pasquesi/Xor
 * @version 5/7/17
 */
public class AroundWall extends Actor
{
    /**
     * creates an AroundWall object and scales it to 100x100 pixels and makes it transparent
     */
    public AroundWall(){
        getImage().scale(100,100);
        getImage().setTransparency(0);
    }
    /**
     * Act - do whatever the AroundWall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
