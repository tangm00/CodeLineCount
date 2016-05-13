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
	
	public static String[] code = {"java", "c", "cpp", "cs", "jsp"};// 代码类型
	public String[] matchStrings  = {".*\\.java$", ".*\\.c$", ".*\\.cpp$", ".*\\.cs$", ".*\\.jsp$"};				 // 匹配文件类型
//	protected String codeTypeStr;			// 代码类型
	protected int codeLineTotal;			// 代码总行
	protected int codeLineWork;			// 有效代码行数
	protected int codeLineNotes;			// 注释行数
	protected int codeLineBlank;			// 空行数
	protected File[] selFiles;				// 选择的文件
	
	protected String readmeStr = "功能：统计代码行数\n作者：唐敏\n时间：2015/08/02 20:41 -- 2015/08/29 19:17\n版本：1.0\n"
			+"修改：完善了jsp代码统计和显示效果的优化\n"
			+"时间：2016-05-11 20:00 -- 2016-05-12 09:37\n"
			+"版本: 1.1";
	
	public CountCodeLine() {
		// TODO Auto-generated constructor stub
		this(code[0]);						// 默认java代码
	}
	public CountCodeLine(String codeTypeStr){
//		this.codeTypeStr = codeTypeStr;
		clrStatistics();					// 统计数据清零
	}
	public void clrStatistics(){
		this.codeLineBlank = 0;
		this.codeLineNotes = 0;
		this.codeLineTotal = 0;
		this.codeLineWork = 0;
	}
	
	// 统计代码总行数行数
	public void cntJavaCodeLinesNotEmpty(){
		for (int i = 0; i < selFiles.length; i++) {
			readDirectory(selFiles[i]);
		}
		codeLineTotal = codeLineWork + codeLineNotes + codeLineBlank;
	}
	
	// 递归遍历目录
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
	
	// 统计.c .cpp .java文件
	public void countFile(File file){
		try {
			// 字节输入流
			FileInputStream in = new FileInputStream(file);
			// 字符输入流
			InputStreamReader inr = new InputStreamReader(in);
			// 缓冲字符输入流
			BufferedReader inbr = new BufferedReader(inr);
			try {
				String value = inbr.readLine().trim();
				boolean bNotes = false;		// 是否还在注释块中
				while(value!=null){
//						String strTmp = value.trim();
//						if (!strTmp.isEmpty()) {
//							ret++;		// 记录非空行数
//						}				
					// 匹配空行
					if (value.matches("^\\s*$")) {
						codeLineBlank++;
					}
					// 匹配注释行
					else if (value.matches("^\\/\\/.*")) {
						codeLineNotes++;
					}
					else if (value.matches("^\\/\\*.*") && !value.matches(".*\\*\\/$")) {
						codeLineNotes++;
						bNotes = true;
					}
					else if (bNotes) { 		// 还在注释中
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
	
	
	// 统计jsp文件
	public void cntJspFile(File file ) {
		
		try {
			// 字节输入流
			FileInputStream in = new FileInputStream(file);
			// 字符输入流
			InputStreamReader inr = new InputStreamReader(in);
			// 缓冲字符输入流
			BufferedReader inbr = new BufferedReader(inr);
			
			try {
				String value = inbr.readLine().trim();
				boolean bNotes = false;		// 是否还在注释块中
				while(value!=null){

					// 匹配空行
					if (value.matches("^\\s*$")) {
						codeLineBlank++;
					}
					// 匹配注释行  // 
					else if (value.matches("^\\/\\/.*")) {
						codeLineNotes++;
					}
					else if (value.matches("^\\/\\*.*") && !value.matches(".*\\*\\/$") 
							|| value.matches("^<!--.*") && !value.matches(".*-->$")
							) {
						codeLineNotes++;
						bNotes = true;
					}
					else if (bNotes) { 		// 还在注释中
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
