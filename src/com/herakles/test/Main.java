/*
 * This is a linear order sort algorithm which depends on the maximum element and the minimum element in the input. 
 * For example if the maximum value is 100 then this will perform in O(100) irrespective of the number of elements in the input array.
 * Contrast this will Bubble Sort which with perform in O(100) if 10 elements are in the input array and will be O(400) if 20 elements
 * are in the input.
 */
package com.herakles.test;

import java.util.HashMap;
import java.util.HashSet;


public class Main {
    public static void main(String[] args) {
        int arr[] = {2,78,4,65,11,3,30,7,25,6};
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
        
        for (int i = 0; i < ip.length; i++) {
            for(int j = i+1; j < ip.length; j++) {
                if (ip[i] > ip[j]) {
                    int tmp = ip[i];
                    ip[i] = ip[j];
                    ip[j] = tmp;
                } 
            }
        }
        return(ip);
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
    
    private static int[] yashSort(int[] ip) {
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
        }
        return(ip);
    }
}
