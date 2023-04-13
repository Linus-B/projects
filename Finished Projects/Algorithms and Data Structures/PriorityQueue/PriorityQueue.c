#include "priority.h"

void print_queue(FILE* out, PriorityHead* p){
    while (p->size > 0){
        PriorityNode tmp = Extract_Max(p);
        fprintf(out, "%s %d\n", tmp.name, tmp.number);
    }
}

int main(int argc, char **argv)   {
	FILE *in_file; 
	FILE *out_file; 

    in_file = fopen(argv[1], "r");
    out_file = fopen(argv[2], "w");

    if(!in_file || !out_file)   {
        fprintf(stderr, "Error opening file\n");
        return -1;
    }
    char buff[255];
    PriorityHead* head = malloc(sizeof(PriorityHead));
    Head_Init(head);

    PriorityNode tmp;

    while (fscanf(in_file, "%s", buff) != EOF){
        if (strcmp(buff, "insert") == 0){
            fscanf(in_file, "%s %d", tmp.name, &tmp.number);
            Insert(head, tmp);
        }
        else if (strcmp(buff, "max") == 0){
            fprintf(out_file, "%s\n", Maximum(head).name);
        }
        else if (strcmp(buff, "extract") == 0){
            fprintf(out_file, "%s\n", Extract_Max(head).name);
        } 
        else if (strcmp(buff, "quit") == 0){
            break;

        } else {
            fprintf(out_file, "%s Invalid input\n", buff);
            break;

        }
    }
    print_queue(out_file, head);
    free(head);
    fclose(in_file);
    fclose(out_file);

    return 0;
}