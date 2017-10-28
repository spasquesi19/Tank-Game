import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Walls that tanks and bullets can't pass through
 * 
 * @author Sam Pasquesi/Xor 
 * @version 5/7/17
 */
public class Wall extends Actor
{
    int northSide=0;
    int eastSide=0;
    int southSide=0;
    int westSide=0;
    
    public Wall(){
    }
    
    /**
     * Act - do whatever the Wall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }  
    
    /**
     * sets the bounds for the wall to mark where the edges are
     */
    public void setBounds(){
        northSide = getY()-(getImage().getHeight()/2);
        eastSide = getX()+(getImage().getWidth()/2);
        southSide = getY()+(getImage().getHeight()/2);
        westSide = getX()-(getImage().getWidth()/2);
    }
    
}
