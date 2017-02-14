package pfc;
import pfc.Driver;

public class ConcreteObserver implements PeruvianObserver {
 
	protected Driver gameDriver;
    protected String observerState;

    public ConcreteObserver( Driver driver )
    {
        this.gameDriver = driver ;
    }
    
	public void update() {
	    // do nothing
	}

    public String showState()
    {
        System.out.println( "Observer: " + this.getClass().getName() + " = " + observerState );
        return observerState;
    }


    public String getResult()
    {
        return observerState;
    }
	 
}
 