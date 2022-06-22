package view;

/**
 * 内层结果窗口，用于显示详细的核酸检测信息
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.UserDao;
import model.ST2;

public class InnerResultFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String sid;
	private UserDao userDao = new UserDao();
	private JLabel lbl_resault;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InnerResultFrame frame = new InnerResultFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InnerResultFrame() {
		setTitle("\u6838\u9178\u68C0\u6D4B\u7ED3\u679C\u8BE6\u60C5");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 481);
		this.setLocationRelativeTo(null);// 居中
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl1 = new JLabel("检测结果详情");
		lbl1.setBounds(0, 0, 364, 33);
		lbl1.setFont(new Font("宋体", Font.PLAIN, 16));
		lbl1.setBackground(new Color(0, 255, 127));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setOpaque(true);
		contentPane.add(lbl1);

		lbl_resault = new JLabel("\u672A\u68C0\u6D4B");
		lbl_resault.setBounds(0, 32, 364, 72);
		lbl_resault.setForeground(new Color(255, 255, 255));
		lbl_resault.setFont(new Font("宋体", Font.BOLD, 20));
		lbl_resault.setBackground(new Color(50, 205, 50));
		lbl_resault.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_resault.setOpaque(true);
		contentPane.add(lbl_resault);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 105, 364, 288);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setShowVerticalLines(false);
		table.setFont(new Font("宋体", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
				new Object[][] { { "\u59D3\u540D", null }, { "\u5C45\u6C11\u53F7", null },
						{ "\u6837\u672C\u53F7", null }, { "\u68C0\u6D4B\u5E8F\u5217", null },
						{ "\u91C7\u6837\u65F6\u95F4", null }, { "\u91C7\u6837\u5730\u70B9", null },
						{ "\u91C7\u6837\u65B9\u5F0F", null }, { "\u5316\u9A8C\u533B\u751F", null }, },
				new String[] { "New column", "New column" }) {
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.setRowHeight(36);
		table.setBackground(new Color(255, 255, 255));
		
		scrollPane.setColumnHeaderView(table);
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	public void fillTable() {
		List<ST2> list = userDao.st2list(sid);
		if(list.size()==0) {
			JOptionPane.showMessageDialog(null, "该样本目前还未检测！");
			dispose();
			return ;
		}
		ST2 st2 = list.get(0);
		//System.out.println(st2);
		String resault = st2.getResault();
		String uname = st2.getUname();
		String uid = st2.getUid();
		String sid = st2.getSid();
		String no = st2.getNo();
		String date = st2.getDate();
		String point = st2.getPoint();
		String method = st2.getMethod();
		String dname = st2.getDname();
		
		lbl_resault.setText(resault);
		if(resault.equals("阳性")) lbl_resault.setForeground(Color.RED);
		
		//table.getModel().setValueAt(resault, 1, 1);
		table.getModel().setValueAt(uname, 0, 1);
		table.getModel().setValueAt(uid, 1, 1);
		table.getModel().setValueAt(sid, 2, 1);
		table.getModel().setValueAt(no, 3, 1);
		table.getModel().setValueAt(date, 4, 1);
		table.getModel().setValueAt(point, 5, 1);
		table.getModel().setValueAt(method, 6, 1);
		table.getModel().setValueAt(dname, 7, 1);
	}
		

}
