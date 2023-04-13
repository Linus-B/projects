Created by Linus Bendel-Stenzel

March 9th, 2023

NOTE: I forgot to do --no-ff for the first merge, so that is why it looks like there is no working branch there. But there was. I just forgot. Ah well. 


**Overview**
This is the game Lights Out created in LISP. The goal of the game is to turn all the lights out. There is a 3x3 board of lights, and selecting any one light changes the state of every orthonogonally adjacent light.

In order to play the game, run the function (start-game). Then, follow the prompts! Each position is ordered from 1-9 
starting in top left, ending at bottom right. If you want an ai to play the game instead, then run the function (start-game-ai). 


**File Structure**

There are 3 files in this project
  * README.md
  * lights-out.lisp
    * This contains all functions related to the game
    * print-between (lst start end count) : prints all values in the list between the start and end numbers
    * print-row (lst row) : prints a given row of the grid stored as list by calling print-between
    * print-board (lst) : prints each row of the board
    * toggle (light) : if 0, return 1. If 1, returns 0
    * toggle-light (lst index) : changes the light at a specific index, returns that new list
    * toggle-multiple (lst index-lst) : toggles the lights in lst at indexes listed in index-lst. 
    * are-lights-out (lst) : checks if everything in list is 0, returns t or nil
    * random-board (len) : return a randomly generated list of 9 1's and 0's. 
    * generate-board () : creates a random board until it makes one that has at least one light on. 
    * select-group (center-number) Given a number between 1-9, creates a list of the numbers surrounding it that must be toggled. 
    * get-input (prompt) : Asks the user the prompt, and gets input until it's between 1 and 9
    * one-turn (board message count) : Checks if the user won, and does the user's move. 
    * chase-rows (board index-lst) : if the light above is lit, then select this light
    * chase (board) : calls chase-rows with default list 3 4 5 6 7 8
    * ai-turn (board count) : Checks if the computer won, and if not then it chases down the lights.
    * start-game () : starts the game
    * start-game-ai () : Starts game with ai playing
    * test-function () : tests each functionm above, and prints error if one didn't work