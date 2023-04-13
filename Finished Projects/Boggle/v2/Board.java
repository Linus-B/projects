
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board {
    private char[][] board;
    private Trie dict;
    private int size;
    private ArrayList<ArrayList<String> > wordsFound;


    public Board(char[][] board, Trie dict){
        this.board = board;
        this.dict = dict;
        this.size = board.length;
        this.wordsFound = new ArrayList<ArrayList<String> >();
    }

    private boolean doesExist(ArrayList<String> wordList, String word){
        for (int i = 0; i < wordList.size(); i++){
            if (wordList.get(i).compareTo(word) == 0){
                return true;
            }
        }
        return false;
    }


    private void findWordRecur(String wordPart, boolean[][] checked, int row, int column){
        // Check if in bounds, and not checked. If yes, then return
        // if (row < 0 || column < 0 || row >= size || column >= size){
        //     return;
        // }
        // if (checked[row][column] == Boolean.TRUE){
        //     return;
        // }

        String newWord = wordPart + board[row][column];
        // If a word, add to the correct place in the output
        if (dict.doesExist(newWord)){
            int length = newWord.length();
            // Making sure there is a box for the words of that length to go to
            while (length - 2 > wordsFound.size()){
                wordsFound.add(new ArrayList<String>());
            }
            if (!(doesExist(wordsFound.get(length - 3), newWord))){
                wordsFound.get(length - 3).add(newWord);
            }

        } 

        // If there are more possible words, check more. 

        boolean[][] newChecked = new boolean[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                newChecked[i][j] = checked[i][j];
            }
        }
        newChecked[row][column] = Boolean.TRUE;

        if (dict.isPrefix(newWord)){
            for (int i = -1; i < 2; i++){
                for (int j = -1; j < 2; j++){
                    if (row + i > -1 && column + j > -1 && row + i < size && column + j < size && !(checked[row +  i][column + j])){
                        if (i != 0 || j != 0){
                            findWordRecur(newWord, newChecked, row + i, column + j);
                        }
                    }
                }
            }
        }
    }

    //TODO test
    public void findAllWords(){
        boolean[][] checkedArray = new boolean[size][size];
        for (int i = 0; i < size; i++){
            Arrays.fill(checkedArray[i], Boolean.FALSE);
        }
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                findWordRecur("", checkedArray, i, j);
            }
        }
        outputWords();

    }

    //TODO test
    public void outputWordsFile(File file) throws IOException{

        FileWriter outwrite;
        try {
            outwrite = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        for (int i = 0; i < wordsFound.size(); i++){
            for (int j = 0; j < wordsFound.get(i).size(); j++){
                outwrite.write(wordsFound.get(i).get(j) + "\n");
            }
        }
        outwrite.close();
    }

    public void outputWords(){
        int total_points = 0;
        int[] points_for_word = {100, 400, 800, 1400, 1800, 2200, 3000};
        for (int i = 0; i < wordsFound.size(); i++){
            Collections.sort(wordsFound.get(i));
            System.out.println("There are " + wordsFound.get(i).size() + " words of size: " + (i + 3));
            for (int j = 0; j < wordsFound.get(i).size(); j++){
                if (i > 6){
                    total_points += points_for_word[6];
                } else {
                    total_points += points_for_word[i];
                }
                System.out.println(wordsFound.get(i).get(j));
            }
        }
        System.out.println("Total points for the board: " + total_points);
    }
}
