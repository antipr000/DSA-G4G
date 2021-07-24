import java.net.StandardSocketOptions;
import java.util.*;
import java.util.LinkedList;

class Node{
    int val;
    Node left;
    Node right;

    Node(){
        val = 0;
        left = right = null;
    }

    Node(int val){
        this.val = val;
        left = right = null;
    }

    Node(int val, Node left, Node right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Trees {
    public static void main(String[] args) {

    }
}

class NAryTreeMethods{
    ArrayList<Integer> []tree;

    NAryTreeMethods(int n){
        tree = new ArrayList[n+1];

    }

    public void addAEdge(int u, int v){
        tree[u].add(v);
        tree[v].add(u);
    }

    public void dfs(int curr, int parent){
        for(int v: tree[curr]){
            if(v != parent){
                dfs(v, curr);
            }
        }
    }

    public int leaves(int curr, int parent){
        boolean isLeaf = true;
        int ans = 0;
        for(int v: tree[curr]){
            if(v != parent){
                isLeaf = false;
                ans += leaves(v, curr);
            }
        }

        if(isLeaf) return 1;
        return ans;
    }

    public void bfs(int root, int n){
        Queue<Integer> q = new LinkedList<>();
        boolean [] visited = new boolean[n+1];
        visited[root] = true;
        q.add(root);
        //1 -> 2 3  2-> 4 5

        while(!q.isEmpty()){
            int first = q.poll();
            for(int v: tree[first]){
                if(!visited[v]){
                    q.add(v);
                    visited[v] = true;
                }
            }
        }
    }
}


class BinaryTreeMethods{
    private Node root;

    private void preorder(Node curr){
        //CLR
        System.out.print(curr.val + " ");

        if(curr.left != null) preorder(curr.left);
        if(curr.right != null) preorder(curr.right);

    }

    private void inorder(Node curr){
        //LCR
        if(curr.left != null) inorder(curr.left);

        System.out.print(curr.val + " ");

        if(curr.right != null) inorder(curr.right);
    }

    private void postorder(Node curr){
        //LRC
        if(curr.left != null) postorder(curr.left);
        if(curr.right != null) postorder(curr.right);

        System.out.print(curr.val + " ");
    }


    private int numLeaves(Node curr){

        if(curr.left == null && curr.right == null) return 1;

        return numLeaves(curr.left) + numLeaves(curr.right);
    }

    public int countLeaves(){
        return numLeaves(root);
    }

    void treeTraversal(){
        inorder(root);
    }

    boolean isomorphicTrees(Node lcurr, Node rcurr){
        if(lcurr == null && rcurr == null) return true;
        else if(lcurr == null || rcurr == null) return false;
        return (isomorphicTrees(lcurr.left, rcurr.left) && isomorphicTrees(lcurr.right, rcurr.right)) ||
                (isomorphicTrees(lcurr.right, rcurr.left) && isomorphicTrees(lcurr.left, rcurr.right));
    }

    void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            Node curr = q.poll();
            System.out.print(curr.val + " ");
            if(curr.left != null) q.add(curr.left);
            if(curr.right != null) q.add(curr.right);
        }
    }

}