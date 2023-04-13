import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Board {
    private char[][] board;
    private Dictionary dict;
    private int size;
    private int[] numberOfWords = new int[5];
    private String[][] allWords = new String[5][150];

    public Board(int size){
        this.board = new char[size][size];
        this.size = size;
    }

    public Board(char[][] board, Dictionary dict){
        this.board = board;
        this.dict = dict;
        this.size = board.length;
    }


    private void findWordRecur(String wordPart, boolean[][] checked, int row, int column){
        // Check if in bounds, and not checked. If yes, then return
        if (row < 0 || column < 0 || row >= size || column >= size){
            return;
        }
        if (checked[row][column] == Boolean.TRUE){
            return;
        }

        String newWord = wordPart + board[row][column];
        // If a word, add to the correct place in the output
        if (dict.isWord(newWord)){
            int length = newWord.length();
            if (length > 7){
                length = 7;
            }
            if (numberOfWords[length - 3] < 150){
                allWords[length - 3][numberOfWords[length - 3]] = newWord;
                numberOfWords[length - 3] += 1;
            }

        } 

        // If there are more possible words, check more. 

        boolean[][] newChecked = new boolean[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                newChecked[i][j] = checked[i][j];
            }
        }

        if (dict.isMore(newWord)){
            for (int i = -1; i < 2; i++){
                for (int j = -1; j < 2; j++){
                    if (i != 0 || j != 0){
                        newChecked[row][column] = Boolean.TRUE;
                        findWordRecur(newWord, newChecked, row + i, column + j);
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

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < allWords[i].length; j++){
                outwrite.write(allWords[i][j] + "\n");
            }
        }
        outwrite.close();
    }

    public void outputWords(){
        for (int i = 0; i < 5; i++){
            System.out.println("There are " + (numberOfWords[i]) + " " + (i + 3) + "-Letter Words:");
            for (int j = 0; j < allWords[i].length; j++){
                if (allWords[i][j] != null){
                    System.out.print(allWords[i][j] + "\n");
                }
            }
        }
    }
}
