package org.fphealthy.webservice.impl;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.fphealthy.webservice.Login;

public class LoginImpl implements Login {
	Logger log = Logger.getLogger("lavasoft");

	@Override
	public String login(String xml) {
//			return "{ \"state\": { \"return\": \"true\", \"info\": \"\", \"code\": \"\" } }";
		// TODO Auto-generated method stub
		return "{\"name\":\"SUPERUSR\",\"number\":\"0\"}";
//		Context.Response.Write("{ \"state\": { \"return\": \"true\", \"info\": \"\", \"code\": \"\" } }");
//	    Context.Response.End();
	}

}
