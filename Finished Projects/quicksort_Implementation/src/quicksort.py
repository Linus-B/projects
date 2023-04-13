# quicksort_main.py
# Module to perform quicksort given a list
#
# 
#
# Created - 2023 January - Linus Bendel-Stenzel
# Updated - 2023 January - Linus Bendel-Stenzel

def swap(arr, left, right):
    """ Swaps two values in an array

    Parameters
    ----------
    A : list
        A list of values
    l : value
        First value to be swapped
    r : value
        Second value to be swapped
    """
    tmp = arr[left]
    arr[left] = arr[right]
    arr[right] = tmp

def partition(arr, left, right):
    """ From a key value at r, swaps all values less than it to the left,
    and all values more than it to the right. Keeps track of and returns 
    the number of assignments and comaprisons. 
    
    Parameters
    ----------
    A : list
        A list of values
    left : integer
        Left-most value looked at in array
    right : integer
        Right-most value looked at in array

    Returns
    ----------
    Tuple (a, b, c)
    a - The sorted position of the key in the array
    b - the number of comparisons performed
    c - the number of assignments performed. 
    """
    key = arr[right]
    i = left - 1
    j = left

    comparisons = 1
    # Counting the final while loop comparison which won't be counted in the loop
    assignments = 3
    # Counting the 3 assignments made just above

    while j < right:
        comparisons += 2
        # While loop and if statement
        if arr[j] <= key:
            i += 1
            swap(arr, i, j)
            assignments += 4
            # 3 assignments from swapping, and 1 from i += 1
        j += 1
        assignments += 1

    swap(arr, i + 1, j)
    # swaps the key into its sorted position
    comparisons += 3
    return (i + 1, comparisons, assignments)



def quicksort_helper(arr, left, right):
    """ Recursive function to partition the array A, then sort left side then right side
    Also keeps track of and returns the number of comparisons and assignments performed. 

    Parameters
    ----------
    A : list
        A list of values
    left : integer
        Left-most value looked at in array
    right : integer
        Right-most value looked at in array

    Returns
    ----------
    Tuple (a, b)
    a - number of comparisons performed
    b - number of assignments made
    """
    comparisons = 1
    assignments = 0
    if left < right:
        (q, tmp_comp, tmp_assign) = partition(arr, left, right)
        # q is position of the key in array
        comparisons += tmp_comp
        assignments += tmp_assign

        (tmp_comp, tmp_assign) = quicksort_helper(arr, left, q - 1)
        comparisons += tmp_comp
        assignments += tmp_assign

        (tmp_comp, tmp_assign) = quicksort_helper(arr, q + 1, right)
        comparisons += tmp_comp
        assignments += tmp_assign

    return (comparisons, assignments)


def quicksort(arr):
    """ Calls quicksort_helper with default values 0 and len(A) - 1
        
    Returns
    ----------
    Tuple (a, b)
    a - number of comparisons made
    b - number of assignments made
    """
    (comparisons, assignments) = quicksort_helper(arr, 0, len(arr) - 1)
    return (comparisons, assignments)