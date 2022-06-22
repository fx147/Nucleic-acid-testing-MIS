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
	 * 此方法用于DetectionFrame
	 * 从未化验的样本中选择ST1类的结果
	 * 其中limit用来限制每次展示结果的个数
	 */
	public static List<ST1> st1Dlist() {//查询数据库中存在的所有样本信息
		// TODO 自动生成的方法存根
		String sql = "select 姓名,样本号,检测序列\r\n" + 
				"from 居民,样本信息表,检测序列 \r\n" + 
				"where 居民.居民号=样本信息表.居民号 \r\n" + 
				"and 样本信息表.检测号 = 检测序列.检测号\r\n" + 
				"and 样本信息表.化验结果 = '未化验'\r\n" +
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

	/**
	 * 此方法是化验操作带来的两个数据更新，
	 * 一是更新样本的化验结果，
	 * 二是将样本和医生的对应关系插入到样本医生对照表
	 */
	public static int Detection(String method, String resault, String did, String sid) {
		// TODO 自动生成的方法存根
		String sql1="update 样本信息表 set 化验结果 = ? where 样本号 = ?";
		String sql2="insert into 样本医生对照表(样本号,医生号,采样方式) values(?,?,?)";

		int jdbc_update1 = DButils.jdbc_update(sql1, resault, sid);
		int jdbc_update2= DButils.jdbc_update(sql2, sid, did, method);
		return jdbc_update2;
	}
	
	public static List<StFinished> StFlist(String tle) {
		// TODO 自动生成的方法存根
		//因为这个查出来的数据之后还会用到，所以创建了一个视图
		String sql = "select 样本医生对照表.样本号,样本信息表.化验结果,居民.姓名,检测序列.检测序列 \r\n" + 
				"from 样本医生对照表,样本信息表,居民,检测序列\r\n" + 
				"where 样本医生对照表.样本号 = 样本信息表.样本号 \r\n" + 
				"and 样本信息表.居民号 = 居民.居民号 \r\n" + 
				"and 样本信息表.检测号 = 检测序列.检测号 \r\n" + 
				"and 样本医生对照表.医生号 = (select 医生号 from 医生 where 电话号码 = ?)\r\n" + 
				"order by 检测序列";
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
				String name = rs.getString("姓名");
				String sid = rs.getString("样本号");
				String no = rs.getString("检测序列");
				String result = rs.getString("化验结果");
				
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
		// TODO 自动生成的方法存根
		String sql = "select 样本医生对照表.样本号,样本信息表.化验结果,居民.姓名,检测序列.检测序列 \r\n" + 
				"from 样本医生对照表,样本信息表,居民,检测序列\r\n" + 
				"where 样本医生对照表.样本号 = 样本信息表.样本号 \r\n" + 
				"and 样本信息表.居民号 = 居民.居民号 \r\n" + 
				"and 样本信息表.检测号 = 检测序列.检测号 \r\n" + 
				"and 样本医生对照表.医生号 = (select 医生号 from 医生 where 电话号码 = ?)\r\n" + 
				"and 样本信息表.化验结果 = '阳性' \r\n" + 
				"order by 检测序列;";
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
				String name = rs.getString("姓名");
				String sid = rs.getString("样本号");
				String no = rs.getString("检测序列");
				String result = rs.getString("化验结果");
				
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
		// TODO 自动生成的方法存根
		String sql = "with cte as (select 居民号,count(*) as 检测数 from 样本信息表 group by 居民号)\r\n" + 
				"select 居民.居民号,cte.检测数 \r\n" + 
				"from 居民 left outer join cte\r\n" + 
				"on 居民.居民号 = cte.居民号\r\n" + 
				"order by 检测数 desc";
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
				String uid = rs.getString("居民号");
				int count = rs.getInt("检测数");
				
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
