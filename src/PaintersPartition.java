import java.util.*;

public class PaintersPartition {

    public static void main(String []args){
            Scanner sc = new Scanner(System.in);
            int A = sc.nextInt();
            int B = sc.nextInt();

            int N = sc.nextInt();
            ArrayList<Integer> C = new ArrayList<>();
            for(int i=0;i<N;i++){
                int C_i = sc.nextInt();
                C.add(C_i);
            }
            Solution sol = new Solution();
            System.out.println(sol.minTime(A, B, C));
            sc.close();
    }
}

class Solution{
    ArrayList<Integer> C;

    public int minTime(int A, int B, ArrayList<Integer> C){

        this.C = new ArrayList<>();

        for (Integer integer : C) {
            this.C.add(B * integer); //We change the C array to hold time taken to paint each board
        }

        int lo = Collections.max(this.C); //Min time will be when each board is assigned to different worker

        int hi = this.C.stream().mapToInt(i->i).sum(); //Max time will be when there is one worker

        if(predicate(lo, A)) return lo;

        while(lo < hi-1){
            int mid = (lo + hi)/2;
            if(predicate(mid, A)) hi = mid;
            else lo = mid;
        }

        return hi;
    }

    /*
    *  The predicate function returns true if the number of workers needed to paint all the boards is less than equals A
    *  such that maximum time each worker works is max_time
    * */
    private boolean predicate(int max_time, int A){
        int num_workers = numWorkers(max_time);
        return num_workers <= A;
    }

    /*
    * numWorkers returns the minimum no of workers needed to paint all boards such that no worker works more than
    * max_time
    * */
    private int numWorkers(int max_time){
        //We do a greedy job allotment. Try giving the current worker as much as we can and the use a new worker.
        int num_worker = 1;
        int curr_time = 0;
        for (Integer integer : this.C) {
            if ((curr_time + integer) <= max_time) {
                //Current worker can paint this board
                curr_time += integer;
            } else {
                //Current worker cannot take this board. We need to use a new worker
                num_worker += 1;
                curr_time = integer; //The new worker will paint this board
            }
        }
        return num_worker;
    }
}
