package pfc;

public class WaitingState implements GameState 
{
    Driver gameDriver;
   
    public WaitingState(Driver gameDriver)
    {
        //super(gameDriver);
        this.gameDriver = gameDriver;
    }

    public void setState(){
        System.out.println("In waiting state");
        gameDriver.setState(gameDriver.getKickOffState());
    }   

    //void setState();
    public void encodeInput(){}
    public void guessParity(){}
    //void createGame();
    public void startGame(){}

    public void createGame()
    {
        gameDriver.setState(gameDriver.getKickOffState());
    }
    
    public String toString()
    {
        return "WaitingState";
    }
    
}
