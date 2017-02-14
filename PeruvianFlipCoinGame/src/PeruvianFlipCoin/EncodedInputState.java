package pfc;
import java.util.concurrent.ThreadLocalRandom;

public class EncodedInputState implements GameState 
{
   
    Driver gameDriver;
    public EncodedInputState(Driver gameDriver)
    {
        //super(gameDriver);
        this.gameDriver = gameDriver;
    }

   
    public void setState(){
        gameDriver.setState(gameDriver.getVerifiedOutputState());
    }
 
    //void setState();
    public void encodeInput(){}
    //void guessParity();
    public void createGame(){}
    public void startGame(){}

    public void guessParity()
    {
        gameDriver.setState(gameDriver.getVerifiedOutputState());
    }

    public String toString()
    {
        return "Captain has encoded a String";
    }

}
