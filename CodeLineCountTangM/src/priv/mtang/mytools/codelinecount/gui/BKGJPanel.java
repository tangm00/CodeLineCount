/**
 * �Զ���JPanel��壬�ı䴰��ı���ͼƬ
 * �����е����в��ֶ�Ӧ�ڴ�������ɣ���ע���¼�������
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
	
	public static final String PATH_BKG_IMG_RSC_STRING = "src//img//bkg4.jpg";			// ����ͼƬ����Դ·��
	//private ImageIcon imgIcon;
	private Image img;
	
	private GridBagLayout gb;			// ���ֹ�����
	private GridBagConstraints gbc;
	// �������
	private Font myFont;
	private Color myColor;
	private int height = 30;			// ����߶�
	
	private CountCodeLine myCntCodeLine ;		// ͳ�ƴ���Ĺ�����
	public CountCodeLine getCountCodeLine(){
		return myCntCodeLine;
	}
	
	public BKGJPanel(){
		this.init();		
		this.createUI();
	}
	public void init(){	
		myCntCodeLine = new CountCodeLine();		// �������ʼ��
		// ���ñ���ͼƬ
		// ������Դ��·��
		img = Toolkit.getDefaultToolkit().getImage(this.PATH_BKG_IMG_RSC_STRING);					
		gb = new GridBagLayout();
		gbc = new GridBagConstraints();
		
		myFont = new Font("�����п�", Font.PLAIN, height);
		myColor = new Color(255,0,0);
	}
	
	public void createUI(){
	
	}
	
	// ��дpaintComponent()
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
		g.setFont(myFont);
		g.setColor(myColor);
//		String string = String.format("������������%d\n��Ч������%d\nע������:%d\n�����������%d", myCntCodeLine.getCodeLineTotal(), myCntCodeLine.getCodeLineWork(),	myCntCodeLine.getCodeLineNotes(), myCntCodeLine.getCodeLineBlank());
//		g.drawString(string, 0, 200);
		g.drawString(String.format("����������%d��", myCntCodeLine.getCodeLineTotal()), 0, height * 2);
		g.drawString(String.format("��Ч��%d��", myCntCodeLine.getCodeLineWork()), 0, height * 4);
		g.drawString(String.format("ע�ͣ�%d��", myCntCodeLine.getCodeLineNotes()), 0, height * 6);
		g.drawString(String.format("���У�%d��", myCntCodeLine.getCodeLineBlank()), 0, height * 8);
	}
	// ��ʾ���
	public void showResult(){
		paintComponent(getGraphics());		
	}
	
}
