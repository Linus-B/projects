package Woozle;

import java.util.Random;

public class Children {

	private int fitness, length;
	private char[] chars;
	private String Alphabet = "qwertyuioplkjhgfdsamnbvcxz ";
	private float MutationRate;
	
	Children(int SentenceLength, float mutaterate, char[] GoalSentence){
		length = SentenceLength;
		MutationRate = mutaterate;
		randomize();
		fitness = FindFitness(GoalSentence);
	}
	
	public void randomize() {
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			chars[i] = Alphabet.charAt(rand.nextInt(Alphabet.length()));
		}
	}
	
	public void mutate() {
		Random rand = new Random();
		float max = 10000;
		float maxmutation = MutationRate * max;
		for (int i = 0; i < length; i++) {
			if (rand.nextInt((int)max) < maxmutation) {
				chars[i] = Alphabet.charAt(rand.nextInt(Alphabet.length()));
			}
		}
	}
	
	public int GetLength() {
		return length;
	}
	
	public char GetChar(int position) {
		return chars[position];
	}
	
	public int FindFitness(char[] GoalSentence) {
		for (int i = 0; i < length; i++) {
			if (GoalSentence[i] == chars[i]) {
				fitness += 1;
			}
		}
		return fitness;
	}
	
	public int GetFitness() {
		return fitness;
	}
	
	public void output() {
		for (int i = 0; i < length; i++) {
			System.out.print(chars[i]);
		}
		System.out.println("");
	}
}
