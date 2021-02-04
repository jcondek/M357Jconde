package UF2;

public class Main{

	public static void main(String[] args) {

		Contenidor conten = new Contenidor();
		Productor produ = new Productor(conten);
		Consumidor consum = new Consumidor(conten);
		
	
		produ.start();
		consum.start();
	}

	

	
}
