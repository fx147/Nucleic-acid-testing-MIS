package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Participate;
import model.ST1;
import model.StFinished;
import utils.DButils;
import utils.JDBCUtils;

public class DocterDao {
	/**
	 * �˷�������DetectionFrame
	 * ��δ�����������ѡ��ST1��Ľ��
	 * ����limit��������ÿ��չʾ����ĸ���
	 */
	public static List<ST1> st1Dlist() {//��ѯ���ݿ��д��ڵ�����������Ϣ
		// TODO �Զ����ɵķ������
		String sql = "select ����,������,�������\r\n" + 
				"from ����,������Ϣ��,������� \r\n" + 
				"where ����.�����=������Ϣ��.����� \r\n" + 
				"and ������Ϣ��.���� = �������.����\r\n" + 
				"and ������Ϣ��.������ = 'δ����'\r\n" +
				" " + 
				"limit 5 ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = JDBCUtils.getConnection();
		List<ST1> list = new ArrayList<ST1>();
		try {
			ps = conn.prepareStatement(sql);
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

	/**
	 * �˷����ǻ�������������������ݸ��£�
	 * һ�Ǹ��������Ļ�������
	 * ���ǽ�������ҽ���Ķ�Ӧ��ϵ���뵽����ҽ�����ձ�
	 */
	public static int Detection(String method, String resault, String did, String sid) {
		// TODO �Զ����ɵķ������
		String sql1="update ������Ϣ�� set ������ = ? where ������ = ?";
		String sql2="insert into ����ҽ�����ձ�(������,ҽ����,������ʽ) values(?,?,?)";

		int jdbc_update1 = DButils.jdbc_update(sql1, resault, sid);
		int jdbc_update2= DButils.jdbc_update(sql2, sid, did, method);
		return jdbc_update2;
	}
	
	public static List<StFinished> StFlist(String tle) {
		// TODO �Զ����ɵķ������
		//��Ϊ��������������֮�󻹻��õ������Դ�����һ����ͼ
		String sql = "select ����ҽ�����ձ�.������,������Ϣ��.������,����.����,�������.������� \r\n" + 
				"from ����ҽ�����ձ�,������Ϣ��,����,�������\r\n" + 
				"where ����ҽ�����ձ�.������ = ������Ϣ��.������ \r\n" + 
				"and ������Ϣ��.����� = ����.����� \r\n" + 
				"and ������Ϣ��.���� = �������.���� \r\n" + 
				"and ����ҽ�����ձ�.ҽ���� = (select ҽ���� from ҽ�� where �绰���� = ?)\r\n" + 
				"order by �������";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = JDBCUtils.getConnection();
		List<StFinished> list = new ArrayList<StFinished>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, tle);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("����");
				String sid = rs.getString("������");
				String no = rs.getString("�������");
				String result = rs.getString("������");
				
				StFinished s = new StFinished(sid,result,name,no);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, null, ps, conn);
		}
		return list;
	}

	public static List<StFinished> StFlist2(String tle) {
		// TODO �Զ����ɵķ������
		String sql = "select ����ҽ�����ձ�.������,������Ϣ��.������,����.����,�������.������� \r\n" + 
				"from ����ҽ�����ձ�,������Ϣ��,����,�������\r\n" + 
				"where ����ҽ�����ձ�.������ = ������Ϣ��.������ \r\n" + 
				"and ������Ϣ��.����� = ����.����� \r\n" + 
				"and ������Ϣ��.���� = �������.���� \r\n" + 
				"and ����ҽ�����ձ�.ҽ���� = (select ҽ���� from ҽ�� where �绰���� = ?)\r\n" + 
				"and ������Ϣ��.������ = '����' \r\n" + 
				"order by �������;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = JDBCUtils.getConnection();
		List<StFinished> list = new ArrayList<StFinished>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, tle);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("����");
				String sid = rs.getString("������");
				String no = rs.getString("�������");
				String result = rs.getString("������");
				
				StFinished s = new StFinished(sid,result,name,no);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, null, ps, conn);
		}
		return list;
	}
	
	public static List<Participate> ParticipateList() {
		// TODO �Զ����ɵķ������
		String sql = "with cte as (select �����,count(*) as ����� from ������Ϣ�� group by �����)\r\n" + 
				"select ����.�����,cte.����� \r\n" + 
				"from ���� left outer join cte\r\n" + 
				"on ����.����� = cte.�����\r\n" + 
				"order by ����� desc";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		conn = JDBCUtils.getConnection();
		List<Participate> list = new ArrayList<Participate>();
		try {
			ps = conn.prepareStatement(sql);
			//ps.setString(1, tle);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String uid = rs.getString("�����");
				int count = rs.getInt("�����");
				
				Participate s = new Participate(uid,count);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, null, ps, conn);
		}
		return list;
	}
}
