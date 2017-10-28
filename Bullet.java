import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Basic Bullet that doesn't have any special abilities
 * 
 * @author Sam Pasquesi/Xor
 * @version 5/7/17
 */
public class Bullet extends SmoothMover implements Moveable
{
    public final int SPEED = 5;
    public static int MAX_BULLETS = 2;
    public boolean inBag = true;
    public boolean playerBullet = false;
    public int imageWidth = 24;
    public int imageHeight = 14;
    public Tank shooter;
    public final int DAMAGE = 1;

    /**
     * Constructor for Bullet Objects. Scales down the bullet image to a reasonable size
     */
    public Bullet(){
        getImage().scale(26,10);
    }

    /**
     * shoots the bullet and checks to see if the bullet hit the edge of the screen or
     * hit something. If it hits something then it's placed back into the bag
     */
    public void act() 
    {
        shoot();
        if(onEdge() || hit()){
            inBag = true;
            getWorld().removeObject(this);
        }
    }  

    /**
     * points the bullet towards the target (mouse for player or around the player tank
     * for CpuTanks)
     */
    public void point(){
        if(playerBullet){
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if(mouse!=null && mouse.getButton()==1){
                turnTowards(mouse.getX(), mouse.getY());
            }
        }
        else{
            if(((CpuTank)shooter).target.getWorld()!=null)
                turnTowards(roughTargetX(), roughTargetY());
        }
        inBag=false;
    }
    
    /**
     * Gets a random X coordinate that is around the exact player location
     */
    public int roughTargetX(){
        int rand = (int)(Math.random()*10);
        if((int)(Math.random()*2)==0){
            rand*=-1;
        }
        return ((CpuTank)shooter).target.getX() + rand;
    }
    
    /**
     * Gets a random Y coordinate that is around the exact player location
     */
    public int roughTargetY(){
        int rand = (int)(Math.random()*10);
        if((int)(Math.random()*2)==0){
            rand*=-1;
        }
        return ((CpuTank)shooter).target.getY() + rand;
    }

    /**
     * Boolean that returns true if the bullet is on the edge of the world
     */
    private boolean onEdge(){
        //make these constants
        if(getX()==599 || getX()==0 || getY()==399 || getY()==0){
            return true;
        }
        return false;
    }

    /**
     * boolean that returns true if the bullet hits something that should destroy it
     * It also makes an explosion
     */
    public boolean hit(){
        for(int i = 0; i<getIntersectingObjects(null).size(); i++){
            if(hasNoEffect(i)){
                //return false;
            }
            //else if(getIntersectingObjects(null).size()>0){
            else if(getIntersectingObjects(null).get(i) instanceof Tank){
                Tank t = (Tank)getIntersectingObjects(null).get(i);
                t.affectHealth(DAMAGE);
                if(t.health<=0){
                    Explosion e = new Explosion(t.getX(), t.getY(), getWorld());
                    e.act();
                    getWorld().removeObject(t.getHealthBar());
                    getWorld().removeObject(t);
                }
                return true;
            }
            else if(getIntersectingObjects(null).get(i) instanceof Wall){
                return wallReact();
            }
            else if(getIntersectingObjects(null).get(i) instanceof Bullet){
                Bullet b = (Bullet)getIntersectingObjects(null).get(i);
                b.inBag=true;
                getWorld().removeObject(b);
                return true;
            }
            else{
                return true;
            }
            //  }
        }
        return false;
    }

    /**
     * boolean for wall react to determine if it was a hit
     */
    public boolean wallReact(){
        return true;
    }

    /**
     * boolean that returns true if the bullet it shooting
     */
    public boolean isShooting(){
        for(int i = 0; i<MAX_BULLETS; i++){
            if(getWorld().getObjects(Bullet.class).get(i)==this){
                return true;
            }
        }
        return false;
    }

    /**
     * moves the bullet at its speed
     */
    public void shoot(){
        move(SPEED);
    }
    
    public void move(){
        shoot();
    }

    /**
     * boolean that returns true if the intersecting object has no effect on the bullet
     */
    private boolean hasNoEffect(int i){
        Object thing = getIntersectingObjects(null).get(i);
        return thing==shooter || thing instanceof HealthBar || thing instanceof Powerup
        || thing instanceof LineOfSight || thing instanceof AroundWall
        || thing instanceof Explosion;
    }
}
