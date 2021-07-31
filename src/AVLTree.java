
class AVLNode{
    int val;
    AVLNode left;
    AVLNode right;
    int max_height;
    int balance;

    AVLNode(){
        val = 0;
        balance = 0;
        max_height = 0;
        left = right = null;
    }

    AVLNode(int val, AVLNode left, AVLNode right){
        this.val = val;
        this.left = left;
        this.right = right;
        computeMaxHeight();
        computeBalance();
    }

    void computeMaxHeight(){
        int max_left = (left == null ? 0 : left.max_height) + 1;
        int max_right = (right == null ? 0 : right.max_height) + 1;
        this.max_height = Math.max(max_left, max_right);
    }

    void computeBalance(){
        int leftBalance = (left == null ? 0 : left.max_height);
        int rightBalance = (right == null ? 0 : right.max_height);
        this.balance = leftBalance - rightBalance;
    }

}

public class AVLTree {
    private AVLNode root;

    private AVLNode rightRotation(AVLNode curr){
        /*
        *       x                  y
        *     y     T3   =>     T1   x
        *   T1   T2                T2  T3
        * */

        AVLNode leftNode = curr.left;
        curr.left = leftNode.right;
        leftNode.right = curr;
        curr.computeMaxHeight();
        curr.computeBalance();
        leftNode.computeMaxHeight();
        leftNode.computeBalance();
        return leftNode;
    }

    private AVLNode leftRotation(AVLNode curr){
        //TBD

        return curr;
    }

    private AVLNode reBalance(AVLNode curr){
        curr.computeMaxHeight();
        curr.computeBalance();

        if(Math.abs(curr.balance) <= 1){
            return curr;
        }

        if(curr.balance > 1){
            if(curr.left.balance < 0){
                curr.left = leftRotation(curr.left);
                return rightRotation(curr);
            }
            return rightRotation(curr);
        } else{
            if(curr.right.balance > 0){
                curr.right = rightRotation(curr.right);
                return leftRotation(curr);
            }
            return leftRotation(curr);
        }
    }

    private AVLNode _insert(AVLNode curr, int val){
        if(curr.val > val){
            if(curr.left == null){
                curr.left = new AVLNode(val, null, null);
                curr.computeMaxHeight();
                curr.computeBalance();
                return  curr;
            } else{
                AVLNode newLeft = _insert(curr.left, val);
                curr.left = newLeft;
                return reBalance(curr);
            }
        } else{
            if(curr.right == null){
                curr.right = new AVLNode(val, null, null);
                curr.computeMaxHeight();
                curr.computeBalance();
                return curr;
            } else{
                curr.right = _insert(curr.right, val);
                return reBalance(curr);
            }
        }
    }

    void insertInBST(AVLNode curr, int val){
        if(curr.val > val){
            if(curr.left == null){
                curr.left = new AVLNode(val, null, null);
            } else{
                _insert(curr.left, val);
            }
        } else{
            if(curr.right == null){
                curr.right = new AVLNode(val, null, null);
            } else{
               _insert(curr.right, val);
            }
        }
    }

    void insert(int val){
        root = _insert(root, val);
    }
}