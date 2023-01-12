import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AAAA {
    public static void main(String[] args) {
        int nums[]= {4,3,6,16,8,2};
        longestSquareStreak(nums);
    }
    static int count=0;

    static LinkedList<Integer> list=new LinkedList<>();
    public static int longestSquareStreak(int[] nums) {

        dfs(nums,0);
        return count;
    }
    public static void dfs(int[]nums,int startindex) {
        if (list.size()>=2) {


            Collections.sort(list);
            if (aa(list)) {
                if (list.size()>count) {
                    count=list.size();

                }
            }

        }

        for (int i = startindex; i < nums.length; i++) {
            list.add(nums[i]);

            dfs(nums, i+1);
            list.removeLast();
        }

    }
    public static boolean aa(List<Integer> list2) {
        for (int i = 1; i < list2.size(); i++) {
            if (list2.get(i)!=list2.get(i-1)*list2.get(i-1)) {
                return false;
            }
        }
        return true;
    }
}
