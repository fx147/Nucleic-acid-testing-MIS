package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import dao.UserDao;
import model.User;

/**
 * 用于保存账号和密码
 * 
 *
 */
public class LoginConfig {
	/**
	 * 把账号密码写入文件
	 * 
	 * @param user
	 */
	public static void save(String name,String pwd,int index) {
		List<String> list = new ArrayList<>();
		list.add("name" + name);
		list.add("pwd" + pwd);
		list.add("index" + index);
		
		//可以把信息都查出来
		try {
			FileUtils.writeLines(new File("password.txt"), list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取账号和密码 和用户id
	 */
	public static List<String> reader() {

		String str = "";
		try {
			str = FileUtils.readFileToString(new File("password.txt"), "GBK");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String userName = subString(str, "name", "pwd");// 账号
		String password = subString(str, "pwd", "index");// 账号
		
		int indexOf2 = str.indexOf("index");//返回位置
		String indexOf1 = str.substring(indexOf2);
		String index = indexOf1.substring(5);
		List<String> list = new ArrayList<>();
		list.add(userName.trim());
		list.add(password.trim());
		list.add(index.trim());
		
		return list;
	}

	public static String subString(String str, String strStart, String strEnd) {
		/* 找出指定的2个字符在 该字符串里面的 位置 */
		int strStartIndex = str.indexOf(strStart);
		int strEndIndex = str.indexOf(strEnd);

		/* index 为负数 即表示该字符串中 没有该字符 */
		if (strStartIndex < 0) {
			return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
		}
		if (strEndIndex < 0) {
			return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
		}
		/* 开始截取 */
		String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
		return result;
	}

	/**
	 * 清空文件
	 */
	public static void clean() {
		File file = new File("password.txt");
		if (!file.exists()) {
			System.out.println("文件不存在");
		} else {
			file.delete();
		}

	}

	public static void main(String[] args) {
		List<String> list = reader();

//		String sql="select 姓名,居民号,住址 from 居民 where 电话号码=? and 密码=?";
//		List<User> jdbc_select = DButils.jdbc_select(sql, User.class, "17335357019" , "123456");
		
		String sql1="select * from 居民";
		List<User> jdbc_select = DButils.jdbc_select(sql1, User.class);
		
		List<User> user1 = UserDao.userlist(list.get(0));
		//System.out.println(user1);
		User fx=user1.get(0);
		System.out.println("用户名:" + fx.getUsername());
		System.out.println("密码:" + fx.getPassword());
		System.out.println("姓名:" + fx.getName());
		System.out.println("居民号:" + fx.getId());
		System.out.println("住址:" + fx.getAddress());
		
		// save(new User("胡绵发", "123456", 1));
	}
}
