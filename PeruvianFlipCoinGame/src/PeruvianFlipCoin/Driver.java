//import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
package pfc;
import java.util.Random;
import java.util.*;

/**
 * Write a description of class Driver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Driver
{
    /**
     * Act - do whatever the Driver wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //CaptainA team1;
    //CaptainB team2;
    //GameState state = new GameState();
    Circuit c = new Circuit();
    
    private static Driver theDriver;

    boolean[] boolEncoded = new boolean[6];
    boolean[] boolInputCaptainA = new boolean[6];
    int inputParity, guessedParity,gameKey;
    String givenInput,generatedOutput;
    String outputResult;
   // private NoPlayerState noPlayerState;
    private WaitingState waitingState;
    private KickOffState kickOffState;
    private NoInputState noInputState;
    private EncodedInputState encodedInputState;
    private VerifiedOutputState verifiedOutputState;

    private GameState gameState;
    private String output = "";
    private ArrayList<PeruvianObserver> observers = new ArrayList<PeruvianObserver>();

    public static Driver getInstance()
    {
        if(theDriver == null)
        {
            theDriver = new Driver();
            return theDriver;
        }
        else
        {
            return theDriver;
        }
    }


    public Driver()
    {
        
        //team1 = new CaptainA(2);
        //team2 = new CaptainB(4);
        waitingState = new WaitingState(this);
        kickOffState = new KickOffState(this);
        noInputState = new NoInputState(this);
        encodedInputState = new EncodedInputState(this);
        verifiedOutputState = new VerifiedOutputState(this);

        gameState = waitingState;
    }
    
    public String encodedInput(String input)
    {
        //this.boolEncoded = boolEncoded;

        givenInput = input;
        char [] inputbits = input.toCharArray();
        char [] outputBits = new char[6];
        int i=0;
        for(char a : inputbits)
        {
            if(a=='1')
            boolInputCaptainA[i]=true;
            else
            boolInputCaptainA[i]=false;
          i++;
        }
            //this.boolInputCaptainA = boolInputCaptainA;
            System.out.println("Input: "+boolInputCaptainA[0]+""+boolInputCaptainA[1]+""+boolInputCaptainA[2]+""+boolInputCaptainA[3]+""+boolInputCaptainA[4]+""+boolInputCaptainA[5]);
            
            boolEncoded = c.randomSelection(boolInputCaptainA);
            
            System.out.println("Encoded: "+boolEncoded[0]+""+boolEncoded[1]+""+boolEncoded[2]+""+boolEncoded[3]+""+boolEncoded[4]+""+boolEncoded[5]);
            
            inputParity = findParity();
            i=0;
            for(boolean a : boolEncoded)
            {
                if(a)
               outputBits[i]='1';
               else
               outputBits[i]='0';
                 i++;
              }
              System.out.println("hksdbdfhgcsih"+gameState.getClass().getName());
            gameState.encodeInput();
            notifyObservers();
            outputResult = new String(outputBits);
            return outputResult;
    }

    public String guessParity(int guessedParity)
    {
        
        this.guessedParity = guessedParity;
            if(guessedParity == inputParity)
            {
                output = "Captain B won";
                System.out.println("Captain B won");
            }
            else
            {
                output = "Captain A won";
                System.out.println("Captain A won");
            }
            
            gameState.guessParity();
            notifyObservers();
            output = output + ",VerifiedOutputState";
            return output;
    }

    public String getResult(){

        return output;
    }

    public String createGame(){

        Random gen = new Random();

        int minRange =1000;

        gameKey = minRange + gen.nextInt(8999);

        gameState.createGame();
        //setState(kickOffState);
        notifyObservers();

        return (Integer.toString(gameKey));

    }

    public String startGame(String providedKey){


        if((Integer.toString(gameKey)).equals(providedKey))
        {
            gameState.startGame();
            //setState(noInputState);
            notifyObservers();
            return "true";
            

        }

        else
        {
            return "false";
        }

    }

    public String getInputBits(){

        return givenInput;
    }

    public String getGuessedParity(){

        return Integer.toString(guessedParity);
    }

    public String getEncodedBits(){

        generatedOutput="";

        for(boolean a : boolEncoded)
        {
            if(a==true)
            {
                generatedOutput+="1";
            }

            else
                generatedOutput+="0";
        }

        return generatedOutput;
    }

    public void resetGame(){

        //gameState.setState();
        setState(waitingState);
        notifyObservers();
        gameKey = 1000;
    }

    public void setState(GameState state){


        this.gameState = state;

    }

    public String getSummary()
    {
    	CustomResult summary = new CustomResult();
        String result = summary.CollectResult();
    	return result;
    }

    public String getState(){

       return this.gameState.getClass().getName();
    }

    public GameState getStateIntance(){
        return this.gameState;
    }

    public GameState getKickOffState()
    {
        return kickOffState;
    }

    public GameState getNoInputState()
    {
        return noInputState;
    }

    public GameState getVerifiedOutputState()
    {
        return verifiedOutputState;
    }

    public GameState getWaitingState()
    {
        return waitingState;
    }

    public GameState getEncodedInputState()
    {
        return encodedInputState;
    }

    public void attach (PeruvianObserver p){

        observers.add(p);
    }

    public void notifyObservers() {
        for (PeruvianObserver obj  : observers)
        {
            obj.update();
        }
    }


    public PeruvianObserver getInputObserver()
    {
        return observers.get(0);
    }

    public PeruvianObserver getEncodingObserver()
    {
        return observers.get(1);
    }

    public PeruvianObserver getParityGuessObserver()
    {
        return observers.get(2);
    }

    public PeruvianObserver getOutputObserver()
    {
        return observers.get(3);
    }

    public String fetchEncodedBits()
    {
        return outputResult;
    }

/*
    public void act() 
    {
        if(state.getClass().getName() == "KickOffState")
        {
            boolInputCaptainA = team1.encodeInput();
            System.out.println("Input: "+boolInputCaptainA[0]+""+boolInputCaptainA[1]+""+boolInputCaptainA[2]+""+boolInputCaptainA[3]+""+boolInputCaptainA[4]+""+boolInputCaptainA[5]);
            
            boolEncoded = c.randomSelection(boolInputCaptainA);
            
            System.out.println("Encoded: "+boolEncoded[0]+""+boolEncoded[1]+""+boolEncoded[2]+""+boolEncoded[3]+""+boolEncoded[4]+""+boolEncoded[5]);
            
            inputParity = findParity();
            
            state = new EncodedInputState();
        }
        
        if(state.getClass().getName() == "EncodedInputState")
        {
            guessedParity = team2.getParity();
            
            if(guessedParity == inputParity)
            {
                System.out.println("Captain B won");
            }
            else
            {
                System.out.println("Captain A won");
            }
            
            state = new VerifiedOutputState();
        }
        
        
        // Add your action code here.
    }   
*/
    public int findParity()
    {
        int i=0;
        int bitCounter = 0;
        for(i=0; i<6; i++)
        {
            if(boolInputCaptainA[i]==true)
            {
                bitCounter++;
            }
        }
        if(bitCounter%2 == 0)
        return 0;
        else
        return 1;
    }
}
