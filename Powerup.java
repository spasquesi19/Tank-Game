import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Powerups that give the player tank new abilities
 * 
 * @author Sam Pasquesi/Xor
 * @version 5/7/17
 */
public class Powerup extends Actor
{
    String power = "";
    
    /**
     * creates a new powerup object and scales it down
     */
    public Powerup(){
        getImage().scale(45,25);
    }
    /**
     * Act - do whatever the Powerup wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }    
    
    /**
     * sets the power of the powerup to a random one. Choices are: speed bullet,
     * bounce bullet, seeking bullet, add another bullet, or extra health
     */
    public void setPower(){
        int random = (int)(Math.random()*5);
        if(random == 0){
            power = "speed";
        }
        else if(random == 1){
            power = "bounce";
        }
        else if(random == 2){
            power = "seeking";
        }
        else if(random == 3){
            power = "add bullet";
            setImage("powerupBullet.png");
            getImage().scale(45,25);
        }
        else if(random == 4){
            power = "health";
            setImage("powerupHealth.png");
            getImage().scale(45,25);
        }
    }
    
    /**
     * returns a string of the powerup's power
     */
    public String getPower(){
        return power;
    }
    
    /**
     * returns true if the powerup is touching an object
     */
    public boolean publicIsTouching(){
        if(isTouching(null))
            return true;
        return false;
    }
}
