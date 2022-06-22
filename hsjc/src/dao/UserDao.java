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
			sql="select * from ���� where �绰����=? and ����=?";
		}
		else {
			sql="select * from ҽ�� where �绰����=? and ����=?";
		}
		List<User> jdbc_select = DButils.jdbc_select(sql, User.class, name , pwd);
		return jdbc_select;
	}
	
	public static List<NATest> cy(String tid, int n){//�����ṩ�ļ��Ų�ѯ���ݿ����Ƿ���ڴ˼���
		String sql="select * from ������� where ����=? and �������>?";
		List<NATest> jdbc_select = DButils.jdbc_select(sql, NATest.class, tid, n);
		return jdbc_select;
	}

	public int reg(String tle, String pwd, String name, String id, String add, int index) {
		// TODO �Զ����ɵķ������
		String sql1;
		String sql2;
		String sql3;
		String substr = id.substring(14);
		int repeatTle;
		int repeatUid = 0;
		if(index==0) {
			sql1="insert into ����(�绰����,����,����,�����,סַ) values(?,?,?,?,?)";
			sql2="select * from ���� where �绰����=?";
			sql3="select * from ���� where substring(�����,15,18)=?";
			List<User> jdbc_select = DButils.jdbc_select(sql2, User.class, tle);
			List<User> jdbc_select2 = DButils.jdbc_select(sql3, User.class, substr);
			repeatTle = jdbc_select.size();
			repeatUid = jdbc_select2.size();
		}
		else {
			sql1="insert into ҽ��(�绰����,����,����,ҽ����,����������) values(?,?,?,?,?)";
			sql2="select * from ҽ�� where �绰����=?";
			List<Docter> jdbc_select = DButils.jdbc_select(sql2, Docter.class, tle);
			repeatTle = jdbc_select.size();
		}
		
		if(repeatTle != 0) {//�绰�����ظ�
			return -1;
		}
		if(repeatUid != 0 ) {//uid����λ�ظ�
			return -2;
		}
		int jdbc_update = DButils.jdbc_update(sql1, tle, pwd, name, id, add);
		return jdbc_update;
	}

	public static List<User> userlist(String tle) {
		// TODO �Զ����ɵķ������
		String sql = "select * from ���� where �绰����=?";
		//String sql = "select * from ���� ";
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
				String tle2 = rs.getString("�绰����");
				String pwd = rs.getString("����");
				String uid = rs.getString("�����");
				String name = rs.getString("����");
				String address = rs.getString("סַ");
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
		// TODO �Զ����ɵķ������
		String sql = "select * from ҽ�� where �绰����=?";
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
				String tle2 = rs.getString("�绰����");
				String pwd = rs.getString("����");
				String did = rs.getString("ҽ����");
				String name = rs.getString("����");
				String testCenter = rs.getString("����������");
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

	public int Mondify(String name, String add, String tle, int index) {//�޸ĸ�����Ϣ
		// TODO �Զ����ɵķ������
		String sql;
		if(index==0) {
			sql="update ���� set ����=?,סַ=? where �绰����=?";
		}else {
			sql="update ҽ�� set ����=?,����������=? where �绰����=?";
		}

		int jdbc_update = DButils.jdbc_update(sql, name, add, tle);
		return jdbc_update;
	}

	public int Cancel(String tle,int index) {//ע���˻�
		// TODO �Զ����ɵķ������
		String sql1="";
		String sql2="";
		String sql3="";
		int jdbc_update=0;
		if(index==0) {
			sql1="delete from ����ҽ�����ձ� where ������=(select ������ from ������Ϣ�� where ����� = (select ����� from ���� where �绰���� = ?))";
			sql2="delete from ������Ϣ�� where �����=(select ����� from ���� where �绰���� = ?)";
			sql3="delete from ���� where �绰���� = ?";
			int jdbc_update1 = DButils.jdbc_update(sql1, tle);
			int jdbc_update2 = DButils.jdbc_update(sql2, tle);
			jdbc_update = DButils.jdbc_update(sql3, tle);
			
		}else {
			sql1="delete from ����ҽ�����ձ� where ҽ���� = (select ҽ���� from ҽ�� where �绰���� = ?)";
			sql2="delete from ҽ�� where �绰���� = ?";
			int jdbc_update1 = DButils.jdbc_update(sql1, tle);
			jdbc_update = DButils.jdbc_update(sql2, tle);
			
		}

		return jdbc_update;
	}

	public int updatePwd(String newpwd, String tle, int index) {//��������
		// TODO �Զ����ɵķ������
		String sql;
		if(index==0) {
			sql="update ���� set ����=? where �绰����=?";
		}else {
			sql="update ҽ�� set ����=? where �绰����=?";
		}
		int jdbc_update = DButils.jdbc_update(sql, newpwd, tle);
		return jdbc_update;
	}
	
	public static List<NATest> testlist() {//��ѯ���к�������,�����������i������ͨ��ʲô��ѯ
		// TODO �Զ����ɵķ������
		String sql = "select * from ������� ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = JDBCUtils.getConnection();
		List<NATest> list = new ArrayList<NATest>();
		list = queryForTest(sql);
		return list;	
	}
	
	public static List<NATest> queryForTest(String sql,Object...args) {//��ѯ���к�������,�����������i������ͨ��ʲô��ѯ
		// TODO �Զ����ɵķ������
		//String sql = "select * from ������� ";
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;		
		List<NATest> list = new ArrayList<NATest>();
		
		try {
			ps = conn.prepareStatement(sql);
			for(int i=0;i<args.length;i++) {//��ÿ����ռλ���滻�ɴ��ݽ����Ĳ���
				ps.setObject(i+1, "%"+args[i]+"%");
			}
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String tid = rs.getString("����");
				String point = rs.getString("����");
				String date = rs.getString("���ʱ��");
				String no = rs.getString("�������");
				
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
	
	public static List<NATest> testlist(int type,String a) {//��ѯ���к�������,�����������i������ͨ��ʲô��ѯ
		// TODO �Զ����ɵķ������
		String sql = new String();
	
		switch(type){
			case 1:
				sql = "select * from ������� where ���� like ?";
				break;
			case 2:
				sql="select * from ������� where ����  like ?";
				break;
			case 3:
				sql="select * from ������� where ���ʱ�� like ?";
				break;
			case 4:
				sql="select * from ������� where ������� like ?";
				break;
		}
		List<NATest> list = new ArrayList<NATest>();
		list = queryForTest(sql,a);
		return list;
	}
	
	public static int jc_count(String id) {
		int count = 0;
		String sql ="select count(*) from ������Ϣ�� where �����=?";
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
	
	public static int jc_totalCount() {//��ѯ����������
		int count = 0;
		String sql ="with cte as (select distinct ������� from �������)\r\n" + 
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

	public static List<Sample> slistAll() {//��ѯ���ݿ��д��ڵ�����������Ϣ
		// TODO �Զ����ɵķ������
		String sql = "select * from ������Ϣ�� ";
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
				String sid = rs.getString("������");
				String result = rs.getString("������");
				String uid = rs.getString("�����");
				String tid = rs.getString("����");
				
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
	
	public int SCollect(Sample s) {//����������Ϣ����ɲ�������
		// TODO �Զ����ɵķ������
		String sql="insert into ������Ϣ��  values(?,?,?,?)";
		String sid = s.getSid();
		String result = s.getResult();
		String uid = s.getUid();
		String tid = s.getTid();
		int jdbc_update = DButils.jdbc_update(sql, sid, result, uid, tid);
		return jdbc_update;
	}
	
	//���ص�һ������ѯ������
	public static List<ST1> st1list(String tle) {//��ѯ���ݿ��д��ڵ�����������Ϣ
		// TODO �Զ����ɵķ������
		String sql = "select ����,������,������� \r\n" + 
				"	from ����,������Ϣ��,�������\r\n" + 
				"	where �绰����=? \r\n" + 
				"	and ����.�����=������Ϣ��.����� \r\n" + 
				"	and ������Ϣ��.���� = �������.���� ";
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
				String name = rs.getString("����");
				String sid = rs.getString("������");
				String no = rs.getString("�������");
				
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
	//���صڶ�������ѯ������
	public static List<ST2> st2list(String sid) {//��ѯ���ݿ��д��ڵ�����������Ϣ
		// TODO �Զ����ɵķ������
		String sql = "select ������,����.����,����.�����,������Ϣ��.������ ,�������,���ʱ��,����,������ʽ,ҽ��.���� \r\n" + 
				"from ����,������Ϣ��,�������,ҽ��,����ҽ�����ձ�\r\n" + 
				"where ������Ϣ��.������=? \r\n" + 
				"and ����.�����=������Ϣ��.����� \r\n" + 
				"and ������Ϣ��.���� = �������.���� \r\n" + 
				"and ������Ϣ��.������ = ����ҽ�����ձ�.������ \r\n" + 
				"and ����ҽ�����ձ�.ҽ���� = ҽ��.ҽ���� ; ";
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
				String resault = rs.getString("������");
				String uname = rs.getString("����.����");
				String uid = rs.getString("����.�����");
				//String sid = rs.getString("������Ϣ��.������");
				String no = rs.getString("�������");
				String date = rs.getString("���ʱ��");
				String point = rs.getString("����");
				String method = rs.getString("������ʽ");
				String dname  = rs.getString("ҽ��.����");
				
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
		// TODO �Զ����ɵķ������
		String sql = "insert into ������� values(?,?,?,?)";
		int jdbc_update = DButils.jdbc_update(sql,id, point, date, no);
		return jdbc_update;
	}	
}
