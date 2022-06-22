package view.docter;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.DocterDao;
import dao.UserDao;
import model.Docter;
import model.NATest;
import model.ST1;
import utils.LoginConfig;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 这个view用来展示医生具体化验的界面
 * @author 86173
 *
 */
public class DetectionFrame extends JFrame {

	private JPanel contentPane;
	private Integer index;
	private JTable table;
	//private int click;
	private JLabel lbl_sid;
	protected String sid="-1";
	private JComboBox cb1;
	private JComboBox cb2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetectionFrame frame = new DetectionFrame();
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
	public DetectionFrame() {
		setTitle("化验");
		setBounds(100, 100, 375, 510);
		this.setLocationRelativeTo(null);//居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 240, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u6837\u672C\u5316\u9A8C");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(new Color(152, 251, 152));
		label.setBounds(0, 0, 365, 36);
		label.setOpaque(true);
		contentPane.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 34, 365, 146);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(248, 248, 255));
		table.addMouseListener(new MouseAdapter() {


			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				sid = (String) table.getModel().getValueAt(i, 0);
				lbl_sid.setText(sid);
				//click++;
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"\u6837\u672C\u53F7", "\u59D3\u540D", "\u68C0\u6D4B\u5E8F\u5217"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(51);
		table.setRowHeight(25);
		fillTable();
		scrollPane.setViewportView(table);
		
		JLabel label_1 = new JLabel("\u6837\u672C\u53F7:");
		label_1.setBackground(new Color(240, 248, 255));
		label_1.setBounds(45, 212, 81, 21);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u91C7\u6837\u65B9\u5F0F:");
		label_2.setBackground(new Color(240, 248, 255));
		label_2.setBounds(45, 248, 81, 21);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u5316\u9A8C\u7ED3\u679C:");
		label_3.setBackground(new Color(240, 248, 255));
		label_3.setBounds(45, 284, 81, 21);
		contentPane.add(label_3);
		
		lbl_sid = new JLabel("\u8BF7\u70B9\u51FB\u8868\u683C");
		lbl_sid.setBackground(new Color(250,240,230));
		lbl_sid.setBounds(165, 212, 95, 21);
		lbl_sid.setOpaque(true);
		contentPane.add(lbl_sid);
		
		cb1 = new JComboBox();
		cb1.setBackground(new Color(240, 255, 255));
		cb1.setModel(new DefaultComboBoxModel(new String[] {"\u54BD\u62ED\u5B50", "\u9F3B\u62ED\u5B50"}));
		cb1.setBounds(163, 245, 83, 27);
		contentPane.add(cb1);
		
		cb2 = new JComboBox();
		cb2.setBackground(new Color(240, 255, 255));
		cb2.setModel(new DefaultComboBoxModel(new String[] {"\u672A\u5316\u9A8C", "\u9634\u6027", "\u9633\u6027"}));
		cb2.setBounds(163, 281, 81, 27);
		contentPane.add(cb2);
		
		JButton button_commit = new JButton("\u63D0\u4EA4\u5316\u9A8C\u7ED3\u679C");
		button_commit.setBackground(new Color(240, 255, 255));
		button_commit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetectionPerformed(e);
			}
		});
		button_commit.setBounds(109, 342, 134, 29);
		contentPane.add(button_commit);
		
		JButton button_refresh = new JButton("\u66F4\u65B0\u672A\u5316\u9A8C\u6837\u672C");
		button_refresh.setBackground(new Color(240, 255, 255));
		button_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTable();
			}
		});
		button_refresh.setBounds(106, 410, 141, 29);
		contentPane.add(button_refresh);
		System.out.println("sid:"+sid+"..");
		//获取当前用户的身份
		index = Integer.valueOf(LoginConfig.reader().get(2));
	}
	

	protected void DetectionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
		if(sid.equals("-1")) {
			JOptionPane.showMessageDialog(null, "请选择要化验的样本！");
			return;
		}
		
		int k=table.getSelectedRow();

		
		String method = (String) cb1.getSelectedItem();
		String resault = (String) cb2.getSelectedItem();
		
		List<String> reader = LoginConfig.reader();
		String tle = reader.get(0);
		System.out.println(tle);
		Docter d = UserDao.docterlist(tle).get(0);
		String did = d.getDid();
		
		
		if(cb2.getSelectedIndex()==0) {
			JOptionPane.showMessageDialog(null, "请选择化验结果！");
			return;
		}
		int i = DocterDao.Detection(method,resault,did,sid);//将数据插入到样本医生表和更新样本信息表中的结果字段
		if(i>0) {
			JOptionPane.showMessageDialog(null, "已提交！");
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.removeRow(k);
			cb2.setSelectedIndex(0);
			sid="-1";
			lbl_sid.setText("请选择样本");
		}else {
			JOptionPane.showMessageDialog(null, "提交失败!");
		}
	}

	public void fillTable() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);// 设置成0行
		List<ST1> st1dlist = DocterDao.st1Dlist();
		
		for (int i=0;i<st1dlist.size();i++) {
			Vector v = new Vector<>();
			ST1 st1 = st1dlist.get(i);
			v.add(st1.getSid());
			v.add(st1.getName());
			v.add(st1.getNo());

			dtm.addRow(v);
			
		}
	}

}
