import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Line of sight to check if enemy tanks can "see" the player tank
 * 
 * @author Xor
 * @version 5/7/17
 */
public class LineOfSight extends Actor
{
    Actor mouse;
    Actor cat;
    int mouseX;
    int mouseY;
    int catX;
    int catY;
    GreenfootImage myImage;
    /**
     * creates a line of sight object. Takes in two actors: one seer and one target
     */
    public LineOfSight (Actor c, Actor m)
    {
        cat = c;
        mouse = m;
        setImage(new GreenfootImage(1,1));
    }
    /**
     * draws a line between the two actors
     */
    public void act() 
    {
        if(mouse.getWorld()!=null && cat.getWorld()!=null)
        {
            mouseX = mouse.getX();
            mouseY = mouse.getY();
            catX = cat.getX();
            catY = cat.getY();
            int myX = (catX + mouseX)/2;
            int myY = (catY + mouseY)/2;
            setLocation(myX, myY);
            turnTowards(mouseX, mouseY);
            int d = (int)Math.hypot((catX-mouseX), (catY-mouseY));
            getImage().scale(d, 1);
            /* commented out section makes line of sight visible
            
            myImage = getImage();
            myImage.setColor(Color.BLACK);
            myImage.drawLine(0,0,d,0);
            */
            
        }
        else{
            getWorld().removeObject(this);
        }
    }    
    
    /**
     * if the line is intersected by a wall then it returns false
     */
    public boolean clearLOS()
    {
        return(getOneIntersectingObject(Wall.class)==null);
    }
}