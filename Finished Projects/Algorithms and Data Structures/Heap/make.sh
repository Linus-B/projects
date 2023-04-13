gcc -c -g tree.c tree.h

gcc -c -g heap.c heap.h

gcc -c -g huffman.c

gcc -Wall -g -o huffman huffman.o heap.o tree.o

