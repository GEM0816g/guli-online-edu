import org.junit.Test;

public class A1 {
    public static void main(String[] args) {
        ListNode cu1=new ListNode(8);
        ListNode cu2=new ListNode(3,cu1);
        ListNode cu3=new ListNode(13,cu2);
        ListNode cu4=new ListNode(2,cu3);
        ListNode cu5=new ListNode(5,cu4);
        removeNodes(cu5);

    }
    @Test
    public  int pivotInteger(int n) {

        int temp1=0;
        int temp2=0;
        for (int i = 1; i <= n; i++) {
            temp1+=i;

            for (int j = i; j <= n; j++) {

                temp2+=j;

            }
            if (temp1==temp2) {
                return i;
            }
            temp2=0;

        }

        return -1;
    }
    public static  class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    @Test
    public  static ListNode removeNodes(ListNode head) {
        ListNode curListNode=new ListNode(0,head);

        ListNode cur=curListNode;
        ListNode cur2=curListNode;
        while(curListNode!=null) {

            if ( curListNode.next!=null &&curListNode.next.next!=null  && curListNode.next.val<curListNode.next.next.val && curListNode.next.val!=0) {

                curListNode.next=curListNode.next.next;
                ListNode curListNode1=new ListNode(0,head);
                curListNode=curListNode1;
            }else {
                curListNode=curListNode.next;
            }


        }
        if (cur.next.val<cur.next.next.val &&cur.next.val!=0){
            cur.next=cur.next.next;
        }
        ListNode ans = cur2.next;
        return ans;
    }
}
