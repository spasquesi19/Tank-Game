import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Health bar that follows its corresponding tank and shows how much health it has left
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends SmoothMover
{
    Tank tank;
    private int currentHealth;
    private int maxHealth;
    /**
     * Constructor for the HealthBar. Takes in a Tank as a parameter
     */
    public HealthBar(Tank t){
        tank = t;
        maxHealth=tank.MAX_HEALTH;
        currentHealth=maxHealth;
    }
    /**
     * sets the location to just beneath the tank
     */
    public void act() 
    {
        setLocation(tank.getX(), tank.getY()+15);
    }    
    
    /**
     * Changes the health and updates the health bar picture
     */
    public void change(int update){
        currentHealth = update;
        if(maxHealth==2){
            if(currentHealth==2){
                setImage("Health3-3.png");
            }
            else if(currentHealth==1){
                setImage("Health1-3.png");
            }
        }
        else if(maxHealth==3){
            if(currentHealth ==3){
                setImage("Health3-3.png");
            }
            else if(currentHealth==2){
                setImage("Health2-3.png");
            }
            else if(currentHealth==1){
                setImage("Health1-3.png");
            }
        }
    }
    
    
    
    
}
