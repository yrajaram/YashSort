# YashSort
Super cool sorting algorithm

This is a linear order sort algorithm which depends on the maximum element and the minimum element in the input. 

For example if the input provided contains elements that range between 0 to 100 then this algorithm will perform in O(100) irrespective of the number of elements in the input array. 

Contrast this with Bubble Sort which with perform in O(100) if 10 elements are in the input array and will be O(400) if 20 elements are in the input. 

(I know this is not how big O should be used but explaining in this way highlights the nature of this algorithm well.)

## comparison
Here is a comparision I made with BubbleSort and QuickSort - YashSort stacks up pretty well. [The inputs did not have any duplicate values and the inpuit values were between 0 and 99 (inclusive of both)]

Provided input values:	0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 ... 99 

130,052 nano sec
After bubble sort:	0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 ... 99 

12,308 nano sec
After Yash sort:	0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 ... 99 

28,718 nano sec
After quick sort:	0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 ... 99
