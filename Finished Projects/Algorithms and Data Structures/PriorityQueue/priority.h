#ifndef PRIORITY_H
#define PRIORITY_H

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>

typedef struct _Priority_Node {
    int number;
    char name[64];
} PriorityNode;

typedef struct _Priority_Head {
    PriorityNode priority[500];
    int size; 

} PriorityHead;

void Head_Init(PriorityHead* p);
void Insert(PriorityHead* p, PriorityNode addition);
PriorityNode Maximum(PriorityHead* p);
PriorityNode Extract_Max(PriorityHead* p);
void Bubble_Down(PriorityHead* p, int pos);
void Bubble_Up(PriorityHead* p, int pos);



#endif