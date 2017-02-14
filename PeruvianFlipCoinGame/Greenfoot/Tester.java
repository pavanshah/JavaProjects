import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TesterClass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester extends Actor
{
    Circuit c = new Circuit();
    int[] input1 = {0,0,0,0,0,0};
    int[] input2 = {0,1,0,1,0,1};
    int[] input3 = {1,1,1,1,1,1};
    int[] output1 = new int[6];
    int[] output2 = new int[6];
    int[] output3 = new int[6];
    //boolean[] input1 = {false,false,false,false,false,false};
    //input1 = {0,0,0,0,0,0};
    
    /**
     * Act - do whatever the TesterClass wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    public void testAllCircuits()
    {
        circuitOneTester();
        circuitTwoTester();
        circuitThreeTester();
        circuitFourTester();
        circuitFiveTester();
    }
    
    public void circuitOneTester()
    {
        System.out.println("Testing the First Circuit:\n");
        
        boolean[] boolinput1 = new boolean[6];
        boolean[] boolinput2 = new boolean[6];
        boolean[] boolinput3 = new boolean[6];
        boolean[] booloutput1 = new boolean[6];
        boolean[] booloutput2 = new boolean[6];
        boolean[] booloutput3 = new boolean[6];
        int[] expectedoutput1 = {0,0,1,1,1,0};
        int[] expectedoutput2 = {0,0,1,1,1,0};
        int[] expectedoutput3 = {0,0,0,1,1,0};
        boolean flag1 = true,flag2 = true,flag3 = true;
        
        for(int i=0;i<6;i++)
		{
			if(input1[i]==1)
				boolinput1[i] = true;
			else
				boolinput1[i] = false;
			
			if(input2[i]==1)
				boolinput2[i] = true;
			else
				boolinput2[i] = false;
				
			if(input3[i]==1)
				boolinput3[i] = true;
			else
				boolinput3[i] = false;
		}
		
		System.out.println("Testing for Input: "+input1[0]+""+input1[1]+""+input1[2]+""+input1[3]+""+input1[4]+""+input1[5]);
		
		//booloutput1 = c.circuit1(boolinput1);
		booloutput1 = c.testerfunction(boolinput1, 1);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput1[j]==true)
				output1[j] = 1;
			else
				output1[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput1[k] != output1[k])
		    {
		        flag1 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput1[0]+""+expectedoutput1[1]+""+expectedoutput1[2]+""+expectedoutput1[3]+""+expectedoutput1[4]+""+expectedoutput1[5]);		
		System.out.println("Actual Output: "+output1[0]+""+output1[1]+""+output1[2]+""+output1[3]+""+output1[4]+""+output1[5]);
		System.out.println("Test case result: "+flag1+"\n");
		
		//For input2
		
		System.out.println("Testing for Input: "+input2[0]+""+input2[1]+""+input2[2]+""+input2[3]+""+input2[4]+""+input2[5]);
		
		booloutput2 = c.testerfunction(boolinput2,1);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput2[j]==true)
				output2[j] = 1;
			else
				output2[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput2[k] != output2[k])
		    {
		        flag2 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput2[0]+""+expectedoutput2[1]+""+expectedoutput2[2]+""+expectedoutput2[3]+""+expectedoutput2[4]+""+expectedoutput2[5]);		
		System.out.println("Actual Output: "+output2[0]+""+output2[1]+""+output2[2]+""+output2[3]+""+output2[4]+""+output2[5]);
		System.out.println("Test case result: "+flag2+"\n");
		
		//For input3
		
		System.out.println("Testing for Input: "+input3[0]+""+input3[1]+""+input3[2]+""+input3[3]+""+input3[4]+""+input3[5]);
		
		booloutput3 = c.testerfunction(boolinput3,1);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput3[j]==true)
				output3[j] = 1;
			else
				output3[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput3[k] != output3[k])
		    {
		        flag3 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput3[0]+""+expectedoutput3[1]+""+expectedoutput3[2]+""+expectedoutput3[3]+""+expectedoutput3[4]+""+expectedoutput3[5]);		
		System.out.println("Actual Output: "+output3[0]+""+output3[1]+""+output3[2]+""+output3[3]+""+output3[4]+""+output3[5]);
		System.out.println("Test case result: "+flag3+"\n");
    }
    
    public void circuitTwoTester()
    {
        System.out.println("Testing the Second Circuit:\n");
        
        boolean[] boolinput1 = new boolean[6];
        boolean[] boolinput2 = new boolean[6];
        boolean[] boolinput3 = new boolean[6];
        boolean[] booloutput1 = new boolean[6];
        boolean[] booloutput2 = new boolean[6];
        boolean[] booloutput3 = new boolean[6];
        int[] expectedoutput1 = {1,0,0,1,1,0};
        int[] expectedoutput2 = {1,0,0,0,0,1};
        int[] expectedoutput3 = {0,0,0,1,0,1};
        boolean flag1 = true,flag2 = true,flag3 = true;
        
        for(int i=0;i<6;i++)
		{
			if(input1[i]==1)
				boolinput1[i] = true;
			else
				boolinput1[i] = false;
			
			if(input2[i]==1)
				boolinput2[i] = true;
			else
				boolinput2[i] = false;
				
			if(input3[i]==1)
				boolinput3[i] = true;
			else
				boolinput3[i] = false;
		}
		
		System.out.println("Testing for Input: "+input1[0]+""+input1[1]+""+input1[2]+""+input1[3]+""+input1[4]+""+input1[5]);
		
		booloutput1 = c.testerfunction(boolinput1,2);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput1[j]==true)
				output1[j] = 1;
			else
				output1[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput1[k] != output1[k])
		    {
		        flag1 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput1[0]+""+expectedoutput1[1]+""+expectedoutput1[2]+""+expectedoutput1[3]+""+expectedoutput1[4]+""+expectedoutput1[5]);		
		System.out.println("Actual Output: "+output1[0]+""+output1[1]+""+output1[2]+""+output1[3]+""+output1[4]+""+output1[5]);
		System.out.println("Test case result: "+flag1+"\n");
		
		//For input2
		
		System.out.println("Testing for Input: "+input2[0]+""+input2[1]+""+input2[2]+""+input2[3]+""+input2[4]+""+input2[5]);
		
		booloutput2 = c.testerfunction(boolinput2,2);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput2[j]==true)
				output2[j] = 1;
			else
				output2[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput2[k] != output2[k])
		    {
		        flag2 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput2[0]+""+expectedoutput2[1]+""+expectedoutput2[2]+""+expectedoutput2[3]+""+expectedoutput2[4]+""+expectedoutput2[5]);		
		System.out.println("Actual Output: "+output2[0]+""+output2[1]+""+output2[2]+""+output2[3]+""+output2[4]+""+output2[5]);
		System.out.println("Test case result: "+flag2+"\n");
		
		//For input3
		
		System.out.println("Testing for Input: "+input3[0]+""+input3[1]+""+input3[2]+""+input3[3]+""+input3[4]+""+input3[5]);
		
		booloutput3 = c.testerfunction(boolinput3,2);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput3[j]==true)
				output3[j] = 1;
			else
				output3[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput3[k] != output3[k])
		    {
		        flag3 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput3[0]+""+expectedoutput3[1]+""+expectedoutput3[2]+""+expectedoutput3[3]+""+expectedoutput3[4]+""+expectedoutput3[5]);		
		System.out.println("Actual Output: "+output3[0]+""+output3[1]+""+output3[2]+""+output3[3]+""+output3[4]+""+output3[5]);
		System.out.println("Test case result: "+flag3+"\n");
    }
    
    
    public void circuitThreeTester()
    {
        System.out.println("Testing the Third Circuit:\n");
        
        boolean[] boolinput1 = new boolean[6];
        boolean[] boolinput2 = new boolean[6];
        boolean[] boolinput3 = new boolean[6];
        boolean[] booloutput1 = new boolean[6];
        boolean[] booloutput2 = new boolean[6];
        boolean[] booloutput3 = new boolean[6];
        int[] expectedoutput1 = {0,1,1,0,0,0};
        int[] expectedoutput2 = {0,1,1,0,0,1};
        int[] expectedoutput3 = {0,0,1,1,0,1};
        boolean flag1 = true,flag2 = true,flag3 = true;
        
        for(int i=0;i<6;i++)
		{
			if(input1[i]==1)
				boolinput1[i] = true;
			else
				boolinput1[i] = false;
			
			if(input2[i]==1)
				boolinput2[i] = true;
			else
				boolinput2[i] = false;
				
			if(input3[i]==1)
				boolinput3[i] = true;
			else
				boolinput3[i] = false;
		}
		
		System.out.println("Testing for Input: "+input1[0]+""+input1[1]+""+input1[2]+""+input1[3]+""+input1[4]+""+input1[5]);
		
		booloutput1 = c.testerfunction(boolinput1,3);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput1[j]==true)
				output1[j] = 1;
			else
				output1[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput1[k] != output1[k])
		    {
		        flag1 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput1[0]+""+expectedoutput1[1]+""+expectedoutput1[2]+""+expectedoutput1[3]+""+expectedoutput1[4]+""+expectedoutput1[5]);		
		System.out.println("Actual Output: "+output1[0]+""+output1[1]+""+output1[2]+""+output1[3]+""+output1[4]+""+output1[5]);
		System.out.println("Test case result: "+flag1+"\n");
		
		//For input2
		
		System.out.println("Testing for Input: "+input2[0]+""+input2[1]+""+input2[2]+""+input2[3]+""+input2[4]+""+input2[5]);
		
		booloutput2 = c.testerfunction(boolinput2,3);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput2[j]==true)
				output2[j] = 1;
			else
				output2[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput2[k] != output2[k])
		    {
		        flag2 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput2[0]+""+expectedoutput2[1]+""+expectedoutput2[2]+""+expectedoutput2[3]+""+expectedoutput2[4]+""+expectedoutput2[5]);		
		System.out.println("Actual Output: "+output2[0]+""+output2[1]+""+output2[2]+""+output2[3]+""+output2[4]+""+output2[5]);
		System.out.println("Test case result: "+flag2+"\n");
		
		//For input3
		
		System.out.println("Testing for Input: "+input3[0]+""+input3[1]+""+input3[2]+""+input3[3]+""+input3[4]+""+input3[5]);
		
		booloutput3 = c.testerfunction(boolinput3,3);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput3[j]==true)
				output3[j] = 1;
			else
				output3[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput3[k] != output3[k])
		    {
		        flag3 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput3[0]+""+expectedoutput3[1]+""+expectedoutput3[2]+""+expectedoutput3[3]+""+expectedoutput3[4]+""+expectedoutput3[5]);		
		System.out.println("Actual Output: "+output3[0]+""+output3[1]+""+output3[2]+""+output3[3]+""+output3[4]+""+output3[5]);
		System.out.println("Test case result: "+flag3+"\n");
    }
    
    public void circuitFourTester()
    {
        System.out.println("Testing the Fourth Circuit:\n");
        
        boolean[] boolinput1 = new boolean[6];
        boolean[] boolinput2 = new boolean[6];
        boolean[] boolinput3 = new boolean[6];
        boolean[] booloutput1 = new boolean[6];
        boolean[] booloutput2 = new boolean[6];
        boolean[] booloutput3 = new boolean[6];
        int[] expectedoutput1 = {0,0,0,0,1,0};
        int[] expectedoutput2 = {0,0,0,0,1,0};
        int[] expectedoutput3 = {0,0,0,0,1,1};
        boolean flag1 = true,flag2 = true,flag3 = true;
        
        for(int i=0;i<6;i++)
		{
			if(input1[i]==1)
				boolinput1[i] = true;
			else
				boolinput1[i] = false;
			
			if(input2[i]==1)
				boolinput2[i] = true;
			else
				boolinput2[i] = false;
				
			if(input3[i]==1)
				boolinput3[i] = true;
			else
				boolinput3[i] = false;
		}
		
		System.out.println("Testing for Input: "+input1[0]+""+input1[1]+""+input1[2]+""+input1[3]+""+input1[4]+""+input1[5]);
		
		booloutput1 = c.testerfunction(boolinput1,4);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput1[j]==true)
				output1[j] = 1;
			else
				output1[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput1[k] != output1[k])
		    {
		        flag1 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput1[0]+""+expectedoutput1[1]+""+expectedoutput1[2]+""+expectedoutput1[3]+""+expectedoutput1[4]+""+expectedoutput1[5]);		
		System.out.println("Actual Output: "+output1[0]+""+output1[1]+""+output1[2]+""+output1[3]+""+output1[4]+""+output1[5]);
		System.out.println("Test case result: "+flag1+"\n");
		
		//For input2
		
		System.out.println("Testing for Input: "+input2[0]+""+input2[1]+""+input2[2]+""+input2[3]+""+input2[4]+""+input2[5]);
		
		booloutput2 = c.testerfunction(boolinput2,4);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput2[j]==true)
				output2[j] = 1;
			else
				output2[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput2[k] != output2[k])
		    {
		        flag2 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput2[0]+""+expectedoutput2[1]+""+expectedoutput2[2]+""+expectedoutput2[3]+""+expectedoutput2[4]+""+expectedoutput2[5]);		
		System.out.println("Actual Output: "+output2[0]+""+output2[1]+""+output2[2]+""+output2[3]+""+output2[4]+""+output2[5]);
		System.out.println("Test case result: "+flag2+"\n");
		
		//For input3
		
		System.out.println("Testing for Input: "+input3[0]+""+input3[1]+""+input3[2]+""+input3[3]+""+input3[4]+""+input3[5]);
		
		booloutput3 = c.testerfunction(boolinput3,4);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput3[j]==true)
				output3[j] = 1;
			else
				output3[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput3[k] != output3[k])
		    {
		        flag3 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput3[0]+""+expectedoutput3[1]+""+expectedoutput3[2]+""+expectedoutput3[3]+""+expectedoutput3[4]+""+expectedoutput3[5]);		
		System.out.println("Actual Output: "+output3[0]+""+output3[1]+""+output3[2]+""+output3[3]+""+output3[4]+""+output3[5]);
		System.out.println("Test case result: "+flag3+"\n");
    }
    
    public void circuitFiveTester()
    {
        System.out.println("Testing the Fifth Circuit:\n");
        
        boolean[] boolinput1 = new boolean[6];
        boolean[] boolinput2 = new boolean[6];
        boolean[] boolinput3 = new boolean[6];
        boolean[] booloutput1 = new boolean[6];
        boolean[] booloutput2 = new boolean[6];
        boolean[] booloutput3 = new boolean[6];
        int[] expectedoutput1 = {1,1,1,0,0,0};
        int[] expectedoutput2 = {1,1,1,1,0,0};
        int[] expectedoutput3 = {0,0,0,1,1,0};
        boolean flag1 = true,flag2 = true,flag3 = true;
        
        for(int i=0;i<6;i++)
		{
			if(input1[i]==1)
				boolinput1[i] = true;
			else
				boolinput1[i] = false;
			
			if(input2[i]==1)
				boolinput2[i] = true;
			else
				boolinput2[i] = false;
				
			if(input3[i]==1)
				boolinput3[i] = true;
			else
				boolinput3[i] = false;
		}
		
		System.out.println("Testing for Input: "+input1[0]+""+input1[1]+""+input1[2]+""+input1[3]+""+input1[4]+""+input1[5]);
		
		booloutput1 = c.testerfunction(boolinput1,5);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput1[j]==true)
				output1[j] = 1;
			else
				output1[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput1[k] != output1[k])
		    {
		        flag1 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput1[0]+""+expectedoutput1[1]+""+expectedoutput1[2]+""+expectedoutput1[3]+""+expectedoutput1[4]+""+expectedoutput1[5]);		
		System.out.println("Actual Output: "+output1[0]+""+output1[1]+""+output1[2]+""+output1[3]+""+output1[4]+""+output1[5]);
		System.out.println("Test case result: "+flag1+"\n");
		
		//For input2
		
		System.out.println("Testing for Input: "+input2[0]+""+input2[1]+""+input2[2]+""+input2[3]+""+input2[4]+""+input2[5]);
		
		booloutput2 = c.testerfunction(boolinput2,5);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput2[j]==true)
				output2[j] = 1;
			else
				output2[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput2[k] != output2[k])
		    {
		        flag2 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput2[0]+""+expectedoutput2[1]+""+expectedoutput2[2]+""+expectedoutput2[3]+""+expectedoutput2[4]+""+expectedoutput2[5]);		
		System.out.println("Actual Output: "+output2[0]+""+output2[1]+""+output2[2]+""+output2[3]+""+output2[4]+""+output2[5]);
		System.out.println("Test case result: "+flag2+"\n");
		
		//For input3
		
		System.out.println("Testing for Input: "+input3[0]+""+input3[1]+""+input3[2]+""+input3[3]+""+input3[4]+""+input3[5]);
		
		booloutput3 = c.testerfunction(boolinput3,5);
		
		for(int j=0;j<6;j++)
		{
			if(booloutput3[j]==true)
				output3[j] = 1;
			else
				output3[j] = 0;
		}
		
		for(int k=0;k<6;k++)
		{
		    if(expectedoutput3[k] != output3[k])
		    {
		        flag3 = false;
		    }
		}
		System.out.println("Expected Output: "+expectedoutput3[0]+""+expectedoutput3[1]+""+expectedoutput3[2]+""+expectedoutput3[3]+""+expectedoutput3[4]+""+expectedoutput3[5]);		
		System.out.println("Actual Output: "+output3[0]+""+output3[1]+""+output3[2]+""+output3[3]+""+output3[4]+""+output3[5]);
		System.out.println("Test case result: "+flag3+"\n");
    }
}
