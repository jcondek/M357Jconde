package UF2;

public class thread2 extends Thread {



	public thread2(int[] contador) {

		this.contador = contador;
	}

	@Override
	public void run() {
		try {

			for (int i = 0; i < contador.length; i++) {

				System.out.print(contador + " - ");
			}
		} catch (Exception e) {

		}
	}
}
