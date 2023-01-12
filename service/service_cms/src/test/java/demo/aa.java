package demo;

public class aa {

    public static void main(String[] args) {
        System.out.println(maxScore("011101"));
    }
    public static int maxScore(String s) {
        int maxNum=0;
        int left=0;

        while(left<s.length()-1) {
            int leftNums=0;
            int rightNums=0;
            for (int i=0; i <=left ; i++) {
                if (s.charAt(i) =='0') {
                    leftNums++;
                }
            }

            for (int j = left+1; j <s.length() ; j++) {
                if (s.charAt(j) =='1') {
                    rightNums++;
                }
            }



            if (maxNum<leftNums+rightNums) {
                maxNum=leftNums+rightNums;
            }
            left++;


        }




        return maxNum;



    }
}
