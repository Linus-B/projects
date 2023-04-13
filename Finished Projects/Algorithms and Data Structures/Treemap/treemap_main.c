//TREEMAP main.c
//@author: Linus

#include "treemap.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>


int main(int argc, char *argv[]){
    int echo = 0; // Echoing on or off
    if(argc > 1 && strcmp("-echo",argv[1])==0) { // turn echoing on via -echo command line option
    echo = 1;
  }
    printf("TreeMap Editor\n");
    printf("Commands:\n");
    printf("  quit:            exit the program\n");
    printf("  print:           shows contents of the tree in reverse sorted order\n");
    printf("  add <key> <val>: inserts the given key/val into the tree, duplicate keys are ignored\n");
    printf("  get <key>:       prints FOUND if the name is in the tree, NOT FOUND otherwise\n");
    printf("  clear:           eliminates all key/vals from the tree\n");
    printf("  preorder:        prints contents of the tree in pre-order which is how it will be saved\n");
    printf("  save <file>:     writes the contents of the tree in pre-order to the given file\n");
    printf("  load <file>:     clears the current tree and loads the one in the given file\n");
    char line[128];
    char tmp_key[128];
    char tmp_val[128];
    char tmp_filename[128];
    char* tmp = NULL;


    treemap_t* tree = malloc(sizeof(treemap_t));
    treemap_init(tree);
    while(1){
        printf("TM> ");
        if (scanf("%s", line) == EOF){
            treemap_clear(tree);
            free(tree);
            return 0;
        } else if (strcmp("quit", line) == 0){
            if (echo){
                printf("quit\n");
            }
            treemap_clear(tree);
            free(tree);
            return 0;
        } else if (strcmp("print", line) == 0){
            if (echo){
                printf("print\n");
            }
            treemap_print_revorder(tree);
        } else if (strcmp("clear", line) == 0){
            if (echo){
                printf("clear\n");
            }
            treemap_clear(tree);
        } else if (strcmp("preorder", line) == 0){
            if (echo){
                printf("preorder\n");
            }
            treemap_print_preorder(tree);
        } else if (strcmp("add", line) == 0){
            scanf("%s %s", tmp_key, tmp_val);
            if (echo){
                printf("add %s %s\n", tmp_key, tmp_val);
            }
            treemap_add(tree, tmp_key, tmp_val);
        } else if (strcmp("get", line) == 0){
            scanf("%s", tmp_key);
            if (echo){
                printf("get %s\n", tmp_key);
            }
            tmp = treemap_get(tree, tmp_key);
            if (tmp != NULL){
                printf("FOUND: %s \n", tmp);
            } else {
                printf("NOT FOUND \n");
            }
        } else if (strcmp("save", line) == 0){
            scanf("%s:", tmp_filename);
            if (echo){
                printf("save %s\n", tmp_filename);
            }
            treemap_save(tree, tmp_filename);
        } else if (strcmp("load", line) == 0){
            scanf("%s:", tmp_filename);
            if (echo){
                printf("load %s\n", tmp_filename);
            }
            if (treemap_load(tree, tmp_filename) == 0){
                printf("ERROR: could not open file '%s'\nload failed\n", tmp_filename);
            }
        } else {
            printf("Invalid Input\n");
        }
    }
    treemap_clear(tree);
    free(tree);
    return 0;
}