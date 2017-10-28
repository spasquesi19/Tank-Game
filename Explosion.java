import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Uses the GifImage Class to make an animated explosion after a tank is destroyed
 * 
 * @author Sam Pasquesi/Xor
 * @version 5/7/17
 */
public class Explosion extends Actor
{
    GifImage explosion = new GifImage("explosion.gif");
    GreenfootImage lastImage;
    boolean setup = true;
    int x;
    int y;
    World w;
    /**
     * Creats and scales the explosion
     */
    public Explosion(int x, int y, World w){
        getImage().scale(15,15);
        lastImage = explosion.getImages().get(explosion.getImages().size()-1);
        this.x=x;
        this.y=y;
        this.w = w;
    }
    
    /**
     * sets up the explosion and removes the explosion once the gif has reached the
     * last frame
     */
    public void act() 
    {
        if(setup)
            setup();
        setImage(explosion.getCurrentImage());
        if(isDone())
            getWorld().removeObject(this);
    }    
    
    /**
     * set up method that places the explosion over the destroyed tank
     */
    public void setup(){
        w.addObject(this,x,y);
        setup=false;
    }
    
    /**
     * boolean to check if the gif has reached the last frame
     */
    public boolean isDone(){
        if(explosion.getCurrentImage().equals(lastImage)){
            return true;
        }
        return false;
    }
}
