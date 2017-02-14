import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PeruvianFlipCoinWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public PeruvianFlipCoinWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1); 
        GreenfootImage images =  new GreenfootImage("WelcomeScreen.png");
        setBackground(images);
        gameButtons();
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */

    private void gameButtons()
    {
        Driver driver = new Driver();
        addObject(driver,4,5);
        Mobile mobile = new Mobile(2);
        addObject(mobile,871,480);
        mobile.setLocation(871,480);
        Mobile mobile2 = new Mobile(3);
        addObject(mobile2,872,563);
        mobile2.setLocation(872,563);
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        //Driver driver = new Driver();
        //addObject(driver,4,5);
    }
}
