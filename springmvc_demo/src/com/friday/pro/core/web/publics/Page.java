package com.friday.pro.core.web.publics;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：工具类
 * @author: lxie
 */
public class Page {
	
	public static void main(String[] args) {
		System.out.println(Page.pageView(7, 5, 6, 20));
	}
	
	
	/**
	 * 显示页码列表
	 * @param pageNow ：当前页码
	 * @param pageBefore ：当前页码前的页数
	 * @param pageAfter  ：当前页码后的页数
	 * @param totalPage	   ：总页数
	 * @return
	 */
	public static List pageView(int pageNow,int pageBefore,int pageAfter,int totalPage){
		List page_l = new ArrayList();
		if(pageNow-pageBefore <= 0){
			for (int i = pageNow-1; i > 0; i--) {
				page_l.add(pageNow-i);
			}
		}
		else{
			for (int i = pageBefore; i > 0; i--) {
				page_l.add(pageNow-i);
			}
		}
		if(pageNow+pageAfter > totalPage){
			pageAfter = pageAfter-(pageNow+pageAfter-totalPage);
			for (int i = 0; i < pageAfter+1; i++) {
				page_l.add(pageNow+i);
			}
		}else{
			for (int i = 0; i < pageAfter+1; i++) {
				page_l.add(pageNow+i);
			}
		}
		return page_l;
	}
	
}
