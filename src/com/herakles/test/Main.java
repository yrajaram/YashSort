/*
 * This is a linear order sort algorithm which depends on the maximum element and the minimum element in the input. 
 * For example if the maximum value is 100 then this will perform in O(100) irrespective of the number of elements in the input array.
 * Contrast this will Bubble Sort which with perform in O(100) if 10 elements are in the input array and will be O(400) if 20 elements
 * are in the input.
 */
package com.herakles.test;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;


public class Main {
    private static long start, end;

    public static void main(String[] args) {

        int arr[] = {0,2,78,44,65,11,33,30,7,25,6, 15, 21, 45, 99, 32, 13, 50, 81, 3,79,43,64,10,34,29,8,24,5, 14, 20};
        int arr2[] = new int[arr.length];
        
        System.out.print("Provided input values:\t");
        printArray(arr);
        
        System.arraycopy(arr,0, arr2, 0, arr.length);

        arr2 = bubbleSort(arr2);
        System.out.print("After bubble sort:\t");
        printArray(arr2);
        
        System.arraycopy(arr,0, arr2, 0, arr.length);
        arr2 = yashSort(arr2);
        System.out.print("After Yash sort:\t");
        printArray(arr2);
    }
    
    private static void printArray(int[] ip) {
        for (int i = 0; i < ip.length; i++) {
            System.out.print(ip[i]+" ");
        }
        System.out.println("\n");
    }
    
    private static int[] bubbleSort(int[] ip) {
        start = System.nanoTime();
        for (int i = 0; i < ip.length; i++) {
            for(int j = i+1; j < ip.length; j++) {
                if (ip[i] > ip[j]) {
                    int tmp = ip[i];
                    ip[i] = ip[j];
                    ip[j] = tmp;
                } 
            }
        }
        end = System.nanoTime();
        System.out.printf("%d nano sec\n",end-start);
        return(ip);
    }
	
private static int[] quickSort(int[] ip) {
        start = System.nanoTime();
        quickSort0(ip, 0, ip.length-1);
        end = System.nanoTime();
        System.out.printf("%d nano sec\n",end-start);
        return(ip);
    }
    
    private static int partition(int arr[], int left, int right) { 
    	int i = left, j = right; 
    	int tmp; 
    	int pivot = arr[(left + right) / 2]; 

    	while (i <= j) { 
    		while (arr[i] < pivot) 
    			i++; 

    		while (arr[j] > pivot) 
    			j--; 

    		if (i <= j) { 
    			tmp = arr[i]; 
    			arr[i] = arr[j]; 
    			arr[j] = tmp; 
    			i++; 
    			j--; 
    		} 
    	}; 

    	return i; 
    } 

    private static void quickSort0(int arr[], int left, int right) { 
    	int index = partition(arr, left, right); 

    	if (left < index - 1) 
    		quickSort0(arr, left, index - 1); 

    	if (index < right) 
    		quickSort0(arr, index, right); 
    }
   
    // Handle duplicates
    private static int[] yashSort2(int[] ip) {
        HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
        Integer element = new Integer(0), val = new Integer(0);
        int min = 0, max = 0;
        for (int i = 0; i < ip.length; i++) {
            if (min > ip[i]) min = ip[i];
            if (max < ip[i]) max = ip[i];
            element = Integer.valueOf(ip[i]);
            
            if ((val = h.get(element))!=null) {
                val = Integer.sum(val.intValue(), 1);
            } else {
                val = new Integer(1);
            }
            h.put(element, val);
        }
//        System.out.println("min:"+min+" max:"+max+" hashmap:"+h);
        int tmp =0;
        for (int i = min; i <= max; i++) {
            if ((val = h.get(new Integer(i))) != null) {
                for (int j = 0; j<val; j++) {
                    ip[tmp++] = i;
                }
            }
        }
        return(ip);

    }
    
    private static int[] yashSort1(int[] ip) {
        HashSet<Integer> h = new HashSet<Integer>();
        int min = 0, max = 0;
        
        for (int i = 0; i < ip.length; i++) {
            if (min > ip[i]) min = ip[i];
            if (max < ip[i]) max = ip[i];
            
            h.add(Integer.valueOf(ip[i]));
        }

        int tmp =0;
        for (int i = min; i <= max; i++) {
            if (h.contains(Integer.valueOf(i))) {
                    ip[tmp++] = i;
            }
        }
        return(ip);
    }
    
    // Handle gaps
    private static int[] yashSort3(int[] ip) {
        HashSet<Integer> h = new HashSet<Integer>();
        int min = Integer.MAX_VALUE;
        int oldMin = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int oldMax = Integer.MIN_VALUE;
        
        for (int i = 0; i < ip.length; i++) {
            if (min > ip[i]) min = ip[i];
            if ((ip[i] > min) && (ip[i] < oldMin)) {
            	oldMin = ip[i];
            }
            if (max < ip[i]) max = ip[i];
            if ((ip[i] < max) && (ip[i] > oldMax)) {
            	oldMax = ip[i];
            }

            h.add(Integer.valueOf(ip[i]));
        }

        int tmp = 2;
        ip[0] = min;
        ip[1] = oldMin;
        ip[ip.length-2] = oldMax;
        ip[ip.length-1] = max;
        
        for (int i = oldMin+1; i < oldMax-1; i++) {
            if (h.contains(Integer.valueOf(i))) {
                    ip[tmp++] = i;
            }
            if (tmp==(ip.length-2)) break;
        }
        return(ip);
    }
    
    // Using BitSet to avoid hashset
    private static int[] yashSort5(int[] ip) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        BitSet bb = new BitSet();
        int tmp = 0;
        
        start = System.nanoTime();

        for (int i = 0; i < ip.length; i++) {

            if (min > ip[i]) min = ip[i];
            if (max < ip[i]) max = ip[i];
            
            bb.set(ip[i]);
        }

        for (int i = 0; i < bb.length(); i++) {
        	if (bb.get(i))	ip[tmp++] = i;
		}

        end = System.nanoTime();
        System.out.printf("%d nano sec\n",end-start);
        
        return(ip);
    }
    
    // Assumption: no duplicates in input and inputs in the range 0-100 (for now, will improve later) 
    private static int[] yashSort(int[] ip) {
    	byte[] b = new byte[14]; //to sort numbers in range 0-100
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int index = 0, mask = 0, tmp = 0;
        
        start = System.nanoTime();

        for (int i = 0; i < ip.length; i++) {

            if (min > ip[i]) min = ip[i];
            if (max < ip[i]) max = ip[i];
            
            index = ip[i]/Byte.SIZE;
            mask = 1<<(ip[i]%Byte.SIZE);
        
            b[index] = (byte) (b[index] | mask) ;
        }
        
        for (int i = 0; i < b.length; i++) {      
        	if (b[i]!=0)
			for (byte j = 0; j < Byte.SIZE; j++) {
				mask = (b[i]&(1<<j));
				if (mask >0) {
					ip[tmp++]=i*Byte.SIZE+j;
				}
			}
		}
        
		
        end = System.nanoTime();
        System.out.printf("%d nano sec\n",end-start);
        
        return(ip);
    }
}
