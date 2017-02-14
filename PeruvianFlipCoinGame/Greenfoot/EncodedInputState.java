import java.util.concurrent.ThreadLocalRandom;
/**
 * Write a description of class EncodedInputState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class EncodedInputState extends GameState 
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class EncodeInputState
     */
    public EncodedInputState()
    {
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
    
    public String toString()
    {
        return "Captain has encoded a String";
    }
    
    public boolean[] encodeString(boolean [] inputString)
    {
        Circuit myCircuit = new Circuit();
        
        int randomCircuitToUse = ThreadLocalRandom.current().nextInt(1,6); //Min 1 and Max 5
        boolean [] encodedString = new boolean[6];
        switch(randomCircuitToUse){
            case 1: 
                encodedString = myCircuit.circuit1(inputString);
                break;
            case 2: 
                encodedString = myCircuit.circuit2(inputString);
                break;
            case 3: 
                encodedString = myCircuit.circuit3(inputString);
                break;
            case 4:
                encodedString = myCircuit.circuit4(inputString);
                break;
            case 5:
                encodedString = myCircuit.circuit5(inputString);
                break;
            
        }
        
        return encodedString;
    }
}
