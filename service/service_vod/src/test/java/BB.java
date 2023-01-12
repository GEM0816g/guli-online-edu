

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BB {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Integer> list = new ArrayList<>();
       // StringBuffer stringBuffer = new StringBuffer();
     //   StringBuffer stringBuffer1 = new StringBuffer();
        String[] stringBuffer=new String[n];
        String[] stringBuffer1=new String[m];

        for (int i = 0; i < n; i++) {
            stringBuffer[i]= String.valueOf(scanner.nextInt());
        }

        for (int j = 0; j < m; j++) {
            stringBuffer1[j]= String.valueOf(scanner.nextInt());
        }
       list= ff(n, m, stringBuffer, stringBuffer1);
        for (int i=list.size()-1;i>=0;i--){
            if (i==0){
                System.out.print(list.get(i));
            }
            else{
                System.out.print(list.get(i)+" ");
            }

        }

    }

    public static List<Integer> ff(int n, int m, String[] nStrings, String[] mStrings) {
        int i = 2;
        int jw = 0;
        n--;
        m--;
        List<Integer> list = new ArrayList<>();

        while (n >= 0 && m >= 0) {
            int sum = Integer.valueOf(nStrings[n]) + Integer.valueOf(mStrings[m]) + jw;

            list.add(sum % i);
            jw = sum / i;
            n--;
            m--;
            i++;
        }

        while (n >= 0) {
            int sum = Integer.valueOf(nStrings[n]) + jw;
            list.add(sum % i);
            jw = sum / i;
            n--;
            i++;
        }

        while (m >= 0) {
            int sum = Integer.valueOf(mStrings[m]) + jw;
            list.add(sum % i);
            jw = sum / i;
            m--;
            i++;
        }
        if (jw != 0) {
            list.add(jw);
        }

        return list;
    }

}
