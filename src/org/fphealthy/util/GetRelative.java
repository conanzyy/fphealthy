package org.fphealthy.util;

import java.util.ArrayList;
import java.util.HashMap;

public class GetRelative {
	private GetRelative(){}
	private static GetRelative real=null;
	public static GetRelative getInstance(){
		if(real==null){
			real=new GetRelative();
		}
		return real;
	}
	
}
