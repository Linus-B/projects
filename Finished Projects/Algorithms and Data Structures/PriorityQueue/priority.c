#include "priority.h"

void Head_Init(PriorityHead* p){
    p->size = 0;
    p->priority[0].number = __INT_MAX__;
}


void bubbleUp(PriorityHead* p, int pos)  {
    PriorityNode element; 

    element.number = p->priority[pos].number;
    strcpy(element.name, p->priority[pos].name);

    while (p->priority[pos / 2].number < element.number) {
        strcpy(p->priority[pos].name, p->priority[pos/2].name);
        p->priority[pos].number = p->priority[pos/2].number;

        pos /= 2;
    } 
    strcpy(p->priority[pos].name, element.name);
    p->priority[pos].number = element.number;
}

void bubbleDown(PriorityHead* p, int pos)  {
    int child;
    PriorityNode element;
    strcpy(element.name, p->priority[pos].name);
    element.number = p->priority[pos].number;

    while(1)   {
        // first child 
        child = pos * 2;
	if(child > p->size)
	       break;	

        if (child != p->size && p->priority[child + 1].number > p->priority[child].number) {
	     // second child 
             child++;
        }
        /* To check if the last element fits ot not it suffices to check if the last element
         is less than the minimum element among both the children*/

        if (element.number > p->priority[child].number) {
		    strcpy(p->priority[pos].name, p->priority[child].name);
		    p->priority[pos].number = p->priority[child].number;
        } else /* It fits there */
        {
            break;
        }
	pos = child;
    }
    strcpy(p->priority[pos].name, element.name);
    p->priority[pos].number = element.number;
}

void Insert(PriorityHead* p, PriorityNode addition) {
    p->size ++;
    if(p->size >= 500)   {
	  fprintf(stderr, "Exceeded p->priority capacity\n");
	  assert(0); 
    }
    /*Insert in the last place*/
    p->priority[p->size].number = addition.number;
    strcpy(p->priority[p->size].name, addition.name);

    bubbleUp(p, p->size);
}

PriorityNode Maximum(PriorityHead* p){
    return p->priority[1];
}

PriorityNode Extract_Max(PriorityHead* p){
        PriorityNode minElement;

    strcpy(minElement.name, p->priority[1].name);
    minElement.number = p->priority[1].number;

    strcpy(p->priority[1].name, p->priority[p->size].name);
    p->priority[1].number = p->priority[p->size].number;
    /* now refers to the index at which we are now */
    bubbleDown(p, 1);

    p->size--;
    return minElement;
}