import java.util.ArrayList;
import java.util.Arrays;

public class MathematicalOperations {
    public static void main(String[] args) {
        int a = 3, b = 4;

        MathematicalImpl mpl = new MathematicalImpl();
//        System.out.println(mpl.fastExponentiation(a, b));
//        mpl.printAllDivisors(9 * 4 * 25);
//        mpl.findAllPrimes(100);
//        mpl.printPrimeFactorization(36);
        System.out.println(mpl.gcd(48,9));
        System.out.println(mpl.gcd(5,7));
        System.out.println(mpl.gcd(100,50));
    }
}

class MathematicalImpl{
    public int fastExponentiation(int a, int b, int m){
        //Finding (a^b)%m in O(logb)
        int res = 1;
        a %= m;
        while(b>0){
            if((b&1) > 0){
                res = (res * a)%m;
            }
            b>>=1; //b/=2
            a = (a*a)%m;
        }
        return res;
    }

    public void printAllDivisors(int a){
        //Finding all divisors of a number in O(sqrt(a))
        System.out.println("Printing all divisors of " + a);

        for(int i=1;i*i<=a;i++){
            if((i*i) == a){
                System.out.print(i + " ");
            }else{
                if((a%i) == 0){
                    System.out.print(i + " ");
                    System.out.print(a/i + " ");
                }
            }
        }

    }

    public void findAllPrimes(int N){
        //Finding all primes upto N in O(N)
        boolean []isPrime = new boolean[N+1];
        for(int i=2;i<=N;i++) isPrime[i] = true;
        for(int i=2; i*i<=N; i++){
            if(isPrime[i]){
                //i is a prime number. We will mark all multiples of i as composite
                for(int j=i*i;j<=N;j+=i){
                    isPrime[j] = false;

                }
            }
        }
        System.out.printf("The primes upto %d are: %n", N);
        for(int i=2; i<=N; i++){
            if(isPrime[i]) System.out.print(i + " ");
        }
        System.out.println("");
    }

    public void printPrimeFactorization(int N){
        //Prime factorization construction in O(N) and finding prime factors in O(logN)
        int []spf = new int[N+1]; //Smallest prime factor
        for(int i=0;i<=N;i++) spf[i] = i;
        for(int i=2; i*i<=N; i++){
            if(spf[i] == i){
                //i is a prime number. We will mark all multiples of i as composite
                for(int j=i*i;j<=N;j+=i){
                    spf[j] = Math.min(spf[j], i);
                }
            }
        }
        System.out.println("SPF Array: " + Arrays.toString(spf));
        System.out.println("Prime factorization of " + N + " is:");
        while(N>1){
            System.out.print(spf[N] + " ");
            N = N/spf[N];
        }

        System.out.println("");

    }

    int gcd(int a, int b){
        if(b==0) return  a;

        return gcd(b, a%b);
    }
}