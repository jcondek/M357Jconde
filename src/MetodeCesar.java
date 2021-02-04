import java.util.Scanner;

public class MetodeCesar {
	
	static Scanner lector = new Scanner(System.in);

	public static void main(String[] args) {

		menu();

	}

	public static void mostrarDades(String text) {
		System.out.println("Text: " + text + "");

	}

	public static void menu() {
		
		Scanner lector = new Scanner(System.in); //Inicialitzem el scanner
		
		System.out.println("Menu:");
		
		boolean sortir = false;
		
		while (!sortir) {
			
			System.out.println("***********************");
			System.out.println("1: Xifrar el text ");
			System.out.println("2: Desxifrar el text ");
			System.out.println("3: Sortir del programa ");
			System.out.println("_______________________");
			System.out.println("Tria la opcio: ");
			
			int menuInicial = lector.nextInt();
			lector.nextLine();
			switch (menuInicial) {

			case 1:
				
				String dada1=demanarDades();
				String dada2=xifrar(dada1);
				mostrarDades(dada2);
				break;

			case 2:
				
				String dada3=demanarDades();
				String dada4=desxifrar(dada3);
				mostrarDades(dada4);
				break;
				
			case 3:
				
				System.out.print("Has sortit del programa ");
				sortir = true; //Posem true perque pugui finalitzar el programa
				break;

			}
		}

	}

	public static String xifrar(String dades) {

		String operacio = "";
		
		char[] text = dades.toCharArray();

		for (int i = 0; i < text.length; i++) {
			operacio = operacio + ((char) (text[i] + 3));
		}
		return operacio;
	}

	public static String desxifrar(String dades) {
		
		String operacio = "";
		
		char[] text = dades.toCharArray();

		for (int i = 0; i < text.length; i++) {
			operacio = operacio + ((char) (text[i] - 3));
		}
		return operacio;
	}

	public static String demanarDades() {
		
		String text = "";

		System.out.println("Introdueix text per teclat: ");
		text = lector.next();
		lector.nextLine();

		return text;

	}

}