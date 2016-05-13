/**
 * 自定义JPanel面板，改变窗体的背景图片
 * 窗体中的所有布局都应在此类中完成，含注册事件监听器
 */
package priv.mtang.mytools.codelinecount.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import priv.mtang.mytools.codelinecount.bll.CountCodeLine;


/**
 * @author tangm
 *
 */
public class BKGJPanel extends JPanel {
	
	public static final String PATH_BKG_IMG_RSC_STRING = "src//img//bkg4.jpg";			// 背景图片的资源路径
	//private ImageIcon imgIcon;
	private Image img;
	
	private GridBagLayout gb;			// 布局管理器
	private GridBagConstraints gbc;
	// 布局组件
	private Font myFont;
	private Color myColor;
	private int height = 30;			// 字体高度
	
	private CountCodeLine myCntCodeLine ;		// 统计代码的功能类
	public CountCodeLine getCountCodeLine(){
		return myCntCodeLine;
	}
	
	public BKGJPanel(){
		this.init();		
		this.createUI();
	}
	public void init(){	
		myCntCodeLine = new CountCodeLine();		// 功能类初始化
		// 设置背景图片
		// 背景资源的路径
		img = Toolkit.getDefaultToolkit().getImage(this.PATH_BKG_IMG_RSC_STRING);					
		gb = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		myFont = new Font("华文行楷", Font.PLAIN, height);
		myColor = new Color(255,0,0);
	}
	
	public void createUI(){
	
	}
	
	// 重写paintComponent()
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
		g.setFont(myFont);
		g.setColor(myColor);
//		String string = String.format("代码总行数：%d\n有效行数：%d\n注释行数:%d\n额外空行数：%d", myCntCodeLine.getCodeLineTotal(), myCntCodeLine.getCodeLineWork(),	myCntCodeLine.getCodeLineNotes(), myCntCodeLine.getCodeLineBlank());
//		g.drawString(string, 0, 200);
		g.drawString(String.format("代码总数：%d行", myCntCodeLine.getCodeLineTotal()), 0, height * 2);
		g.drawString(String.format("有效：%d行", myCntCodeLine.getCodeLineWork()), 0, height * 4);
		g.drawString(String.format("注释：%d行", myCntCodeLine.getCodeLineNotes()), 0, height * 6);
		g.drawString(String.format("空行：%d行", myCntCodeLine.getCodeLineBlank()), 0, height * 8);
	}
	// 显示结果
	public void showResult(){
		paintComponent(getGraphics());		
	}
	
}
