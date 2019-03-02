package nim;

public abstract class Strategija {
	protected int [] diskovi;
	protected int verzija;
	protected int maxdubina;
	//protected int prevmove;
	protected int s;
	protected int k;
	
	public Strategija(int[] diskovi, int verzija, int prevmove,int maxdubina){
		this.diskovi=diskovi; 
		this.verzija=verzija;
		//this.prevmove=prevmove;
		this.maxdubina=maxdubina;
	}
	
	public abstract void igraj(int prevmove);
	public int getS(){return s;}
	public int getK(){return k;}
	
	protected int max(int a, int b){		//vraca onaj koji je veci ako su isti vraca drugi
		if(a>=b)return a;
		else return b;
	}
	protected int min(int a, int b){
		if(a<=b)return a;
		else return b;
	}
	
	protected int heuristic(int[] diskovi){
		int h=0;
		for (int i = 0; i < diskovi.length; i++) {
			h^=diskovi[i];
		}
		return h;
	}
	protected int isTerminal(int[] diskovi){
		int t=1;
		for (int i = 0; i < diskovi.length; i++) {
			if(diskovi[i]!=0){t=0; break;}
		}
		
		return t;
	}
}
