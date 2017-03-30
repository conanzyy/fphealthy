package com.friday.pro.core.web.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * xml处理累
 * @author lxie
 */
public class Dom4jWriteXml {

	public static void main(String[] args) {
		// 创建文档对象
		Document doc = DocumentHelper.createDocument();
		// 创建根元素
		Element root = doc.addElement("students");
		// 添加第一层的子元素
		Element ele = root.addElement("student").addAttribute("id", "123");

		// 添加更下一层的子元素及属性
		ele.addElement("name").addText("java");
		ele.addElement("course").addText("jsp");

		// 添加第一层的子元素
		Element ele2 = root.addElement("student").addAttribute("id", "323");

		// 添加更下一层的子元素及属性
		ele2.addElement("name").addText("oracle");
		ele2.addElement("course").addText("ejb");

		// 创建文件名
		String fileName = "student.xml";
		// 创建输出流，以及字符编码的设定
		OutputStreamWriter out = null;
		XMLWriter writer = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(fileName),
					"utf-8");
			// 创建文档格式化对象
			OutputFormat format = OutputFormat.createPrettyPrint();
			// 创建字符输出流,并且套接输出流和格式话对象
			writer = new XMLWriter(out, format);
			// 将xml文档写入内存
			writer.write(doc);
		} catch (UnsupportedEncodingException e) {
			System.out.println(e);
		} catch (FileNotFoundException e) {
			System.out.println(e);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流
				writer.close();
				// 关闭流
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
