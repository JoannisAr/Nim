package nim;

public class AlfaBeta extends Strategija{

	public AlfaBeta(int[] diskovi,int v,int prevmove,int maxdubina) {
		super(diskovi,v,prevmove,maxdubina);
		
	}

	@Override
	public void igraj(int prevmove) {
		alfabeta(diskovi,0,prevmove,-100,100,true);
		
	}
	
	private int alfabeta(int[] diskovi, int dubina,int prevmove, int alfa, int beta, boolean maxigrac){
		if(isTerminal(diskovi)==1 || dubina==maxdubina){
			
			return heuristic(diskovi);
		}
		if(maxigrac){		//max igrac
			int best=-1;
			int b=0;
			int[] flags=new int[11];
			for(int i=0;i<diskovi.length;i++)	//inicijalizujem flags
				flags[diskovi[i]]=1;
		
			for(int i=1; i<=min(2*prevmove,10);i++){
				for(int j=0;j<diskovi.length;j++){
					
					if((diskovi[j]>=i) && ((flags[diskovi[j]-i]!=1)||(diskovi[j]-i==0))){
						int[] niz=new int[diskovi.length];
						for(int k=0;k<diskovi.length;k++) niz[k]=diskovi[k];
						niz[j]-=i;
						int value=alfabeta(niz,dubina+1,i,alfa,beta,false);
						best= max(best,value);
						alfa=max(alfa,best);
						if((best==value)&&(dubina==0)){s=j; k=i;}
						if(alfa>=beta){b=1;break;}
						
					}	
				}
				if(b==1)break;
			}
			return best;
		}
		
		else{			//min igrac
			int best=100;
			int b=0;
			int[] flags=new int[11];
			for(int i=0;i<diskovi.length;i++)	
				flags[diskovi[i]]=1;
			
			for(int i=1; i<=min(2*prevmove,10);i++){
				for(int j=0;j<diskovi.length;j++){
					
					if((diskovi[j]>=i) &&((flags[diskovi[j]-i]!=1)||(diskovi[j]-i==0))){
						int[] niz=new int[diskovi.length];
						for(int k=0;k<diskovi.length;k++) niz[k]=diskovi[k];
						niz[j]-=i;
						int value=alfabeta(niz,dubina+1,i,alfa,beta,true);
						best= min(best,value);
						beta=min(beta,best);
						if(beta<=alfa){b=1;break;}
						
					}
				}
				if(b==1)break;
			}
			return best;
		}

		
	}

}
