package UF2;


public class Productor extends Thread{
	
	int num = 0;
	Contenidor conten;

	
	public Productor(Contenidor conten) {
		
		this.conten = conten;
		
	}
	
	public void run() {
		
		
		for(int i=0; i<1000; i++) {
			
			num = ((2*i)+1);
			conten.agafar(num);
			
		}
	}


	


}
