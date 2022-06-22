package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import dao.UserDao;
import model.User;

/**
 * ���ڱ����˺ź�����
 * 
 *
 */
public class LoginConfig {
	/**
	 * ���˺�����д���ļ�
	 * 
	 * @param user
	 */
	public static void save(String name,String pwd,int index) {
		List<String> list = new ArrayList<>();
		list.add("name" + name);
		list.add("pwd" + pwd);
		list.add("index" + index);
		
		//���԰���Ϣ�������
		try {
			FileUtils.writeLines(new File("password.txt"), list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ�˺ź����� ���û�id
	 */
	public static List<String> reader() {

		String str = "";
		try {
			str = FileUtils.readFileToString(new File("password.txt"), "GBK");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String userName = subString(str, "name", "pwd");// �˺�
		String password = subString(str, "pwd", "index");// �˺�
		
		int indexOf2 = str.indexOf("index");//����λ��
		String indexOf1 = str.substring(indexOf2);
		String index = indexOf1.substring(5);
		List<String> list = new ArrayList<>();
		list.add(userName.trim());
		list.add(password.trim());
		list.add(index.trim());
		
		return list;
	}

	public static String subString(String str, String strStart, String strEnd) {
		/* �ҳ�ָ����2���ַ��� ���ַ�������� λ�� */
		int strStartIndex = str.indexOf(strStart);
		int strEndIndex = str.indexOf(strEnd);

		/* index Ϊ���� ����ʾ���ַ����� û�и��ַ� */
		if (strStartIndex < 0) {
			return "�ַ��� :---->" + str + "<---- �в����� " + strStart + ", �޷���ȡĿ���ַ���";
		}
		if (strEndIndex < 0) {
			return "�ַ��� :---->" + str + "<---- �в����� " + strEnd + ", �޷���ȡĿ���ַ���";
		}
		/* ��ʼ��ȡ */
		String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
		return result;
	}

	/**
	 * ����ļ�
	 */
	public static void clean() {
		File file = new File("password.txt");
		if (!file.exists()) {
			System.out.println("�ļ�������");
		} else {
			file.delete();
		}

	}

	public static void main(String[] args) {
		List<String> list = reader();

//		String sql="select ����,�����,סַ from ���� where �绰����=? and ����=?";
//		List<User> jdbc_select = DButils.jdbc_select(sql, User.class, "17335357019" , "123456");
		
		String sql1="select * from ����";
		List<User> jdbc_select = DButils.jdbc_select(sql1, User.class);
		
		List<User> user1 = UserDao.userlist(list.get(0));
		//System.out.println(user1);
		User fx=user1.get(0);
		System.out.println("�û���:" + fx.getUsername());
		System.out.println("����:" + fx.getPassword());
		System.out.println("����:" + fx.getName());
		System.out.println("�����:" + fx.getId());
		System.out.println("סַ:" + fx.getAddress());
		
		// save(new User("���෢", "123456", 1));
	}
}
