package view.docter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import utils.LoginConfig;
import utils.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 展示增加检测序列界面
 * @author 86173
 *
 */
public class TestManageFrame extends JFrame {

	private JPanel contentPane;
	private int index;
	private JTextField txt_id;
	private JTextField txt_point;
	private JTextField txt_date;
	private JTextField txt_no;
	private UserDao userDao = new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestManageFrame frame = new TestManageFrame();
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
	public TestManageFrame() {
		setTitle("检测序列管理");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 403);
		this.setLocationRelativeTo(null);//居中

		//获取当前用户的身份
		index = Integer.valueOf(LoginConfig.reader().get(2));
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl1 = new JLabel("\u589E\u52A0\u68C0\u6D4B\u5E8F\u5217");
		lbl1.setBackground(new Color(175, 238, 238));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setBounds(0, 0, 363, 29);
		lbl1.setOpaque(true);
		contentPane.add(lbl1);
		
		JLabel label = new JLabel("\u68C0\u6D4B\u53F7:");
		label.setBounds(51, 71, 81, 21);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u68C0\u6D4B\u70B9:");
		label_1.setBounds(51, 126, 81, 21);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u68C0\u6D4B\u65F6\u95F4:");
		label_2.setBounds(51, 176, 81, 21);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u68C0\u6D4B\u5E8F\u5217:");
		label_3.setBounds(51, 232, 81, 21);
		contentPane.add(label_3);
		
		txt_id = new JTextField();
		txt_id.setBounds(159, 68, 148, 27);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
		
		txt_point = new JTextField();
		txt_point.setBounds(159, 123, 148, 27);
		contentPane.add(txt_point);
		txt_point.setColumns(10);
		
		txt_date = new JTextField();
		txt_date.setBounds(159, 173, 148, 27);
		contentPane.add(txt_date);
		txt_date.setColumns(10);
		
		txt_no = new JTextField();
		txt_no.setBounds(159, 229, 148, 27);
		contentPane.add(txt_no);
		txt_no.setColumns(10);
		
		JButton but_reset = new JButton("\u91CD\u7F6E");
		but_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_id.setText("");
				txt_point.setText("");
				txt_date.setText("");
				txt_no.setText("");
			}
		});
		but_reset.setBounds(56, 284, 92, 29);
		contentPane.add(but_reset);
		
		JButton but_add = new JButton("\u63D0\u4EA4");
		but_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPerformed(e);
			}
		});
		but_add.setBounds(204, 284, 92, 29);
		contentPane.add(but_add);
	}

	protected void AddPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		String id = txt_id.getText();
		String point = txt_point.getText();
		String date = txt_date.getText();
		String no = txt_no.getText();
		int i = userDao.insertTest(id,point,date,no);
		
		if(StringUtil.isEmpty(id) || StringUtil.isEmpty(point) || StringUtil.isEmpty(date) || StringUtil.isEmpty(no)) {
			JOptionPane.showMessageDialog(null, "添加信息不能为空！");
			return;
		}
		if(id.length()!=8) {
			JOptionPane.showMessageDialog(null, "请检查检测号是否输入正确！");
			return;
		}
	    for(int j=no.length();--j>=0;){
	        int chr=no.charAt(j);
	        if(chr<48 || chr>57) {
	        	JOptionPane.showMessageDialog(null, "请正确输入序列号！");
	        	return;
	        }	    
	    }

		
		if(i>0) {//注册成功
			this.dispose();
			JOptionPane.showMessageDialog(null, "添加成功");
		}else {//注册失败
			JOptionPane.showMessageDialog(null, "添加失败！");
		}
		
	}
}
