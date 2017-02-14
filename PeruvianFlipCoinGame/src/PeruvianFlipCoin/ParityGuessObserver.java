package pfc;
import pfc.Driver;

public class ParityGuessObserver extends ConcreteObserver implements SummaryDecorator {
	

	public ParityGuessObserver(Driver driver)
	{
		super(driver);
	}



	public void update ()
	{
		if(gameDriver.getState().equals("pfc.VerfifiedOutputState"))
		{
			observerState = gameDriver.getGuessedParity();
		}
	}


	public String getResult()
	{
		String result;
		result = "Parity Guessed By Captain B - "+gameDriver.getGuessedParity();
		return result;
	}

}