import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemy Tanks that are controlled by the computer
 * 
 * @author Sam Pasquesi/Xor
 * @version 5/7/17
 */
public class CpuTank extends Tank implements CanShoot
{
    public LineOfSight los;
    public PlayerTank target;
    boolean setup = true;
    public int fireRate;
    
    int time = 0;
    int waitTime = 0;
    /**
     * creates a tank with a random type of bullet
     */
    public CpuTank(){
        int rand = (int)(Math.random()*4);
        for(int i = 0; i< Bullet.MAX_BULLETS; i++){
            if(rand==0){
                bullets.add(new Bullet());
                fireRate = 0;
            }
            else if(rand == 1){
                bullets.add(new BounceBullet());
                fireRate = 0;
            }
            else if(rand == 2){
                bullets.add(new FastBullet());
                fireRate = 0;
            }
            else if(rand == 3){
                bullets.add(new SeekingBullet());
                fireRate = 1;
            }
            bullets.get(i).shooter=this;
        }
    }
    
    /**
     * sets up the tank and calls the super act method and behavior method that is unique
     * for each different cpuTank
     */
    public void act() 
    {
        if(setup){
            setup();
            setup = false;
        }
        super.act();
        behave();
    }   
    
    /**
     * sets up the cputank object with a lineofsight object on the player's tank
     */
    public void setup(){
        target = getWorld().getObjects(PlayerTank.class).get(0);
        los = new LineOfSight(this, target);
        getWorld().addObject(los,0,0);
    }
    
    /**
     * shoots a bullet if the tank has a clear line of sight of the player tank
     */
    public void fire(){
        if(los.getWorld()!=null && los.clearLOS()){
            if(fireRate==0){
                waitTime = 99;
            }
            else if(fireRate ==1){
                waitTime = 199;
            }
            if(time>waitTime){
                shootBullet();
                time = 0;
            }
            else
                time++;
        }
    }
    
    public void behave(){
        if(target.getWorld()!=null)
            fire();
    }
    
    public void move(){
        super.move(SPEED);
    }
}
