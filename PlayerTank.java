import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Player controlled tank
 * 
 * @author Sam Pasquesi/Xor
 * @version 5/7/17
 */
public class PlayerTank extends Tank
{  
    int count = 0;
    /**
     * creates a new playertank with bullets
     */
    public PlayerTank(){
        for(int i = 0; i< Bullet.MAX_BULLETS; i++){
            bullets.add(new Bullet());
            bullets.get(i).shooter=this;
            bullets.get(i).playerBullet = true;
        }
    }

    /**
     * Takes in user input to move the tank
     * takes in parameters for moves speed and turn speed
     */
    public void go(int speed, int turnSpeed){
        if(Greenfoot.isKeyDown("W")){
            move(speed);
        }
        if(Greenfoot.isKeyDown("S")){
            move(-speed);
        }
        if(Greenfoot.isKeyDown("D")){
            turn(turnSpeed);
        }
        if(Greenfoot.isKeyDown("A")){
            turn(-turnSpeed);
        }
    }

    /**
     * moves and fires a bullet if the left mouse button is pressed
     * also sets tanks power if the tank runs over a powerup
     */
    public void act() 
    {
        super.act();
        move();
        if(Greenfoot.mouseClicked(null) && Greenfoot.getMouseInfo().getButton()==1){
            fire();
        }
        if(isTouching(Powerup.class)){
            Powerup p = getIntersectingObjects(Powerup.class).get(0);
            setPower(p.getPower());
            getWorld().removeObject(p);
        }
    }    

    /**
     * moves the tank forward at a speed
     */
    public void move(){
        go(SPEED,TURN_SPEED);
    }
    
    /**
     * fires a bullet
     */
    public void fire(){
        shootBullet();
    }

    /**
     * sets a power if the tank runs over a powerup
     */
    public void setPower(String power){
        if(power.equals("speed")){
            for(int i=0; i<Bullet.MAX_BULLETS; i++){
                bullets.set(i, new FastBullet());
                bullets.get(i).shooter=this;
                bullets.get(i).playerBullet=true;
            }
        }
        else if(power.equals("bounce")){
            for(int i=0; i<Bullet.MAX_BULLETS; i++){
                bullets.set(i, new BounceBullet());
                bullets.get(i).shooter=this;
                bullets.get(i).playerBullet=true;
            }
        }
        else if(power.equals("seeking")){
            for(int i=0; i<Bullet.MAX_BULLETS; i++){
                bullets.set(i, new SeekingBullet());
                bullets.get(i).shooter=this;
                bullets.get(i).playerBullet=true;
            }
        }
        else if(power.equals("add bullet")){
            Bullet.MAX_BULLETS++;
            if(bullets.get(0) instanceof Bullet)
                bullets.add(new Bullet());
            else if(bullets.get(0) instanceof BounceBullet)
                bullets.add(new BounceBullet());
            else if(bullets.get(0) instanceof FastBullet){
                bullets.add(new FastBullet());
            }
            bullets.get(bullets.size()-1).shooter=this;
            bullets.get(bullets.size()-1).playerBullet=true;
        }
        else if(power.equals("health")){
            if(health<MAX_HEALTH){
                affectHealth(-1);
            }
        }
    }
}
