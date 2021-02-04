import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class jCondeAct2 {

	Scanner lector = new Scanner(System.in);// Inicialitzacio de Scanner

	public static void main(String[] args) {

		//Inicialització de variables
		int longClau = demanarNom();
		SecretKey clau = crearClau(longClau);

		mostrarClauByte(clau);//Imprimeix la clau en Bytes
		mostrarClauBase64(clau);//Imprimeix la clau en Base64
		mostrarClauBinari(clau);//Imprimeix la clau en Binari
		mostrarHex(clau);//Imprimeix la clau en Hex

	}

	private static void mostrarClauBinari(SecretKey clau) {
		
		int i = 0;
		byte [] clauBinari = clau.getEncoded();//Mostra l'array 
		
		System.out.println("***************************");
		System.out.println("La teva clau en Binari es: ");
		
		//Recorrem tota l'array per tal d'imprimirles
		for(i=0; i<clauBinari.length; i++) {
			
			System.out.println(Integer.toBinaryString(clauBinari[i]));	//Imprimeixes l'array de la clau en binari
		}	
	}

	private static void mostrarClauBase64(SecretKey clau) {
		
		byte [] clau64 = clau.getEncoded();
		byte[] encodedBytes = Base64.getEncoder().encode(clau64);
		
		System.out.println("**************************");
		System.out.println("La teva clau en Base64 es:");
		System.out.println(new String(encodedBytes)); //Imprimeixes la clau en Base64
		
	}

	private static void mostrarHex(SecretKey clau) {
		
		int i = 0;
		byte [] clauHex = clau.getEncoded();
		
		System.out.println("*********************");
		System.out.println("La teva clau Hex es: ");
		
		for(byte b : clauHex) {
			String st = String.format("%02X", b);
            System.out.print(st);//Imprimeixes la clau Hex
		}

	}

	private static void mostrarClauByte(SecretKey clau) {
		
		int i = 0;
		byte [] clauByte = clau.getEncoded();
		
		System.out.println("**************************");
		System.out.println("La teva clau en bytes es: ");
		
		//Recorrem tota l'array per tal d'imprimirles
		for(i=0; i<clauByte.length; i++) {
			
			System.out.println(clauByte[i]); //Imprimeixes l'array de la clau byte
			
		}
		
		
	}

	private static SecretKey crearClau(int longClau) {
		
		//Inicialitzacio de KeyGererator i SecretKey
		KeyGenerator generarClau = null;
		SecretKey secretKey = null;

		try {
			
			generarClau = KeyGenerator.getInstance("AES");
			generarClau.init(256);
			
			secretKey = generarClau.generateKey();

		} 
		
		catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		
		return secretKey;
	}

	private static int demanarNom() {
		Scanner lector = new Scanner(System.in);
		
		//Inicialitzacio de la variable
		int nom = 0;

		System.out.println("Introdueix el long de la clau desitjada: ");//Et demana el long que vols introduir

		boolean tipusCorrecte = lector.hasNextInt();

		if (tipusCorrecte) {

			nom = lector.nextInt();
			lector.nextLine();
			
		} 
		
		else {

			System.out.println("Clau incorrecta");
			lector.nextLine();
			nom = demanarNom();
			
		}

		return nom;

	}
}
