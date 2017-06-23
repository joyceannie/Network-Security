

public class rc4KeyGeneration {
	/**
	 * RC4 key generation algorithm
	 */
	private int[] S;//array to generate subkeys
	private int[] K;//encryption key
	public rc4KeyGeneration(int l){
		K=new int[l];
		S=new int[256];
	}
	public rc4KeyGeneration(int[] num){
		K=num;
		S=new int[256];
	}
	private void KSA(){
		for (int i=0;i<256;i++){
			S[i]=i;
		}
		int j=0;
		for (int i=0;i<256;i++){
			j=(j+S[i]+K[i%K.length])%256;
			swap(i,j);
			
		}
	}
	private void SGA(){
		int i=0,j=0,u=0;
		for (u=0;u<20;u++){
			i=(i+1)%256;
			j=(j+S[i])%256;
			swap(i,j);
			int key=S[(S[i]+S[j])%256];
			System.out.print(key+ " ");
		}
		System.out.println();
	}	
	private void swap(int a,int b){
		int temp =S[a];
		S[a]=S[b];
		S[b]=temp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Case 0:Initial Key Vector:256");
		rc4KeyGeneration rc4Obj1 = new rc4KeyGeneration(256);
		System.out.print("Generated key stream : ");
		rc4Obj1.KSA();
		rc4Obj1.SGA();
		int[] num = {15,202,33,6,8};
		System.out.println();
		System.out.print("Case 1:Initial Key Vector : ");
		for (int i=0;i<num.length;i++){
			System.out.print(num[i]+" ");
		}
		System.out.println();
		rc4KeyGeneration rc4Obj2 = new rc4KeyGeneration(num);
		System.out.print("Generated key stream : ");
		rc4Obj2.KSA();
		rc4Obj2.SGA();		
	}

}
