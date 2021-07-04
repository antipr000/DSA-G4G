public class LinkedList {
    public static void main(String[] args) {
        Node head = new Node();
        head.data = 10;

        Node curr = head;
        for(int i=1;i<10;i++){
            curr.next = new Node(i*10+15, null);
            curr = curr.next;
        }

        LinkedListOperations ops = new LinkedListOperations();

        ops.print(head);
        System.out.println(ops.length(head));

        Node reversedHead = ops.reverse(head);

        ops.print(reversedHead);

        System.out.println(ops.detectCycle(reversedHead));

        curr = reversedHead;
        while(curr.next != null) curr = curr.next;

        curr.next = reversedHead.next;

        System.out.println(ops.detectCycle(reversedHead));

    }
}

class LinkedListOperations{
    public void print(Node head){
        System.out.println("==================List is====================");
        while(head != null){
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println("");
    }

    public Node reverse(Node head){
        Node curr = head;
        head = head.next;
        curr.next = null;
        while(head != null){
            Node nextNode = head.next;
            head.next = curr;
            curr = head;
            head = nextNode;
        }
        return curr;
    }

    public int length(Node head){
        int len = 0;
        while(head != null){
            len ++;
            head = head.next;
        }
        return len;
    }

    public boolean detectCycle(Node head){
        Node slowPtr = head, fastPtr = head;
        slowPtr = slowPtr.next;
        if(fastPtr.next == null) return false;
        fastPtr = fastPtr.next.next;

        while (slowPtr != null && fastPtr != null){
            if(slowPtr == fastPtr) return true;
            slowPtr = slowPtr.next;
            if(fastPtr.next == null) return false;
            fastPtr = fastPtr.next.next;
        }
        return false;
    }
}

class Node{
    int data;
    Node next;
    Node(){
        data = 0;
        next = null;
    }

    Node(int val, Node nextNode){
        data = val;
        next = nextNode;
    }
}