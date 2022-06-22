package view.docter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.LoginConfig;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.DocterDao;
import dao.UserDao;
import model.NATest;
import model.StFinished;

public class SDFinishedFrame extends JFrame {

	private JPanel contentPane;
	private int index;
	private JTable table;
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private JTable table2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SDFinishedFrame frame = new SDFinishedFrame();
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
	public SDFinishedFrame() {
		setTitle("\u5DF2\u68C0\u6D4B\u6837\u672C");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 481);
		this.setLocationRelativeTo(null);//居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//获取当前用户的身份
		index = Integer.valueOf(LoginConfig.reader().get(2));
		
		JLabel lbl1 = new JLabel("\u5DF2\u5316\u9A8C\u6837\u672C\u8BE6\u60C5");
		lbl1.setFont(new Font("宋体", Font.BOLD, 16));
		lbl1.setBackground(new Color(0, 191, 255));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setBounds(0, 0, 365, 35);
		lbl1.setOpaque(true);
		contentPane.add(lbl1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 50, 365, 154);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(248, 248, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"\u5E8F\u53F7", "\u6837\u672C\u53F7", "\u59D3\u540D", "\u68C0\u6D4B\u5E8F\u5217", "\u5316\u9A8C\u7ED3\u679C"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(47);
		table.getColumnModel().getColumn(0).setMinWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(98);
		table.getColumnModel().getColumn(3).setPreferredWidth(55);
		table.getColumnModel().getColumn(4).setPreferredWidth(68);
		table.setRowHeight(26);
		
		// 查询出用户信息
		List<String> reader = LoginConfig.reader();
		String tle = reader.get(0);
		List<StFinished> tlist1 = DocterDao.StFlist(tle);//视图出问题了
		fillTable(table,tlist1);
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("\u60A8\u68C0\u6D4B\u6837\u672C\u603B\u6570\u4E3A\uFF1A");
		label.setBounds(10, 219, 162, 21);
		contentPane.add(label);
		
		JLabel lblX = new JLabel("x");
		lblX.setFont(new Font("宋体", Font.BOLD, 16));
		lblX.setForeground(Color.BLACK);
		lblX.setBounds(192, 219, 81, 21);
		contentPane.add(lblX);
		
		JLabel label_1 = new JLabel("\u5176\u4E2D\u9633\u6027\u6837\u672C\u6570\u4E3A:");
		label_1.setBounds(10, 255, 162, 21);
		contentPane.add(label_1);
		
		JLabel lblY = new JLabel("y");
		lblY.setBackground(new Color(245, 255, 250));
		lblY.setFont(new Font("宋体", Font.BOLD, 16));
		lblY.setForeground(Color.RED);
		lblY.setBounds(192, 255, 81, 21);
		contentPane.add(lblY);
		scrollPane_1.setBounds(0, 291, 365, 134);
		contentPane.add(scrollPane_1);
		
		table2 = new JTable();
		table2.setFont(new Font("宋体", Font.BOLD, 12));
		table2.setBackground(new Color(255, 69, 0));
		table2.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
					"\u5E8F\u53F7", "\u6837\u672C\u53F7", "\u59D3\u540D", "\u68C0\u6D4B\u5E8F\u5217", "\u5316\u9A8C\u7ED3\u679C"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					true, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		});
		table2.getColumnModel().getColumn(0).setPreferredWidth(47);
		table2.getColumnModel().getColumn(0).setMinWidth(10);
		table2.getColumnModel().getColumn(1).setPreferredWidth(98);
		table2.getColumnModel().getColumn(3).setPreferredWidth(55);
		table2.getColumnModel().getColumn(4).setPreferredWidth(68);
		table2.setRowHeight(25);
		List<StFinished> tlist2 = DocterDao.StFlist2(tle);
		fillTable(table2,tlist2);
		scrollPane_1.setViewportView(table2);
		
		String totalSample = tlist1.size()+"";
		String positiveNum = tlist2.size()+"";
		
		lblX.setText(totalSample);
		lblY.setText(positiveNum);
		
		
	}
	public void fillTable(JTable t,List<StFinished> tlist) {
		DefaultTableModel dtm = (DefaultTableModel) t.getModel();
		DefaultTableCellRenderer r   = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		t.setDefaultRenderer(Object.class, r);
		dtm.setRowCount(0);// 设置成0行
		
		for (int i=0;i<tlist.size();i++) {
			Vector v = new Vector<>();
			StFinished test = tlist.get(i);
			v.add(i+1);
			v.add(test.getSid());
			v.add(test.getName());
			v.add(test.getNo());
			v.add(test.getResult());
			dtm.addRow(v);
		}
	}
}
