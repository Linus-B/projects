import java.io.File;
import java.io.IOException;

import java.util.Scanner;



public class BoggleSolver{


    public static void main(String[] args) throws IOException{

        // TODO: test new input
        String inputFileName = "../inputBoard.txt";
        File finBoard = new File(inputFileName);
        Scanner sin = new Scanner(finBoard);
        int size = sin.nextInt();
        char[][] tmpBoard = new char[size][size];
        String letters = sin.next();
        if (letters.length() < size * size){
            System.out.print("Well that's an invalid board");
            sin.close();
            return;
        }
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                char newLetter = letters.charAt(size*i+j);
                tmpBoard[i][j] = newLetter;
                System.out.print(newLetter + " ");
            }
            System.out.println();
        }
        sin.close();



        File fin = new File("../words_alpha.txt");
        Dictionary dict = new Dictionary(fin);

        Board board = new Board(tmpBoard, dict);
        board.findAllWords();

        // Some testing first

        // System.out.println("is word: " + dict.isWord("abb"));
        // System.out.println("is word: " + dict.isWord("karma"));
        // System.out.println("Is more: " + dict.isMore("ab"));
        // System.out.println("is word: " + dict.isWord("karma"));

    }


}