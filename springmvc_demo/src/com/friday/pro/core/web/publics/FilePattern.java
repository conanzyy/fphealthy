package com.friday.pro.core.web.publics;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类描述：工具类
 * @author: lxie
 */
public class FilePattern {
	/**
	 * 文件类型验证
	 * param fileName 文件名
	 * param pattern 验证文件格式的区间("JPEG,BMP,GIF,JPG,ICO,PNG,TIF")
	 */
	public static boolean FilePatternType(String fileName,String pattern){
		fileName = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()).toUpperCase();
		List list = new ArrayList();
		String typeStr = pattern;
		String typeArr[] = typeStr.split(",");
		for (int i = 0; i < typeArr.length; i++) {
			list.add(typeArr[i].toUpperCase());
		}
		if(list.contains(fileName))
			return true;
		else
			return false;
	}
	/**
	 * 验证文件是否是图片类型
	 * param fileName 文件名
	 */
	public static boolean isImagePattern(String fileName){
		fileName = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()).toUpperCase();
		List list = new ArrayList();
		String typeStr = "JPEG,BMP,GIF,JPG,ICO,PNG,TIF";
		String typeArr[] = typeStr.split(",");
		for (int i = 0; i < typeArr.length; i++) {
			list.add(typeArr[i].toUpperCase());
		}
		if(list.contains(fileName))
			return true;
		else
			return false;
	}
	/**
	 * 验证文件是否是视频类型
	 * param fileName 文件名
	 */
	public static boolean isRadioPattern(String fileName){
		fileName = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()).toUpperCase();
		List list = new ArrayList();
		String typeStr = "AVI,MPG,MPEG,RMVB,RM,MP4,MOV";
		String typeArr[] = typeStr.split(",");
		for (int i = 0; i < typeArr.length; i++) {
			list.add(typeArr[i].toUpperCase());
		}
		System.out.println(list);
		if(list.contains(fileName))
			return true;
		else
			return false;
	}
	
	/**
	 * 验证str是否是数字
	 * @author lxie
	 **/
	public static boolean isNumeric(String str){
          Pattern pattern = Pattern.compile("^-{0,1}[0-9]+(.[0-9]+){0,1}$");
          Matcher isNum = pattern.matcher(str);
          if( !isNum.matches()){
                return false;
          }
          return true;
    }
	public static void main(String[] args) {
		System.out.println(FilePattern.isNumeric("7.42999999999999971578290569595992565155029296875"));
		System.out.println(FilePattern.isNumeric("2227.42999999999999971578290569595992565155029296875"));
		System.out.println(FilePattern.isNumeric("72.42sdsaf22222222"));
		System.out.println(FilePattern.isNumeric("2.sadf"));
		System.out.println(FilePattern.isNumeric("222.3"));
		System.out.println(FilePattern.isNumeric("2"));
		System.out.println(FilePattern.isNumeric("-2"));
		System.out.println(FilePattern.isNumeric("-222.3222742999999999999971578290569595992565155029296875"));
	}
}
