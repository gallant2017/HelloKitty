package com.gallant.xx;

/**
 * 不常用逻辑运算符
 *
 * @Author: Huang Junhao
 * @Date: 2020/1/14 15:13
 */
public class Xx {
    public static void main(String[] args) {

        //位异或运算（^）
        //运算规则是：两个数转为二进制，然后从高位开始比较，如果相同则为0，不相同则为1
        int a = 8, b = 16;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));

        // 1000
        //10000
        //11000
        System.out.println(a ^ b);
        System.out.println(Integer.parseInt("11000", 2));

        //位或运算符（|）
        //运算规则：两个数都转为二进制，然后从高位开始比较，两个数只要有一个为1则为1，否则就为0。
        // 1000
        //10000
        //11000
        System.out.println(a | b);


        //位与运算符（&）
        //运算规则：两个数都转为二进制，然后从高位开始比较，如果两个数都为1则为1，否则为0。
        // 1000
        //10000
        //00000
        System.out.println(a & b);
        System.out.println(Integer.parseInt("00000", 2));

    }
}
