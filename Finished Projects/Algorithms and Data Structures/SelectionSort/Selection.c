#include <stdlib.h>
#include <stdio.h>

void swap(int* arr, int a, int b){
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
}

// Returns the Index of the pivot
int quick_sort(int* arr, int start, int end){
    int i = start, j = start;
    swap(arr, (start + end) / 2, end);
    int key = arr[end];
    for (j = i; j < end; j++){
        if (arr[j] < key){
            swap(arr, i, j);
            i++;
        }
    }
    swap (arr, i, end);
    return i;
}

int main(int argc, char** argv){
    FILE* fin = fopen(argv[1], "r");
    FILE* fout = fopen(argv[2], "w");
    if(!fin || !fout)   {
        fprintf(stderr, "Error opening file\n");
        return -1;
    }
    int ith = 0;
    fscanf(fin, "%*c %d", &ith);
    ith--; //because array starts at 0 not 1
    int arr[512];
    int size = 0;
    int start = 0, pivot = 0;

    while (fscanf(fin, "%d", &arr[size]) != EOF){
        size++;
    }
    int end = size;

    if (ith < 0  || ith > size){
        return -1;
    }
    while (1){
        pivot = quick_sort(arr, start, end);
        //This is where the pivot swapped to
        if (pivot > ith){
            if (end == pivot){
                end--;
            }else {
                end = pivot;
            }// It's in the bottom part
        } else if (pivot < ith){
            if (start == pivot){
                start++;
            }else {
                start = pivot;
            }// It's in the top part
        } else{
            break;// If we found it
        }
    }
    fprintf(fout, "%d", arr[ith]);


    fclose(fin);
    fclose(fout);
    return 0;
}