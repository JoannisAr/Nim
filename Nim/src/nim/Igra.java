package nim;
import java.awt.*;
public class Igra extends Thread{
	private Tabla tabla;
	private Igrac[] igraci=new Igrac[2];
	private int rezim;
	private int verzija;
	private int verzija2;
	private int tekIgrac=0;
	private int dubina;
	
	public Igra(int rezim, String stubovi, String diskovi, String dubina, int verzija,int verzija2 ){
		tabla=new Tabla(Integer.parseInt(stubovi), diskovi,this);
		tabla.setStanje("Na potezu je igrac 0");
		this.rezim=rezim;
		this.verzija=verzija;
		this.verzija2=verzija2;
		this.dubina=Integer.parseInt(dubina);
		dodajIgrace();
	}
	public Igra(int rezim, String stubovi, String diskovi){
		tabla=new Tabla(Integer.parseInt(stubovi), diskovi,this);
		this.rezim=rezim;
		dodajIgrace();
	}
	
	public void run(){
		tabla.setStanje("Na potezu je igrac 0");
		while(!interrupted()){
			igraci[tekIgrac].igraj();
			tabla.proveriStanje();
			tekIgrac=1-tekIgrac;
		}
		
	}
	
	public int getTekIgrac(){return tekIgrac;}
	
	public void signaliziraj(){ ((Covek)igraci[tekIgrac]).signaliziraj();}
	
	private void dodajIgrace(){
		switch(rezim){
		case 0:{igraci[0]=new Covek(0); igraci[0]=new Covek(1); break;} 
		case 1:{
			igraci[0]=new Racunar(0,this,verzija); 
			igraci[1]=new Covek(1); 
			break;
		}
		case 2:{igraci[0]=new Racunar(0,this,verzija); igraci[1]=new Racunar(1,this,verzija2); break;}
		case 3:{igraci[0]=new Covek(0); igraci[1]=new Racunar(1,this,verzija); break;} 
		}
	}
	
	public void cekaj(){
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void uzmi(int s, int k,boolean isCovek){tabla.uzmi(s,k,isCovek);}
	public void blokirajDugme(){tabla.blokirajDugme();}
	public void odblokirajDugme(){tabla.odblokirajDugme();}
	public void proveriStanje(){tabla.proveriStanje();}
	
	public int getPrevmove(){return tabla.getPrevmove();}
	public int getDubina(){return dubina;}
	public int getVerzija(){return verzija;}
	public int getVerzija2(){return verzija2;}
	public int[] getDiskovi(){return tabla.getDiskovi();}
	public int getStubovi(){return tabla.getStubovi();}
}
