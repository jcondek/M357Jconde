import java.security.KeyPair;
import java.security.KeyPairGenerator;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Scanner;

public class xifraAsimetric {

	public static Scanner lector = new Scanner(System.in);

	// Variables inicialitzades
	public static String textXifrar;

	static SecretKey clau = crearClauAES(null);
	static KeyPair clau1 = crearClauRSA(null);

	public static void main(String[] args) {

		menu();// Cridem la funció de menu

	}

	public static void menu() {// Funcio per fer el menu

		System.out.println("**********");
		System.out.println("Menu: ");

		boolean sortir = false;// Inicialitzem sortir a false

		while (!sortir) {// Mentres sortir no sigui false entrara dins e imprimira el menu

			// Imprimim el menu
			System.out.println("***********************");
			System.out.println("1: Xifrar el text");
			System.out.println("2: Sortir del programa");
			System.out.println("_______________________");
			System.out.println("Tria la opcio: ");

			int menuInicial = lector.nextInt();
			lector.nextLine();

			switch (menuInicial) {// Fem el switch per fer el menu

			case 1:

				// Demanem el text que volem xifrar
				System.out.println("Quin text vols xifrar: ");
				textXifrar = lector.next();
				lector.nextLine();

				// Pasem les funcions amb el atributs corresponent
				encriptarRSA(textXifrar, clau1, clau);
				encriptarAES(textXifrar, clau);
				break;

			case 2:

				System.out.println("Has sortit del programa. ");
				sortir = true;// Posem true a sortir per tal de que quan triem la opcio 2 el programa finalitzi.
								
				break;

			}
		}

	}

	private static SecretKey crearClauAES(String textXifrar) {// Creem la funcio SecretKey per tal de generar la clau
																		

		// Inicialitzacio de KeyGererator i SecretKey
		KeyGenerator generarClau = null;
		SecretKey clauAES = null;

		try {

			// Generem la clau AES
			generarClau = KeyGenerator.getInstance("AES");
			generarClau.init(128);

			clauAES = generarClau.generateKey();

		}

		catch (Exception e) {

			System.out.println("Error: " + e);

		}

		return clauAES;// Retornem la SecretKey
	}

	private static KeyPair crearClauRSA(KeyPair secretKeyRSA) {// Creem la funcio KeyPair per tal de generar la clau
																			 
		KeyPairGenerator generarClau1 = null;
		KeyPair clauRSA = null;
		
			

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

	public static void encriptarRSA(String textXifrar, KeyPair clauRSA, SecretKey clauAES) {// Funcio per encriptar en RSA

		//Guardem la clau en una array de bytes
		byte[] clauEncriptRSA = null;

		try {

			// Inicialitzem el Cipher amb RSA
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, clauRSA.getPublic());
			clauEncriptRSA = cipher.doFinal(clauAES.getEncoded());

			System.out.println("*********************");
			System.out.println("La teva clau Hex en RSA es: ");

			//Imprimim la clau en Hexadecimal de el encriptat en RSA
			for (int i = 0; i < clauEncriptRSA.length; i++) {
				String st = String.format("%02X", i);
				System.out.print(st);// Imprimeixes la clau Hex
			}

		} catch (Exception e) {

			System.out.println("Error: " + e);

		}

	}

	
	public static void encriptarAES(String textXifrar, SecretKey clauAES) {// Funcio per encriptar en ECB

		//Guardem les claus en arrays de bytes
		byte[] clauByteAES = clauAES.getEncoded();
		byte[] clauEncriptAES = null;

		try {

			// Inicialitzem el Cipher amb ECB
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, clauAES);
			clauEncriptAES = cipher.doFinal(textXifrar.getBytes());

			System.out.println("*********************");
			System.out.println("La teva clau Hex en AES es: ");

			//Imprimim la clau en Hexadecimal de el encriptat en AES
			for (int i = 0; i < clauByteAES.length; i++) {
				String st = String.format("%02X", i);
				System.out.print(st);// Imprimeixes la clau Hex
			}

		} catch (Exception e) {

			System.out.println("Error: " + e);

		}

	}
}
