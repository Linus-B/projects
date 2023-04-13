import java.io.*;

public class Dictionary {

    private String[] wordDictionary;

    public Dictionary(File fin) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(fin));
        String tmp;
        int counter = 0;
        // Find length
        while ((tmp = br.readLine()) != null){
            if (tmp.length() > 2){
                counter++;
            }
            //if (counter % 50 == 0){
                //System.out.println(tmp);
            //}
        }
        br.close();

        br = new BufferedReader(new FileReader(fin));
        this.wordDictionary = new String[counter];
        // Put words into the dictionary array
        counter = 0;
        while ((tmp = br.readLine()) != null){
            if (tmp.length() > 2){
                wordDictionary[counter] = tmp;
                counter++;
            }
        }
        br.close();
    }

    public boolean isWord(String word){
        int tmp = getPositionRecur(word, 0, wordDictionary.length - 1);
        // Returns if the location we get back is that word.
        // If not, then the word must not exist.
        return word.compareTo(wordDictionary[tmp]) == 0;
    }



    private int getPositionRecur(String word, int startPos, int endPos){
        if (startPos == endPos){
            return startPos;
        }
        int comparison = 0;
        int midPoint = (startPos + endPos) / 2;
        try {
            comparison = word.compareTo(wordDictionary[midPoint]);
        } catch(Exception e){
            System.out.println("startPos: " + startPos);
            System.out.println("endPos: " + endPos);
            System.out.println("Length of array: " + wordDictionary.length);
            System.out.println("word: " + word);



        }
        if (comparison < 0){
            return getPositionRecur(word, startPos, midPoint);
        } else if (comparison > 0){
            return getPositionRecur(word, midPoint + 1, endPos);
        } else {
            return midPoint;
        }
    }

    private int getPosition(String word){
        return getPositionRecur(word, 0, wordDictionary.length - 1);
    }

    public boolean isMore(String word){
        int base = getPosition(word);
        int other = getPosition(word + "z");
        // Finds if there are any words with additional letters 
        // from that base. 
        return other > base;
    }
    
}
