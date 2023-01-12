public class Test {
    public static void main(String[] args) {
        System.out.println(sum100());
        System.out.println(sum1001());
        System.out.println(sum1002());
        System.out.println(sum1003digui(100));
        System.out.println(sum1004dengchashulie());
    }
    /*第一种for循环*/
    public static int sum100(){
        int result = 0;
        for (int i = 1; i <= 100; i++) {
            result += i;
        }
        return  result;
    }
    /*第二种while循环*/
    public static int sum1001(){
        int result = 0;
        int i = 1;
        while (i <= 100) {
            result += i;
            i++;
        }
      return result;
    }
    /*第三种do，while循环*/
    public static int sum1002(){
        int result = 0, i = 1;
        do {
            result += i;
            i++;
        } while (i <= 100);
      return result;

    }
    /*第四种递归*/
    public static int sum1003digui(int n) {
        if(n==1) {
            return 1;
        }else {
            return n+sum1003digui(n-1);
        }
    }
    /*第五种等差数列循环*/
    public static int sum1004dengchashulie(){
        int sum = (1+100)*100 / 2;
        return sum;
    }
}
