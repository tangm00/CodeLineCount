/**
 * 自定义JLabel，改变窗体的背景图片
 */
package priv.mtang.mytools.codelinecount.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * @author tangm
 *
 */
public class BKGJLabel extends JLabel{
	private Image img;
	public BKGJLabel(){
		img = Toolkit.getDefaultToolkit().getImage("//src//img//bkg1.jpg");
	}
	public BKGJLabel(Image image){
//		super(img);
//		img = imgIcon.getImage();
		img = image;
		
	}
//	public void paintComponent(Graphics g){
//		super.paintComponent(g);
//		//this.setBounds(0, 0, this.getWidth(), this.getHeight());
//		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),null);
//	}
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),null);
	}
}
