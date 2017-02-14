package pfc;

public class NoInputState implements GameState 
{
    Driver gameDriver;
    public NoInputState(Driver gameDriver)
    {
        //super(gameDriver);
        this.gameDriver = gameDriver;
    }

    public void setState(){
        gameDriver.setState(gameDriver.getEncodedInputState());
    }
           

    //void setState();
    //void encodeInput();
    public void guessParity(){}
    public void createGame(){}
    public void startGame(){}

    public void encodeInput()
    {
        gameDriver.setState(gameDriver.getEncodedInputState());
    }

    public String toString()
    {
        return "No Input State";
    }
}
