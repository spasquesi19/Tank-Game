import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Seeking bullet is a special type of bullet that gets the target tanks in range and
 * follows them
 * 
 * @author Sam Pasquesi/Xor
 * @version (a version number or a date)
 */
public class SeekingBullet extends Bullet
{
    private final int SEEK_RANGE = 100;
    private boolean locked = false;
    private Tank target;
    private final int TURN_SPEED = 4;
    int setX = 0;
    int setY = 0;
    
    /**
     * creates a seeking bullet and sets the target equal to null
     */
    public SeekingBullet(){
        target = null;
    }

    /**
     * if the bullet is not already locked onto a target it will look for a target
     * within a range and move towards it
     */
    public void act() 
    {
        super.act();
        if(!locked && getWorld()!=null)
            findTarget();
        if(getWorld()==null){
            locked = false;
            target = null;
        }
    }  

    /**
     * gets the distance from the bullet to an actor(from parameter)
     */
    private double getDistance(Actor actor) {
        return Math.hypot(actor.getX() - getX(), actor.getY() - getY());
    }

    /**
     * finds the closest target
     */
    private void findTarget(){
        double nearestDist = SEEK_RANGE;
        double dist = -1;
        if(playerBullet){
            List<CpuTank> nearby = getObjectsInRange(SEEK_RANGE, CpuTank.class);
            CpuTank nearestTank = null;
            for(int i = 0; i<nearby.size(); i++){
                dist = getDistance(nearby.get(i));
                if(dist<nearestDist){
                    nearestTank = nearby.get(i);
                    nearestDist = dist;
                }
            }

            target = nearestTank;
            if(target!=null && target.getWorld()!=null){
                locked = true;
            }
            else{
                locked = false;
            }
        }
        else{
            List<PlayerTank> nearby = getObjectsInRange(SEEK_RANGE, PlayerTank.class);
            PlayerTank nearestTank = null;

            for(int i = 0; i<nearby.size(); i++){
                dist = getDistance(nearby.get(i));
                if(dist<nearestDist){
                    nearestTank = nearby.get(i);
                    nearestDist = dist;
                }
            }

            target = nearestTank;
            if(target!=null && target.getWorld()!=null){
                locked = true;
            }
            else{
                locked = false;
            }
        }

        
    }

    /**
     * shoots the bullet. If the bullet has a target, it will follow it
     */
    public void shoot(){
        if(!locked && target==null || target.getWorld()==null){
            super.shoot();
        }
        else{
            int targetX = target.getX();
            int targetY = target.getY();
            for(int i = 0; i<10; i++){
                if(targetX>getX()){
                    setX++;
                }
                else if(targetX<getX()){
                    setX--;
                }
                if(targetY>getY()){
                    setY++;
                }
                else if(targetY<getY()){
                    setY--;
                }
            }
            turnTowards(setX, setY);
            super.shoot();
        }
    }

    /**
     * points the bullet towards the mouse
     */
    public void point(){
        if(playerBullet){
            MouseInfo mouse = Greenfoot.getMouseInfo();
            setX = mouse.getX();
            setY = mouse.getY();
        }
        else{
            setX = roughTargetX();
            setY = roughTargetY();
        }
        super.point();
    }
}
