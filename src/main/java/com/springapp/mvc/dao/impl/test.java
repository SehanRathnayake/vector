package com.springapp.mvc.dao.impl;

import java.util.HashMap;

/**
 * Created by Sehan Rathnayake on 16/11/12.
 */
public class test {
//    static HashMap<Integer,Integer> map=new HashMap();
//    static HashMap<Integer,Integer> oddDevisors=new HashMap();
//
//
//    static long countSum(int[] numbers) {
//        long sum=0;
//        for(int i=0; i<numbers.length;i++){
//            sum +=oddDevisorSum(numbers[i]);
//        }
//        return sum;
//    }
//    static long oddDevisorSum(int x){
//        Object y=map.get(x);
//
//        if(y!=null)return (Integer)y;
//        int sum=0;
//        for(int i=1;i<=x;i++){
//
//            if(x%i==0 && i%2 !=0){
//                sum  +=i;
//            }
//
//        }
//        map.put(x,sum);
//        return sum;
//    }

//    static int moves(int[] a) {
//        int i = 0;
//        int j = a.length - 1;
//        int count = 0;
//        for (; i < a.length; i++) {
//            if (i >= j) {
//                break;
//            } else {
//                if (a[i] % 2 != 0) {
//                    for (; j >= 0; j--) {
//                        if (a[j] % 2 == 0) {
//                            if (i >= j) {
//                                break;
//                            }
//                            else{
//                                count++;
//                            }
//                        }
//                    }
//                }
//
//            }
//
//        }
//        return count;
//
//    }
static int[][] findMatrix(int[][] a) {
    a=new int[4][5];
int n=a[0].length;
  int b=a.length;

    System.out.println(a+" "+b);
return null;

}
    public static void main(String args){
        findMatrix(null);
    }
}