package view;
/**
 * 记录用户信息窗口
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dao.UserDao;
import model.Docter;
import model.User;
import utils.LoginConfig;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class YhxxFrame extends JFrame {

	private JPanel contentPane;
	private UserDao userDao = new UserDao();
	private JTable table;
	private int index;
	private int flag;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YhxxFrame frame = new YhxxFrame();
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
	public YhxxFrame() {
		setTitle("\u7528\u6237\u4FE1\u606F");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 481);
		this.setLocationRelativeTo(null);//居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//获取当前用户的身份
		index = Integer.valueOf(LoginConfig.reader().get(2));
		
		JLabel label = new JLabel("\u7528\u6237\u4FE1\u606F\u8BE6\u60C5");
		label.setFont(new Font("宋体", Font.BOLD, 18));
		label.setForeground(new Color(255, 255, 255));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(new Color(152, 251, 152));
		label.setOpaque(true);
		label.setBounds(0, 0, 363, 33);
		contentPane.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 60, 326, 160);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(255, 239, 213));
		table.setFont(new Font("仿宋", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"\u59D3\u540D", "\u8303\u65ED"},
				{"\u7535\u8BDD\u53F7\u7801", "17335357019"},
				{"居民号", "41162720010716511X"},
				{"住址", null},
			},
			new String[] {
				"", ""
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		if(index==1) {
			table.getModel().setValueAt("医生号", 2, 0);
			table.getModel().setValueAt("化验中心", 3, 0);
		}
		fillTable();
		table.setRowHeight(40);

		// 设置列的宽度、首选宽度、最小宽度、最大宽度
		//tableColumn.setWidth(int width);
		//tableColumn.setPreferredWidth(int preferredWidth);
		//tableColumn.setMinWidth(int minWidth);
		//tableColumn.setPreferredWidth(30);
		
		scrollPane.setRowHeaderView(table);
		
		JButton button_modify = new JButton("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		button_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyActionPerformed(e);
			}
		});
		button_modify.setIcon(new ImageIcon(YhxxFrame.class.getResource("/images/reset.png")));
		button_modify.setFont(new Font("宋体", Font.PLAIN, 16));
		button_modify.setForeground(new Color(0, 0, 0));
		button_modify.setBackground(new Color(0, 255, 0));
		button_modify.setBounds(92, 277, 168, 29);
		button_modify.setOpaque(true);
		
		contentPane.add(button_modify);
	}
	protected void ModifyActionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		ModifyFrame MFrame = new ModifyFrame();
		MFrame.setVisible(true);
		if(MFrame.getFlag()==1) {//说明修改成功
			flag=MFrame.getFlag();
			System.out.println("flag--->"+flag);
			this.dispose();
		}
	}

	public int getFlag() {
		return flag;
	}
	
	/**
	 * 初始化表格
	 */
	public void fillTable() {
		// 查询出用户信息
		List<String> reader = LoginConfig.reader();
		String tle = reader.get(0);
		String name;
		String id;
		String add;
		if(index==0) {
			User u = userDao.userlist(tle).get(0);
			name = u.getName();
			id = u.getId();
			add = u.getAddress();
		}else {
			Docter d = userDao.docterlist(tle).get(0);
			name = d.getDname();
			id = d.getDid();
			add = d.getTestCenter();
		}
		
		//System.out.println(u);
		// 设置表格中指定单元格的数据
		table.getModel().setValueAt(name, 0, 1);
		table.getModel().setValueAt(tle, 1, 1);
		table.getModel().setValueAt(id, 2, 1);
		table.getModel().setValueAt(add, 3, 1);
	}
}
