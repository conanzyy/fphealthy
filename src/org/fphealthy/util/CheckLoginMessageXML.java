package org.fphealthy.util;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;

public class CheckLoginMessageXML implements Runnable{
	
	public static Logger log=Logger.getLogger(CheckLoginMessageXML.class);
	private CountDownLatch countDownLatch;
	@SuppressWarnings("unchecked")
	private HashMap data;
	@SuppressWarnings("unchecked")
	private List<HashMap> idaset;
	private List list;
	private String result;
	@SuppressWarnings("unchecked")
	public CheckLoginMessageXML(List list,HashMap data,List<HashMap> idaset, CountDownLatch countDownLatch){ 
		 this.data = data;       
		 this.idaset = idaset; 
		 this.list=list;
		 this.countDownLatch = countDownLatch; 
	}
	public void run() {
		 try {
			String arg0 = generationXML(list);
			String endpoint="http://fphealthy.duapp.com/services/Insert";
			String wsdl="http://webservice.fphealthy.org";
			String wsdlName="doinsert";
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			QName qname=new QName(wsdl,wsdlName);
			call.setOperationName(qname);
			call.addParameter("in0", org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			result = (String)call.invoke(new Object[]{arg0});
			System.out.println(result);
     		//CountDownLatch countDownLatch = new CountDownLatch(1); 
     		//Thread thread = new Thread(new CheckLoginVerificationXML(result,idaset,countDownLatch));          
     		//thread.start(); 
     		//countDownLatch.await();    
		 	}
	      catch (Exception e) {
	      }
	      countDownLatch.countDown();    
	}
	@SuppressWarnings("unchecked")
	public String generationXML(List list) throws Exception {
		StringBuffer parser = new StringBuffer();
		parser.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?> ");
		parser.append(" <SvcCont>");
		parser.append(" 	<Relations> ");
		for(Object map:list){
			HashMap hmap=(HashMap)map;
			parser.append(" 	<Relation> ");
			parser.append(" 		<Astr>"+(String)hmap.get("Astr")+"</Astr>");
			parser.append(" 		<Bstr>"+(String)hmap.get("Bstr")+"</Bstr>");
			parser.append(" 		<Order>"+(String)hmap.get("Order")+"</Order>");
			parser.append(" 	</Relation> ");
		}
		parser.append(" 	</Relations> ");
		parser.append(" </SvcCont>");
		return parser.toString();
	}
}
