package nim;

public class AB extends Strategija{

	public AB(int[] diskovi, int v, int prevmove,int maxdubina) {
		super(diskovi,v,prevmove,maxdubina);
		
	}

	@Override
	public void igraj(int prevmove) {
		//this.prevmove=prevmove;
		minimax(diskovi,0,prevmove,true);
		
	}
	
	private int minimax(int[] diskovi, int dubina,int prevmove, boolean maxigrac){
		if(isTerminal(diskovi)==1 || dubina==maxdubina){
			
			return heuristic(diskovi);
			
		}
		if(maxigrac){		//max igrac
			int best=-1;
			int[] flags=new int[11];
			for(int i=0;i<diskovi.length;i++)	//inicijalizujem flags
				flags[diskovi[i]]=1;
			
			for(int i=1; i<min(2*prevmove,10);i++){
				for(int j=0;j<diskovi.length;j++){
					
					if((diskovi[j]>=i) && ((flags[diskovi[j]-i]!=1)||(diskovi[j]-i==0))){
						int[] niz=new int[diskovi.length];
						for(int k=0;k<diskovi.length;k++) niz[k]=diskovi[k];
						niz[j]-=i;
						int value=minimax(niz,dubina+1,i,false);
						best= max(best,value);
						if((best==value)&&(dubina==0)){s=j; k=i;}
						
					}	
				}
			}
			return best;
		}
		else{			//min igrac
			int best=100;
			int[] flags=new int[11];
			for(int i=0;i<diskovi.length;i++)	
				flags[diskovi[i]]=1;
			
			for(int i=1; i<min(2*prevmove,10);i++){
				for(int j=0;j<diskovi.length;j++){
					
					if((diskovi[j]>=i) && ((flags[diskovi[j]-i]!=1)||(diskovi[j]-i==0))){
						int[] niz=new int[diskovi.length];
						for(int k=0;k<diskovi.length;k++) niz[k]=diskovi[k];
						niz[j]-=i;
						int value=minimax(niz,dubina+1,i,true);
						best= min(best,value);
						
					}
				}
			}
			return best;
		}

		
	}
	
	
	

}
