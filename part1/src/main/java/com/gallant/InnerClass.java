package com.gallant;

import com.gallant.nio.SocketChannelDemo;

/**
 * Created by huangjunhao on 20/4/7.
 */
public class InnerClass {

    public static String C = "xxxx";
    public String A;

    public static class InnerClass2 {
        public static String B = "bbbb";
    }


    public static void main(String[] args) {

        int[] arr = new int[]{0, 1, 0, 4, 1,
                0, 1, 3, 0, 6,
                0, 1, 0, 3};

        int waterTotal = 0;
        //外层循环
        for (int i = 0; i < arr.length; i++) {

            //自身
            int curr = arr[i];
            //自身位置存水量
            int waterOfCurrIndex = 0;

            //找出当前左边最高
            int leftMax = 0;
            for (int l = 0; l < i; l++) {
                if (leftMax < arr[l]) {
                    leftMax = arr[l];
                }
            }

            //找出当前右边最高
            int rightMax = 0;
            for (int r = i; r < arr.length; r++) {
                if (rightMax < arr[r]) {
                    rightMax = arr[r];
                }
            }


            int min = leftMax < rightMax ? leftMax : rightMax;
            if (min > curr) {
                //当前元素存水量
                waterOfCurrIndex = min - curr;
                waterTotal += waterOfCurrIndex;
            }

            System.out.println(arr[i] + " 左边最大:" + leftMax + " 右边最大:" + rightMax + " 当前位置存水:" + waterOfCurrIndex + "立方");
        }

        System.out.println("合计:" + waterTotal + "立方水");
    }
}
