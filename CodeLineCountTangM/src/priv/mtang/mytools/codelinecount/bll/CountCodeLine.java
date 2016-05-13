/**
 * 
 */
package priv.mtang.mytools.codelinecount.bll;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mtang
 * 
 */
public class CountCodeLine {
	
	public static String[] code = {"java", "c", "cpp", "cs", "jsp"};// ��������
	public String[] matchStrings  = {".*\\.java$", ".*\\.c$", ".*\\.cpp$", ".*\\.cs$", ".*\\.jsp$"};				 // ƥ���ļ�����
//	protected String codeTypeStr;			// ��������
	protected int codeLineTotal;			// ��������
	protected int codeLineWork;			// ��Ч��������
	protected int codeLineNotes;			// ע������
	protected int codeLineBlank;			// ������
	protected File[] selFiles;				// ѡ����ļ�
	
	protected String readmeStr = "���ܣ�ͳ�ƴ�������\n���ߣ�����\nʱ�䣺2015/08/02 20:41 -- 2015/08/29 19:17\n�汾��1.0\n"
			+"�޸ģ�������jsp����ͳ�ƺ���ʾЧ�����Ż�\n"
			+"ʱ�䣺2016-05-11 20:00 -- 2016-05-12 09:37\n"
			+"�汾: 1.1";
	
	public CountCodeLine() {
		// TODO Auto-generated constructor stub
		this(code[0]);						// Ĭ��java����
	}
	public CountCodeLine(String codeTypeStr){
//		this.codeTypeStr = codeTypeStr;
		clrStatistics();					// ͳ����������
	}
	public void clrStatistics(){
		this.codeLineBlank = 0;
		this.codeLineNotes = 0;
		this.codeLineTotal = 0;
		this.codeLineWork = 0;
	}
	
	// ͳ�ƴ�������������
	public void cntJavaCodeLinesNotEmpty(){
		for (int i = 0; i < selFiles.length; i++) {
			readDirectory(selFiles[i]);
		}
		codeLineTotal = codeLineWork + codeLineNotes + codeLineBlank;
	}
	
	// �ݹ����Ŀ¼
	public void readDirectory(File file){
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				readDirectory(f);
			}
		}
		else {
			String string = file.getName();
			if (string.matches(matchStrings[0])				// .java
					|| string.matches(matchStrings[1])		// .c
					|| string.matches(matchStrings[2])		// .cpp
					|| string.matches(matchStrings[3])		// .cs
					) {
				countFile(file);
			}
			else if (string.matches(matchStrings[4])) {		// .jsp
				cntJspFile(file);
			}
		}
	}
	
	// ͳ��.c .cpp .java�ļ�
	public void countFile(File file){
		try {
			// �ֽ�������
			FileInputStream in = new FileInputStream(file);
			// �ַ�������
			InputStreamReader inr = new InputStreamReader(in);
			// �����ַ�������
			BufferedReader inbr = new BufferedReader(inr);
			try {
				String value = inbr.readLine().trim();
				boolean bNotes = false;		// �Ƿ���ע�Ϳ���
				while(value!=null){
//						String strTmp = value.trim();
//						if (!strTmp.isEmpty()) {
//							ret++;		// ��¼�ǿ�����
//						}				
					// ƥ�����
					if (value.matches("^\\s*$")) {
						codeLineBlank++;
					}
					// ƥ��ע����
					else if (value.matches("^\\/\\/.*")) {
						codeLineNotes++;
					}
					else if (value.matches("^\\/\\*.*") && !value.matches(".*\\*\\/$")) {
						codeLineNotes++;
						bNotes = true;
					}
					else if (bNotes) { 		// ����ע����
						codeLineNotes++;
						if (value.matches(".*\\*\\/$")) {
							bNotes = false;
						}
					}
					else if (value.matches("^\\/\\*.*\\*\\/$")) {
						codeLineNotes++;
					}
					else  {
						codeLineWork++;
					}
					value = inbr.readLine();
				}
				in.close();
				inr.close();
				inbr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// ͳ��jsp�ļ�
	public void cntJspFile(File file ) {
		
		try {
			// �ֽ�������
			FileInputStream in = new FileInputStream(file);
			// �ַ�������
			InputStreamReader inr = new InputStreamReader(in);
			// �����ַ�������
			BufferedReader inbr = new BufferedReader(inr);
			
			try {
				String value = inbr.readLine().trim();
				boolean bNotes = false;		// �Ƿ���ע�Ϳ���
				while(value!=null){

					// ƥ�����
					if (value.matches("^\\s*$")) {
						codeLineBlank++;
					}
					// ƥ��ע����  // 
					else if (value.matches("^\\/\\/.*")) {
						codeLineNotes++;
					}
					else if (value.matches("^\\/\\*.*") && !value.matches(".*\\*\\/$") 
							|| value.matches("^<!--.*") && !value.matches(".*-->$")
							) {
						codeLineNotes++;
						bNotes = true;
					}
					else if (bNotes) { 		// ����ע����
						codeLineNotes++;
						if (value.matches(".*\\*\\/$") || value.matches(".*-->$")) {
							bNotes = false;
						}
					}
					else if (value.matches("^\\/\\*.*\\*\\/$") || value.matches("^<!--.*-->$")) {
						codeLineNotes++;
					}
					else  {
						codeLineWork++;
					}
					value = inbr.readLine();
				}
				in.close();
				inr.close();
				inbr.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	// getters and setters
	public String getReadmeStr() {
		return readmeStr;
	}
	public void setReadmeStr(String readmeStr) {
		this.readmeStr = readmeStr;
	}
	public File[] getSelFiles() {
		return selFiles;
	}
	public void setSelFiles(File[] selFiles) {
		this.selFiles = selFiles;
	}
	public static String[] getCode() {
		return code;
	}
	public static void setCode(String[] code) {
		CountCodeLine.code = code;
	}
//	public String getCodeTypeStr() {
//		return codeTypeStr;
//	}
//	public void setCodeTypeStr(String codeTypeStr) {
//		this.codeTypeStr = codeTypeStr;
//	}
	public int getCodeLineTotal() {
		return codeLineTotal;
	}
	public void setCodeLineTotal(int codeLineTotal) {
		this.codeLineTotal = codeLineTotal;
	}
	public int getCodeLineWork() {
		return codeLineWork;
	}
	public void setCodeLineWork(int codeLineWork) {
		this.codeLineWork = codeLineWork;
	}
	public int getCodeLineNotes() {
		return codeLineNotes;
	}
	public void setCodeLineNotes(int codeLineNotes) {
		this.codeLineNotes = codeLineNotes;
	}
	public int getCodeLineBlank() {
		return codeLineBlank;
	}
	public void setCodeLineBlank(int codeLineBlank) {
		this.codeLineBlank = codeLineBlank;
	}
	
	
	// for testing func
	public void read(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
//				System.out.println(f.getName());
				read(f);
			}
		}
		else {
			String fileNameStr = file.getName();
			
			if (fileNameStr.matches(".*\\.java$")			// *.java
					|| fileNameStr.matches(".*\\.cpp$")			// *.cpp
					|| fileNameStr.matches(".*\\.c$")
					|| fileNameStr.matches(".*\\.html$")
					|| fileNameStr.matches(".*\\.jsp$")
					|| fileNameStr.matches(".*\\.cs$")
					) {
				System.out.println(file.getName());
			}		
			
		}
	}
	
//	public static void  main(String[] args) {
//		File file  = new File("C:\\Users\\Administrator\\Desktop\\test\\");
//		new CountCodeLine().read(file);
//	}
}
