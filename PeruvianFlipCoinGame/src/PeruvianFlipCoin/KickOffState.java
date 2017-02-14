package pfc;

public class KickOffState implements GameState 
{
    Driver gameDriver;
    public KickOffState(Driver gameDriver)
    {
        //super(gameDriver);
        this.gameDriver = gameDriver;
    }

    public void setState(){
        gameDriver.setState(gameDriver.getNoInputState());
    }
    
    //void setState();
    public void encodeInput(){}
    public void guessParity(){}
    public void createGame(){}
    //void startGame();

    public void startGame()
    {
        gameDriver.setState(gameDriver.getNoInputState());
    }   

    public String toString()
    {
        return "KickOffState";
    }
    
}
