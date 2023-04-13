#include <stdio.h>
#include "tree.h"
#include "heap.h"

void write_code(FILE* fout){
    for (int i = 0; i < 256; i++){
        if(huffman_code[i][19] > 0){
            //printf("%c has something\n", i);
            fprintf(fout, "%c:", (char)i);
            //for (int j = huffman_code[i][19]; j > 0; j--){
            for (int j = 0; j < huffman_code[i][19]; j++){
                fprintf(fout, "%d", huffman_code[i][j]);
            }
            fprintf(fout, "\n");
        }
    }
}

int main(int argc, char** argv){
    int counting_bin[256];
    int buff = 0;
    FILE* fin = fopen(argv[1], "r");
    FILE* fout = fopen(argv[2], "w");
    if(!fin || !fout)   {
        fprintf(stderr, "Error opening file\n");
        return -1;
    }

    for(int i = 0; i < 256; i++)  
		counting_bin[i] = 0;
        buff = fgetc(fin);
	while (buff != EOF) {
		counting_bin[(int)buff]++;
        buff = fgetc(fin);
    } // Find the frequencies

    heapInit(); //Insert into a minHeap
	for(int i = 0; i < 256; i++){
		if(counting_bin[i]) 
			HeapInsert(i, NULL, counting_bin[i]);
    }

    //Takes out the minimum from heap, inserts into tree. 
    while (heapSize > 1){
		huffman_tree_node* new_node = malloc(sizeof(huffman_tree_node));
		HeapNode tmp1 = DeleteMin();
		HeapNode tmp2 = DeleteMin();
		new_node->c = (char)3; // Unused, representative of not a leaf node
		new_node->freq = tmp1.freq + tmp2.freq;
		new_node->left = Convert(tmp1.c, tmp1.freq, tmp1.t);
		new_node->right = Convert(tmp2.c, tmp2.freq, tmp2.t);
		HeapInsert(3, new_node, new_node->freq);
	}// Take out of heap, then add back


	huffman_tree = DeleteMin().t;
    huffman_init();// Init the encoder
	encode(0, 0, huffman_tree);
    write_code(fout);

    fclose(fin);
    fclose(fout);
    return 0;
}