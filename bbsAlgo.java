import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class bbsAlgo {
	/**
	 * Given two prime numbers p and q, the algorithm generates p*q-1 pseudo random bits and finds the cycle length.
	 */ 
	BigInteger p;
	BigInteger q;
		
	public bbsAlgo(BigInteger a, BigInteger b){
		this.p = a;
		this.q = b;
	}

	private BigInteger euclidean(BigInteger p,BigInteger q){
		//finding gcd
		BigInteger a = p;
		BigInteger b = q;
		while (b.intValue()!=0){
			BigInteger temp = b;
			b = a.mod(b);
			a = temp;
		}
		return a;
	}
	
	private void bbs(){
		System.out.println("Running BBS algorithm for p = "+p + " and "+q);
		BigInteger n = p.multiply(q);
		Set<BigInteger> seedSet = new HashSet<BigInteger>();
		//finding s values
		for (int i=1;i<=((n).subtract(BigInteger.valueOf(1)).divide(BigInteger.valueOf(2))).intValue();i++){
			BigInteger gcd = euclidean(BigInteger.valueOf(i),n);
			if (gcd.compareTo(BigInteger.valueOf(1))==0){
				seedSet.add(BigInteger.valueOf(i));
			}
		}
		Object[] seedArray =  seedSet.toArray();
		Set<BigInteger> firstX = new HashSet<BigInteger>();
		Set<Set<BigInteger>> finalSequence = new HashSet<Set<BigInteger>>();
		List<List<BigInteger>> finalList = new ArrayList<List<BigInteger>>();
		for (int i=0;i<seedArray.length;i++){
			BigInteger x0;
			
			x0 = BigInteger.valueOf(Integer.parseInt(seedArray[i].toString()));
			x0 = x0.multiply(x0).mod(n);
			
			if (!firstX.contains(x0)){
				firstX.add(x0);
			}
			
		}		
		//For each x0 in firstX, find the partialSequence and add it to finalSequence
		Object X0Values[] = firstX.toArray();	
		for (int i=0;i<X0Values.length;i++){
			BigInteger currentX = BigInteger.valueOf(Integer.parseInt(X0Values[i].toString()));
			currentX = currentX.multiply(currentX).mod(n);
			Set<BigInteger> partialSequence = new HashSet<BigInteger>();
			List<BigInteger> partialList = new ArrayList<BigInteger>();
			String bits = "";
			for (int j=0;j<n.intValue();j++){
				if (!partialSequence.contains(currentX)){
					partialSequence.add(currentX);
					partialList.add(currentX);
				}
				currentX = currentX.multiply(currentX).mod(n);
				int bit = currentX.mod(BigInteger.valueOf(2)).intValue();
				if (bits.length()<20){
					bits = bits + String.valueOf(bit) ;
				}
						
			}
			System.out.print("Cycle for "+X0Values[i]+" -> ");
			for (BigInteger b:partialList){
			System.out.print(b+ " ");
			}
			System.out.println();
			System.out.println("Cycle Length = "+partialList.size());
			System.out.println("First 20 bits are " + bits  );
			//add partial sequence to final sequence
			boolean isAdded = finalSequence.add(new HashSet<BigInteger>(partialSequence));
			if (isAdded){
				finalList.add(new ArrayList<BigInteger>(partialList));
			}
		}
		System.out.println("Number of distinct cycles = "+finalSequence.size());
		System.out.println("Distinct cycles are ");
		for (List partial: finalList){
			for (Object b:partial){
				System.out.print(b+" ");
			}
			System.out.println();
		}		
			
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		bbsAlgo obj1 = new bbsAlgo(BigInteger.valueOf(3),BigInteger.valueOf(7));
		obj1.bbs();
		System.out.println("-----------------------------------------------");
		bbsAlgo obj2 = new bbsAlgo(BigInteger.valueOf(7),BigInteger.valueOf(11));
		obj2.bbs();
		System.out.println("-----------------------------------------------");
		bbsAlgo obj3 = new bbsAlgo(BigInteger.valueOf(19),BigInteger.valueOf(23));
		obj3.bbs();
	}

}
