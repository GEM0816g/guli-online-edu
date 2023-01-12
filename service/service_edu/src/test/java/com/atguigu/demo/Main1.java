package com.atguigu.demo;

import java.util.ArrayList;

public class Main1 {
    public static int getValue(int i) {
        int result = 0;
        switch (i) {
            case 1:
                result = result + i;
            case 2:
                result = result + i * 2;
            case 3:
                result = result + i * 3;
        }
        return result;
    }
    public static void main(String[] args) {
         int ii=getValue(2);
        System.out.println(ii);


        ArrayList<String> list = new ArrayList();
        list.add("hello");
        list.add("aaa");
        list.add("hello");
        list.add("hello");
        list.add("bbb");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i)+"");
            if ("hello".equals(list.get(i))) {


                list.remove(i);
            }
        }
//
//        for (int i = list.size() - 1; i >= 0; i--) {
//            if ("hello".equals(list.get(i))) {
//                list.remove(i);
//            }
//        }


        for (String s : list) {
            System.out.println(s);

        }



    }

}
