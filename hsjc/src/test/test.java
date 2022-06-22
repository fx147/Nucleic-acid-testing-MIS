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
		// TODO 自动生成的方法存根

		List<User> list = UserDao.userlist("17335357019");
		User st2 = list.get(0);
		
		//List<Sample> slist = UserDao.slistAll();
		
		// 获取迭代器
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
		System.out.println("用户名:" + fx.getUsername());
		System.out.println("密码:" + fx.getPassword());
		System.out.println("姓名:" + fx.getName());
		System.out.println("居民号:" + fx.getId());
		System.out.println("住址:" + fx.getAddress());*/
	}

}
