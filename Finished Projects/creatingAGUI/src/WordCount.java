import java.util.TreeMap; // TreeMap
import java.util.ArrayList; // ArrayList
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Linus Bendel-Stenzel
 * Class used to count and categorize the words in a file.
 */
public class WordCount {

    private ArrayList<String> wordArrList;
    private TreeMap<String, Integer> wordMap;

    public WordCount(){
        wordArrList = new ArrayList<String>();
        wordMap = new TreeMap<String, Integer>();
    }

    public WordCount(String filename){
        wordArrList = new ArrayList<String>();
        readFile(filename);
        wordMap = new TreeMap<String, Integer>();
        cleanData();
        createMap();
    }


    /** 
     * If any words have dashes in them, removes the dash, and 
     * adds them back as separate words into the arraylist. 
     * //TODO: Reconnect words
     * @param wordArrList Arraylist of words 
     */
    public void removeDashes() {
        String word;
        for (int i = wordArrList.size() - 2; i >= 0; i--) {
            word = wordArrList.get(i);
            if (word.charAt(word.length() - 1) == '-'){
                // Add new to end of list
                wordArrList.add(word + wordArrList.get(i + 1));
                // Remove the other two
                wordArrList.remove(i + 1);
                wordArrList.remove(i);
            }

        }
    }

    
    /** 
     * Makes all words in stored arraylist lower case
     */
    public void removeCase() {
        for (int i = 0; i < wordArrList.size(); i++) {
            wordArrList.set(i, wordArrList.get(i).toLowerCase());
        }
    }

    
    /** 
     * Removes all non-alpha characters from stored arraylist of words
     */
    public void removePunctuation() {
        for (int i = 0; i < wordArrList.size(); i++) {
            // Using regular expression to say everything not in those ranges.
            wordArrList.set(i, wordArrList.get(i).replaceAll("[^a-zA-Z ]", ""));
        }
    }

    
    /** 
     * Calls functions to remove dashes, remove punctuation, and make lower case.
     */
    public void cleanData() {
        removeDashes();
        removePunctuation();
        removeCase();
    }

    
    /** 
     * Converts the stored and cleaned arraylist into a treemap with each word's frequency. 
     */
    
    public void createMap() {
        wordMap = new TreeMap<String, Integer>();
        for (int i = 0; i < wordArrList.size(); i++) {
            String inputWord = wordArrList.get(i);
            if (wordMap.containsKey(inputWord)) {
                wordMap.put(inputWord, wordMap.get(inputWord) + 1);
            } else {
                wordMap.put(inputWord, 1);
            }
        }
    }

    
    /** 
     * Reads a file, and then stores it in an arraylist contianing the words in the file. 
     * @param filename name of the file contained inside the "input" file. 
     */
    public void readFile(String filename) {
        String path = System.getProperty("user.dir") + "/input/";
        File infile = new File(path + filename);
        // This will store all of the new lines
        try {
            Scanner sin = new Scanner(infile);
            while (sin.hasNext()) {
                String nextWord = sin.next();
                wordArrList.add(nextWord);
            }
            sin.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    
    /** 
     * Fromt he stored of mapped frequencies, outputs the information to a file in alphabetical order
     * @param wordMap treemap of the words that will be printed. 
     */
    public void printToFile(String filename) {
        String path = System.getProperty("user.dir") + "/output/";
        File outfile = new File(path + filename);
        try {
            FileWriter fout = new FileWriter(outfile);
            String output = "";
            for (String entry : wordMap.keySet()) {
                output = output + "[" + entry + ": " + wordMap.get(entry) + "]\n";
            }
            fout.write(output);
            fout.close();
        } catch (IOException e) {
            System.out.println("IO Exception error");
        }
    }

    /**
     * getter for the arrayList
     * @return the arraylist of stored words
     */
    public ArrayList<String> getArrayList() {
        return this.wordArrList;
    }

    /**
     * Starts the controller for the GUI.  
     * Then outptus to a file inside the "output" folder with the same name as the input file.
     * Usage: WordCount 
     * @param none
     */
    public static void main(String[] args) {
        @SuppressWarnings(value = { "unused" })
        WordCountController theApplication = new WordCountController();
    }
}