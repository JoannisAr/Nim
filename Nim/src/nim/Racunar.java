package nim;

public class Racunar extends Igrac{
	private Igra igra;
	private int verzija; 
	private Strategija s;
	private int maxdubina;
	
	public Racunar(int id, Igra i,int verzija) {
		super(id);
		this.igra=i;
		
		maxdubina=igra.getDubina();
		switch(verzija){
		case 0:{ s=new AB(igra.getDiskovi(),verzija, igra.getPrevmove(),maxdubina);break;}
		case 1:{ s=new AlfaBeta(igra.getDiskovi(),verzija, igra.getPrevmove(),maxdubina); break;}
		case 2:{ s=new Takmicarski(igra.getDiskovi(),verzija, igra.getPrevmove(),maxdubina); break;}
		}
		
	}

	@Override
	public void igraj() {
		igra.blokirajDugme();
		igra.cekaj();
		s.igraj(igra.getPrevmove());
		igra.uzmi(s.getS(), s.getK(),false);
		
		igra.proveriStanje();
		igra.odblokirajDugme();
	}

}
