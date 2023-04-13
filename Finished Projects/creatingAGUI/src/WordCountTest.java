import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Unit tests of the WordCount class, to be run with JUnit 5.
 *
 * To compile against the JUnit classes on the command line, use
 * a command like
 * <pre>
 *   javac -cp .:./junit-platform-console-standalone-1.9.2.jar WordCount.java WordCountTest.java
 * </pre>
 * In order to run the tests in this class on the command line, use
 * a command like
 * <pre>
 *   java -jar ./junit-platform-console-standalone-1.9.2.jar -cp . --scan-classpath .
 * </pre>
 * or
 * <pre>
 *   java -jar ./junit-platform-console-standalone-1.9.2.jar -cp . --select-class WordCountTest
 * </pre>
 * @author Linus Bendel-Stenzel
 * adapted from file made by Erik Steinmetz
 */
public class WordCountTest {

    /**
     * Empty Constructor
     */
    public WordCountTest() {

    }
    

    /**
     * Tests the readFile(String filename) function in the WordCount class
     */
    @Test
    public void testFileIn(){
        System.out.println("Reading in");
        WordCount wc = new WordCount();
        wc.readFile("test.txt");
        // the rest of the dashes will be removed in the next step, so these are A-okay. 
        Object[] expected = {"The", "hello", "it", "was", "fine"};
        // Convert arraylist to an array so that we can use assertEquals
        Object[] actual = wc.getArrayList().toArray();
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests the removeDashes() function in the WordCount class
     */
    @Test
    public void testRmDashes(){
        System.out.println("Removing dashes");
        WordCount wc = new WordCount();
        wc.readFile("dashTest.txt");
        wc.removeDashes();
        // the rest of the dashes will be removed in the next step, so these are A-okay. 
        Object[] expected = {"a-b", "-c",  "c-g-q",  "d-ef"};
        // Convert arraylist to an array so that we can use assertEquals
        Object[] actual = wc.getArrayList().toArray();
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests the removeCase() function in the WordCount class
     */
    @Test
    public void testRmCase(){
        System.out.println("Removing case");
        WordCount wc = new WordCount();
        wc.readFile("caseTest.txt");
        wc.removeCase();
        // the rest of the dashes will be removed in the next step, so these are A-okay. 
        Object[] expected = {"apple", "horse", "apple", "horse"};
        // Convert arraylist to an array so that we can use assertEquals
        Object[] actual = wc.getArrayList().toArray();
        assertArrayEquals(expected, actual);
    }

    /**
     * Tests the removePunctuation() function in the WordCount class
     */
    @Test
    public void testRmPunctuation(){
        System.out.println("Removing punc");
        WordCount wc = new WordCount();
        wc.readFile("puncTest.txt");
        wc.removePunctuation();
        // the rest of the dashes will be removed in the next step, so these are A-okay. 
        Object[] expected = {"thats", "", "a", "lkdsa", ""};
        // Convert arraylist to an array so that we can use assertEquals
        Object[] actual = wc.getArrayList().toArray();

        assertArrayEquals(expected, actual);
    }


}
