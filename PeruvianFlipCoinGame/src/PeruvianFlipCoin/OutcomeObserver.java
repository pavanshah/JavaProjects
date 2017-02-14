package pfc;
import pfc.Driver;
public class OutcomeObserver extends ConcreteObserver implements SummaryDecorator {
	

	public OutcomeObserver(Driver driver)
	{
		super(driver);
	}



	public void update ()
	{
		if(gameDriver.getState().equals("pfc.VerfifiedOutputState"))
		{
			observerState = gameDriver.getResult();
		}
	}


	public String getResult()
	{
		String[] result = gameDriver.getResult().split(",", 2);

		String finalResult = "Final Result - "+result[0];
		return finalResult;
	}

}