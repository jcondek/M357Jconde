package UF2;

import java.util.ArrayList;


public class Contenidor extends Thread{

	ArrayList <Integer> numTotal = new ArrayList <Integer>();
	int numRetard;
	

	public synchronized int treure() {
		if(numTotal.isEmpty()) {
			
			try {
				
				wait();
				
			}
			catch(Exception e) {
				
				System.out.println("Error: " + e);
				
			}
		}
		
		numRetard = numTotal.get(0);
		numTotal.remove(0);
		notify();
		return numRetard;
		
	}
	
	public synchronized void agafar(int num) {
		
		
		numTotal.add(num);
		notify();
		
	}
	
	public ArrayList<Integer> getNumTotal(){
		
		return numTotal;
		
		
	}
	
	public void setNumTotal (ArrayList<Integer> numTotal){
		
		this.numTotal = numTotal;
		
	}
	
	

}
