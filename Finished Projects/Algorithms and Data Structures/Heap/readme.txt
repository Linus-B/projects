To be honest this program is way overkill. There are a lot of unncessary functions and steps and stuff. 
The reason is because I made a full compression and decompression program in another class.
This is my code from that class, modified slightly in order to output and input the right stuff.

First this program finds the frequencies, and puts them in a minHeap
Then, it extracts from the least common letter to the most common, and using Hoffman's
algorithm, it creates a hoffman tree. Then, this program encodes each letter, however that
step isn't actually necessary, you could just make a recursive program to go through the tree. But
given the code I had already made, I just finish up the encoding, and then print out the 
encoded array

