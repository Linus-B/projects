//TREEMAP funcs.c
//@author Linus Bendel-Stenzel


#include "treemap.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>


void treemap_init(treemap_t *tree){
    tree -> size = 0;
    tree -> root = NULL;
}

int treemap_add(treemap_t *tree, char key[], char val[]){
    node_t* tmp_head = tree -> root;
    node_t* tmp_node = malloc(sizeof(node_t));
    strcpy(tmp_node -> key, key);
    strcpy(tmp_node -> val, val);
    tmp_node -> left = NULL;
    tmp_node -> right = NULL;
    tree -> size += 1;
    if (tmp_head == NULL){
        tree -> root = tmp_node;
        return 1;
    }
    while(1){
        if (strcmp(tmp_head -> key, key) > 0){
            if (tmp_head -> left == NULL){
                tmp_head -> left = tmp_node;
                return 1;
            } else{
                tmp_head = tmp_head -> left;
            }
        } else if (strcmp(tmp_head -> key, key) < 0){
            if (tmp_head -> right == NULL){
                tmp_head -> right = tmp_node;
                return 1;
            } else{
                tmp_head = tmp_head -> right;
            }
        } else {
            strcpy(tmp_head -> val, val);
            tree -> size -= 1;
            free(tmp_node);
            printf("modified existing key\n");
            return 0;
        }
    }
    free(tmp_node);
    return -1;
}


char *treemap_get(treemap_t *tree, char key[]){
    node_t* tmp_node = tree -> root;
    while(tmp_node != NULL){
        if (strcmp(tmp_node -> key, key) < 0){
            tmp_node = tmp_node -> right;
        } else if (strcmp(tmp_node -> key, key) > 0){
            tmp_node = tmp_node -> left;
        } else{
            return tmp_node -> val;
        }
    }
    return NULL;

}

void treemap_clear(treemap_t *tree){
    node_remove_all(tree -> root);
    tree -> root = NULL;
    tree -> size = 0;

}

void node_remove_all(node_t *cur){
    if (cur != NULL){
        node_remove_all(cur -> left);
        node_remove_all(cur -> right);
        free(cur);
    }
}

void node_print_revorder(node_t *cur, int indent){
    if (cur != NULL){
        node_print_revorder(cur -> right, indent + 1);
        for (int i = 0; i < indent * 2; i++){
            printf(" ");
        }
        printf("%s -> %s\n", cur -> key, cur -> val);
        node_print_revorder(cur -> left, indent + 1);
    }
}

void treemap_print_revorder(treemap_t *tree){
    node_print_revorder(tree -> root, 0);
}

void node_write_preorder(node_t *cur, FILE *out, int depth){
    if (cur != NULL){
        for (int i = 0; i < depth * 2; i++){
            fprintf(out, " ");
        }
        fprintf(out, "%s %s\n", cur -> key, cur -> val);
        node_write_preorder(cur -> left, out, depth + 1);
        node_write_preorder(cur -> right, out, depth + 1);
    }
}

void treemap_print_preorder(treemap_t *tree){
    node_write_preorder(tree -> root, stdout, 0);
}

void treemap_save(treemap_t *tree, char *fname){
    FILE* fin = fopen(fname, "w");
    node_write_preorder(tree -> root, fin, 0);
    fclose(fin);
}

int treemap_load(treemap_t *tree, char *fname ){
    char input[2][128];
    FILE* fin = fopen(fname, "r");
    if (fin == NULL){
        return 0;
    }
    treemap_clear(tree);
    while (fscanf(fin, "%s %s", input[0], input[1]) != EOF){
        treemap_add(tree, input[0], input[1]);
    }
    fclose(fin);
    return 1;
}



