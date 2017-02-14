import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;

/**
 * Write a description of class CaptainA here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaptainA extends Player
{
    /**
     * Act - do whatever the CaptainA wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       
    }    
    
    public CaptainA(int pNum)
        {
            super(pNum);
           // setImage(images[pNum]);
        }         
        
        public boolean[] encodeInput(){
        String input="";
        boolean[] boolinput = new boolean[6];       
         
        JFrame frame = new JFrame("circuitInput");
        input = JOptionPane.showInputDialog(frame, "Enter 6 binary bits to be encoded:");
        
       // input = Greenfoot.ask("Enter 6 binary bits to be encoded:");       
       
        
        System.out.println(input);
        char [] inputbits = input.toCharArray();
        int i=0;
        for(char a : inputbits)
        {
            if(a=='1')
            boolinput[i]=true;
            else
            boolinput[i]=false;
          i++;
        }
        
        return boolinput;
    }

}
