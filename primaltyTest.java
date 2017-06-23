import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class primaltyTest {

	/**
	 * For a given integer N, the program computes sieve of Eratosthenes.
   Using Miller-Selfridge-Rabin's primalty test, all the problable primes between 3 and N are computed.. 
   Comparing the two lists, all probable prime which are composite are identified. 
	 */
	private boolean[] sieveofEratosthenes(int n){
		boolean [] isPrime = new boolean[n+1];
		for (int i=0;i<n;i++){
			isPrime[i]=true;
		}
		int j=1;
		while (j*j<=n){
			j=j+1;
			if (isPrime[j]==true){
				for (int i=j*2;i<=n;i=i+j){
					isPrime[i]=false;
				}
			}
		}
		return isPrime;
	}
	
	private boolean millerRabinTest(int n){
		if (n%2==0){
			return false;
		}
		int q=n-1;
		int k=0;
		int a=2;
		while (q%2==0){
			q=q/2;
			k++;
		}
		double c=Math.pow(a,q)%n;
		if (c==1){
			return true;
		}
		for (int j=0;j<k;j++){
			int val = (int) Math.pow(c,Math.pow(2,j));
			if (val%n==n-1){
				return true;
			}
		}
		return false;
	}
	
	public static List<Integer> findFactors(int num){
		List<Integer> factors = new ArrayList<Integer>();
		int factor = 1;
		while (factor <num){
			if (num%factor == 0){
				factors.add(factor);
				}
			factor++;
		}
		return factors;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		primaltyTest obj = new primaltyTest();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter value of n");
		int n=scanner.nextInt();
		boolean[] sieve = obj.sieveofEratosthenes(n);
		boolean[] isProbablePrime=new boolean[n+1];
		for (int i=2;i<=n;i++){
			 isProbablePrime[i]=obj.millerRabinTest(i);
		}
		int count=1;

		List<Integer> probablePrimes = new ArrayList<Integer>();
		for (int i=3;i<n+1;i++){
			if (isProbablePrime[i]==true && sieve[i]==false){		
				probablePrimes.add(i);
			}
			if (sieve[i]){
				count++;
			}
		}
		System.out.println("Number of primes = "+count);
		if (probablePrimes.size()==0){
			System.out.println("No probable primes");
		}
		else{
			System.out.println("probable primes are:");
			for (Integer num:probablePrimes){
				System.out.println(num);
			}
			System.out.println("Factors of probable primes are: ");
			for (Integer num:probablePrimes){
				List<Integer> factors = findFactors(num);
				System.out.print(num+" -> ");
				for (int i=0;i<factors.size()-1;i++){
					System.out.print(factors.get(i)+" ,");
				}
				System.out.println(factors.get(factors.size()-1));
			}
		}
	}

}
