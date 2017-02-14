package pfc;
import pfc.Driver;

public class ObserverClient{
	
	public ObserverClient()
	{
	Driver gameDriver = Driver.getInstance();

	InputObserver io = new InputObserver(gameDriver);
	EncodingObserver eo = new EncodingObserver(gameDriver);
	ParityGuessObserver pgo = new ParityGuessObserver(gameDriver);
	OutcomeObserver oo = new OutcomeObserver(gameDriver);
	
    gameDriver.attach(io);
	gameDriver.attach(eo);
	gameDriver.attach(pgo);
	gameDriver.attach(oo);

	}



	

}