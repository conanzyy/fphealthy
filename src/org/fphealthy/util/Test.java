package org.fphealthy.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
public class Test{

	 public static void main(String[] args) throws Exception {    
		   queryList();
	 }
	 @SuppressWarnings("unchecked")
	public static void queryList() throws Exception {
			List list=new ArrayList();
			List<HashMap> idaset=new ArrayList<HashMap>();
			HashMap data=new HashMap();
			HashMap data2=new HashMap();
			data2.put("Astr", "h");
			data2.put("Bstr", "8");
			data2.put("Order", "0");
			list.add(data2);
	    	data.put("Astr", "g");
	    	data.put("Bstr", "7");
			data.put("Order", "0");
			list.add(data);
			
			
				/**用来让主线程等待threadCount个子线程执行完毕 **/
				CountDownLatch countDownLatch = new CountDownLatch(1); 
				/**启动threadCount个子线程 **/ 
				Thread thread = new Thread(new CheckLoginMessageXML(list,data,idaset,countDownLatch));
				thread.start(); 
				 try{             
					 /**主线程等待所有子线程执行完成，再向下执行 **/ 
					 countDownLatch.await();
					 }catch (InterruptedException e){             
						 System.out.println(e);
					 }  
//			}
		}
	 
}
