This program runs quicksort on a list of integers.
It keeps track of the assignments and comparisons done, then there is a summary and analysis regarding that data in outputFiles/prelim_results.txt

HOW-TO-USE:
  *  CD Into SRC folder. From there, you can run the commands listed below. 
  *  To create a file of random numbers to sort, use numgen.py with usage listed below.
Or you can submit your own with one number per line of file.
  *  Then use quicksort_main.py to sort the numbers, usage listed below.
  *  The output will be stored in output-FILENAME file in the outputFiles folder. 

There are 3 folders:
1. **inputFiles**
  * This contains any txt file used as input for quicksort. Numgen.py saves files to this folder.  
2. **outputFiles**
  * This contains any txt file used as output for quicksort. 
     *  All files starting with "output-" are a sorted list of numbers
     *  "stats.txt" file has raw stats about each sort done. quicksort_main.py saves to this file.
     *  "prelim_results.txt" has analysis of stats from the stats.txt file.
3. **src**
  *  Contains all .py files used in running the code.
  1. numgen.py
    *  This file creates an array of random integers
    *  Usage: python3 numgen.py filename quantity [rangesize]
    *  functions:
      *  create_list( how_many, range_size)
      *  write_list_to_file( filename, data_list) 
  2. quicksort_main.py
    *  This file sorts a list from every file in a folder. 
    *  Usage: python3 quisort_main.py folder_name 
    *  functions:
      *  read_file_to_list(filename)
      *  write_list_to_file(filename, data_list)
      *  add_to_stats(filename, data_size, comparisons, assignments, from_file)
  3. quicksort.py
    *  This file implements quicksort, called by quicksort_main.py
    *  functions: 
      * def swap(arr, left, right):
      * def partition(arr, left, right):
      * def quicksort_helper(arr, left, right):
      * def quicksort(arr):




Design:
numgen is used to make a valid input file for our quicksort program. 
quicksort_main.py reads and writes to files, and calls quicksort.py to perform quicksort. 
quicksort.py has a recursive helper function to facilitate quicksort, and record stats about the number of assignments and comparisons in the process. 