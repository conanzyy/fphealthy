package org.fphealthy.webservice.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.fphealthy.dao.Mydao;
import org.fphealthy.webservice.Insert;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class InsertImpl implements Insert {
	Logger log = Logger.getLogger("lavasoft"); 
	protected HashMap data;
	/**存放返回报文XML类型**/
	protected String reter_xml_type;
	
	private String result; 
	
	@SuppressWarnings("unchecked")
	List<HashMap> idaset;
	private Mydao dao;
	@Autowired
	public void setMydao(Mydao dao) {
		this.dao = dao;
	}
	@Override
	public String doinsert(String xml){
		SAXBuilder builder = new SAXBuilder();
		Reader reader = new StringReader(xml);
		try {
			Document doc = builder.build(reader);
			Element rootEl = doc.getRootElement();
			if ("SvcCont".equals(rootEl.getName())) {
				if(!getData(rootEl)){
					log.info("111111");
				}
			} else {
				log.info("222222");
			}
		} catch (JDOMException e) {
			log.info("33333"+e);
		} catch(Exception e){
			log.info(e.getMessage());
		}
	//		List alist=(List)( idaset.get(0).get("Relation"));
			try {
				List alist=	(List) data.get("Relation");
				System.out.println("list:"+alist);
				int size=alist.size();
				for(int i=0;i<size;i++){
					Object[] objs=new Object[3];
					HashMap map=(HashMap)(alist.get(i));
					objs[0]= map.get("Astr");
					objs[1]=map.get("Bstr");
					objs[2]=(map.get("Order")==null||map.get("Order").equals("null")||map.get("Order").equals(""))?0:map.get("Order");
					dao.update("insert ignore into relations(astr,bstr,`orderi`) values(?,?,?)", objs);
				}
				return "ok";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "fail:"+e.getMessage();
			}
	}
	protected final boolean getData(Element rootEl) throws Exception {
		Element DATAHEAD = (Element)rootEl.getChildren("Relations").get(0);
		data=getDataByElement(DATAHEAD);
		idaset.add(data);
	return true;
	}
	@SuppressWarnings("unchecked")
	protected final HashMap getDataByElement(Element element) throws Exception{
		HashMap data = new HashMap();
		List list=element.getChildren("Relation");
		List recommendlist=new ArrayList();
		for(int i=0;i<list.size();i++){
			Element productInfo =(Element)list.get(i);
			Map infodata=new HashMap();
			infodata.put("Astr", getElementText(productInfo.getChild("Astr")));
			infodata.put("Bstr", getElementText(productInfo.getChild("Bstr")));
			infodata.put("Order", getElementText(productInfo.getChild("Order")));
			recommendlist.add(infodata);
		}
		data.put("Relation", recommendlist);
		return data;
	}
	protected final String getElementText(Element element) {
		if (element == null)
			return "";
		return element.getTextTrim();
	}
}
