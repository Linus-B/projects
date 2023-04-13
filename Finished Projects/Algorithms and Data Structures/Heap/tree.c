#include "heap.h"
 
huffman_tree_node *huffman_tree;
char huffman_code[256][20];
 
huffman_tree_node* Convert(char c, int freq, huffman_tree_node* t){
    if (t){
        return t;
    }
    huffman_tree_node* tmp = malloc(sizeof(huffman_tree_node));
    tmp->c = c;
    tmp->freq = freq;
    tmp->left = NULL;
    tmp->right = NULL;

    return tmp;
}

void huffman_init(){
    for (int i = 0; i < 256; i++){
        for(int j = 0; j < 20; j++){
            huffman_code[i][j] = 0;
        }
    }
}

void encode(int encoded_pattern, int depth, huffman_tree_node* node){
    //Depth is the number of significant bits
    if (node == NULL){
    }
    else if ((int)node->c == 3){// If not a leaf
        encode(encoded_pattern * 2, depth + 1, node->left);
        encode(encoded_pattern * 2 + 1, depth + 1, node->right);
    } else {// If a leaf
        for (int i = 0; i < 19; i++){
            huffman_code[(int)node->c][i] = ((1 << i) & encoded_pattern) >> i;
            // Results in 1 or 0
        }
        huffman_code[(int)node->c][19] = depth;
    }
}
/******************************************
 *                                        *
 *   Enter your code for huffman coding   *
 *                                        *
 ******************************************/
 
 

