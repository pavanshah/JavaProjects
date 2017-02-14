package pfc;
import pfc.Driver;

public class InputObserver extends ConcreteObserver implements SummaryDecorator {
	

	public InputObserver(Driver driver)
	{
		super(driver);
	}

	public String observerState = "";

	public void update ()
	{

		if(gameDriver.getState().equals("pfc.NoInputState"))
		{
			observerState = gameDriver.getInputBits();
		}
	}


	public String getResult()
	{
		String result;
		result = "Circuit Input Provided By Captain A - "+gameDriver.getInputBits();
		return result;
	}

}