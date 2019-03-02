package nim;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.System;

public class Nim extends Frame{
	private Igra igra;
	private Font f=new Font(null,Font.CENTER_BASELINE ,18);
	
	private Panel panel=new Panel(new GridLayout(0, 2));
	private Panel p11=new Panel(new GridLayout(1,2));
	private Panel p12=new Panel(new GridLayout(1, 2));
	private Panel p13=new Panel(new GridLayout(1, 2));
	
	private CheckboxGroup grupa=new CheckboxGroup();
	private Checkbox cc=new Checkbox("covek-covek",true,grupa);		//0
	private Checkbox cr=new Checkbox("covek-racunar",false,grupa);	//1
	private Checkbox rr=new Checkbox("racunar-racunar",false,grupa);	//2
	private Checkbox rc=new Checkbox("racunar-covek",false,grupa);
	
	private Button ok =new Button("OK");
	
	
	
	//konstruktor
	
	public Nim(){
		super("Nim");
		setBounds(100,100,700,600);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
		
		p11.add(cc);p11.add(cr); p12.add(rr);p12.add(rc); 
		p11.setFont(f);p12.setFont(f);
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Opcije();
				
			}
		});
		p13.add(ok);
		panel.add(p11); panel.add(p12);	panel.add(p13);
		this.add(panel);
	}
	
	
	
	private class Opcije extends Frame{
		private Panel panel=new Panel(new GridLayout(0, 2));
		
		private Label lab1=new Label("Br stubova? ",Label.CENTER);	
		private Label lab2=new Label("Raspored diskova?", Label.CENTER);
		private Label lab3=new Label("Dubina stabla?", Label.CENTER);
		private Label lab4=new Label("Nacin igranja?", Label.CENTER);
		private Label lab5=new Label("Nacin igranja 2?",Label.CENTER);
		
		private Panel p51=new Panel(new GridLayout(1, 2));	//paneli za radio button ab alfabeta takmicarski
		private Panel p52=new Panel(new GridLayout(1, 2));
		private Panel p61=new Panel(new GridLayout(1, 2));
		private Panel p62=new Panel(new GridLayout(1, 2));
		
		private TextField stubovi=new TextField(); 
		private TextField diskovi=new TextField();
		private TextField dubina=new TextField();
		
		private CheckboxGroup grupa2=new CheckboxGroup();
		private Checkbox ab=new Checkbox("AB",true,grupa2);	//0
		private Checkbox alfabeta=new Checkbox("AlfaBeta",false,grupa2);	//1
		private Checkbox tesko=new Checkbox("takmicarski",false,grupa2);	//2
		
		private CheckboxGroup grupa3=new CheckboxGroup();
		private Checkbox ab2=new Checkbox("AB",true,grupa3);	//0
		private Checkbox alfabeta2=new Checkbox("AlfaBeta",false,grupa3);	//1
		private Checkbox tesko2=new Checkbox("takmicarski",false,grupa3);	//2
		
		private Button start=new Button("START");
		
		//popunjava prozor samo sa opcijama covek-covek
		private void popuni1(){
			
			panel.add(lab1);
			panel.add(stubovi);
			panel.add(lab2);
			panel.add(diskovi);
			panel.setFont(f);
			
			addStart();
			
		}
		
		//popunjava prozor s dodatnim opcijama za racunar
		private void popuni2(){
		
			panel.add(lab1); panel.add(stubovi);
			panel.add(lab2); panel.add(diskovi);
			panel.add(lab3); panel.add(dubina);
			
			p51.add(lab4); p51.add(ab);
			p52.add(alfabeta); p52.add(tesko);
			panel.add(p51); panel.add(p52);
			p61.add(lab5);	p61.add(ab2);
			p62.add(alfabeta2);	p62.add(tesko2);
			panel.add(p61);	panel.add(p62);
			
			addStart();
			
		}
		
		private void addStart(){
			start.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int rezim=0,verzija=0,verzija2=0;
					if(cr.getState())rezim=1;
					if(rr.getState())rezim=2;
					if(rc.getState())rezim=3;
					
					if(rezim!=0){
						if(alfabeta.getState())verzija=1;
						if(tesko.getState())verzija=2;
						if(alfabeta2.getState())verzija=1;
						if(tesko2.getState())verzija=2;
						//System.out.println("stubovi: "+stubovi.getText()+", diskovi: "+diskovi.getText()+", dubina: "+dubina.getText());
						igra=new Igra(rezim, stubovi.getText(), diskovi.getText(), dubina.getText(), verzija,verzija2);
						igra.start();
					}
					else{
						//System.out.println("stubovi: "+stubovi.getText()+", diskovi: "+diskovi.getText());
						igra=new Igra(rezim, stubovi.getText(), diskovi.getText());
						igra.start();
						
						
					}
					
				}
			});
			
			panel.add(start);
		}
		
		private Opcije(){
			super("Opcije");
			setBounds(100,100,800,700);
			
			
			if(cr.getState() || rr.getState()||rc.getState()){
				popuni2();
			}
			else popuni1();
			
			this.add(panel);
			
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e){
					dispose();
				}
			});
			setVisible(true);
		}
	}
	
	public static void main(String[] args){
		new Nim();
	}
}
