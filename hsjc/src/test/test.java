package test;

import java.util.Iterator;
import java.util.List;



import dao.UserDao;
import model.NATest;
import model.ST1;
import model.ST2;
import model.Sample;
import model.User;
import utils.LoginConfig;

public class test {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

		List<User> list = UserDao.userlist("17335357019");
		User st2 = list.get(0);
		
		//List<Sample> slist = UserDao.slistAll();
		
		// ��ȡ������
       // Iterator<NATest> it = tlist.iterator();
		Iterator<User> it = list.iterator();
        int i = 1;
        while(it.hasNext()) {
        	
        	System.out.print(i + "  " );
            System.out.println(it.next());
            i++;
        }
        
        System.out.println(st2);
		
		/*String tle = LoginConfig.reader().get(0);
		System.out.println(tle);
		List<User> user1 = UserDao.userlist(tle);
		User fx=user1.get(0);
		System.out.println("�û���:" + fx.getUsername());
		System.out.println("����:" + fx.getPassword());
		System.out.println("����:" + fx.getName());
		System.out.println("�����:" + fx.getId());
		System.out.println("סַ:" + fx.getAddress());*/
	}

}
