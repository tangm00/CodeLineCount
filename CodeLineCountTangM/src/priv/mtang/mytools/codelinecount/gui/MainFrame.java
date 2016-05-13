/**
 * ͳ�ƴ��������
 */
package priv.mtang.mytools.codelinecount.gui;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Description:
 * <br/>class name: MainFrame
 * <br/>class function: main window of the application
 * <br/>creation date: 2015-08-02 20:41
 * <br/>finish date: 2015-08-29 19:17
 * @author tangm
 * @version 1.0
 */
public class MainFrame extends JFrame {
	
	private static MainFrame instance ;	// ���Լ�ʵ��������
	
	private JLabel jlbBkg;
	private BKGJPanel bkgjpl ;				// �������ô���JPanel
	private JMenuBar mb;					// �˵���
	private JMenu jmFile;					// �˵����ļ���
	private JMenuItem jmiSelFile, jmiExit;	// ���ļ����˵��ѡ���ļ�,�˳�
	private JMenu jmAbout;					// ����
	private JMenuItem jmiCodeLineCount;		// ����ͳ�ƹ���
	
	private JMenu jmSettings;				// ���ã����壬��ɫ��
	
	/**
	 * ������˽�л���������������ɵ�ʵ��������Ķ���
	 */
	private MainFrame(){
		super();
		this.init();
		this.designUI();
		this.addEventListener();
		this.showWindow();
	}
	/**
	 * �����Ĺ����Ļ�ȡʵ���Ľӿ�
	 * ������ʵ������
	 * @return ��һʵ��������
	 */
	public static MainFrame getInstance(){
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	public void showWindow(){
		this.setVisible(true);
	}
	// �������ó�ʼ��
	private void init(){

		// �Զ����JPanel�������BKGJPanel��ʵ�ʵ����ݴ���
		bkgjpl = new BKGJPanel();				// ������������ 	
		
		// �˵�������
		mb = new JMenuBar();
		// "�ļ�"�˵�����˵���
		jmFile = new JMenu("�ļ�");
		jmiSelFile = new JMenuItem("ѡ���ļ�");
		jmiExit = new JMenuItem("�˳�");
		// "����"�˵�����˵���
		jmAbout = new JMenu("����");
		jmiCodeLineCount = new JMenuItem("����ͳ��");
		// "����"�˵�
		jmSettings = new JMenu("����");
	}
	
	// ���沼�����
	private void designUI(){
		this.setTitle("��������ͳ�ƹ���-By tangm");	// ���ô��ڱ���
		this.setBounds(350, 150, 640, 480);			// ���ô�����ʾλ�ü���С
		this.changeFrameIcon();						// �����Զ����������ͱ�����ͼ��
//		this.changeFrameBKGround();					// ��1 �����Զ��崰��ı���ͼ�򱳾�ɫ
		
//		this.changeFrameBKG();						// ��2 ���ñ���ͼ,��С�ɱ�
		
		this.changeFrameBKG3rd();					// ��3 ���ñ���ͼ,��С�ɱ�

		this.setJMenuBar(mb);				// �˵���
		mb.add(jmFile);						// �ļ�
		jmFile.add(jmiSelFile);				// ѡ���ļ�
		jmFile.add(jmiExit);				// �˳�
		mb.add(jmAbout);					// ����
		jmAbout.add(jmiCodeLineCount);		// ����ͳ��
		mb.add(this.jmSettings);			// ����
		
	}
	
	// ���ô�����Զ���ͼ��
	public void changeFrameIcon(){
		// ��1: ���·ֱ��ܳɹ�ִ�У��ﵽĿ��
		Toolkit tool = this.getToolkit();	// �õ�һ��ToolKit����
		Image myImg = tool.getImage("src\\img\\TangM.gif");	// ��tool�����ȡͼ��
//		Image myImg = tool.createImage("src\\img\\TangM.gif");	// ��tool�����ȡͼ��
		this.setIconImage(myImg);
		
		// ��2: ���·ֱ��ܳɹ�ִ�У��ﵽĿ��
//		ImageIcon myImgIcon = new ImageIcon("src\\img\\TangM.gif");
//		this.setIconImage(myImgIcon.getImage());
		
		// ��3: ���·ֱ��ܳɹ�ִ�У��ﵽĿ��
//		this.setIconImage(Toolkit.getDefaultToolkit().createImage("F:\\FFOutput\\TangM.jpg"));
//		this.setIconImage(Toolkit.getDefaultToolkit().createImage("src\\img\\TangM.jpg"));
//		this.setIconImage(Toolkit.getDefaultToolkit().createImage("src\\img\\TangM.gif"));
	}
	
	// ����Frame�Ĵ��屳��ͼƬ
	// ��1: ���ü̳е�paint(Graphics g)�������Ʊ���
	// ȱ��: ����˸,
	public void changeFrameBKGround(Graphics g){
		try {
			BufferedImage bkgImage = ImageIO.read(new File("src\\img\\bkg1.jpg"));
			g = this.getGraphics();
			g.drawImage(bkgImage, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	// �����paint������ʹ�÷�1����JFrame�ı���ͼ
//	public void paint(Graphics g){
//		super.paint(g);
//		this.changeFrameBKGround(g);
//	}
	
	// ��2����ͼƬ����JLabel��,�ı�JFrame�ı���ͼ
	public void changeFrameBKG(){
		
		ImageIcon imgBkg = new ImageIcon("src\\img\\bkg1.jpg");
		if (jlbBkg!=null) {
			this.getLayeredPane().remove(jlbBkg);
		}
		jlbBkg = new JLabel(imgBkg);
		imgBkg.setImage(imgBkg.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST));
//		BKGJLabel jlbBkg = new BKGJLabel(imgBkg.getImage());
		
		//this.getLayeredPane().add(jlbBkg, new Integer(Integer.MIN_VALUE));
		// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
//		jlbBkg.setBounds(0,0,imgBkg.getIconWidth(),imgBkg.getIconHeight());
		jlbBkg.setBounds(0, 0, this.getWidth(), this.getHeight());
		// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		JPanel imgPanel = (JPanel)this.getContentPane();
		imgPanel.setOpaque(false);
		
		this.getLayeredPane().add(jlbBkg, new Integer(Integer.MIN_VALUE));
		
//		jlbBkg.setBounds(0, 0, this.getWidth(), this.getHeight());
	}
	// �����paint������ʹ�÷�2����JFrame�ı���ͼ
//	public void paint(Graphics g){
//		super.paint(g);
//		
//		this.changeFrameBKG();
//	}
	
	// ��3���Զ���JPanel��Ϊ����ı�����
	public void changeFrameBKG3rd(){
		Container contentPane = this.getContentPane();
		contentPane.add(bkgjpl);
		bkgjpl.setOpaque(false);				// ���ݴ���͸����
	}


	// ע���¼�������
	private void addEventListener(){
		// Ϊ��������ע���¼�������:�رմ��壬�˳�����
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		// �˳�
		this.jmiExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		// ѡ���ļ�
		this.jmiSelFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser jfc = new JFileChooser(".");
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);	// ֻ��ѡ���ļ���������ѡ��Ŀ¼
				jfc.setMultiSelectionEnabled(true);		// ����ͬʱѡ�����ļ�
				int ret = jfc.showDialog(new JLabel(), "ѡ��Դ���ļ�");
				if (ret == JFileChooser.APPROVE_OPTION) {
					File [] selFiles = jfc.getSelectedFiles();				 // ��ȡѡ����ļ�
					bkgjpl.getCountCodeLine().setSelFiles(selFiles);		 // �����ļ��б��ļ�orĿ¼
					bkgjpl.getCountCodeLine().cntJavaCodeLinesNotEmpty();	 // ��������
					bkgjpl.showResult();									 // ��ʾ���
					bkgjpl.getCountCodeLine().clrStatistics();				 // �������㣬 ��Ӱ����ʾ
				}
			}
		});
		// ����-����ͳ��
		jmiCodeLineCount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				StringBuffer strBuff = new StringBuffer(bkgjpl.getCountCodeLine().getReadmeStr());
				JOptionPane.showMessageDialog(null,	strBuff, "��������ͳ��", JOptionPane.DEFAULT_OPTION);
			}
		});
		
		// "����":���壬��ɫ��
		jmSettings.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				JOptionPane.showMessageDialog(null, "SettingsJDialog��Ĺ���");
			}
		});
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// new MainFrame().showWindow();
		MainFrame.getInstance().showWindow();
	}

}
