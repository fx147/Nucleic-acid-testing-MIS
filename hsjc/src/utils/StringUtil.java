package utils;

/**
 * �ַ���������
 * 
 * @author admin
 *
 */

public class StringUtil {
	/**
	 * �ж��Ƿ�Ϊ��
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
	 * �ж��Ƿ��ǿ�
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
	 * �ж��ַ����Ƿ�Ϊ������
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
			if(str.charAt(i)<48 || str.charAt(i)>57) {//���ֵ�ASIIֵ��48��57
				return false;
			}
			return true;
		}while(i>0);
		//return false;
	}
	/**
	 * �жϵ绰�����Ƿ�Ϸ�
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
	 * �жϾ�����Ƿ�Ϸ�
	 * �����Ϊ18λ�����ֻ�17λ����+X
	 * @param uid
	 * @return
	 */
	public static boolean isCorrectUid(String uid) {
		
		if(uid.length()==18) {//��һ��Ҫ����18λ
			String substr1 = uid.substring(0,17);
			char last = uid.charAt(17);
			if(isNumstr(uid)) {//�ڶ�18λ������
				return true;
			}
			else if(isNumstr(substr1) && last=='X') {//����ǰ17λ�����֣����һλΪX
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
