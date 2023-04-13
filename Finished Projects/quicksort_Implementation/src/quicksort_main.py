# quicksort_main.py
# Module to perform quicksort on a file
#
# Usage: python3 quicksort_main.py filename 
#
# Created - 2023 January - Linus Bendel-Stenzel
# Updated - 2023 January - Linus Bendel-Stenzel

import sys
import os
import quicksort

def read_file_to_list(filename):
    """ Reads a file and stores the information into an array

    The values from file are written one per line

    Parameters
    ----------
    filename : string
        The name of the file to be read

    Returns
    ----------
    unsorted_array : list
        list of integers in the same order as appears in file. 
    """
    unsorted_array = []
    infile =  open(filename, "r")
    lines = infile.readlines()
    for line in lines:
        unsorted_array.append(int(line))
    return unsorted_array

def write_list_to_file(filename, data_list):
    """ Writes the given data list out to a file with the given filename.

    The values are written one per line.
    Note: Copy of same function found in numgen.py written by Erik Steinmetz
        
    Parameters
    ----------
    filename : string
        The name of the file to be written
    data_list : list
        A list of ints to be written to the file
    """
    outfile = open( filename, 'w')
    for value in data_list :
        aline = str(value) + "\n"
        outfile.write( aline)
    outfile.close()

def add_to_stats(filename, data_size, comparisons, assignments, from_file):
    """
    Given some data, writes and organizes that data into a file.

    The format of the output file is each data point on one line 
    separated by spaces:
    size_of_data comparisons_done assignments_done

    Parameters
    ----------
    filename : string
        The name of the file to be read
    data_size : int
        Size of the array sorted
    comparisons : int
        Number of comparisons made in sort
    assignments : int
        Number of Assignments made in sort
    """
    infile = open(filename, "r")
    lines = infile.readlines()
    infile.close()

    outfile = open(filename, "w")

    already_printed = False
    if (len(lines) < 1):
        # If file is empty, then write out the first line
        outfile.write(str(data_size) + " " + str(comparisons) + " " + str(assignments) + " " + from_file + "\n")
        print(lines)
    else:
        for line in lines:
            output = line.split()
            if (not already_printed and int(output[0]) > data_size):
            # This is insertion sort on one element because we are adding one element to this file.
                already_printed = True
                outfile.write(str(data_size) + " " + str(comparisons) + " " + str(assignments) + " " + from_file + "\n")
            outfile.write(line)
            # Write each line of data back into the file
        if (not already_printed):
        # if the correct place to put the new data is at the end of the file, do that here. 
            outfile.write(str(data_size) + " " + str(comparisons) + " " + str(assignments) + " " + from_file + "\n")
    outfile.close()

def process_file(path, filename):
    """
    Given a file, and its path, the function uses quicksort on the file, then
    outputs the sorted file to outputFiles folder, and to the stats.txt file. 

    Parameters
    ----------
    path : string
        The path to the filename
    filename : string
        The name of the file to be read
    """
    # For every file in folder, get filename, and join together. 
    infile = os.path.join(path, filename)
    print(infile)
    output_file = "../outputFiles/output-" + filename
    number_array = read_file_to_list(infile)
    (comparisons, assignments) = quicksort.quicksort(number_array)
    write_list_to_file(output_file, number_array)
    add_to_stats("../outputFiles/stats.txt", len(number_array), comparisons, assignments, filename)


def main():
    """Takes a folder, and iterates through each ffile in the folder. Then
    uses quicksort to sort all the values in each file, and output to another file. 

    The name of the input folder is specified by command line arguments.
    """
    if len(sys.argv) < 2 :
        print( "Usage: python3 quicksort_main.py folder_name")
        exit()
    input_folder = "../" + sys.argv[1]
    for filename in os.listdir(input_folder):
        process_file(input_folder, filename)
        






if __name__ == "__main__":
    main()