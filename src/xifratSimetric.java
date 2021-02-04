
import java.util.Random;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


class xifratSimetric {

	public static Scanner lector = new Scanner(System.in);
	
	//Variables inicialitzades
	public static int longitudClau;
	public static String textXifrar;


	SecretKey clau = crearClau(longitudClau, null);

	public static void main(String[] args){
		

		menu();// Cridem la funció de menu

	}

	public static void menu(){//Funcio per fer el menu

		System.out.println("**********");
		System.out.println("Menu: ");

		boolean sortir = false;// Inicialitzem sortir a false

		while (!sortir) {// Mentres sortir no sigui false entrara dins e imprimira el menu

			//Imprimim el menu
			System.out.println("***********************");
			System.out.println("1: Xifrar el text");
			System.out.println("2: Sortir del programa");
			System.out.println("_______________________");
			System.out.println("Tria la opcio: ");

			int menuInicial = lector.nextInt();
			lector.nextLine();

			switch (menuInicial) {// Fem el switch per fer el menu

			case 1:

				//Demanem la longitud de la clau
				System.out.println("Quina longitud de la clau vols fer servir: ");
				longitudClau = lector.nextInt();
				lector.nextLine();
				
				System.out.println("*******************************************");
				
				//Demanem el text que volem xifrar
				System.out.println("Quin text vols xifrar: ");
				textXifrar = lector.next();
				lector.nextLine();

				//Pasem les funcions amb el atributs corresponent
				encriptarECB(longitudClau, textXifrar);
				encriptarCBC(longitudClau, textXifrar);
				break;

				
			case 2:

				System.out.println("Has sortit del programa. ");
				sortir = true;// Posem true a sortir per tal de que quan triem la opcio 2 el programa finalitzi.
				break;

			}
		}


	
	}
	private static SecretKey crearClau(int longClau, String textXifrar) {//Creem la funcio SecretKey per tal de generar la clau 
		
		//Inicialitzacio de KeyGererator i SecretKey
		KeyGenerator generarClau = null;
		SecretKey secretKey = null;

		try {
			
			//Generem la clau
			generarClau = KeyGenerator.getInstance("AES");
			generarClau.init(256);
			
			secretKey = generarClau.generateKey();

		} 
		
		catch (Exception e) {

			System.out.println("Error: " + e);
			
		}

		
		return secretKey;//Retornem la SecretKey
	}
	
	public static void encriptarECB(int longClau, String textXifrar){//Funcio per encriptar en ECB
		
   
		 
		byte [] clauHex = crearClau(longClau, textXifrar).getEncoded();
		

        try {
        	
        	//Inicialitzem el Cipher amb ECB
        	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, crearClau(longClau, textXifrar));
			
			System.out.println("*********************");
			System.out.println("La teva clau Hex en ECB es: ");
			
			for(int i=0; i<clauHex.length; i++) {
				String st = String.format("%02X", i);
	            System.out.print(st);//Imprimeixes la clau Hex
			}
			
		} catch (Exception e) {
			
			System.out.println("Error: " + e);
	
		}
 
    }
	
	//Funcio per encriptar amb CBC
	public static void encriptarCBC(int longClau, String textXifrar){//Funcio per encriptar en CBC
		
		   
		//Inicialitzacio de les arrays
		byte [] clauHex = crearClau(longClau, textXifrar).getEncoded();
		byte [] array = new byte [16];
		new Random().nextBytes(array); 

        try {
        	
        	//Inicialitzem IVParameterSpec
        	IvParameterSpec ivParam = new IvParameterSpec(array);
        	
        	//Inicialitzem el Cipher amb CBC
        	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");		
			cipher.init(Cipher.ENCRYPT_MODE, crearClau(longClau, textXifrar), ivParam);
			byte [] array1 = cipher.doFinal(textXifrar.getBytes());
			
			System.out.println("*********************");
			System.out.println("La teva clau Hex en CBC es: ");
			
			for(int i=0; i<array1.length; i++) {
				String st = String.format("%02X", array1[i]);
	            System.out.print(st);//Imprimeixes la clau Hex
			}
			
		} catch (Exception e) {
			
			System.out.println("Error: " + e);
			
		}
 
    }
       
	
}
