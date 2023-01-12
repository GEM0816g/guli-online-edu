


import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        int n;
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt();
        // 1,5,9,13
        int d=4;
        int a1=1;
        //数列数量
        int an=a1+(n-1)*d;

        int mid=1+(an-1)/2;
        //总数
        int Sn=(a1+an)*n/2;


        System.out.println(mid);




    }
}
