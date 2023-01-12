


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class B {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        Stack<Integer> nList = new Stack<>();
        Stack<Integer> mList = new Stack<>();

        List<Integer> arrList=new ArrayList<>();
        n = scanner.nextInt();
        m = scanner.nextInt();
        int jinzhi = 2;
        int yichu = 0;
        for (int i = 0; i < n; i++) {
            nList.push(scanner.nextInt());
        }

        for (int j = 0; j < m; j++) {
            mList.push(scanner.nextInt());
        }


        while (nList.size() > 0 && mList.size() > 0) {
            int temp1 = nList.pop();
            int temp2 = mList.pop();
            int temp3 = temp1 + temp2 + yichu;
            yichu = 0;
            if (temp3 >= jinzhi) {

                int temp4 = temp3;
                while (temp4 > jinzhi) {

                    temp4 = temp4 / jinzhi;
                    yichu++;
                }

                arrList.add(temp3 % jinzhi);
                jinzhi++;


            } else {
                arrList.add(temp3);
                jinzhi++;
            }

        }
        while (nList.size() > 0) {
            int temp1 = nList.pop() + yichu;
            yichu=0;
            if (temp1 >= jinzhi) {

                int temp4 = temp1;
                while (temp4 > jinzhi) {

                    temp4 = temp4 / jinzhi;
                    yichu++;
                }

                arrList.add(temp1 % jinzhi);
                jinzhi++;

            } else {
                arrList.add(temp1);

            }
        }
            while (mList.size() > 0) {
                int temp2 = mList.pop() + yichu;

                yichu = 0;
                if (temp2 >= jinzhi) {

                    int temp4 = temp2;
                    while (temp4 > jinzhi) {

                        temp4 = temp4 / jinzhi;
                        yichu++;
                    }

                    arrList.add(temp2 % jinzhi);
                    jinzhi++;

                }else{
                    arrList.add(temp2);

                }
            }

           for (int i=arrList.size()-1;i>=0;i--)
           {
               if (i==0){
                   System.out.print(arrList.get(i));
               }else{
                   System.out.print(arrList.get(i)+" ");
               }

           }

    }
}