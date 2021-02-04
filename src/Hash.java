import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Hash {

	public static void main(String[] args) {
		
		menu();//Cridem a la funcio menu dins del main 
		
 
	}
	
	public static void mostrarDades(String dada2, String dada3) {
		System.out.println("Text md5: " + dada2 + "");//Imprimeix el codi md5
		System.out.println("Text sha256: " + dada3 + "");//Imprimeix el codi sha-256
		

	}

	public static void menu() {
		
		Scanner lector = new Scanner(System.in);//Inicialitzem el Scanner
		
		System.out.println("Menu: ");
		
		boolean sortir = false;//Inicialitzem sortir a false
		
		while(!sortir) {//Mentres sortir no sigui false entrara dins e imprimira el menu
			
			System.out.println("***********************");
			System.out.println("1: Xifrar el text ");
			System.out.println("2: Sortir del programa ");
			System.out.println("_______________________");
			System.out.println("Tria la opcio: ");
			
			int menuInicial = lector.nextInt();
			lector.nextLine();
			
			switch(menuInicial) {//Fem el switch per fer el menu
			
			
			case 1:
				
				String dada2=md5();//Li retornem la operacio que hem fet a la funcio md5();
				String dada3=sha256();//Li retornem la operacio que hem fet a la funcio sha256();
				mostrarDades(dada2, dada3);//Mostra les dades que imprimim a la funcio mostrarDades	
				break;
				
			case 2:
				
				System.out.println("Has sortit del programa. ");
				sortir = true;//Posem true a sortir per tal de que quan triem la opcio 2 el programa finalitzi.
				break;
				
				
			}
		}
	}
	
	
	private static String md5() {
		
		MessageDigest missatge;//Declarem el MessageDigest
		StringBuffer stringBuffer = new StringBuffer();
		
        try {
        	
            missatge = MessageDigest.getInstance("MD5");//Inicialitzem el MessageDigest
            missatge.update(demanarDades().getBytes());
            byte[] MD5 = missatge.digest();
            
            for (byte bytes : MD5) {
            	
                stringBuffer.append(String.format("%02x", bytes & 0xff));
                
            }
 
        } 
        
        catch (NoSuchAlgorithmException exception) {
        	
            exception.printStackTrace();
            
        }
		
		return stringBuffer.toString();//Retornem el valor de lo que hi ha guardat en el StringBuffer
	}
	
	private static String sha256() {
		
		MessageDigest missatge;//Declarem el MessageDigest
		StringBuffer stringBuffer = new StringBuffer();
		
        try {
        	
            missatge = MessageDigest.getInstance("SHA-256");//Inicialitzem el MessageDigest
            missatge.update(demanarDades().getBytes());
            byte[] MD5 = missatge.digest();
            
            for (byte bytes : MD5) {
            	
                stringBuffer.append(String.format("%02x", bytes & 0xff));
                
            }
 
        } 
        
        catch (NoSuchAlgorithmException exception) {
        	
            exception.printStackTrace();
            
        }
		
		return stringBuffer.toString();//Retornem el valor de lo que hi ha guardat en el StringBuffer
		
	}
	
	
	
	public static String demanarDades() {
		Scanner lector = new Scanner(System.in);

		System.out.println("Introdueix text per teclat: ");//Escribim el text que volem xifrar
		String text = lector.next();
		lector.nextLine();

		return text;//Retornem el text que em escrit per pantalla

	}
}
