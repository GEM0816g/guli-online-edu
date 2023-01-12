import java.util.Scanner;


public class c {


        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            long a = scanner.nextLong();
            long b = scanner.nextLong();
            if (b>0){
                if (a<0){
                    System.out.print(a*(-1));
                }else {
                    System.out.print(a);
                }
            }
            if (b<0){
                if (a<0){
                    System.out.print(a);
                }else {
                    System.out.print(a*(-1));
                }
            }

        }

}
