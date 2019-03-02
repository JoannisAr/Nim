package nim;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.StringTokenizer;

import javax.swing.Box.Filler;
import javax.swing.JOptionPane;


public class Tabla extends Frame{
	
	//klasa Kanvas
	private class Kanvas extends Canvas{

		public void paint(Graphics g){
			int w=getWidth()/(2*n);
			int h=(int)(0.8*(getHeight()/10));
			double dx=0.2*getWidth()/(n+1);
			double dy=0.1*getHeight()/10;
			double x=dx;
			double y=this.getHeight()-80;
			
			g.setColor(Color.BLUE);
			for(int i=0;i<n;i++){
				for(int j=0;j<diskovi[i];j++){
					g.fillRect((int)x, (int)y, w, h);
					y=y-h-dy;
				}
				x=x+w+dx;
				y=this.getHeight()-80;
			}
			
		}
	}

	private Igra igra;
	private Kanvas canvas;
	private int n;
	private int[] diskovi;
	private int prevmove=11;
	private Label stanje=new Label("STANJE");
	private Button uzmi;
	
	public Tabla(int stubovi, String disk, Igra igra){
		super("Igra");
		this.igra=igra;
		
		setBounds(100,100,800,700);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
		
		n=stubovi;
		
		//System.out.print("stubovi: "+n+", diskovi: ");
		diskovi=new int[stubovi];
		StringTokenizer st=new StringTokenizer(disk, ",");
		for (int i = 0; i < n; i++) {
			diskovi[i]=Integer.parseInt(st.nextToken());
			//System.out.print(diskovi[i]+", ");
		}
		
	popuniProzor();
	}
	
	//popunjavanje prozora
	private void popuniProzor(){
		canvas = new Kanvas();
		canvas.setBounds(getX(), getY() + 30, getWidth(), (int) (getHeight() * 0.7));
		this.add(canvas, "Center");
		canvas.repaint();
		
		Panel juzni=new Panel(new GridLayout(0,4));
		Label labstub=new Label("stub: "), labkol=new Label("kolicina: ");
		TextField stub=new TextField(), kolicina=new TextField();
		uzmi=new Button("uzmi");
		
		juzni.add(labstub); juzni.add(stub); juzni.add(labkol); juzni.add(kolicina);
		juzni.add(uzmi); 
		Font font=new Font(null,Font.BOLD,24);
		stanje.setFont(font);
		uzmi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int s=Integer.parseInt(stub.getText());
				int k=Integer.parseInt(kolicina.getText());
				
				int t=uzmi(s,k,true);
				igra.signaliziraj();
				if(diskovi[s-1]==0 && t==1) proveriStanje();
			}
		});
		
		this.add(juzni, "South");
		this.add(stanje, "North");
	}
		
	public void proveriStanje(){
		int flag=1;
		for (int i = 0; i < diskovi.length; i++) {
			if(diskovi[i]!=0){flag=0; break;}
		}
		if(flag==1){
			igra.interrupt();
			setStanje("POBEDIO JE IGRAC "+igra.getTekIgrac()+"!");
		}
		else{
			setStanje("Na potezu je igrac "+igra.getTekIgrac());
		}
	}
	
	public void setStanje(String s){
		stanje.setText(s);
	}
	public int[] getDiskovi(){return diskovi;}
	public int getStubovi(){return n;}
	
	public int uzmi(int s, int k,boolean isCovek){
		
		int ss=s;
		if(isCovek){
			ss=s-1;
			for (int i = 0; i < diskovi.length; i++) {
				if((diskovi[i]==diskovi[ss]-k)&&(diskovi[i]!=0)) {JOptionPane.showMessageDialog(null,"Nemoguc potez (2 stuba sa istim brojem diskova)!!!"); return 0;}
			}
		}
		
		if(k>2*prevmove && k<=diskovi[ss]){ JOptionPane.showMessageDialog(null, "Ne mozete uzeti vise diskova nego u prethodnom potezu!"); return 0;}
		if(k>diskovi[ss]){JOptionPane.showMessageDialog(null,"Ne mozete uzeti vise diskova nego sto postoji!");return 0;}
		
		if(ss>=0 && ss<=n && diskovi[ss]>=k){
			prevmove=k;
			diskovi[ss]-=k;
			canvas.repaint();
			return 1;
		}
		else return 0;
	
		
	}
	
	public void blokirajDugme(){
		uzmi.setEnabled(false);
	}
	public void odblokirajDugme(){
		uzmi.setEnabled(true);
	}
	
	public int getPrevmove(){return prevmove;}

};
