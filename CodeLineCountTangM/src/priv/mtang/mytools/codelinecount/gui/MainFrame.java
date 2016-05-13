/**
 * 统计代码的行数
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
	
	private static MainFrame instance ;	// 对自己实例的引用
	
	private JLabel jlbBkg;
	private BKGJPanel bkgjpl ;				// 背景设置代理JPanel
	private JMenuBar mb;					// 菜单栏
	private JMenu jmFile;					// 菜单：文件、
	private JMenuItem jmiSelFile, jmiExit;	// （文件）菜单项：选择文件,退出
	private JMenu jmAbout;					// 关于
	private JMenuItem jmiCodeLineCount;		// 代码统计功能
	
	private JMenu jmSettings;				// 设置：字体，颜色等
	
	/**
	 * 构造器私有化，不允许外界自由地实例化该类的对象
	 */
	private MainFrame(){
		super();
		this.init();
		this.designUI();
		this.addEventListener();
		this.showWindow();
	}
	/**
	 * 对外界的公开的获取实例的接口
	 * 主窗体实例引用
	 * @return 单一实例的引用
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
	// 对象引用初始化
	private void init(){

		// 自定义的JPanel类的子类BKGJPanel是实际的内容窗格
		bkgjpl = new BKGJPanel();				// 背景代理设置 	
		
		// 菜单栏设置
		mb = new JMenuBar();
		// "文件"菜单及其菜单项
		jmFile = new JMenu("文件");
		jmiSelFile = new JMenuItem("选择文件");
		jmiExit = new JMenuItem("退出");
		// "关于"菜单及其菜单项
		jmAbout = new JMenu("关于");
		jmiCodeLineCount = new JMenuItem("代码统计");
		// "设置"菜单
		jmSettings = new JMenu("设置");
	}
	
	// 界面布局设计
	private void designUI(){
		this.setTitle("代码行数统计工具-By tangm");	// 设置窗口标题
		this.setBounds(350, 150, 640, 480);			// 设置窗口显示位置及大小
		this.changeFrameIcon();						// 设置自定义任务栏和标题栏图标
//		this.changeFrameBKGround();					// 法1 设置自定义窗体的背景图或背景色
		
//		this.changeFrameBKG();						// 法2 设置背景图,大小可变
		
		this.changeFrameBKG3rd();					// 法3 设置背景图,大小可变

		this.setJMenuBar(mb);				// 菜单项
		mb.add(jmFile);						// 文件
		jmFile.add(jmiSelFile);				// 选择文件
		jmFile.add(jmiExit);				// 退出
		mb.add(jmAbout);					// 关于
		jmAbout.add(jmiCodeLineCount);		// 代码统计
		mb.add(this.jmSettings);			// 设置
		
	}
	
	// 设置窗体的自定义图标
	public void changeFrameIcon(){
		// 法1: 以下分别能成功执行，达到目的
		Toolkit tool = this.getToolkit();	// 得到一个ToolKit对象
		Image myImg = tool.getImage("src\\img\\TangM.gif");	// 有tool对象获取图像
//		Image myImg = tool.createImage("src\\img\\TangM.gif");	// 有tool对象获取图像
		this.setIconImage(myImg);
		
		// 法2: 以下分别能成功执行，达到目的
//		ImageIcon myImgIcon = new ImageIcon("src\\img\\TangM.gif");
//		this.setIconImage(myImgIcon.getImage());
		
		// 法3: 以下分别能成功执行，达到目的
//		this.setIconImage(Toolkit.getDefaultToolkit().createImage("F:\\FFOutput\\TangM.jpg"));
//		this.setIconImage(Toolkit.getDefaultToolkit().createImage("src\\img\\TangM.jpg"));
//		this.setIconImage(Toolkit.getDefaultToolkit().createImage("src\\img\\TangM.gif"));
	}
	
	// 设置Frame的窗体背景图片
	// 法1: 利用继承的paint(Graphics g)方法绘制背景
	// 缺点: 会闪烁,
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
	// 下面的paint方法，使用法1设置JFrame的背景图
//	public void paint(Graphics g){
//		super.paint(g);
//		this.changeFrameBKGround(g);
//	}
	
	// 法2：将图片放在JLabel中,改变JFrame的背景图
	public void changeFrameBKG(){
		
		ImageIcon imgBkg = new ImageIcon("src\\img\\bkg1.jpg");
		if (jlbBkg!=null) {
			this.getLayeredPane().remove(jlbBkg);
		}
		jlbBkg = new JLabel(imgBkg);
		imgBkg.setImage(imgBkg.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_FAST));
//		BKGJLabel jlbBkg = new BKGJLabel(imgBkg.getImage());
		
		//this.getLayeredPane().add(jlbBkg, new Integer(Integer.MIN_VALUE));
		// 把标签的大小位置设置为图片刚好填充整个面板
//		jlbBkg.setBounds(0,0,imgBkg.getIconWidth(),imgBkg.getIconHeight());
		jlbBkg.setBounds(0, 0, this.getWidth(), this.getHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel imgPanel = (JPanel)this.getContentPane();
		imgPanel.setOpaque(false);
		
		this.getLayeredPane().add(jlbBkg, new Integer(Integer.MIN_VALUE));
		
//		jlbBkg.setBounds(0, 0, this.getWidth(), this.getHeight());
	}
	// 下面的paint方法，使用法2设置JFrame的背景图
//	public void paint(Graphics g){
//		super.paint(g);
//		
//		this.changeFrameBKG();
//	}
	
	// 法3：自定义JPanel作为窗体的背景类
	public void changeFrameBKG3rd(){
		Container contentPane = this.getContentPane();
		contentPane.add(bkgjpl);
		bkgjpl.setOpaque(false);				// 内容窗格透明化
	}


	// 注册事件监听器
	private void addEventListener(){
		// 为本窗体类注册事件监听器:关闭窗体，退出程序
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		// 退出
		this.jmiExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		// 选择文件
		this.jmiSelFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser jfc = new JFileChooser(".");
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);	// 只许选择文件，不允许选择目录
				jfc.setMultiSelectionEnabled(true);		// 允许同时选择多个文件
				int ret = jfc.showDialog(new JLabel(), "选择源码文件");
				if (ret == JFileChooser.APPROVE_OPTION) {
					File [] selFiles = jfc.getSelectedFiles();				 // 获取选择的文件
					bkgjpl.getCountCodeLine().setSelFiles(selFiles);		 // 传递文件列表，文件or目录
					bkgjpl.getCountCodeLine().cntJavaCodeLinesNotEmpty();	 // 计算行数
					bkgjpl.showResult();									 // 显示结果
					bkgjpl.getCountCodeLine().clrStatistics();				 // 数据清零， 不影响显示
				}
			}
		});
		// 关于-代码统计
		jmiCodeLineCount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				StringBuffer strBuff = new StringBuffer(bkgjpl.getCountCodeLine().getReadmeStr());
				JOptionPane.showMessageDialog(null,	strBuff, "代码行数统计", JOptionPane.DEFAULT_OPTION);
			}
		});
		
		// "设置":字体，颜色等
		jmSettings.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				JOptionPane.showMessageDialog(null, "SettingsJDialog类的功能");
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
