**SUMMARY**

Written by Linus Bendel-Stenzel. Last edited: February 2023

This program finds the frequency of every word in a given input file.
Input files are stored in the input folder. Output files stored in output folder.
Gui is created using Swift, and allows the user to choose a txt file.

**RUNNING IT**

Go into the src folder, and run the following command after compiling the .java files:

**Usage:** WordCount

There are 3 folders, and some extra files:
1. input
  * This is where to place input files to be read.
2. output
  * This is where all output files will be placed
3. src
  * WordCount.java
    * Contains the main function. Converts a text file to a map of each word's frequency.
    * WordCount()
    * WordCount(String filename)
    * removeDashes()
    * removeCase()
    * removePunctuation()
    * cleanData()
    * createMap()
    * readFile()
    * printToFile()
    * main(String[] args)
  * WordCountTest.java
    * Has tests for each function in the WordCount class
    * WordCountTest()
    * testFileIn()
    * testRmDashes()
    * testRmCase()
    * testRmPunctuation
  * WordCountController.java
    * Is the controller for the gui. Model is WordCount
    * WordCountController()
    * actOnFile()
    * actionPerformed()
  * WordCountGui.java
    * Sets the layout for the gui
    * WordCountGui()
4. junit-platform-console-standalone-1.9.2.jar
  * Contains the classes and methods required for unit testing
5. SDD.ods and SDD.pdf
  * Contains the software design document in several formats.
