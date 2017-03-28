package org.course.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.course.DBbean.UserBean;
import org.course.entity.AddressBook;
import org.course.message.resp.Article;
import org.course.message.resp.Music;
import org.course.message.resp.MusicMessage;
import org.course.message.resp.NewsMessage;
import org.course.message.resp.TextMessage;
import org.course.util.MessageUtil;
import org.course.util.PublicUtil;
import org.course.yahoo.geocode.query;
import org.course.yahoo.weather.YahooWeather;
import org.course.yahoo.weather.yweather.Forecast;




/**
 * 核心服务类
 * 
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	
	public static String str="";
	public static StringBuffer sb;
	
	public  static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
            System.out.println("用户发送消息类型："+msgType);
			// 默认回复此文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setFuncFlag(0);
//			NewsMessage newsMessage = new NewsMessage();
//			newsMessage.setToUserName(fromUserName);
//			newsMessage.setFromUserName(toUserName);
//			newsMessage.setCreateTime(new Date().getTime());
//			newsMessage.setFuncFlag(0);
			//用户 关注/取消关注事件处理
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				String event=requestMap.get("Event");
				// 这是新用户关注时默认发的一条信息。可以做一个欢迎处理。
				if(MessageUtil.EVENT_TYPE_SUBSCRIBE.equals(event)){
					textMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
					textMessage.setContent(getMainMenu());
					respMessage = MessageUtil.textMessageToXml(textMessage);
//					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//					textMessage.setContent(getMainMenu());
					
//					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//					textMessage.setContent(getMainMenu());
					// 将文本消息对象转换成xml字符串
					//关注推送图文信息
//					List<Article> articleList = new ArrayList<Article>();
//					Article article1 = new Article();
//					article1.setTitle("员工信息1");
//					article1.setDescription("");
//					article1.setPicUrl("http://www.zhukejia.cn/images/Koala.jpg");
//					article1.setUrl("http://192.168.47.1:7001/salemobile/work/work/getPic.do");
//					Article article2 = new Article();
//					article2.setTitle("员工testtest");
//					article2.setDescription("");
//					article2.setPicUrl("http://www.zhukejia.cn/images/Chrysanthemum.jpg");
//					article2.setUrl("http://192.168.47.1:7001/salemobile/work/work/getDetailWork.do");
//					Article article3 = new Article();
//					article3.setTitle("员工员工test");
//					article3.setDescription("");
//					article3.setPicUrl("http://www.zhukejia.cn/images/Desert.jpg");
//					article3.setUrl("http://192.168.47.1:7001/salemobile/work/work/worksearchresult.do");
//					articleList.add(article1);
//					articleList.add(article2);
//					articleList.add(article3);
//					newsMessage.setArticleCount(articleList.size());
//					newsMessage.setArticles(articleList);
//					respMessage = MessageUtil.newsMessageToXml(newsMessage);
//					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
//					newsMessage.setArticles(articleList);
//					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// 这是用户取消关注时做处理。（为以后进行账户和微信解绑）
				else if(MessageUtil.EVENT_TYPE_UNSUBSCRIBE.equals(event)){
					
				}
				
			}
			// 用户发送文本消息处理
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content");
				//回复文本类型
				textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
				if ("？".equals(content)||"?".equals(content)) {
					// 这是用户发查询编码。可以做一个查询菜单处理。
					textMessage.setContent(getMainMenu());
					// 将文本消息对象转换成xml字符串
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
				//回复音乐信息
				else if("听歌".equals(content)){
					respMessage=getMusicText(requestMap);
				}
				else if("董天天".equals(content)){
					textMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
					textMessage.setContent(getMainMenu());
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}else if("章小飞".equals(content)){
					textMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
					textMessage.setContent(fromUserName+"--"+toUserName+"--"+getSb());
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
				//回复通讯录信息 （连结数据库查询）
				else if(PublicUtil.isContainChinese(content)){
					textMessage.setContent(getAddressBookInfo(requestMap));
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
				//回复图文信息
				else {
					respMessage=gettext(requestMap);
				}
			}
			//用户发送位置PO
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respMessage=getWeather(requestMap);
			}
			//用户发送图片
			else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){
				 textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
				 textMessage.setContent("好漂亮的图片。[色]");
				// 将文本消息对象转换成xml字符串
				respMessage = MessageUtil.textMessageToXml(textMessage);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(respMessage);
		return respMessage;
	}
	
	private static String getSb() {
		// TODO Auto-generated method stub
		String respMessage = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append("此人是傻逼![右哼哼]");
		respMessage=buffer.toString();
		return respMessage;
	}

	/**
	 * 查询通讯录信息
	 * @param requestMap
	 * @return
	 */
	public static String getAddressBookInfo(Map<String, String> requestMap){
		UserBean userbean=new UserBean();
		String respMessage = null;
		// 接收用户发送的文本消息内容
		String content = requestMap.get("Content");
		List<AddressBook> addressBookList=userbean.getAddressBookInfo(content);
		if(addressBookList==null||addressBookList.size()==0){
			respMessage="查无此人![右哼哼]";
		}else{
			StringBuffer buffer = new StringBuffer();
			for(int i=0;i<addressBookList.size();i++){
				buffer.append("姓名："+((AddressBook)addressBookList.get(i)).getUserName()).append("\n");
				buffer.append("工号："+((AddressBook)addressBookList.get(i)).getJobNumber()).append("\n");
		 		buffer.append("邮箱："+((AddressBook)addressBookList.get(i)).getMail()).append("\n");
				buffer.append("手机："+((AddressBook)addressBookList.get(i)).getMobile()).append("\n");
				buffer.append("*******************************").append("\n\n");
			}
			respMessage=buffer.toString();
		}
		return respMessage;
	}
	
	
	/**
	 * 发送天气预报
	 * @param requestMap
	 * @return
	 */
	public static String  getWeather(Map<String, String> requestMap){
		String respMessage = null;
		try {
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");

			// 默认回复此文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			StringBuffer content = new StringBuffer("亲!").append("\n\n");
			// 此处先调用Yahoo的PlaceFinder服务，获取用户当前所在地的woeid。
			// 再调用Yahoo的Weather服务获取天气情况。
			String placeUrl ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20geo.placefinder%20where%20text=\""+ requestMap.get("Location_X") +","+requestMap.get("Location_Y")+"\"%20and%20gflags=\"R\"";
			JAXBContext jc = JAXBContext.newInstance(query.class);
			Unmarshaller u = jc.createUnmarshaller();
			URL url = new URL(placeUrl);
			query query = (query) u.unmarshal(url);
			content.append(query.getResults().getResult().getCity() + "：");
			//获取当前所在地的woeid查询当地的天气情况
			String weatherUrl = String.format("http://weather.yahooapis.com/forecastrss?w=%s&u=c", query.getResults().getResult().getWoeid());
			url = new URL(weatherUrl);
			jc = JAXBContext.newInstance(YahooWeather.class);
			u = jc.createUnmarshaller();
			YahooWeather weather = (YahooWeather) u.unmarshal(url);
			List<Forecast> list = weather.getChannel().getItem().getForecasts();
			content.append("今天最低温度" + list.get(0).getLow() + "℃，最高温度" + list.get(0).getHigh() + "℃").append("\n\n");
			content.append("明天最低温度" + list.get(1).getLow() + "℃，最高温度" + list.get(1).getHigh() + "℃").append("\n\n");
			if (list.get(0).getHigh() <= 15 || list.get(1).getHigh() <= 15) {
				content.append("天凉，注意保暖哦!").append("\n\n");
			} else if(list.get(0).getHigh() <= 25 || list.get(1).getHigh() <= 25) {
				if (list.get(0).getCode() >= 26 && list.get(0).getCode() <= 32) {
					content.append("晴空万里，出去走走吧!").append("\n\n");
				}
			} else {
				content.append("出去看看大街上的黑丝吧!").append("\n\n");
			}
			textMessage.setContent(content.toString());
			respMessage = MessageUtil.textMessageToXml(textMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
	
	/**
	 * 发送图文消息
	 * @param requestMap
	 * @return
	 */
	public static String  gettext(Map<String, String> requestMap){
		String respMessage = null;
		
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		
		TextMessage textMessage = new TextMessage();
		// 接收用户发送的文本消息内容
		String content = requestMap.get("Content");
		// 创建图文消息
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setFuncFlag(0);

		List<Article> articleList = new ArrayList<Article>();
		// 单图文消息
		if ("3".equals(content)) {
			Article article = new Article();
			article.setTitle("双色球的推荐和分析");
			article.setDescription("双色球的推荐和分析");
			article.setPicUrl("http://3.yclandroid.duapp.com/image/3.jpg");
			article.setUrl("http://3g.500.com/info/info_more?flag=&colid=59&rnd=206LDKQPJM04KG431C");
			articleList.add(article);
			// 设置图文消息个数
			newsMessage.setArticleCount(articleList.size());
			// 设置图文消息包含的图文集合
			newsMessage.setArticles(articleList);
			// 将图文消息对象转换成xml字符串
			respMessage = MessageUtil.newsMessageToXml(newsMessage);
		}
		// 单图文消息---不含图片
		else if ("4".equals(content)) {
			Article article = new Article();
			article.setTitle("中奖号码");
			// 图文消息中可以使用QQ表情、符号表情
			article.setDescription("近20期的双色球中奖信息");
			// 将图片置为空
			article.setPicUrl("http://3.yclandroid.duapp.com/image/5.jpg");
			article.setUrl("http://wap.24cai.com/open/getTermsListByType.do?lotteryType=3&pageSize=10");
			articleList.add(article);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			respMessage = MessageUtil.newsMessageToXml(newsMessage);
		}
		// 多图文消息
		else if ("1".equals(content)) {
			Article article1 = new Article();
			article1.setTitle("最新开奖信息");
			article1.setDescription("最新开奖信息");
			article1.setPicUrl("http://2.yclandroid.duapp.com/1.jpg");
			article1.setUrl("http://wap.24cai.com");

			Article article2 = new Article();
			article2.setTitle("【1】双色球的开奖信息");
			article2.setDescription("");
			article2.setPicUrl("http://3.yclandroid.duapp.com/image/ycl.jpg");
			article2.setUrl("http://3g.500.com/info/prize/open/index;jsessionid=5CE6DE6E2518F963EE6700554F0AB4B2?hzid=2353&rnd=49R8TLVTXFH5HIAO4C");

			Article article3 = new Article();
			article3.setTitle("【2】大乐透的开奖信息");
			article3.setDescription("");
			article3.setPicUrl("http://3.yclandroid.duapp.com/image/ycl.jpg");
			article3.setUrl("http://wap.24cai.com/buy/index.do?lotteryType=23");

			articleList.add(article1);
			articleList.add(article2);
			articleList.add(article3);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			respMessage = MessageUtil.newsMessageToXml(newsMessage);
		}
		// 多图文消息---首条消息不含图片
		else if ("2".equals(content)) {
			Article article1 = new Article();
			article1.setTitle("专家名人合买单推荐");
			article1.setDescription("");
			// 将图片置为空
			article1.setPicUrl("http://3.yclandroid.duapp.com/image/2.jpg");
			article1.setUrl("http://wap.24cai.com");

			Article article2 = new Article();
			article2.setTitle("大乐透合买单推荐");
			article2.setDescription("");
			article2.setPicUrl("http://3.yclandroid.duapp.com/image/ycl.jpg");
			article2.setUrl("http://3g.500.com/buy/hm/hm_index?flag=dlt&from=wx");

			Article article3 = new Article();
			article3.setTitle("竞彩足球合买单推荐");
			article3.setDescription("");
			article3.setPicUrl("http://3.yclandroid.duapp.com/image/ycl.jpg");
			article3.setUrl("http://3g.500.com/buy/hm/jczq/parti.action?from=wx");

			Article article4 = new Article();
			article4.setTitle("任选九合买单推荐");
			article4.setDescription("");
			article4.setPicUrl("http://3.yclandroid.duapp.com/image/ycl.jpg");
			article4.setUrl("http://3g.500.com/info/info_hero?lotid=10000&type=1&?from=wx");

			articleList.add(article1);
			articleList.add(article2);
			articleList.add(article3);
			articleList.add(article4);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			respMessage = MessageUtil.newsMessageToXml(newsMessage);
		}
		// 多图文消息---最后一条消息不含图片
		else if ("5".equals(content)) {
			Article article1 = new Article();
			article1.setTitle("彩票图表分析");
			article1.setDescription("");
			article1.setPicUrl("http://3.yclandroid.duapp.com/image/4.jpg");
			article1.setUrl("http://wap.24cai.com");

			Article article2 = new Article();
			article2.setTitle("福彩3D图表走势图\n组选图表分析");
			article2.setDescription("");
			article2.setPicUrl("http://3.yclandroid.duapp.com/image/ycl.jpg");
			article2.setUrl("http://wap.24cai.com/chart/toAnalysis.do?lotteryType=fc3d&flag=4");

			Article article3 = new Article();
			article3.setTitle("双色球图表走势图\n红球前区图表分析");
			article3.setDescription("");
			// 将图片置为空
			article3.setPicUrl("http://3.yclandroid.duapp.com/image/ycl.jpg");
			article3.setUrl("http://wap.24cai.com/chart/toAnalysis.do?lotteryType=ssq&flag=1");

			articleList.add(article1);
			articleList.add(article2);
			articleList.add(article3);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			respMessage = MessageUtil.newsMessageToXml(newsMessage);
		}
		//员工信息首页
		else if("test".equals(content)){
			Article article1 = new Article();
			article1.setTitle("员工信息1");
			article1.setDescription("");
			article1.setPicUrl("http://www.zhukejia.cn/images/Koala.jpg");
			article1.setUrl("http://192.168.47.1:7001/salemobile/work/work/getPic.do");
			Article article2 = new Article();
			article2.setTitle("员工testtest");
			article2.setDescription("");
			article2.setPicUrl("http://www.zhukejia.cn/images/Chrysanthemum.jpg");
			article2.setUrl("http://192.168.47.1:7001/salemobile/work/work/getDetailWork.do");
			Article article3 = new Article();
			article3.setTitle("员工员工test");
			article3.setDescription("");
			article3.setPicUrl("http://www.zhukejia.cn/images/Desert.jpg");
			article3.setUrl("http://192.168.47.1:7001/salemobile/work/work/worksearchresult.do?flag=1");
			articleList.add(article1);
			articleList.add(article2);
			articleList.add(article3);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);
			respMessage = MessageUtil.newsMessageToXml(newsMessage);
		}
		else{
			textMessage.setContent("此功能还在开发中");
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			respMessage = MessageUtil.textMessageToXml(textMessage);
		}
		return respMessage;
	}
	
	/**
	 * 发送音乐消息
	 * @param requestMap
	 * @return
	 */
	public static String  getMusicText(Map<String, String> requestMap){
		String respMessage = null;
		
		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");

		// 创建音乐消息
		MusicMessage musicmessage = new MusicMessage();
		musicmessage.setToUserName(fromUserName);
		musicmessage.setFromUserName(toUserName);
		musicmessage.setCreateTime(new Date().getTime());
		musicmessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
		musicmessage.setFuncFlag(0);
		Music music=new Music();
		music.setDescription("Bandari");
		music.setHQMusicUrl("http://www.zhukejia.cn/voice/10010.mp3");
		music.setMusicUrl("http://www.zhukejia.cn/voice/10010.mp3");
		music.setTitle("寂静山林Silence");
		musicmessage.setMusic(music);
		respMessage = MessageUtil.musicMessageToXml(musicmessage);
		return respMessage;
	}
	
	
	/**
	 * 推送图文消息
	 */
	
	public static String getMainMenu1(){
		
		
		return "";
	}
	
	/**
	 * 关注推送图文信息
	 */
	public static String getMainPicMenu(Map<String, String> requestMap) {
		String respMessage = null;
		List<Article> articleList = new ArrayList<Article>();
		NewsMessage newsMessage = new NewsMessage();
		Article article1 = new Article();
		article1.setTitle("员工信息1");
		article1.setDescription("");
		article1.setPicUrl("http://www.zhukejia.cn/images/Koala.jpg");
		article1.setUrl("http://192.168.47.1:7001/salemobile/work/work/getPic.do");
		Article article2 = new Article();
		article2.setTitle("员工testtest");
		article2.setDescription("");
		article2.setPicUrl("http://www.zhukejia.cn/images/Chrysanthemum.jpg");
		article2.setUrl("http://192.168.47.1:7001/salemobile/work/work/getDetailWork.do");
		Article article3 = new Article();
		article2.setTitle("员工员工test");
		article2.setDescription("");
		article2.setPicUrl("http://www.zhukejia.cn/images/Desert.jpg");
		article2.setUrl("http://192.168.47.1:7001/salemobile/work/work/worksearchresult.do");
		articleList.add(article1);
		articleList.add(article2);
		articleList.add(article3);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		respMessage = MessageUtil.newsMessageToXml(newsMessage);
		return respMessage;
		
	}
	
	/**
	 * 主菜单
	 * 
	 * @return
	 */
	//http://192.168.1.101/salemobile/work/work/getPic.do
	public static String getMainMenu() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("客户经理  董天天  您好:").append("\n\n");
		buffer.append("您的工作数：6").append("\n");
		buffer.append("已完成工作数：5").append("\n");
		buffer.append("未完成工作数：1").append("\n\n");
		buffer.append("<a href=''>点击登录</a>");
		buffer.append("  ");
		buffer.append("<a href='http://192.168.47.1:7001/salemobile/work/work/getFrontPage.do'>查看工作详情</a>").append("\n");
		buffer.append("输入相关序号，查看信息：").append("\n");
		//buffer.append("<a href='http://www.zhukejia.cn/testpage/test1.html'>点击进入主页</a>").append("\n");
		buffer.append("【1】  最新彩票编辑数字[1,2,3,4,5]").append("\n");
 		buffer.append("【2】  天气预报 请发一个地理位置的PO").append("\n");
		buffer.append("【3】  输入部门员工名字可查询联系方式").append("\n");
		buffer.append("【4】  歌曲聆听 输入听歌").append("\n");
		buffer.append("【5】  员工信息输入 test").append("\n");
		//buffer.append("<a href='http://192.168.47.1:7001/salemobile/work/work/getFrontPage.do'><img src='http://www.zhukejia.cn/images/Koala.jpg'/></a>").append("\n");
		//buffer.append("<img src='http://www.zhukejia.cn/images/Koala.jpg'/>").append("\n");
		//带参数传递
		//buffer.append("<a href='http://192.168.47.1:7001/salemobile/work/work/getFrontPage.do'>员工信息</a>").append("\n");
		buffer.append("回复“?”显示此帮助菜单").append("\n\n");
//		buffer.append("【1】  最新彩票编辑数字[1,2,3,4,5]").append("\n");
// 		buffer.append("【2】  天气预报 请发一个地理位置的PO").append("\n");
//		buffer.append("【3】  输入部门员工名字可查询联系方式").append("\n");
//		buffer.append("【4】  歌曲聆听 输入听歌").append("\n\n");
//		buffer.append("回复“?”显示此帮助菜单");
		return buffer.toString();
	}
	
}