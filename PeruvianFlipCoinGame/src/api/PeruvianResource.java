		package api ;

import org.json.* ;
import org.restlet.representation.* ;
import org.restlet.ext.json.* ;
import org.restlet.resource.* ;
import pfc.Driver ;
import pfc.ObserverClient;

public class PeruvianResource extends ServerResource
{
	Driver driver = Driver.getInstance();
	ObserverClient client = new ObserverClient();


	@Post
	public Representation EncodeInputs(JsonRepresentation input)
	{
		JSONObject json = input.getJsonObject();
		String inputFunction = json.getString("function");


		if(inputFunction.equals("CreateGame"))
		{

			String gameKey = driver.createGame();
			String gameState = driver.getState();
			JSONObject response = new JSONObject();
			response.put( "result", gameKey);
			response.put("state",gameState);
        	return new JsonRepresentation (response) ;

		}

		if(inputFunction.equals("ResetGame"))
		{

			driver.resetGame();
			//String gameKey = driver.createGame();
			String gameState = driver.getState();
			JSONObject response = new JSONObject();
			//response.put( "result", gameKey);
			response.put("state",gameState);
        	return new JsonRepresentation (response) ;




		}

		if(inputFunction.equals("JoinGame"))
		{
			
			String providedKey = json.getString("gameKey");
			String verified = driver.startGame(providedKey);
			String gameState = driver.getState();
			JSONObject response = new JSONObject();
			response.put( "result", verified);
			response.put("state",gameState);
        	return new JsonRepresentation (response) ;
		}

		if(inputFunction.equals("EncodeBits"))
		{
			String encodedInputBits = json.getString("inputBits");
			String output = driver.encodedInput(encodedInputBits);

			JSONObject response = new JSONObject() ;
        	//String state = machine.getStateString() ;
        	response.put( "result", output ) ;

        	return new JsonRepresentation ( response ) ;
		}


        if(inputFunction.equals("GuessParity"))
        {
       		int encodedInputParity = Integer.parseInt(json.getString("inputParity"));
			String output = driver.guessParity(encodedInputParity);

			JSONObject response = new JSONObject() ;
        	response.put( "result", output ) ;

        	return new JsonRepresentation ( response ) ;
        }

        if(inputFunction.equals("GetResult"))
        {
			String output = driver.getResult();
			JSONObject response = new JSONObject() ;
        	response.put( "result", output ) ;

        	return new JsonRepresentation ( response ) ;

        }

        if(inputFunction.equals("GetEncodedBits"))
        {
        	String output = driver.fetchEncodedBits();
			JSONObject response = new JSONObject() ;
        	response.put( "result", output ) ;

        	return new JsonRepresentation ( response ) ;
        }


        if(inputFunction.equals("GetSummary"))
		{
			String summary = driver.getSummary();

			JSONObject response = new JSONObject() ;
        	//String state = machine.getStateString() ;
        	response.put( "result", summary ) ;

        	return new JsonRepresentation ( response ) ;
		}

        	JSONObject response = new JSONObject() ;
        	response.put( "result", "ERROR" ) ;

        	return new JsonRepresentation ( response ) ;
	} 


	@Get
	public Representation getState()
	{
		String gameState = driver.getState();
			JSONObject response = new JSONObject() ;
        	response.put( "result", gameState ) ;

        	return new JsonRepresentation ( response ) ;

	}
}