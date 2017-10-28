import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemy tank that follows walls using an invisible object that is placed around walls
 * 
 * @author Sam Pasquesi/Xor
 * @version 5/7/17
 */
public class WallFollower extends CpuTank
{
    String previousWallSide;
    
    public WallFollower(){
    }
    
    /**
     * Act - do whatever the WallFollower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    /**
     * unique behavior -- follows the wall and shoots if it sees the target player tank
     */
    public void behave(){
        followWall();
        if(target.getWorld()!=null)
            fire();
    }
    
    /**
     * method that moves the tank around walls
     */
    private void followWall(){
        if(checkForNoWall().equals("wall")){
            move(SPEED);
        }
        else if(checkForNoWall().equals("east")){
            setRotation(270);
            setLocation(getX()-1, getY());
        }
        else if(checkForNoWall().equals("west")){
            setRotation(90);
            setLocation(getX()+1, getY());
        }
        else if(checkForNoWall().equals("north")){
            setRotation(180);
            setLocation(getX(), getY()+1);
        }
        else if(checkForNoWall().equals("south")){
            setRotation(0);
            setLocation(getX(), getY()-1);
        }
        if(isAtEdge()){
            turn(180);
        }
    }
    
    /**
     * Returns a string of the direction where there isn't a wall
     */
    private String checkForNoWall(){
        if(getOneObjectAtOffset(15,0,AroundWall.class)==null){
            return "east";
        }
        else if(getOneObjectAtOffset(-15,0,AroundWall.class)==null){
            return "west";
        }
        else if(getOneObjectAtOffset(0,-15,AroundWall.class)==null){
            return "north";
        }
        else if(getOneObjectAtOffset(0,15,AroundWall.class)==null){
            return "south";
        }
        return "wall";
    }
}
