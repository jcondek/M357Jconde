import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
//import java.util.zip.*;

public class JCondePractica {

	//Declaracio de variables
	private static String ruta_keystore = "/crypto/KeyStoreAES.jks";
	private static String password_keystore = "123456";
	private static String clau = "AES";
	private static String password_clau = "123456";
	
	
	public static void main(String[] args) throws Exception {
		KeyStore keyStore = createKeyStore(ruta_keystore, password_keystore);
		
		//Generem la clau
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");//Metode que crea l'objecte keygenerator de tipus AES
		keyGen.init(128);//Metode on iniciem l'objecte de tamany 128
		SecretKey secretKey = keyGen.generateKey();//Guardem la clau generada a la variable de tipus SecretKey
		
		//Imprimim la clau abans de guardar-la al keystore
		byte[] convBytesk = secretKey.getEncoded();//Guarda la clau secretKey en un array de bytes
		StringBuffer sb = new StringBuffer();//Creem un stringBuffer on li posarem la clau dins per poder mostrar-la per pantalla
		for(int i=0; i<convBytesk.length; i++) {
			sb.append(Integer.toHexString((convBytesk[i] & 0xFF) | 0x100).substring(1, 3));
			
		}
		System.out.println("La clau original es: " + sb);
		
		//Guardem la clau al keystore
		KeyStore.SecretKeyEntry keyStoreEntry = new KeyStore.SecretKeyEntry(secretKey);//Crea una classe on esta la secretkey que volem posar en el keystore
		KeyStore.PasswordProtection keyPassword = new KeyStore.PasswordProtection(password_clau.toCharArray());//Crea una classe on hi ha la password que li posarem a la clau
		keyStore.setEntry(clau, keyStoreEntry, keyPassword);//Metode que guarda la clau en el magatzem 
		
		
		//Recuperem la clau al magatzem
		KeyStore.Entry entry = keyStore.getEntry(clau, keyPassword);//Busca una entrada en el keystore 
		SecretKey keyFound =((KeyStore.SecretKeyEntry) entry).getSecretKey();//Guarda la contrasenya en una altre variable SecretKey utilitzan el metode anterior
		
		//Imprimim la clau Recuperada
		byte[] convByte = keyFound.getEncoded();
		StringBuffer sbguardada = new StringBuffer();
		
		for(int i=0; i<convByte.length; i++) {
			
			sbguardada.append(Integer.toHexString((convByte[i] & 0xFF) | 0x100).substring(1, 3));
			
		}
		System.out.println("La clau recuperada es: " + sbguardada);
		System.out.println("------------------------------------");
		encriptar(keyFound);
		desencriptar(keyFound);

		}
	
	
	//Funció per crear el KeyStore
		private static KeyStore createKeyStore(String nomFile, String pw) throws Exception{
			
			KeyStore keyStore = KeyStore.getInstance("JCEKS");//Metode que crea el objecte keystore de tipus JCEKS
			keyStore.load(null, null);//Metode que carga el keystore que hem creat amb la secuencia de entrada donada
			keyStore.store(new FileOutputStream(nomFile), pw.toCharArray());//Metode que guarda el keystore en una ruta especificada i amb una contrasenya tambe especificada
			
			return keyStore;
		}
		
	public static void encriptar(SecretKey keyFound) throws Exception {
		try {

			//Llegim el text del arxiu prova.txt
			String text = "";
			FileReader entrada = new FileReader("/crypto/prova.txt");

			int c = 0;

			while (c != -1) {
				c = entrada.read();
				char letra = (char) c;
				text += letra;
			}
			entrada.close();

			//Encriptem amb la clau recuperada al magatzem
			byte[] textEnBytes = text.getBytes();//Guardem el text del arxiu prova.txt en un array de Bytes
			Cipher cip = Cipher.getInstance("AES/ECB/PKCS5Padding");//creem un objecte de tipus cipher
			cip.init(Cipher.ENCRYPT_MODE, keyFound);//Inicialitzem la variable cip encriptan la clau trobada
			byte[] encripted = cip.doFinal(textEnBytes);

			StringBuilder sb_ecb = new StringBuilder();

			for (int i = 0; i < encripted.length; i++) {
				
				sb_ecb.append(Integer.toString((encripted[i] & 0xff) + 0x100, 16).substring(1));
				
			}

			//Imprimim el misatge encriptat al arxiu Encrypt.txt
			
			FileOutputStream fos = new FileOutputStream("/crypto/Encrypt.txt");
			fos.write(encripted);

		} 
		
		catch (Exception e) {
			
			System.out.println("Error " + e);
			
		}

	}
	
	public static void desencriptar(SecretKey keyFound) throws Exception {

		//Llegim el arxiu amb el misatge encriptat i el pasem a bytes
		byte[] bFile = Files.readAllBytes(Paths.get("/crypto/Encrypt.txt"));//Guardem el misatge en una array de bytes

		//Desencriptem
		Cipher cip = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cip.init(Cipher.DECRYPT_MODE, keyFound, cip.getParameters());//Inicialitzem la variable cip per desencriptar la clau trobada
		byte[] missatgBytes = cip.doFinal(bFile);//Guardem els bytes del missatge en un array de bytes
		String descifrar = new String(missatgBytes,"UTF-8");//Passem els bytes a String i els guardem en la variable descifrar

		//Creem l'arxiu Decrypt.txt e imprimim el missatge de desencriptat
		FileWriter fileWriter = new FileWriter("/crypto/Decrypt.txt");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(descifrar);
		printWriter.close();

	}

}
