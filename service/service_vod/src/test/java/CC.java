import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class CC {
    public static void main(String[] args) {
        int n;
        int m;
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt();
        boolean flag=true;
        m=scanner.nextInt();
        int a[]=new int[n];
        for (int i=0;i<n;i++){
            a[i]=scanner.nextInt();
        }
        int min=0;
        int max=0;

        System.out.println(getMax(a));
        //System.out.println(black(a,m,max,min));



        }
        public static int black(int a[],int m,int max,int min){
            if (m==0){
                return max-min;
            }



            int minn=black(a,m--,max,min);
            int maxx=black(a,m--,max,min);


            return Math.min(minn,maxx);
        }


        public static int getMax(int []a){
       int max=Integer.MIN_VALUE;
       int maxIndex=-1;
       for (int i=0;i<a.length;i++){
           if (max < a[i]) {
               max = a[i];
               maxIndex = i;
           }
       }

        return maxIndex;
        }

    public static int getMin(int []a){
        int min=Integer.MAX_VALUE;
        int minIndex=-1;
        for (int i=0;i<a.length;i++){
            if (min < a[i]) {
                min = a[i];
                minIndex = i;
            }
        }

        return minIndex;
    }



    }

