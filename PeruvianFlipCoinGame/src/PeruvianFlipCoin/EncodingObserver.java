package pfc;
import pfc.Driver;

public class EncodingObserver extends ConcreteObserver implements SummaryDecorator
{
	public EncodingObserver(Driver driver){

		super(driver);
	}


	public void update ()
	{
		if(gameDriver.getState().equals("pfc.NoInputState"))
		{
			observerState = gameDriver.getEncodedBits();
		}
	}


	public String getResult()
	{
		String result;
		result = "Encoded Input By Circuit - "+gameDriver.getEncodedBits();
		return result;
	}
}