package UF2;

public class activitat2 implements Runnable {

	private int [] contador = new int[50];
	
	public static void main(String[] args) {

		thread2 t1 = new thread2(1);
		thread2 t2 = new thread2(2);
		thread2 t3 = new thread2(3);
		thread2 t4 = new thread2(4);
		thread2 t5 = new thread2(5);
		
		try {

		
			t1.start();
			Thread.sleep(100);
			t2.start();
			Thread.sleep(100);
			t3.start();
			Thread.sleep(100);
			t4.start();
			Thread.sleep(100);
			t5.start();

		} catch (Exception e) {

		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
