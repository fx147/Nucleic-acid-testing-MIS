package utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * ��crud���з�װ
 * 
 * @author admin
 *
 */
public class DButils {
	/**
	 * ��������һ���ǲ�ѯ
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	public static int jdbc_update(String sql, Object... objects) {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;//����statement����
		int i = 0;
		try {
			// Ԥ����
			ps = conn.prepareStatement(sql);
			// ��sql��ֵ
			for (int j = 0; j < objects.length; j++) {
				ps.setObject(j + 1, objects[j]);
			}
			// ִ��
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ
			JDBCUtils.close(null, null, ps, conn);
		}
		return i;
	}

	/**
	 * ��ѯ������е������ֶ�
	 * 
	 * @param sql
	 * @param classes
	 * @param objects
	 * @return
	 */
	public static <T> List<T> jdbc_select(String sql, Class<T> classes, Object... objects) {
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> list = null;
		try {
			ps = conn.prepareStatement(sql);
			// ��sql��ֵ
			for (int i = 0; i < objects.length; i++) {
				ps.setObject(i + 1, objects[i]);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();// ���ݱ�Ľṹ
			int col_count = rsmd.getColumnCount();// ��ȡ����ѯ��� ����
			list = new ArrayList<T>();
			while (rs.next()) {
				T t = classes.newInstance();// ����Javabean����
				Map<String, Object> map = new HashMap<String, Object>();
				// ѭ���õ���ǰ�е�����
				for (int i = 0; i < col_count; i++) {
					String colName = rsmd.getColumnLabel(i + 1);// �õ�����
					Object colValue = rs.getObject(colName);// �õ�ֵ
					map.put(colName, colValue);
				}
				// ��map��key-value��ֵӳ�䵽����
				BeanUtils.populate(t, map);
				list.add(t);
			}
			// System.out.println(rsmd);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return list;
	}
}
