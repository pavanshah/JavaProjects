package pfc;

public interface GameState  
{
    //Driver gameDriver;

/*    public GameState(Driver gameDriver)
    {
        this.gameDriver = gameDriver;
    }

    public void setState()
    {

    }
    
    public String toString()
    {
        return null;
    }*/

    public void setState();
    public void encodeInput();
    public void guessParity();
    public void createGame();
    public void startGame();
}
