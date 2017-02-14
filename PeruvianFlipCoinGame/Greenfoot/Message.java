import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Message here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Message extends Actor
{
    /**
     * Act - do whatever the Message wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    static GreenfootImage[] images={ new GreenfootImage("mobile.png"),
                                     new GreenfootImage("mobile.png"),
                                     new GreenfootImage("mobile.png"),
                                     new GreenfootImage("mobile.png"),
                                     new GreenfootImage("message4.png"),
                                     new GreenfootImage("message5.png"),
                                     new GreenfootImage("message6.png"),
                                     new GreenfootImage("message7.png"),
                                     new GreenfootImage("message8.png"),
                                     new GreenfootImage("message9.png"),
                                     new GreenfootImage("message10.png"),
                                     new GreenfootImage("message11.png"),
                                     new GreenfootImage("message12.png"),
                                     new GreenfootImage("message13.png"),
                                     new GreenfootImage("message14.png"),
                                     new GreenfootImage("message15.png"),
                                     new GreenfootImage("message16.png")
                                     };
    
    public Message(int num)
    {
        GreenfootImage image = images[num];
       // image.scale(200, 100);
        setImage(image);     
    }
    
    
   
    
    public void act() 
    {
        // Add your action code here.
    }    
}
