package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * jdbc�ķ�װ
 * 
 * @author admin
 *
 */
public class JDBCUtils {
	/**
	 * ��ȡ����
	 * 
	 * @return
	 */

	public static Connection getConnection() {
		Connection ct = null;
		Statement sm = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//��������
			//�������ݿ�����
			ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/hsjc?useUnicode=true&characterEncoding=UTF8", "root", "16511X");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ct;
	}

	/**
	 * �ͷ���Դ
	 * 
	 * @param rs
	 * @param stm
	 * @param ps
	 * @param conn
	 */
	public static void close(ResultSet rs, Statement stm, PreparedStatement ps, Connection conn) {
		try {
			if (null != rs) {
				rs.close();
			}
		} catch (Exception e) {
		}
		try {
			if (null != stm) {
				stm.close();
			}
		} catch (Exception e) {
		}
		try {
			if (null != ps) {
				ps.close();
			}
		} catch (Exception e) {
		}
		try {
			if (null != conn) {
				conn.close();
			}
		} catch (Exception e) {
		}
	}
}
