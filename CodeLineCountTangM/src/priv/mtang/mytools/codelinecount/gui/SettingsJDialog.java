package priv.mtang.mytools.codelinecount.gui;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;

public class SettingsJDialog extends JDialog {
	private static SettingsJDialog instance=null;
	private Font font ;
	public Font getFont() {
		return font;
	}


	public void setFont(Font font) {
		this.font = font;
	}
	
	private SettingsJDialog(){
		super();
		init();
		createGUI();
		addEventListener();
	}
	
	public static SettingsJDialog getInstance(){
		if(instance == null){
			instance = new SettingsJDialog();
		}
		return instance;
	}

	public void init(){
		this.setTitle("…Ë÷√");
		//this.setBounds(320, 240, 640, 480);
		this.setBounds(new Rectangle(200,100,640,480));
		//this.pack();
	}
	public void createGUI(){
		
	}
	
	public void addEventListener(){
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	
	
	
	public static void main(String[] args) {
		SettingsJDialog.getInstance().show(true);
	}

}
