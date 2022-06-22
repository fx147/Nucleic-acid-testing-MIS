package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.Docter;
import model.NATest;
import model.ST1;
import model.ST2;
import model.Sample;
import model.User;
import utils.DButils;
import utils.JDBCUtils;

public class UserDao {
	public static List<User> login(String name, String pwd, int index){
		String sql;
		if(index==0) {
			sql="select * from 居民 where 电话号码=? and 密码=?";
		}
		else {
			sql="select * from 医生 where 电话号码=? and 密码=?";
		}
		List<User> jdbc_select = DButils.jdbc_select(sql, User.class, name , pwd);
		return jdbc_select;
	}
	
	public static List<NATest> cy(String tid, int n){//根据提供的检测号查询数据库中是否存在此检测号
		String sql="select * from 检测序列 where 检测号=? and 检测序列>?";
		List<NATest> jdbc_select = DButils.jdbc_select(sql, NATest.class, tid, n);
		return jdbc_select;
	}

	public int reg(String tle, String pwd, String name, String id, String add, int index) {
		// TODO 自动生成的方法存根
		String sql1;
		String sql2;
		String sql3;
		String substr = id.substring(14);
		int repeatTle;
		int repeatUid = 0;
		if(index==0) {
			sql1="insert into 居民(电话号码,密码,姓名,居民号,住址) values(?,?,?,?,?)";
			sql2="select * from 居民 where 电话号码=?";
			sql3="select * from 居民 where substring(居民号,15,18)=?";
			List<User> jdbc_select = DButils.jdbc_select(sql2, User.class, tle);
			List<User> jdbc_select2 = DButils.jdbc_select(sql3, User.class, substr);
			repeatTle = jdbc_select.size();
			repeatUid = jdbc_select2.size();
		}
		else {
			sql1="insert into 医生(电话号码,密码,姓名,医生号,化验中心名) values(?,?,?,?,?)";
			sql2="select * from 医生 where 电话号码=?";
			List<Docter> jdbc_select = DButils.jdbc_select(sql2, Docter.class, tle);
			repeatTle = jdbc_select.size();
		}
		
		if(repeatTle != 0) {//电话号码重复
			return -1;
		}
		if(repeatUid != 0 ) {//uid后四位重复
			return -2;
		}
		int jdbc_update = DButils.jdbc_update(sql1, tle, pwd, name, id, add);
		return jdbc_update;
	}

