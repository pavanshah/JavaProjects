package pfc;
import pfc.Driver;

public class CustomResult implements SummaryDecorator {

	InputObserver io;
	EncodingObserver eo;
	ParityGuessObserver pgo;
	OutcomeObserver oo;


	public CustomResult()
	{
		Driver gameDriver = Driver.getInstance();

		io = (InputObserver) gameDriver.getInputObserver();
		eo = (EncodingObserver) gameDriver.getEncodingObserver();
		pgo = (ParityGuessObserver) gameDriver.getParityGuessObserver();
		oo = (OutcomeObserver) gameDriver.getOutputObserver();
		
	}

	public String CollectResult()
	{
		String finalResult = io.getResult() +", "+ eo.getResult() +", "+ pgo.getResult() +", "+ oo.getResult();		
		return finalResult;
	}

	public String getResult()
	{
		return null;
	}
}