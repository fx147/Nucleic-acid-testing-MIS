package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.NATest;
import model.Sample;
import model.User;
import utils.LoginConfig;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * ���view����չʾ�û������Ľ���
 * @author 86173
 *
 */
public class CymFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txt_id;
	private UserDao userDao = new UserDao();
	private int n;
	private String txt1;
	private String uid;
	private JTable table;
	private int total;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CymFrame frame = new CymFrame();
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
	public CymFrame() {
		setTitle("\u91C7\u6837\u7801\u51FA\u793A");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 481);
		this.setLocationRelativeTo(null);//����
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u68C0\u6D4B\u53F7:");
		label.setFont(new Font("����", Font.BOLD, 13));
		label.setBounds(54, 317, 108, 21);
		contentPane.add(label);
		
		txt_id = new JTextField();
		txt_id.setBounds(177, 314, 143, 27);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
		
		JButton button_reset = new JButton("\u6E05\u7A7A");
		button_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_id.setText("");
			}
		});
		button_reset.setFont(new Font("����", Font.BOLD, 13));
		button_reset.setBackground(new Color(255, 240, 245));
		button_reset.setOpaque(true);
		button_reset.setBounds(68, 353, 84, 29);
		contentPane.add(button_reset);
		
		JButton btn_cy = new JButton("\u91C7\u6837");
		btn_cy.setFont(new Font("����", Font.BOLD, 13));
		btn_cy.setBackground(new Color(255, 240, 245));
		btn_cy.setOpaque(true);
		btn_cy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CollectActionPerformed(e);
			}
		});
		btn_cy.setBounds(193, 353, 84, 29);
		contentPane.add(btn_cy);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(0, 0, 366, 123);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea txtrnn = new JTextArea();
		txtrnn.setBackground(new Color(135, 206, 250));
		txtrnn.setFont(new Font("����", Font.BOLD, 14));
		txtrnn.setBounds(59, 57, 307, 51);
		panel.add(txtrnn);
		txtrnn.setEditable(false);
		
		// ��ѯ���û���Ϣ
		List<String> reader = LoginConfig.reader();
		String tle = reader.get(0);
		User u = userDao.userlist(tle).get(0);
		uid = u.getId();
		System.out.println(uid);
		n=userDao.jc_count(uid);
		System.out.println("n:"+n);
		total=userDao.jc_totalCount();
		System.out.println("total:"+total);
		/**
		 * ��bug��һ��
		 * ��model���������ģ�ͽ���һ��
		 * �ѳ�ʾ����������
		 * �ѽ��չʾ����(����ѯ���Ե�ҽ������������������ֻչʾ������Ϣ�������)
		 */
		txt1 = "";
		if(n<total) {
				txt1= "��"+total+"�κ�����, �������" + n +"�μ��\n���ѯ��"+(n+1)+"�μ������Ӧ�ļ���";

		}else {
			txt1="����������к�����";
			//JOptionPane.showMessageDialog(null, "����������к����⣡");
		}
		txtrnn.setText(txt1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 145, 343, 123);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(255, 240, 245));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"\u68C0\u6D4B\u53F7", "\u68C0\u6D4B\u70B9", "\u68C0\u6D4B\u65F6\u95F4", "\u68C0\u6D4B\u5E8F\u5217"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(88);
		table.getColumnModel().getColumn(1).setPreferredWidth(127);
		table.getColumnModel().getColumn(2).setPreferredWidth(108);
		table.getColumnModel().getColumn(3).setPreferredWidth(73);
		table.setRowHeight(25);
		String next = (n+1)+"";
		List<NATest> tlist1 = UserDao.testlist(4,next);
		fillTable(table,tlist1);
		scrollPane.setViewportView(table);
		
	}

	protected void CollectActionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(n==total) {
			JOptionPane.showMessageDialog(null, txt1);
			this.dispose();
			return;
		}
		String tid = txt_id.getText();
		List<NATest> tlist = userDao.cy(tid,n);
		if(tlist.size()>0) {//��������ȷ
			Sample s = new Sample(uid,tid);
			userDao.SCollect(s);
			JOptionPane.showMessageDialog(null, "�����ɹ���");
			dispose();
		}else {
			JOptionPane.showMessageDialog(null, "����ȷ������ţ�");
		}
		
	}
	public void fillTable(JTable t,List<NATest> tlist) {
		DefaultTableModel dtm = (DefaultTableModel) t.getModel();
		dtm.setRowCount(0);// ���ó�0��
		
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
