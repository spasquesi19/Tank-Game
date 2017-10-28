import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A special type of bullet that goes faster than normal ones
 * 
 * @author Sam Pasquesi/Xor
 * @version 5/7/17
 */
public class FastBullet extends Bullet
{
    private final int SPEED = 7;
    /**
     * Act - do whatever the FastBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    /**
     * moves the bullet forward
     */
    public void shoot(){
        move(SPEED);
    }
}
