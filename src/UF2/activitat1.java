package UF2;

public class activitat1 implements Runnable{


	public static void main(String[] args) {
		
		thread1 t1 = new thread1(0);
		thread1 t2 = new thread1(10);
		thread1 t3 = new thread1(20);
		thread1 t4 = new thread1(30);
		thread1 t5 = new thread1(40);
		
		try {
			
			System.out.print(" Thread 1: ");
			t1.start();
			Thread.sleep(100);
			System.out.print(" \n Thread 2: ");
			t2.start();
			Thread.sleep(100);
			System.out.print(" \n Thread 3: ");
			t3.start();
			Thread.sleep(100);
			System.out.print(" \n Thread 4: ");
			t4.start();
			Thread.sleep(100);
			System.out.print(" \n Thread 5: ");
			t5.start();
			
		}
		catch (Exception e) {
			
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

