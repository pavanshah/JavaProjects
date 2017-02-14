import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import org.restlet.resource.ClientResource;
//import org.restlet.representation.Representation ;
import org.restlet.representation.* ;
import org.restlet.ext.json.*;
import org.json.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Write a description of class Driver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Driver extends Actor
{
    /**
     * Act - do whatever the Driver wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    //private final String service_url = "http://peruvian-3c74081a.d9c9ced2.svc.dockerapp.io:80/Peru";
    private final String service_url = "http://peruvian3-0cfeb908-1.8745656e.cont.dockerapp.io/Peru";
    
    
    CaptainA team1;
    CaptainB team2;
    //GameState state = new GameState();
    String state;
    int animationFlag = 0;
    int summaryFlag = 1;
    Circuit c = new Circuit();
    ClientResource peruvianClient;
    boolean[] boolEncoded = new boolean[6];
    boolean[] boolInputCaptainA = new boolean[6];
    int inputParity, guessedParity;
    int playerNumber=9;
    Mobile m;
    
    
    public Driver()
    {
        
        team1 = new CaptainA(2);
        team2 = new CaptainB(4);
        peruvianClient = new ClientResource( service_url ); 
        //state = new KickOffState();
        JSONObject json;
        Representation result = peruvianClient.get();
        System.out.println("In constructor");
        
       
        //World world = getWorld();
      
   
        //getWorld().addObject(m, 200, 200);
        try
        {
            json = new JSONObject(result.getText());
            state = json.getString("result");
            //state = json.getString("result");
            //Mobile m = new Mobile()
            //getWorld().addObject(m, 200, 200);
        }
        catch(Exception e)
        {
        }
        
        
    }
    
    public void act() 
    {
        //state = peruvian
        Representation result = peruvianClient.get();
        try
        {
            JSONObject json = new JSONObject(result.getText());
            state = json.getString("result");
        }
        catch(Exception e)
        {
        }
        
        ArrayList<Mobile> arr = (ArrayList<Mobile>)getWorld().getObjectsAt(871,480,Mobile.class);
        if(arr.size()!=0)
        {
            if(Greenfoot.mouseClicked(arr.get(0)))
            {
                createGame();
            }
        }
        
        ArrayList<Mobile> arrr = (ArrayList<Mobile>)getWorld().getObjectsAt(872,563,Mobile.class);
        if(arrr.size()!=0)
        {
            if(Greenfoot.mouseClicked(arrr.get(0)))
            {
                if(playerNumber != 1)
                {
                    joinGame();
                }
            }
        }
        
        if(state.equals("pfc.NoInputState"))
        {
            
            if(animationFlag == 0)
            {
             prepare();
             initialAnimation();
             animationFlag = 1;             
            }
        }
        
        
        if(state.equals("pfc.NoInputState") && playerNumber==1 && animationFlag == 1){
            getInputString();
        }
        
        if(state.equals("pfc.EncodedInputState") && playerNumber==2){
            getParity();
        }
        
        if(state.equals("pfc.VerifiedOutputState") && summaryFlag == 1){
            getSummary();
        }
        
        
        
        /*
        if(state.getClass().getName() == "KickOffState")
        {
            boolInputCaptainA = team1.encodeInput();
            System.out.println("Input: "+boolInputCaptainA[0]+""+boolInputCaptainA[1]+""+boolInputCaptainA[2]+""+boolInputCaptainA[3]+""+boolInputCaptainA[4]+""+boolInputCaptainA[5]);
            
            boolEncoded = c.randomSelection(boolInputCaptainA);
            
            System.out.println("Encoded: "+boolEncoded[0]+""+boolEncoded[1]+""+boolEncoded[2]+""+boolEncoded[3]+""+boolEncoded[4]+""+boolEncoded[5]);
            
            inputParity = findParity();
            
            state = new EncodedInputState();
        }
        
        if(state.getClass().getName() == "EncodedInputState")
        {
            guessedParity = team2.getParity();
            
            if(guessedParity == inputParity)
            {
                System.out.println("Captain B won");
            }
            else
            {
                System.out.println("Captain A won");
            }
            
            state = new VerifiedOutputState();
        }
        
        */
        // Add your action code here.
    }  
    
    private void prepare()
    {
        ArrayList<Mobile> arr = (ArrayList<Mobile>)getWorld().getObjectsAt(871,480,Mobile.class);
        getWorld().removeObject(arr.get(0));
        ArrayList<Mobile> arrr = (ArrayList<Mobile>)getWorld().getObjectsAt(872,563,Mobile.class);
        getWorld().removeObject(arrr.get(0));
        GreenfootImage images =  new GreenfootImage("world1.png");
        getWorld().setBackground(images);
        Player player1 = new Player(0);
        getWorld().addObject(player1, 608, 616);//RVP done
        Player player2 = new Player(1);
        getWorld().addObject(player2, 941, 625);//Mata done
        Player player3 = new Player(2);
        getWorld().addObject(player3, 398, 133);//Bale done
        Player player4 = new Player(3);
        getWorld().addObject(player4, 82, 122);//James done
        CaptainA capA = new CaptainA(5);
        getWorld().addObject(capA, 242, 378);//Ronaldo done
        CaptainB capB = new CaptainB(4);
        getWorld().addObject(capB, 780, 383);//Rooney done
        Greenfoot.delay(50);
        capA.setLocation(464,378);//Ronaldo Talking
        capB.setLocation(553,384);//Rooney Talking
        Greenfoot.delay(50);
    }
    
    public void createGame()
    {
        if(state.equals("pfc.WaitingState"))
        {
            JSONObject sendRequest = new JSONObject();
            sendRequest.put("function", "CreateGame");
            //response.put( "result", verified);
            //response.put("state",gameState);
            //JsonRepresentation sendRequest = new JsonRepresentation();
            playerNumber = 1;
            Representation keyResult = peruvianClient.post(new JsonRepresentation(sendRequest));//, MediaType.APPLICATION_JSON);
            try
            {
                JSONObject keyJson = new JSONObject(keyResult.getText());
                String gameKey = keyJson.getString("result");
                state = keyJson.getString("state");
                JFrame frame = new JFrame("circuitInput");
                JOptionPane.showMessageDialog(frame, "Your Game Key: "+gameKey);
                System.out.println("Game State"+state);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
            //Representation result = peruvianClient.get() ;
        }
    }
    
    public void joinGame()
    {
       //String input = Greenfoot.ask("Enter 4 digit Game Key to connect");
       
       JFrame frame = new JFrame("circuitInput");
       String input = JOptionPane.showInputDialog(frame, "Enter 4 digit Game Key to connect");
       
       JSONObject sendRequest = new JSONObject();
            sendRequest.put("function", "JoinGame");
            sendRequest.put("gameKey", input);
            //response.put( "result", verified);
            //response.put("state",gameState);
            //JsonRepresentation sendRequest = new JsonRepresentation();
            playerNumber = 2;
            Representation keyResult = peruvianClient.post(new JsonRepresentation(sendRequest));//, MediaType.APPLICATION_JSON);
            try
            {
                JSONObject keyJson = new JSONObject(keyResult.getText());
                String verifiedKey = keyJson.getString("result");
                state = keyJson.getString("state");
                System.out.println("VerifiedKey:"+verifiedKey);
                if(verifiedKey.equals("false"))
                 {
                     JFrame frame1 = new JFrame("circuitInput");
                     JOptionPane.showMessageDialog(frame1,"Incorrect Gamekey, Please reenter","Warning",JOptionPane.WARNING_MESSAGE);
                     System.out.println("VerifiedKey:"+verifiedKey);
                     joinGame();
                 }

                System.out.println("Game State"+state);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
    }
    
    
    public void getInputString(){
        
       //String input = Greenfoot.ask("Please enter six digit input string");
       JFrame frame = new JFrame("circuitInput");
       String input = JOptionPane.showInputDialog(frame, "Enter 6 binary bits to be encoded:");
       
       if(input == null || input.isEmpty() || !input.matches("[0-1]*") || input.length() != 6)
        {
            input = "";
            JFrame frame1 = new JFrame("circuitInput");
            JOptionPane.showMessageDialog(frame1,"Invalid input string, Please reenter","Warning",JOptionPane.WARNING_MESSAGE);
            getInputString();
        }
       
       else
       {
           JSONObject sendRequest = new JSONObject();
            sendRequest.put("function", "EncodeBits");
            sendRequest.put("inputBits", input);
            //response.put( "result", verified);
            //response.put("state",gameState);
            //JsonRepresentation sendRequest = new JsonRepresentation();
           
            Representation keyResult = peruvianClient.post(new JsonRepresentation(sendRequest));//, MediaType.APPLICATION_JSON);
            try
            {
                JSONObject keyJson = new JSONObject(keyResult.getText());
                String verifiedKey = keyJson.getString("result");
                //state = keyJson.getString("state");
                System.out.println("VerifiedKey:"+verifiedKey);
                System.out.println("Game State"+state);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
    
    
    
    public void getParity()
    {
      // String input = Greenfoot.ask("Please Guess the Parity");
      
      
      String encodedBits = new String();
      JSONObject sendRequest = new JSONObject();
      sendRequest.put("function", "GetEncodedBits");
      //sendRequest.put("inputBits", input);
            //response.put( "result", verified);
            //response.put("state",gameState);
            //JsonRepresentation sendRequest = new JsonRepresentation();
           
      Representation keyResult = peruvianClient.post(new JsonRepresentation(sendRequest));//, MediaType.APPLICATION_JSON);
      try
        {
         JSONObject keyJson = new JSONObject(keyResult.getText());
         encodedBits = keyJson.getString("result");
         //state = keyJson.getString("state");
         System.out.println("EncodedBits:"+encodedBits);
         System.out.println("Game State"+state);
        }
        catch(Exception e)
        {
           System.out.println(e);
        }
      
      
      
      
      
      JFrame frame = new JFrame("circuitInput");
      String input = JOptionPane.showInputDialog(frame, "The Encoded output from other player is: "+encodedBits+".\nGuess the input bits Parity:");
      
      if(input == null || input.isEmpty() || !input.matches("[0-1]*") || input.length() != 1)
        {
            input = "";
            JFrame frame1 = new JFrame("circuitInput");
            JOptionPane.showMessageDialog(frame1,"Invalid input, please only enter 0 or 1","Warning",JOptionPane.WARNING_MESSAGE);
            getParity();
        }
        else
        {
       
       JSONObject sendRequest2 = new JSONObject();
            sendRequest2.put("function", "GuessParity");
            sendRequest2.put("inputParity", input);
            Representation keyResult2 = peruvianClient.post(new JsonRepresentation(sendRequest2));//, MediaType.APPLICATION_JSON);
            try
            {
                JSONObject keyJson = new JSONObject(keyResult2.getText());
                String verifiedKey = keyJson.getString("result");
                //state = keyJson.getString("state");
                System.out.println("VerifiedKey:"+verifiedKey);
                //showResult(verifiedKey); 
                System.out.println("Game State"+state);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
    public void showResult(String verifiedKey)
            {
                String[] result = verifiedKey.split(",", 2);
                System.out.println("VerifiedKey:"+result[0]);
                JFrame frame1 = new JFrame("circuitInput");
                JOptionPane.showMessageDialog(frame1, ""+result[0]);
            }
            
    public void getSummary()
    {
            System.out.println("VerifiedOutputState");
            JSONObject sendRequest = new JSONObject();
            sendRequest.put("function", "GetSummary");
            Representation keyResult = peruvianClient.post(new JsonRepresentation(sendRequest));//, MediaType.APPLICATION_JSON);
            String finalResult = new String();
            try
            {
                JSONObject keyJson = new JSONObject(keyResult.getText());
                String summary = keyJson.getString("result");
                System.out.println("Summary "+summary);
                
                
                //player 1 = captain a
                
                if(summary.charAt(137) == 'A')
                {
                    if(playerNumber == 1)
                    {
                        finalResult = "YOU WON!";
                    }
                    else
                    {
                        finalResult = "YOU LOSE!";
                    }
                }
                else
                {   if(playerNumber == 1)
                    {
                        finalResult = "YOU LOSE!";
                    }
                    else
                    {
                        finalResult = "YOU WIN!";
                    }}
                
                JFrame frame1 = new JFrame("result");
                JOptionPane.showMessageDialog(frame1, finalResult);
                
                String[] result = summary.split(",", 5);
                System.out.println("splitted summary"+result[0]);
                
                JFrame frame = new JFrame("circuitInput");
                JOptionPane.showMessageDialog(frame, "Game Summary: \n"+result[0]+"\n"+result[1]+"\n"+result[2]+"\n"+result[3]);
                
                
                
                
                
                JSONObject sendRequest2 = new JSONObject();
                sendRequest2.put("function", "ResetGame");
                //response.put( "result", verified);
                //response.put("state",gameState);
                //JsonRepresentation sendRequest = new JsonRepresentation();
                //playerNumber = 1;
                Representation keyResult3 = peruvianClient.post(new JsonRepresentation(sendRequest2));//, MediaType.APPLICATION_JSON);
                try
                {
                    JSONObject keyJson3 = new JSONObject(keyResult3.getText());
                    //String gameKey = keyJson.getString("result");
                    state = keyJson3.getString("state");
                    
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                
                
                
                
                
                
                
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            
            summaryFlag = 0;
    }
    
    public void initialAnimation()
    {
                World world = getWorld();
                
                Greenfoot.delay(50);
                Mobile mobile1 = new Mobile(1);
                world.addObject(mobile1, 495, 324);
               
                Mobile mobile2 = new Mobile(1);
                world.addObject(mobile2, 526, 324);
                Greenfoot.delay(20);
                //world.showText("Hey Rooney!!", 225, 100);
               // Greenfoot.delay(50);
                
                //world.showText("Hey Chris!!", 370, 100);
                
                Message message1 = new Message(4);//hey cris
                world.addObject(message1, 597, 251);
                Greenfoot.playSound("r1.wav");
                Greenfoot.delay(150);
                world.removeObject(message1);
                
                Message message2 = new Message(5);//hey rooney
                world.addObject(message2,422, 251);
                Greenfoot.playSound("c1.wav");
                Greenfoot.delay(150);
                world.removeObject(message2);
                
                Message message3 = new Message(6);//Lets have a game this weekend
                world.addObject(message3, 597, 251);
                Greenfoot.playSound("r2.wav");
                Greenfoot.delay(200);
                world.removeObject(message3);
                
                Message message4 = new Message(7);//Sure, Come to our homeground
                world.addObject(message4, 422, 251);
                Greenfoot.playSound("c2.wav");
                Greenfoot.delay(200);
                world.removeObject(message4);
                
                Message message5 = new Message(8);//unfair
                world.addObject(message5, 597, 251);
                Greenfoot.playSound("r3.wav");
                Greenfoot.delay(250);
                world.removeObject(message5);
                
                Message message6 = new Message(9); //flip coin
                world.addObject(message6, 597, 251);
                Greenfoot.delay(300);
                world.removeObject(message6);
                
                Message message7 = new Message(10);//Ok, fairness
                world.addObject(message7, 422, 251);
                Greenfoot.playSound("c3.wav");
                Greenfoot.delay(300);
                world.removeObject(message7);
                
                Message message8 = new Message(11);//It's not just a normal toss, it’s called Peruvian flip coin.
                world.addObject(message8, 597, 251);
                Greenfoot.playSound("r4.wav");
                Greenfoot.delay(250);
                world.removeObject(message8);
                
                
                Message message9 = new Message(12);//how does it work?
                world.addObject(message9, 422, 251);
                Greenfoot.playSound("c4.wav");
                Greenfoot.delay(200);
                world.removeObject(message9);
                
                Message message10 = new Message(13); //I'll provide a binary input to this circuit. You need to guess its parity.
                world.addObject(message10, 597, 251);
                Greenfoot.playSound("r5.wav");
                Greenfoot.delay(200);
                world.removeObject(message10);

                Circuit circuit = new Circuit();
                world.addObject(circuit, 510, 500);
                Greenfoot.delay(200);
                world.removeObject(message10);
                world.removeObject(circuit);
                
                
                Message message11 = new Message(14);//If you guess it correct, you win. 
                world.addObject(message11, 597, 251);
                Greenfoot.playSound("r6.wav");
                Greenfoot.delay(200);
                world.removeObject(message11);
                
                Message message12 = new Message(15);//You can verify the result from your end to check fairness.
                world.addObject(message12, 597, 251);
                Greenfoot.playSound("r7.wav");
                Greenfoot.delay(250);
                world.removeObject(message12);
                
                Message message13 = new Message(16); //Ok, I am in.
                world.addObject(message13, 422, 251);
                Greenfoot.playSound("c5.wav");
                Greenfoot.delay(200);
                world.removeObject(message13);
             
                Greenfoot.delay(50);
    }
    
    
}
