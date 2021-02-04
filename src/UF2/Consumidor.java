package UF2;

public class Consumidor extends Thread{

	Contenidor conten;
	int num;
	boolean numPrimer;


	
	public Consumidor(Contenidor conten) {
		
		this.conten = conten;
		
	}
	
	public void run() {
		
		for(int i=0; i<1000; i++) {
			
			num = conten.treure();
			
			System.out.println(num);
			
			if(primer(num)) {
				
				System.out.println("Aquest numero es primer: " + num);
			}
			
			
		}
	}
	
	boolean primer(int num) {
		
		int contador = 2;
		
		  boolean primer1 = true;
		  
		  while ((primer1) && (contador!=num)){
			  
		    if (num % contador == 0) {
		    	
		    	primer1 = false;
			    contador++;
		    } 
		  }
		  
		  return primer1;
		}
			
			
		
		
	}

