import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    
    static GreenfootImage[] images = { new GreenfootImage("fk12.png"),
                                                         new GreenfootImage("fk13.png"),
                                                         new GreenfootImage("fk23.png"),
                                                         new GreenfootImage("fk24.png"),
                                                         new GreenfootImage("fk1.png"),
                                                         new GreenfootImage("fk2 2.png")
                                                         };
    int playerNumber;
         
        public Player(int pNum)
        {
            playerNumber = pNum;
            setImage(images[pNum]);
        }                                                     
                                                         
                                                         
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
