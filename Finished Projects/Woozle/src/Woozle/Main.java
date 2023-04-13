package Woozle;

import java.util.Scanner;

public class Main {
	
	public static void main(String args[]) {
		String InputSentence;
		char[] InputSentenceArray;
		int InputLength;
		float MutationRate;
		Scanner input = new Scanner(System.in);
		System.out.println("What sentence is the end goal?");
		InputSentence = input.nextLine();
		System.out.println("what is the mutation rate?");
		MutationRate = input.nextFloat();
		InputLength = InputSentence.length();
		InputSentenceArray = InputSentence.toCharArray();
		output(InputSentenceArray, InputLength);
		BestChild NumeroUno;
		Children RandomChild = new Children(InputLength, MutationRate, InputSentenceArray);
		RandomChild.randomize();
		RandomChild.output();
		RandomChild.randomize();
		RandomChild.output();
		RandomChild.mutate();
		RandomChild.output();
		RandomChild.mutate();
		RandomChild.output();
	}
	
	
	static void output(char chars[], int length) {
		for (int i = 0; i < length; i++) {
			System.out.print(chars[i]);
		}
		System.out.println("");
	}
}
