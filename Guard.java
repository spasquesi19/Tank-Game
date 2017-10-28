import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Type of cpuTank that moves around in a small square and shoots if it sees the player tank
 * 
 * @author Sam Pasquesi/Xor
 * @version 5/7/17
 */
public class Guard extends CpuTank
{
    int cornerX = 0;
    int cornerY = 0;
    final int SQUARE_SIZE = 50;
    double setCornerX = 0;
    double setCornerY = 0;

    public Guard(){
    }

    /**
     * Act - do whatever the Guard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    

    /**
     * sets up the tank with starting coordinates
     */
    public void setup(){
        super.setup();
        cornerX = getX();
        cornerY = getY();
        //face north
        turnTowards(getX(), getY()-1);
        setCornerX = getNextCornerX();
        setCornerY = getNextCornerY();

        setCornerInBounds();
    }

    /**
     * unique behavior -- moves in a square
     */
    public void behave(){
        moveSquare();
        if(target.getWorld()!=null)
            fire();
    }

    /**
     * method that moves the tank in a square
     */
    private void moveSquare(){
        if(Math.abs(getX()-setCornerX)>SPEED || Math.abs(getY()-setCornerY)>SPEED){
            move(SPEED);
        }
        else{
            turn(90);
            setCornerX = getNextCornerX();
            setCornerY = getNextCornerY();
            
            setCornerInBounds();
        }
    }

    /**
     * method that sets the next target corner for the tank to go to
     */
    private void setCornerInBounds(){
        int c = 0;
        while(setCornerX<c){
            setCornerX++;
        }
        while(setCornerX>getWorld().getWidth()-c){
            setCornerX--;
        }
        while(setCornerY<c){
            setCornerY++;
        }
        while(setCornerY>getWorld().getHeight()-c){
            setCornerY--;
        }
    }

    
/**
 * method that returns a double X coordinate for the next corner
 */
    private double getNextCornerX(){
        double rads = Math.toRadians(getRotation());
        double dx = Math.cos(rads)*SQUARE_SIZE;

        return getX() + dx;
    }

    /**
     * method that returns a double Y coordinate for the next corner
     */
    private double getNextCornerY(){
        double rads = Math.toRadians(getRotation());
        double dy = Math.sin(rads)*SQUARE_SIZE;

        return getY()+dy;
    }
}
