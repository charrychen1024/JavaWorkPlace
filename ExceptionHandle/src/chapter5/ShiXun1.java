package chapter5;

public class ShiXun1 {
	public static void main(String[] args){
		int[] a = {10,20,30,40,50};
		int[] b = {10,2,30};
		try{
			for(int i = 0;i <= 4;i++){
				System.out.println("a["+i+"]/b["+i+"] = "+(a[i]/b[i]));
			}
		}catch(ArithmeticException e){
			System.out.println("The divisor cannot be 0!");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Array index out of bounds!");
		}finally{
			System.out.println("Finshed!");
		}
		
		
	}

}
