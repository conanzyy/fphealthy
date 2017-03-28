package org.fphealthy.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.course.DBdao.DBMysql;
import org.fphealthy.service.FindService;
import org.fphealthy.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MyControl {
	Logger log = Logger.getLogger("lavasoft"); 
	private FindService myService;
	@Autowired
	public void setFindService(FindService myService) {
		this.myService = myService;
	}
	@RequestMapping(value = "/front")
	public ModelAndView doLoginIn(HttpServletRequest req,
			HttpServletResponse res, HttpSession sess) {
		return new ModelAndView("/jsp/frontpage");
	}
	
	@RequestMapping(value = "/getpic")
	public ModelAndView doLoginIn2(HttpServletRequest req,
			HttpServletResponse res, HttpSession sess) {
		return new ModelAndView("/jsp/getpic");
	}
	@RequestMapping(value = "/getResult")
	public String doSearch(HttpServletRequest req,
			HttpServletResponse res, HttpSession sess,String Astr,String Bstr,ModelMap model) throws IOException, ServletException {
		if(Astr==null||Astr.trim().equals("")||Bstr==null||Bstr.trim().equals("")){
			res.sendRedirect("front.do");
		}
		List showlist=myService.showList(Factory.MAXINT,Astr.trim(),Bstr.trim());
		model.addAttribute("showlist",showlist);
//		req.getRequestDispatcher("/jsp/result.jsp").forward(req, res);
//		System.out.println("-----------"+myService.showList(Factory.MAXINT,Astr,Bstr));
		return "/jsp/result";
	}
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest req,
			HttpServletResponse res) throws IOException, ServletException {
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		String printStr=null;
		 try {
				String arg0 = "";
				String endpoint="http://172.20.182.1:8080/services/Login";
				String wsdl="http://webservice.fphealthy.org";
				String wsdlName="login";
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				QName qname=new QName(wsdl,wsdlName);
				call.setOperationName(qname);
				call.addParameter("in0", org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
				printStr = (String)call.invoke(new Object[]{arg0});
				System.out.println("---------"+printStr);
	     		//CountDownLatch countDownLatch = new CountDownLatch(1); 
	     		//Thread thread = new Thread(new CheckLoginVerificationXML(result,idaset,countDownLatch));          
	     		//thread.start(); 
	     		//countDownLatch.await();    
			 	}
		      catch (Exception e) {
		      }
		res.getOutputStream().print(printStr);
		res.getOutputStream().close();
		return null;
	}
}

