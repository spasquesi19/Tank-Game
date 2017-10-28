import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * World where gameplay takes place
 * 
 * @author Sam Pasquesi/Xor
 * @version 5/7/17
 */
public class MyWorld extends World
{
    final int MAX_POWERS = 3;
    int numPowers;
    ArrayList<Powerup> powerups = new ArrayList<Powerup>();
    int levelNum;
    /**
     * Constructor for objects of class MyWorld. Sets up the world size and calls the setup method
     * 
     */
    public MyWorld(int level)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        levelNum = level;
        Tank player = new PlayerTank();
        addObject(player, 1, 1);
        setup();

    }

    /**
     * Act method controls the line of sight objects and checks to see if all the tanks are destroyed to
     * move on to the next level
     */
    public void act(){
        for(int i = 0; i<getObjects(CpuTank.class).size(); i++){
            if(getObjects(CpuTank.class).get(0).los!=null && getObjects(CpuTank.class).get(0).los.getWorld()!=null)
                getObjects(CpuTank.class).get(0).los.act();
        }
        if(getObjects(CpuTank.class).size()==0){
            if(levelNum<4)
                Greenfoot.setWorld(new LevelScreen(levelNum));
            else
                Greenfoot.setWorld(new Winner());
        }
        if(getObjects(PlayerTank.class).size()==0){
            Greenfoot.setWorld(new Loser());
        }
    }

    /**
     * Sets up each level with walls and enemy tanks
     */
    public void setup(){
        if(levelNum == 1){
            for(int i = 1; i<10; i++){
                AroundWall a = new AroundWall();
                addObject(a, 16+32*i, 56);
                Wall wall = new Wall();
                addObject(wall, 16+32*i, 56);
                wall.setBounds();
            }
            for(int i = 1; i<10; i++){
                AroundWall a = new AroundWall();
                addObject(a, 584-32*i, 200);
                Wall wall = new Wall();
                addObject(wall, 584-32*i, 200);
                wall.setBounds();
            }

            for(int i = 1; i<10; i++){
                AroundWall a = new AroundWall();
                addObject(a, 16+32*i, 344);
                Wall wall = new Wall();
                addObject(wall, 16+32*i, 344);
                wall.setBounds();
            }

            addObject(new WallFollower(), 53, 88);
            addObject(new WallFollower(), 293, 232);
            addObject(new WallFollower(), 53, 375);
        }

        else if(levelNum==2){
            addObject(new AroundWall(), 143, 113);
            addObject(new Wall(), 143, 113);

            addObject(new AroundWall(), 467, 158);
            addObject(new Wall(), 467, 158);

            addObject(new AroundWall(), 264, 211);
            addObject(new Wall(), 264, 211);

            addObject(new AroundWall(), 81, 343);
            addObject(new Wall(), 81, 343);

            for(int i = 0; i<getObjects(Wall.class).size(); i++){
                getObjects(Wall.class).get(i).setBounds();
            }

            addObject(new WallFollower(), 143, 144);
            addObject(new WallFollower(), 468, 128);
            addObject(new WallFollower(), 82, 312);
            addObject(new WallFollower(), 263, 243);
            addObject(new Guard(), 468, 343);
        }

        else if(levelNum==3){
            for(int i = 0; i<3; i++){
                addObject(new AroundWall(), 65+32*i, 113);
                addObject(new Wall(), 65+32*i, 113);
            }
            for(int i = 1; i<3; i++){
                addObject(new AroundWall(), 65+32*2, 113-32*i);
                addObject(new Wall(), 65+32*2, 113-32*i);
            }
            
            for(int i = 0; i<3; i++){
                addObject(new AroundWall(), 600-65-32*i, 113);
                addObject(new Wall(), 600-65-32*i, 113);
            }
            for(int i = 1; i<3; i++){
                addObject(new AroundWall(), 600-65-32*2, 113-32*i);
                addObject(new Wall(), 600-65-32*2, 113-32*i);
            }
            
            for(int i = 0; i<3; i++){
                addObject(new AroundWall(), 600-65-32*i, 400-113);
                addObject(new Wall(), 600-65-32*i, 400-113);
            }
            for(int i = 1; i<3; i++){
                addObject(new AroundWall(), 600-65-32*2, 400-113+32*i);
                addObject(new Wall(), 600-65-32*2, 400-113+32*i);
            }
            
            for(int i = 0; i<3; i++){
                addObject(new AroundWall(), 65+32*i, 400-113);
                addObject(new Wall(), 65+32*i, 400-113);
            }
            for(int i = 1; i<3; i++){
                addObject(new AroundWall(), 65+32*2, 400-113+32*i);
                addObject(new Wall(), 65+32*2, 400-113+32*i);
            }
            
            
            for(int i = 0; i<getObjects(Wall.class).size(); i++){
                getObjects(Wall.class).get(i).setBounds();
            }
            
            
            addObject(new Guard(), 350, 300);
            addObject(new Guard(), 200, 300);
            addObject(new Guard(), 200, 130);
            addObject(new Guard(), 350, 130);
            addObject(new Guard(), 522, 58);
            addObject(new Guard(), 522, 372);
            addObject(new Guard(), 38, 372);
            
            for(int i = 0; i<7; i++){
                addObject(new CpuTank(), 38+78*i, 202);
            }
        }

        for(numPowers=0;numPowers<MAX_POWERS; numPowers++){
            Powerup p = new Powerup();
            p.setPower();
            addObject(p, randomX(), randomY());
            while(p.publicIsTouching()){
                removeObject(p);
                addObject(p, randomX(), randomY());
            }
            powerups.add(p);
        }
    }

    /**
     * gets a random value for the X coordinate
     */
    public int randomX(){
        return (int)(Math.random()*getWidth());
    }

    /**
     * Gets a random value for the Y coordinate
     */
    public int randomY(){
        return (int)(Math.random()*getHeight());
    }
}