	public static List<User> userlist(String tle) {
		// TODO 自动生成的方法存根
		String sql = "select * from 居民 where 电话号码=?";
		//String sql = "select * from 居民 ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = JDBCUtils.getConnection();
		List<User> list = new ArrayList<User>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, tle);
			rs = ps.executeQuery();
			while (rs.next()) {
				String tle2 = rs.getString("电话号码");
				String pwd = rs.getString("密码");
				String uid = rs.getString("居民号");
				String name = rs.getString("姓名");
				String address = rs.getString("住址");
				User user = new User(tle2, pwd, uid, address, name);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, null, ps, conn);
		}
		return list;	
	}

	public static List<Docter> docterlist(String tle) {
		// TODO 自动生成的方法存根
		String sql = "select * from 医生 where 电话号码=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = JDBCUtils.getConnection();
		List<Docter> list = new ArrayList<Docter>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, tle);
			rs = ps.executeQuery();
			while (rs.next()) {
				String tle2 = rs.getString("电话号码");
				String pwd = rs.getString("密码");
				String did = rs.getString("医生号");
				String name = rs.getString("姓名");
				String testCenter = rs.getString("化验中心名");
				Docter docter = new Docter(name, tle2, pwd, did, testCenter);
				list.add(docter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, null, ps, conn);
		}
		return list;	
	}

	public int Mondify(String name, String add, String tle, int index) {//修改个人信息
		// TODO 自动生成的方法存根
		String sql;
		if(index==0) {
			sql="update 居民 set 姓名=?,住址=? where 电话号码=?";
		}else {
			sql="update 医生 set 姓名=?,化验中心名=? where 电话号码=?";
		}

		int jdbc_update = DButils.jdbc_update(sql, name, add, tle);
		return jdbc_update;
	}

	public int Cancel(String tle,int index) {//注销账户
		// TODO 自动生成的方法存根
		String sql1="";
		String sql2="";
		String sql3="";
		int jdbc_update=0;
		if(index==0) {
			sql1="delete from 样本医生对照表 where 样本号=(select 样本号 from 样本信息表 where 居民号 = (select 居民号 from 居民 where 电话号码 = ?))";
			sql2="delete from 样本信息表 where 居民号=(select 居民号 from 居民 where 电话号码 = ?)";
			sql3="delete from 居民 where 电话号码 = ?";
			int jdbc_update1 = DButils.jdbc_update(sql1, tle);
			int jdbc_update2 = DButils.jdbc_update(sql2, tle);
			jdbc_update = DButils.jdbc_update(sql3, tle);
			
		}else {
			sql1="delete from 样本医生对照表 where 医生号 = (select 医生号 from 医生 where 电话号码 = ?)";
			sql2="delete from 医生 where 电话号码 = ?";
			int jdbc_update1 = DButils.jdbc_update(sql1, tle);
			jdbc_update = DButils.jdbc_update(sql2, tle);
			
		}

		return jdbc_update;
	}

	public int updatePwd(String newpwd, String tle, int index) {//更新密码
		// TODO 自动生成的方法存根
		String sql;
		if(index==0) {
			sql="update 居民 set 密码=? where 电话号码=?";
		}else {
			sql="update 医生 set 密码=? where 电话号码=?";
		}
		int jdbc_update = DButils.jdbc_update(sql, newpwd, tle);
		return jdbc_update;
	}
	
	public static List<NATest> testlist() {//查询所有核酸序列,传进来的这个i代表着通过什么查询
		// TODO 自动生成的方法存根
		String sql = "select * from 检测序列 ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = JDBCUtils.getConnection();
		List<NATest> list = new ArrayList<NATest>();
		list = queryForTest(sql);
		return list;	
	}
	
	public static List<NATest> queryForTest(String sql,Object...args) {//查询所有核酸序列,传进来的这个i代表着通过什么查询
		// TODO 自动生成的方法存根
		//String sql = "select * from 检测序列 ";
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;		
		List<NATest> list = new ArrayList<NATest>();
		
		try {
			ps = conn.prepareStatement(sql);
			for(int i=0;i<args.length;i++) {//把每个？占位符替换成传递进来的参数
				ps.setObject(i+1, "%"+args[i]+"%");
			}
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String tid = rs.getString("检测号");
				String point = rs.getString("检测点");
				String date = rs.getString("检测时间");
				String no = rs.getString("检测序列");
				
				NATest test = new NATest(tid,point,date,no);
				list.add(test);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, null, ps, conn);
		}
		return list;	
	}
	
	public static List<NATest> testlist(int type,String a) {//查询所有核酸序列,传进来的这个i代表着通过什么查询
		// TODO 自动生成的方法存根
		String sql = new String();
	
		switch(type){
			case 1:
				sql = "select * from 检测序列 where 检测号 like ?";
				break;
			case 2:
				sql="select * from 检测序列 where 检测点  like ?";
				break;
			case 3:
				sql="select * from 检测序列 where 检测时间 like ?";
				break;
			case 4:
				sql="select * from 检测序列 where 检测序列 like ?";
				break;
		}
		List<NATest> list = new ArrayList<NATest>();
		list = queryForTest(sql,a);
		return list;
	}
	
	public static int jc_count(String id) {
		int count = 0;
		String sql ="select count(*) from 样本信息表 where 居民号=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = JDBCUtils.getConnection();
		
		try {
			ps = conn.prepareStatement(sql);
			//ps.setString(1, type);
			ps.setString(1, id);
			//stmt = conn.createStatement();
			rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			JDBCUtils.close(rs, null, ps, conn);
		}
		return count;
	}
	
	public static int jc_totalCount() {//查询核酸检测总数
		int count = 0;
		String sql ="with cte as (select distinct 检测序列 from 检测序列)\r\n" + 
				"select count(*)\r\n" + 
				"from cte";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = JDBCUtils.getConnection();
		
		try {
			ps = conn.prepareStatement(sql);
			//ps.setString(1, type);
			
			//stmt = conn.createStatement();
			rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			JDBCUtils.close(rs, null, ps, conn);
		}
		return count;
	}

	public static List<Sample> slistAll() {//查询数据库中存在的所有样本信息
		// TODO 自动生成的方法存根
		String sql = "select * from 样本信息表 ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = JDBCUtils.getConnection();
		List<Sample> list = new ArrayList<Sample>();
		try {
			ps = conn.prepareStatement(sql);
			//ps.setString(1, tle);
			rs = ps.executeQuery();
			while (rs.next()) {
				String sid = rs.getString("样本号");
				String result = rs.getString("化验结果");
				String uid = rs.getString("居民号");
				String tid = rs.getString("检测号");
				
				Sample s = new Sample(sid,result,uid,tid);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, null, ps, conn);
		}
		return list;
	}
	
	public int SCollect(Sample s) {//插入样本信息，完成采样操作
		// TODO 自动生成的方法存根
		String sql="insert into 样本信息表  values(?,?,?,?)";
		String sid = s.getSid();
		String result = s.getResult();
		String uid = s.getUid();
		String tid = s.getTid();
		int jdbc_update = DButils.jdbc_update(sql, sid, result, uid, tid);
		return jdbc_update;
	}
	
	//返回第一层结果查询的序列
	public static List<ST1> st1list(String tle) {//查询数据库中存在的所有样本信息
		// TODO 自动生成的方法存根
		String sql = "select 姓名,样本号,检测序列 \r\n" + 
				"	from 居民,样本信息表,检测序列\r\n" + 
				"	where 电话号码=? \r\n" + 
				"	and 居民.居民号=样本信息表.居民号 \r\n" + 
				"	and 样本信息表.检测号 = 检测序列.检测号 ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = JDBCUtils.getConnection();
		List<ST1> list = new ArrayList<ST1>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, tle);
			rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("姓名");
				String sid = rs.getString("样本号");
				String no = rs.getString("检测序列");
				
				ST1 s = new ST1(sid,name,no);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, null, ps, conn);
		}
		return list;
	}
	//返回第二层结果查询的序列
	public static List<ST2> st2list(String sid) {//查询数据库中存在的所有样本信息
		// TODO 自动生成的方法存根
		String sql = "select 化验结果,居民.姓名,居民.居民号,样本信息表.样本号 ,检测序列,检测时间,检测点,采样方式,医生.姓名 \r\n" + 
				"from 居民,样本信息表,检测序列,医生,样本医生对照表\r\n" + 
				"where 样本信息表.样本号=? \r\n" + 
				"and 居民.居民号=样本信息表.居民号 \r\n" + 
				"and 样本信息表.检测号 = 检测序列.检测号 \r\n" + 
				"and 样本信息表.样本号 = 样本医生对照表.样本号 \r\n" + 
				"and 样本医生对照表.医生号 = 医生.医生号 ; ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = JDBCUtils.getConnection();
		List<ST2> list = new ArrayList<ST2>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sid);
			rs = ps.executeQuery();
			while (rs.next()) {
				String resault = rs.getString("化验结果");
				String uname = rs.getString("居民.姓名");
				String uid = rs.getString("居民.居民号");
				//String sid = rs.getString("样本信息表.样本号");
				String no = rs.getString("检测序列");
				String date = rs.getString("检测时间");
				String point = rs.getString("检测点");
				String method = rs.getString("采样方式");
				String dname  = rs.getString("医生.姓名");
				
				ST2 s = new ST2(resault,uname,uid,sid,no,date,point,method,dname);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, null, ps, conn);
		}
		return list;
	}

	public int insertTest(String id,String point,String date,String no) {
		// TODO 自动生成的方法存根
		String sql = "insert into 检测序列 values(?,?,?,?)";
		int jdbc_update = DButils.jdbc_update(sql,id, point, date, no);
		return jdbc_update;
	}	
}
