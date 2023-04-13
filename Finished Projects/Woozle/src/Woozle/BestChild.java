package Woozle;


public class BestChild {

	private int BestFitness;
	private char BestChars[];
	
	BestChild(){
		BestFitness = 0;
	}
	
	public int GetFitness() {
		return BestFitness;
	}
	
	public char GetCharacter(int position) {
		return BestChars[position];
	}
	
	
	
	public void Swap(Children NewBestChild, int NewFitness) {
		BestFitness = NewBestChild.GetFitness();
		for (int i = 0; i < NewBestChild.GetLength(); i++) {
			BestChars[i] = NewBestChild.GetChar(i);
		}
	}
	
	public void output(int length) {
		for (int i = 0; i < length; i++) {
			System.out.print(BestChars[i]);
		}
		System.out.println("");
	}
}
