package pfc;

public class VerifiedOutputState implements GameState 
{
    Driver gameDriver;
    public VerifiedOutputState(Driver gameDriver)
    {
        //super(gameDriver);
        this.gameDriver = gameDriver;
    }

    public void setState(){
        //gameDriver.setState(gameDriver.get());
    }


    //void setState(){}
    public void encodeInput(){}
    public void guessParity(){}
    public void createGame(){}
    public void startGame(){}

    public String toString()
    {
        return "VerifiedOutputState";
    }
    
    
    
}
