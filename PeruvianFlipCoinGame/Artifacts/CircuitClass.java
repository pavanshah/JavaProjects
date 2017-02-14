package circuitPackage;

import java.util.Random;
import java.util.Scanner;

public class CircuitClass 
{
	static boolean[] outnumber = new boolean[6];
	static boolean[] finaloutput = new boolean[6];
	static int output1 = 0;
	static int output2 = 0;
	static int output3 = 0;
	static int output4 = 0;
	static int output5 = 0;
	static int output6 = 0;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		int[] input = new int[6];
		boolean[] boolinput = new boolean[6];
		
		System.out.println("Put your six digit binary input here -");
		
		for(int i=0;i<6;i++)
		{
			input[i] = sc.nextInt();
		}
		
		System.out.print("Input to the circuit : ");
	
		for(int i=0;i<6;i++)
		{
			if(input[i]==1)
				boolinput[i] = true;
			else
				boolinput[i] = false;
			
			System.out.print(input[i]);
		}
		
		
		System.out.println("\n1] Select Circuit Randomly\n2] Tester function");
		int choice = sc.nextInt();
		
		if(choice == 1)
		{
			randomselection(boolinput);
		}
		
		if(choice == 2)
		{
			testerfunction(boolinput);
		}
		
	}
	
	
	public static void randomselection(boolean[] boolinput)
	{
		Random selectCircuit = new Random();
		int circuitNumber = selectCircuit.nextInt(5)+1;
		
		System.out.println("Circuit Number : "+circuitNumber);
		
		switch(circuitNumber)
		{
			case 1:
				finaloutput = circuit1(boolinput);
				break;
				
			case 2:
				finaloutput = circuit2(boolinput);
				break;
				
			case 3:
				finaloutput = circuit3(boolinput);
				break;
				
			case 4:
				finaloutput = circuit4(boolinput);
				break;
				
			case 5:
				finaloutput = circuit5(boolinput);
				break;
			
		}
		
		
		
		if(finaloutput[0] == true)
			output1 = 1;
		if(finaloutput[1] == true)
			output2 = 1;
		if(finaloutput[2] == true)
			output3 = 1;
		if(finaloutput[3] == true)
			output4 = 1;
		if(finaloutput[4] == true)
			output5 = 1;
		if(finaloutput[5] == true)
			output6 = 1;
		
		System.out.println("Output of the circuit : "+output1+" "+output2+" "+output3+" "+output4+" "+output5+" "+output6);
		
	}

	public static void testerfunction(boolean[] boolinput, int circuitNumber)
	{
		//System.out.println("\nEnter circuit number : ");
		//int circuitNumber = sc.nextInt();
		//System.out.println("Circuit Number : "+circuitNumber);
		
		switch(circuitNumber)
		{
			case 1:
				finaloutput = circuit1(boolinput);
				break;
				
			case 2:
				finaloutput = circuit2(boolinput);
				break;
				
			case 3:
				finaloutput = circuit3(boolinput);
				break;
				
			case 4:
				finaloutput = circuit4(boolinput);
				break;
				
			case 5:
				finaloutput = circuit5(boolinput);
				break;
			
		}
		
		
		
		if(finaloutput[0] == true)
			output1 = 1;
		if(finaloutput[1] == true)
			output2 = 1;
		if(finaloutput[2] == true)
			output3 = 1;
		if(finaloutput[3] == true)
			output4 = 1;
		if(finaloutput[4] == true)
			output5 = 1;
		if(finaloutput[5] == true)
			output6 = 1;
		
		System.out.println("Output of the circuit : "+output1+" "+output2+" "+output3+" "+output4+" "+output5+" "+output6);
	}
	
	public static boolean[] circuit5(boolean[] boolinput) 
	{
		//First Round
		boolean firstnumber1 = (boolinput[0] ^ boolinput[5]);
		boolean firstnumber2 = !(boolinput[1] & boolinput[2]);
		boolean firstnumber3 = !(boolinput[1] & boolinput[3]);
		boolean firstnumber4 = boolinput[2] | boolinput[4];
		boolean firstnumber5 = !(boolinput[3] & boolinput[5]);
		boolean firstnumber6 = !(boolinput[5]);
		
		System.out.println(firstnumber1+" "+firstnumber2+" "+firstnumber3+" "+firstnumber4+" "+firstnumber5+" "+firstnumber6);
		
		//Second Round
		boolean secondnumber1 = firstnumber1 | firstnumber2;
		boolean secondnumber2 = (firstnumber1 ^ firstnumber3);
		boolean secondnumber3 = firstnumber2 | firstnumber4;
		boolean secondnumber4 = !(firstnumber3 & firstnumber5);
		boolean secondnumber5 = !(firstnumber4 | firstnumber6);
		boolean secondnumber6 = (firstnumber1);
		
		System.out.println(secondnumber1+" "+secondnumber2+" "+secondnumber3+" "+secondnumber4+" "+secondnumber5+" "+secondnumber6);
		
		//Third Round
		boolean thirdnumber1 = (secondnumber2);
		boolean thirdnumber2 = (secondnumber1 | secondnumber2);
		boolean thirdnumber3 = (secondnumber2 & secondnumber3);
		boolean thirdnumber4 = !secondnumber3 | secondnumber4;
		boolean thirdnumber5 = (secondnumber4 ^ secondnumber5);
		boolean thirdnumber6 = !secondnumber5 & secondnumber6;
		
		System.out.println(thirdnumber1+" "+thirdnumber2+" "+thirdnumber3+" "+thirdnumber4+" "+thirdnumber5+" "+thirdnumber6);
		
		outnumber[0] = thirdnumber1;
		outnumber[1] = thirdnumber2;
		outnumber[2] = thirdnumber3;
		outnumber[3] = thirdnumber4;
		outnumber[4] = thirdnumber5;
		outnumber[5] = thirdnumber6;
		
		return outnumber;
		
	}

	public static boolean[] circuit4(boolean[] boolinput) 
	{
				//First Round
				boolean firstnumber1 = (boolinput[0] | boolinput[5]);
				boolean firstnumber2 = !(boolinput[1] | boolinput[2]);
				boolean firstnumber3 = !(boolinput[1] & boolinput[3]);
				boolean firstnumber4 = boolinput[2] ^ boolinput[4];
				boolean firstnumber5 = (boolinput[3] & boolinput[5]);
				boolean firstnumber6 = (boolinput[4]);
				
				System.out.println(firstnumber1+" "+firstnumber2+" "+firstnumber3+" "+firstnumber4+" "+firstnumber5+" "+firstnumber6);
				
				//Second Round
				boolean secondnumber1 = !(firstnumber1 ^ firstnumber2);
				boolean secondnumber2 = (firstnumber1 & firstnumber3);
				boolean secondnumber3 = !(firstnumber2 & firstnumber4);
				boolean secondnumber4 = (firstnumber3 | firstnumber5);
				boolean secondnumber5 = !(firstnumber4 | firstnumber6);
				boolean secondnumber6 = (firstnumber6);
				
				System.out.println(secondnumber1+" "+secondnumber2+" "+secondnumber3+" "+secondnumber4+" "+secondnumber5+" "+secondnumber6);
				
				//Third Round
				boolean thirdnumber1 = (secondnumber1);
				boolean thirdnumber2 = (secondnumber1 ^ secondnumber2);
				boolean thirdnumber3 = (secondnumber2 & secondnumber3);
				boolean thirdnumber4 = !secondnumber3 & secondnumber4;
				boolean thirdnumber5 = (secondnumber4 | secondnumber5);
				boolean thirdnumber6 = !secondnumber5 | secondnumber6;
				
				System.out.println(thirdnumber1+" "+thirdnumber2+" "+thirdnumber3+" "+thirdnumber4+" "+thirdnumber5+" "+thirdnumber6);
				
				outnumber[0] = thirdnumber1;
				outnumber[1] = thirdnumber2;
				outnumber[2] = thirdnumber3;
				outnumber[3] = thirdnumber4;
				outnumber[4] = thirdnumber5;
				outnumber[5] = thirdnumber6;
				
				return outnumber;
		
	}

	public static boolean[] circuit3(boolean[] boolinput) 
	{
		//First Round
		boolean firstnumber1 = !(boolinput[0] | boolinput[1]);
		boolean firstnumber2 = boolinput[2] | boolinput[3];
		boolean firstnumber3 = boolinput[4] & boolinput[5];
		boolean firstnumber4 = boolinput[0] ^ boolinput[1];
		boolean firstnumber5 = !(boolinput[2] & boolinput[3]);
		boolean firstnumber6 = !(boolinput[4]);
		
		System.out.println(firstnumber1+" "+firstnumber2+" "+firstnumber3+" "+firstnumber4+" "+firstnumber5+" "+firstnumber6);
		
		//Second Round
		boolean secondnumber1 = firstnumber1 ^ firstnumber2;
		boolean secondnumber2 = !(firstnumber3 & firstnumber4);
		boolean secondnumber3 = firstnumber5 & firstnumber6;
		boolean secondnumber4 = !(firstnumber1 | firstnumber2);
		boolean secondnumber5 = firstnumber3 | firstnumber4;
		boolean secondnumber6 = !(firstnumber5);
		
		System.out.println(secondnumber1+" "+secondnumber2+" "+secondnumber3+" "+secondnumber4+" "+secondnumber5+" "+secondnumber6);
		
		//Third Round
		boolean thirdnumber1 = !(secondnumber1);
		boolean thirdnumber2 = !(secondnumber2 ^ secondnumber3);
		boolean thirdnumber3 = !(secondnumber4 & secondnumber5);
		boolean thirdnumber4 = secondnumber6 & secondnumber1;
		boolean thirdnumber5 = !(secondnumber2 | secondnumber3);
		boolean thirdnumber6 = secondnumber4 | secondnumber5;
		
		System.out.println(thirdnumber1+" "+thirdnumber2+" "+thirdnumber3+" "+thirdnumber4+" "+thirdnumber5+" "+thirdnumber6);
		
		outnumber[0] = thirdnumber1;
		outnumber[1] = thirdnumber2;
		outnumber[2] = thirdnumber3;
		outnumber[3] = thirdnumber4;
		outnumber[4] = thirdnumber5;
		outnumber[5] = thirdnumber6;
		
		return outnumber;
		
	}

	public static boolean[] circuit2(boolean[] boolinput) 
	{
				//First Round
				boolean firstnumber1 = (boolinput[0] | boolinput[1]);
				boolean firstnumber2 = !(boolinput[1] & boolinput[2]);
				boolean firstnumber3 = (boolinput[0] & boolinput[5]);
				boolean firstnumber4 = !(boolinput[2] ^ boolinput[3]);
				boolean firstnumber5 = !(boolinput[3] & boolinput[5]);
				boolean firstnumber6 = !(boolinput[5]);
				
				System.out.println(firstnumber1+" "+firstnumber2+" "+firstnumber3+" "+firstnumber4+" "+firstnumber5+" "+firstnumber6);
				
				
				//Second Round
				boolean secondnumber1 = (firstnumber1 & firstnumber3);
				boolean secondnumber2 = (firstnumber1 | firstnumber4);
				boolean secondnumber3 = !(firstnumber3);
				boolean secondnumber4 = (firstnumber2 ^ firstnumber5);
				boolean secondnumber5 = !(firstnumber4 & firstnumber6);
				boolean secondnumber6 = !(firstnumber4 | firstnumber5);
				
				System.out.println(secondnumber1+" "+secondnumber2+" "+secondnumber3+" "+secondnumber4+" "+secondnumber5+" "+secondnumber6);
				
				
				//Third Round
				boolean thirdnumber1 = !(secondnumber2 ^ secondnumber3);
				boolean thirdnumber2 = !(secondnumber2);
				boolean thirdnumber3 = (secondnumber1 & secondnumber4);
				boolean thirdnumber4 = !(secondnumber3 & secondnumber6);
				boolean thirdnumber5 = !(secondnumber4 | secondnumber5);
				boolean thirdnumber6 = (secondnumber6 | secondnumber5);
				
				System.out.println(thirdnumber1+" "+thirdnumber2+" "+thirdnumber3+" "+thirdnumber4+" "+thirdnumber5+" "+thirdnumber6);	
				
				
				outnumber[0] = thirdnumber1;
				outnumber[1] = thirdnumber2;
				outnumber[2] = thirdnumber3;
				outnumber[3] = thirdnumber4;
				outnumber[4] = thirdnumber5;
				outnumber[5] = thirdnumber6;
				
				return outnumber;
	}

	public static boolean[] circuit1(boolean[] boolinput) 
	{
		
				//First Round
				boolean firstnumber1 = !(boolinput[0] & boolinput[5]);
				boolean firstnumber2 = boolinput[1] & boolinput[2];
				boolean firstnumber3 = boolinput[1] | boolinput[3];
				boolean firstnumber4 = boolinput[2] ^ boolinput[4];
				boolean firstnumber5 = !(boolinput[3] | boolinput[5]);
				boolean firstnumber6 = !(boolinput[4]);
				
				System.out.println(firstnumber1+" "+firstnumber2+" "+firstnumber3+" "+firstnumber4+" "+firstnumber5+" "+firstnumber6);
				
				//Second Round
				boolean secondnumber1 = firstnumber1 ^ firstnumber2;
				boolean secondnumber2 = !(firstnumber1 | firstnumber3);
				boolean secondnumber3 = firstnumber2 | firstnumber4;
				boolean secondnumber4 = !(firstnumber3 & firstnumber5);
				boolean secondnumber5 = firstnumber4 & firstnumber6;
				boolean secondnumber6 = !(firstnumber6);
				
				System.out.println(secondnumber1+" "+secondnumber2+" "+secondnumber3+" "+secondnumber4+" "+secondnumber5+" "+secondnumber6);
				
				//Third Round
				boolean thirdnumber1 = !(secondnumber1);
				boolean thirdnumber2 = !(secondnumber1 ^ secondnumber2);
				boolean thirdnumber3 = !(secondnumber2 | secondnumber3);
				boolean thirdnumber4 = secondnumber3 | secondnumber4;
				boolean thirdnumber5 = !(secondnumber4 & secondnumber5);
				boolean thirdnumber6 = secondnumber5 & secondnumber6;
				
				System.out.println(thirdnumber1+" "+thirdnumber2+" "+thirdnumber3+" "+thirdnumber4+" "+thirdnumber5+" "+thirdnumber6);
				
				outnumber[0] = thirdnumber1;
				outnumber[1] = thirdnumber2;
				outnumber[2] = thirdnumber3;
				outnumber[3] = thirdnumber4;
				outnumber[4] = thirdnumber5;
				outnumber[5] = thirdnumber6;
				
				return outnumber;
		
	}
}
