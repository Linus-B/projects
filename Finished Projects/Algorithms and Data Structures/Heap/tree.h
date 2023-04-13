#ifndef TREE_H
#define TREE_H

#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <limits.h>

typedef struct _Huffman_Node {
    char c;
    int freq;
    struct _Huffman_Node *left, *right;
} huffman_tree_node;

huffman_tree_node* Convert(char c, int freq, huffman_tree_node* t);
/******************************************
 *                                        *
 *   Enter your code for huffman coding   *
 *                                        *
 ******************************************/
void huffman_init();
void encode(int encoded_pattern, int depth, huffman_tree_node* node);
void clean();

extern huffman_tree_node *huffman_tree;
extern char huffman_code[256][20];
 
#endif
