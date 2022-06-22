package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import dao.UserDao;
import model.NATest;
import utils.LoginConfig;
import view.docter.TestManageFrame;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
/**
 * 这个界面用于展示检测点相关的信息，居民和用户均可使用，但是有差别
 * 医生多一个按钮用来增加检测序列
 * @author 86173
 *
 */
public class JcFrame extends JFrame {

	private NATest t = new NATest();
	private JPanel contentPane;
	private JTable table;
	private JTextField txt;
	private JTable table_1;
	private JComboBox box;
	private int index;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JcFrame frame = new JcFrame();
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
	public JcFrame() {
		setTitle("\u68C0\u6D4B\u4FE1\u606F\u67E5\u8BE2");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 510);
		this.setLocationRelativeTo(null);//居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//获取当前用户的身份
		index = Integer.valueOf(LoginConfig.reader().get(2));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 59, 364, 163);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(255, 255, 240));
		table.setFont(new Font("宋体", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u68C0\u6D4B\u53F7", "\u68C0\u6D4B\u70B9", "\u68C0\u6D4B\u65F6\u95F4", "\u68C0\u6D4B\u5E8F\u5217"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(88);
		table.getColumnModel().getColumn(1).setPreferredWidth(127);
		table.getColumnModel().getColumn(2).setPreferredWidth(108);
		table.getColumnModel().getColumn(3).setPreferredWidth(73);
		List<NATest> tlist = UserDao.testlist();
		fillTable(table,tlist);
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("\u68C0\u6D4B\u4FE1\u606F\u67E5\u8BE2");
		label.setFont(new Font("宋体", Font.BOLD, 18));
		label.setForeground(new Color(255, 255, 255));
		label.setBackground(new Color(250, 128, 114));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 364, 33);
		label.setOpaque(true);
		contentPane.add(label);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 336, 364, 103);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setBackground(new Color(255, 255, 240));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"\u68C0\u6D4B\u53F7", "\u68C0\u6D4B\u70B9", "\u68C0\u6D4B\u65F6\u95F4", "\u68C0\u6D4B\u5E8F\u5217"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(88);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(127);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(108);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(73);
		scrollPane_1.setViewportView(table_1);
		
		JLabel lb = new JLabel("\u68C0\u7D22\u9879:");
		lb.setFont(new Font("宋体", Font.PLAIN, 12));
		lb.setBounds(15, 255, 49, 21);
		contentPane.add(lb);
		
		txt = new JTextField();
		txt.setBounds(172, 250, 143, 27);
		contentPane.add(txt);
		txt.setColumns(10);
		
		JButton button_search = new JButton("\u67E5\u8BE2");
		button_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchActionPerformed(e);
			}
		});
		//button_search.setForeground(new Color(0, 0, 0));
		button_search.setFont(new Font("宋体", Font.PLAIN, 13));
		button_search.setIcon(new ImageIcon(JcFrame.class.getResource("/images/search.png")));
		button_search.setBounds(233, 304, 89, 21);
		button_search.setOpaque(true);
		contentPane.add(button_search);
		
		JButton button_reset = new JButton("\u6E05\u7A7A");
		button_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt.setText("");
			}
		});
		button_reset.setIcon(new ImageIcon(JcFrame.class.getResource("/images/reset.png")));
		button_reset.setFont(new Font("宋体", Font.PLAIN, 13));
		button_reset.setBounds(25, 304, 89, 21);
		contentPane.add(button_reset);
		
		box = new JComboBox();
		box.setFont(new Font("宋体", Font.PLAIN, 14));
		box.setModel(new DefaultComboBoxModel(new String[] {"\u68C0\u6D4B\u53F7", "\u68C0\u6D4B\u70B9", "\u68C0\u6D4B\u65F6\u95F4", "\u68C0\u6D4B\u5E8F\u5217"}));
		box.setBounds(66, 250, 91, 27);
		contentPane.add(box);
		
		JButton button_manage = new JButton("\u7BA1\u7406");
		button_manage.setIcon(new ImageIcon(JcFrame.class.getResource("/images/add.png")));
		button_manage.setFont(new Font("宋体", Font.PLAIN, 13));
		button_manage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagePerformed(e);
			}
		});
		button_manage.setBounds(129, 304, 89, 21);
		if(index==0) {
			button_manage.setVisible(false);
		}
		contentPane.add(button_manage);
	}
	protected void ManagePerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		new TestManageFrame().setVisible(true);
	}

	protected void SearchActionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		String type = box.getSelectedItem().toString();
		String search  = txt.getText();
		int i=1;
		switch(type){
		case "检测号":
			i = 1;
			break;
		case "检测点":
			i = 2;
			break;
		case "检测时间":
			i = 3;
			break;
		case "检测序列":
			i = 4;
			break;
		}
		//int no = Integer.valueOf(sno);
		
		
		List<NATest> tlist1 = UserDao.testlist(i,search);
		System.out.println(type+" "+search);
		System.out.println(tlist1);
		fillTable(table_1,tlist1);
	}

	public void fillTable(JTable t,List<NATest> tlist) {
		DefaultTableModel dtm = (DefaultTableModel) t.getModel();
		dtm.setRowCount(0);// 设置成0行
		
		for (int i=0;i<tlist.size();i++) {
			Vector v = new Vector<>();
			NATest test = tlist.get(i);
			v.add(test.getId());
			v.add(test.getPoint());
			v.add(test.getDate());
			v.add(test.getNo());
			dtm.addRow(v);
		}
	}
}
