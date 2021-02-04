import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;
import java.security.Signature;


public class signatura {
	
	//Declaració de les variables
	public static String text = "";
	public static Signature signar = null;
	public static KeyPairGenerator generarClau1 = null;
	public static KeyPair clauRSA = null;
	public static byte[] textByte;
	public static byte[] signar1;
	public static byte[] byteCodi;
	public static KeyPair clau = crearClauRSA(null);
	public static SecureRandom clau1 = crearSignatura(null, null, null);

	public static void main(String[] args) {
		
		//Cridem al menu
		menu();
	}
	public static void menu() {// Funcio per fer el menu
		
		Scanner lector = new Scanner(System.in);

		System.out.println("**********");
		System.out.println("Menu: ");

		boolean sortir = false;// Inicialitzem sortir a false

		while (!sortir) {// Mentres sortir no sigui false entrara dins e imprimira el menu

			// Imprimim el menu
			System.out.println("***********************");
			System.out.println("1: Signar");
			System.out.println("2: Sortir del programa");
			System.out.println("_______________________");
			System.out.println("Tria la opcio: ");

			int menuInicial = lector.nextInt();
			lector.nextLine();

			switch (menuInicial) {// Fem el switch per fer el menu

			case 1:

				
				// Demanem el text que volem xifrar
				System.out.println("Quin text vols xifrar: ");
				text = lector.next();
				lector.nextLine();
				
				//Cridem les 2 funcions
				crearClauRSA(clau);
				crearSignatura(clau1, clau, text);
				
				break;

			case 2:

				System.out.println("Has sortit del programa. ");
				sortir = true;// Posem true a sortir per tal de que quan triem la opcio 2 el programa finalitzi.
								
				break;

			}
		}

	}
	
	private static KeyPair crearClauRSA(KeyPair secretKeyRSA) {// Creem la funcio KeyPair per tal de generar la clau
		 
		

		try {

			// Generem la clau RSA
			generarClau1 = KeyPairGenerator.getInstance("RSA");
			generarClau1.initialize(2048);

			clauRSA = generarClau1.generateKeyPair();
			
		}

		catch (Exception e) {

			System.out.println("Error: " + e);

		}

		
		return clauRSA;// Retornem la KeyPair
	}
	
	private static SecureRandom crearSignatura(SecureRandom signatura, KeyPair clauRSA, String text) {
	
		try {
		
			//Inicialitzem la signatura
			signar = Signature.getInstance("SHA1withRSA");
			signar.initSign(clauRSA.getPrivate(), signatura);
			
			//Afegim les dades
			textByte = text.getBytes();
			signar.update(textByte);
			
			//Signem les dades	
			signar1 = signar.sign();
			byteCodi = Base64.getEncoder().encode(signar1);
			System.out.println(new String(byteCodi));
			
			//Verifiquem les dades
			signar.initVerify(clauRSA.getPublic());
			signar.update(textByte);
			
			boolean trobat = signar.verify(signar1);
			
			if(!trobat) {//Si no el troba imprimeix el seguent
				
				System.out.println("La signatura no s'ha validat. ");
				
			}
			
			else {//Si el troba imprimeix el seguent
				
				System.out.println("La signatura s'ha validat. ");
			}
			
		}
		
		catch(Exception e) {
			
			System.out.println("Error: " + e);
		}
		
		return signatura;//Retorna el SecureRandom

		
	}

}
