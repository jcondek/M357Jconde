package UF2;

public class thread1 extends Thread{

private int contador;
	
	public thread1 (int contador){
		
		this.contador = contador;
	}

	
	@Override
	public void run() {
		try {
			
			for(int i=0;i<10;i++) {
				
				System.out.print(this.contador++ + " - ");
			}
		}
		catch (Exception e) {
			
		}
		

	}	
}
