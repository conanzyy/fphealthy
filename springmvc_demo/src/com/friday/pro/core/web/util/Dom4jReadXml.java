package com.friday.pro.core.web.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * xml处理累
 * 
 * @author lxie
 */
public class Dom4jReadXml {
	/**
	 * 读取XML文档
	 * */
	public static Document loadXML(String filename) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			saxReader.setEncoding("utf-8");
			document = saxReader.read(new File(filename)); // 读取XML文件,获得document对象
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	/**
	 * 读取根节点
	 * */
	public static Element getRoot(Document document) {
		Element root = document.getRootElement();
		return root;
	}

	/**
	 * 读取子节点 root : 根节点 childName ：子节点元素名称 childs ：{@id,name,value}
	 * '@'表示为节点元素的属性，name，value为节点元素的值
	 * */
	public static List<Map> getChild(Element root, String childName,
			List<String> childs) {
		// 封装数据
		List<Map> elem_l = new ArrayList();
		try {
			// 得到'childName'变量元素的集合
			List<Element> list = root.elements(childName);
			// 使用迭代器遍历
			Iterator<Element> it = list.iterator();

			while (it.hasNext()) {
				// 得到每一个'childName'变量元素
				Element st = it.next();
				Map<String, String> elem_m = elem_m = new HashMap();
				for (int i = 0; i < childs.size(); i++) {
					String child = childs.get(i);
					if (child.indexOf("@") == 0) {
						child = child.substring(1);
						elem_m.put(child, st.attributeValue(child));
					} else {
						elem_m.put(child, st.elementText(child));
					}
				}
				elem_l.add(elem_m);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return elem_l;
	}

	/**
	 * 利用"/"表示从根节点开始选取得到List的集合 childName : 格式 "/根节点/子节点/子节点"
	 * */
	public static List<String> getSelectNodesChild(Document document,
			String childName) {
		// 封装数据
		List<String> elem_l = new ArrayList();
		try {
			List<Element> list = document.selectNodes(childName);
			// 利用for-each遍历
			for (Element el : list) {
				// 得到每一个name元素
				Element ele = el;
				// 封装每一个name元素的文本值
				elem_l.add(ele.getText());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return elem_l;
	}

	/**
	 * 获得指定路径的所有属性 childName : 格式 "/根节点/子节点/@属性名"
	 * */
	public static List<String> getSelectAttrChild(Document document,
			String childName) {
		// 封装数据
		List<String> elem_l = new ArrayList();
		try {
			List<Attribute> attlist = document.selectNodes(childName);
			// 遍历属性值
			for (int i = 0; i < attlist.size(); i++) {
				// 得到所有的属性
				Attribute att = attlist.get(i);
				// 封装属性名以及对应的属性值
				// System.out.println(att.getName()+" "+att.getValue());
				elem_l.add(att.getValue());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return elem_l;
	}

	/**
	 * 尝试用"//表示从当前匹配节点选择文档中的节点" childName : 格式 "//子节点/@属性名"
	 * */
	public static List<Object> getAttrChild(Document document, String childName) {
		// 封装数据
		List<Object> elem_l = new ArrayList();
		try {
			List<Attribute> atlist = document.selectNodes(childName);
			// 循环得到属性
			for (Attribute attri : atlist) {
				// 得到每一个属性
				Attribute attribute = attri;
				// 得到每一个属性值
				elem_l.add(attribute.getData());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return elem_l;
	}

	public static void main(String[] args) {
		// message.xml
		/*
		 * <?xml version="1.0" encoding="utf-8"?> <students> <student id="123">
		 * <name>java</name> <course>jsp</course> </student> <student id="323">
		 * <name>rose</name> <course>ejb</course> </student> </students>
		 */

		String fileName = "src/message.xml";
		Document doc = Dom4jReadXml.loadXML(fileName);
		Element root = Dom4jReadXml.getRoot(doc);
		List<String> childs = new ArrayList();
		childs.add("@id");
		childs.add("name");
		childs.add("course");
		List<Map> elem_l = Dom4jReadXml.getChild(root, "student", childs);
		System.out.println(elem_l);

		/*
		 * =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
		 * *=*=*=*=*=*=*=*=*=*=
		 */

		String childName = "/students/student/name";
		List elemChild_l = Dom4jReadXml.getSelectNodesChild(doc, childName);
		System.out.println(elemChild_l);

		/*
		 * =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
		 * *=*=*=*=*=*=*=*=*=*=
		 */

		childName = "/students/student/@id";
		elemChild_l = Dom4jReadXml.getSelectAttrChild(doc, childName);
		System.out.println(elemChild_l);

		/*
		 * =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=
		 * *=*=*=*=*=*=*=*=*=*=
		 */

		childName = "//student/@id";
		elemChild_l = Dom4jReadXml.getAttrChild(doc, childName);
		System.out.println(elemChild_l);

	}
}
