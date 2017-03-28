package org.course.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共工具类
 * 
 * @author zhukejia
 * @date 2013
 */

public class PublicUtil {
	/**
	 * 检测字符串是否包含字符
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */

	public static boolean isContainChinese(String str) {

		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}
}
