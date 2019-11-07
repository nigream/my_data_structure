package main;

import array.MyArray;
import array.MyArrayE;

/**
 * @author Nigream Lin
 * @date 2019/10/27 15:49
 * @description 测试代码类
 */
public class Main {
    public static void main(String[] args) {
        MyArrayE<Integer> myArray = new MyArrayE<Integer>(20);
        for (int i = 0; i < 10; i++) {
            myArray.addLast(i);
        }
        System.out.println(myArray);
        myArray.add(3,3);
        System.out.println(myArray);
        System.out.println(myArray.getAllIndexesByValue(3));

        System.out.println(myArray.getAllIndexesByValue(20));
        System.out.println(myArray);
        System.out.println(myArray.removeAllElementsByValue(3));
        System.out.println(myArray);
        System.out.println(myArray.removeAllElementsByValue(20));
        System.out.println(myArray);
        System.out.println(myArray.contains(10));

    }

}
