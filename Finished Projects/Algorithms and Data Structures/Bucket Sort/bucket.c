#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void printArray(FILE* fout, char arr[][32], int size){
    for (int i = 0; i < size; i++){
        fprintf(fout, "%s ", arr[i]);
    }
}

void insertionSort(char arr[][32], int elements){
    char tmp[64];
    for (int i = 1; i < elements; i++){
        for (int j = i; j > 0; j--){
            if (strcmp(arr[j], arr[j - 1]) < 0){
                strcpy(tmp, arr[j - 1]);
                strcpy(arr[j - 1], arr[j]);
                strcpy(arr[j], tmp);
            }
        }
    }
}

void bucketSort(char arr[][32], int elements, int pos){
    int offset = 0;
    int letter;
    if (pos == 0){
        offset = -65;
    } else{
        offset = -97;
    }
    int size[26]; // The number of elements in each bucket
    for (int i = 0; i < 26; i++){
        size[i] = 0;
    }
    char buckets[26][50][32];// Each bucket is a letter
    // Step 1: Create Buckets


    // Step 2: Insert Into Buckets
    for (int i = 0; i < elements; i++){
        letter = (int)(arr[i][pos]) + offset;
        strcpy(buckets[letter][size[letter]], arr[i]);
        size[letter]++;
    }


    // Step 3: If a bucket is too large, run bucketSort on it
    for (int i = 0; i < 26; i++){
        if (size[i] > 10){
            bucketSort(buckets[i], size[i], pos+1);
        }
        else{
            insertionSort(buckets[i], size[i]);
        }
    }
    // Step 4: Sort the bucket

    // Step 5: Re-combine the buckets
    int counter = 0;
    for (int i = 0; i < 26; i++){
        for (int j = 0; j < size[i]; j++){
            strcpy(arr[counter], buckets[i][j]);
            counter++;
        }
    }
}

int main(int argc, char** argv){
    FILE* fin = fopen(argv[1], "r");
    FILE* fout = fopen(argv[2], "w");

    if(!fin || !fout)   {
        fprintf(stderr, "Error opening file\n");
        return -1;
    }

    char arr[1024][32];
    char tmp[32];
    int size = 0;
    while(fscanf(fin, "%s", tmp) != EOF){
        strcpy(arr[size], tmp);
        size++;
    }

    bucketSort(arr, size, 0);
    printArray(fout, arr, size);

    fclose(fin);
    fclose(fout);
    return 0;
}