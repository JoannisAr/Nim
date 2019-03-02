package nim;

public class Covek extends Igrac{

	public Covek(int id) {
		super(id);
		
	}

	@Override
	public synchronized void igraj() {
		try {
			wait();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public synchronized void signaliziraj(){
		notifyAll();
	}

}
