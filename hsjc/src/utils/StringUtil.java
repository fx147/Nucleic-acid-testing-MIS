package utils;

/**
 * 字符串工具类
 * 
 * @author admin
 *
 */

public class StringUtil {
	/**
	 * 判断是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否不是空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNoEmpty(String str) {
		if (str != null && "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断字符串是否为纯数字
	 */
	public static boolean isNumstr(String str) {
		int i= 0;
		if(str.length()==0) { 
			return false;
		}else {
			i = str.length();
		}
		do{
			i--;
			if(str.charAt(i)<48 || str.charAt(i)>57) {//数字的ASII值从48到57
				return false;
			}
			return true;
		}while(i>0);
		//return false;
	}
	/**
	 * 判断电话号码是否合法
	 * @return
	 */
	public static boolean isCorrectTle(String tle) {
		if(tle.length()==11 && isNumstr(tle)) {
			return true;
		}else {
			return false;
		}	
	}
	/**
	 * 判断居民号是否合法
	 * 居民号为18位纯数字或17位数字+X
	 * @param uid
	 * @return
	 */
	public static boolean isCorrectUid(String uid) {
		
		if(uid.length()==18) {//第一需要满足18位
			String substr1 = uid.substring(0,17);
			char last = uid.charAt(17);
			if(isNumstr(uid)) {//第二18位纯数字
				return true;
			}
			else if(isNumstr(substr1) && last=='X') {//或者前17位纯数字，最后一位为X
				return true;
			}
			else {
				return false;
			}
			
		}else {
			return false;
		}
		
	}
	public static boolean isCorrectDid(String did) {
		if(did.length()==10 && isNumstr(did)) {
			return true;
		}else {
			return false;
		}	
	}
	
	public static void main(String[] args) {
		if(isCorrectDid("1117123331")) {
			System.out.println("ok");
		}else {
			System.out.println("not ok");
		}
	}
}
