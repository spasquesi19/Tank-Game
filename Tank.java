import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Abstract Tank class with general methods
 * 
 * @author Sam Pasquesi/Xor
 * @version 5/7/17
 */
public abstract class Tank extends SmoothMover implements CanShoot, Moveable
{
    ArrayList<Bullet> bullets= new ArrayList<Bullet>();
    boolean setup = true;
    double xVel = 0;
    double yVel = 0;
    int speed;
    public final int MAX_HEALTH = 2;
    public int health;
    private HealthBar bar;
   
    /**
     * Constructor for tank that sets its health equal to the max health
     */
    public Tank(){
        health = MAX_HEALTH;
    }
    
    /**
     * sets up the tank and makes sure the tank doesn't go through walls
     */
    public void act() 
    {
        if(setup)
            setup();
        wallBlock();
    }  
    
    /**
     * gives the tank a health bar
     */
    private void setup(){
        bar = new HealthBar(this);
        getWorld().addObject(bar,0,0);
        setup=false;
    }
    
    /**
     * makes sure the tank doesn't go through walls
     */
    public void wallBlock(){
        while(isTouching(Wall.class)){
            if(getY()<getIntersectingObjects(Wall.class).get(0).northSide){
                setLocation(getX(),getY()-1);
            }
            if(isTouching(Wall.class)){
                if(getY()>getIntersectingObjects(Wall.class).get(0).southSide){
                    setLocation(getX(),getY()+1);
                }
            }
            if(isTouching(Wall.class)){
                if(getX()>getIntersectingObjects(Wall.class).get(0).eastSide){
                    setLocation(getX()+1,getY());
                }
            }
            if(isTouching(Wall.class)){
                if(getX()<getIntersectingObjects(Wall.class).get(0).westSide){
                    setLocation(getX()-1,getY());
                }
            }
        }
    }

    /**
     * moves the tank forward at some speed and takes in the X and Y Velocity vectors
     */
    public void move(int speed){
        super.move(speed);
        this.speed = speed;
        xVel = speed*Math.cos(Math.toRadians(-getRotation()));
        yVel = speed*Math.sin(Math.toRadians(-getRotation()));
    }

    /**
     * method used when a bullet hits the tank or when it goes over a health powerup.
     * It changes the health and the health bar
     */
    public void affectHealth(int damage){
        health-=damage;
        bar.change(health);
    }
    
    /**
     * gets the tank's health bar
     */
    public HealthBar getHealthBar(){
        return bar;
    }
    
    /**
     * shoots a free bullet from the tank
     */
    public void shootBullet(){
        if(getFreeBullet()!=null){
            Bullet current = getFreeBullet();
            getWorld().addObject(current, getX(), getY());
            if(isTouching(Bullet.class))
                current.point();
            current.act();
        }
    }
    
    /**
     * gets the first bullet that is in the tank's bag
     */
    public Bullet getFreeBullet(){
        for(int i =0; i<Bullet.MAX_BULLETS; i++){
            if(bullets.get(i).inBag == true){
                return bullets.get(i);
            }
        }
        return null;
    }
}
