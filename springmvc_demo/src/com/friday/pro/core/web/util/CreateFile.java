package com.friday.pro.core.web.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

public class CreateFile {
	
	public void createFolder(String path){
		File file =new File(path);    
		//如果文件夹不存在则创建    
		if  (!file .exists()  && !file .isDirectory()){       
		    System.out.println("目录创建成功！");
		    file .mkdir();    
		}
	}
	
	public void createFile(String path,String fileName,StringBuilder strb) throws Exception{
		this.createFolder(path);
		String newName = path + "/" + fileName;
		PrintWriter printStream = new PrintWriter(new OutputStreamWriter(new FileOutputStream(newName),"utf-8"),true);
		printStream.println(strb.toString()); 
		System.out.println("文件：" +fileName+ "创建成功！");
	}
	
	public static void main(String[] args) {
		CreateFile c = new CreateFile();
		String path = "D:\\folders";
		String fileName = "report2.html";
		StringBuilder sb = new StringBuilder();
		sb.append("<html>"); 
		sb.append("<head>"); 
		sb.append("<title>每日运营报表</title>"); 
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"); 
		sb.append("<style type=\"text/css\">"); 
		sb.append("TABLE{border-collapse:collapse;border-left:solid 1 #000000; border-top:solid 1 #000000;padding:5px;}"); 
		sb.append("TH{border-right:solid 1 #000000;border-bottom:solid 1 #000000;}"); 
		sb.append("TD{font:normal;border-right:solid 1 #000000;border-bottom:solid 1 #000000;}"); 
		sb.append("</style></head>"); 
		sb.append("<body bgcolor=\"#FFF8DC\">"); 
		sb.append("<div align=\"center\">"); 
		sb.append("<br/>"); 
		sb.append("<br/>"); 
		sb.append("2222222222222");
		sb.append("sdadasdsad");
		sb.append("<br/><br/>"); 
		sb.append("</div></body></html>"); 
		try {
			//c.createFile(path, fileName, sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
