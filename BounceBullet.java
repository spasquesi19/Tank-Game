import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bounce Bullet is a special type of bullet that bounces off of walls
 * 
 * @author Sam Pasquesi/Xor
 * @version (a version number or a date)
 */
public class BounceBullet extends Bullet
{
    double xVel = 0;
    double yVel = 0;

    public BounceBullet(){
    }

    /**
     * Act - do whatever the BounceBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    

    /**
     * moves the bullet at its speed and logs the xVelocity vector and yVelocity vector
     */
    public void move(int speed){
        super.move(speed);
        xVel = speed*Math.cos(Math.toRadians(-getRotation()));
        yVel = speed*Math.sin(Math.toRadians(-getRotation()));
    }

    /**
     * Special type of wall react. It reflects off of the wall and returns false so the bullet
     * doesn't get destroyed
     */
    public boolean wallReact(){
        reflectSet();
        clearWall();
        return false;
    }

    /**
     * turns the bullet at the proper angle depending on the angle it hit the wall
     */
    public void reflectSet(){
        if(getY()<getIntersectingObjects(Wall.class).get(0).northSide || getY()>getIntersectingObjects(Wall.class).get(0).southSide){
            if(xVel>0)
                setRotation((int)(Math.toDegrees(Math.atan(yVel/xVel))));
            else
                setRotation(180+(int)(Math.toDegrees(Math.atan(yVel/xVel))));
        }
        else if(getX()<getIntersectingObjects(Wall.class).get(0).eastSide || getX()>getIntersectingObjects(Wall.class).get(0).westSide){
            if(yVel>0)
                setRotation(270-(int)(Math.toDegrees(Math.atan(xVel/yVel))));
            else
                setRotation(90-(int)(Math.toDegrees(Math.atan(xVel/yVel))));
        }
        
        
    }
    
    /**
     * moves the bullet forward a little after it has been reflected so that it doesn't get
     * messed up
     */
    private void clearWall(){
        while(isTouching(Wall.class)){
            move(SPEED);
        }
    }
}
