package theinternetherokuappcom;

import groovy.lang.IntRange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class test1{

public static void sums() {
    int i = 1;
    int[] nums = {1, 1, 1, 2, 1};
    while (i<nums.length) {
        int[] firstHalf = Arrays.copyOfRange(nums, 0, nums.length - i);
        int[] secondHalf = Arrays.copyOfRange(nums, nums.length - i, nums.length);
        int sum1 = Arrays.stream(firstHalf).sum();
        int sum2 = Arrays.stream(secondHalf).sum();
        if (sum1 == sum2) {
        System.out.println(sum1 == sum2);
        System.out.println(sum1 + "   " + sum2);}
        else System.out.println(false);
        i++;
    }
}

// squareUp(4) → [0, 0, 0, 1, 0, 0, 2, 1, 0, 3, 2, 1, 4, 3, 2, 1]

    public static void  squareUp(int n) {
    Integer [] array = {0, 0, 0, 1, 0, 0, 2, 1, 0, 3, 2, 1, 4, 3, 2, 1};
    List <Integer> t = new ArrayList<Integer>();
    int[] arr = new int[n*n];
    String str = "";
     //   System.out.println("Original Array : " +
     //           Arrays.toString(array));
     //   Collections.reverse(Arrays.asList(array));

    //    System.out.println("Modified Array : " +
   //             Arrays.toString(array));
        int r= 4;

        while( r>0) {
            List<Integer> testr =new ArrayList <Integer>() ;
            testr.add(0);
            testr.add(0);
            testr.add(0);
            testr.add(0);
            int j = 0;
            for (int i=0; i<r; i++  ){
             testr.add(j,i+1);
             j=j+1;

         }
         r--;
            System.out.println("Modified Array : " +
                    testr);
         str= str+testr.toString()   ;

        }

    }

    public static void main(String[] args){test1.squareUp(4);
    }

}






