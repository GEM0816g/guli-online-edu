package com.atguigu.demo;

public class Main {

    private float a = 1.0f;
    int b = 12;
    static int c = 1;

    public static void main(String[] args) {
        Main main = new Main();

        System.out.println(main.a);
        mm m=new mm();
        System.out.println(m.a=2);
        m.d();
    }



    static class mm{
        private float a = 1.0f;
        private void d(){
            System.out.println("ss");
        }
    }
}


