package view;

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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
/**
 * 重置密码界面
 * @author 86173
 *
 */
public class PwdResetFrame extends JFrame {

	private UserDao userDao = new UserDao();
	private JPanel contentPane;
	private JPasswordField pwd1;
	private JPasswordField pwd2;
	private int index;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PwdResetFrame frame = new PwdResetFrame();
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
	public PwdResetFrame() {
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 150, 375, 241);
		this.setLocationRelativeTo(null);//居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//获取当前用户的身份
		index = Integer.valueOf(LoginConfig.reader().get(2));
		
		JLabel label = new JLabel("\u65B0\u5BC6\u7801:");
		label.setBounds(55, 46, 81, 21);
		contentPane.add(label);
		
		pwd1 = new JPasswordField();
		pwd1.setBounds(139, 43, 164, 27);
		contentPane.add(pwd1);
		
		JLabel label_1 = new JLabel("\u518D\u6B21\u8F93\u5165:");
		label_1.setBounds(55, 85, 81, 21);
		contentPane.add(label_1);
		
		pwd2 = new JPasswordField();
		pwd2.setBounds(139, 82, 164, 27);
		contentPane.add(pwd2);
		
		JButton button_reset = new JButton("\u91CD\u7F6E");
		button_reset.setBackground(new Color(253, 245, 230));
		button_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		button_reset.setBounds(66, 141, 93, 29);
		contentPane.add(button_reset);
		
		JButton button_submit = new JButton("\u63D0\u4EA4");
		button_submit.setBackground(new Color(253, 245, 230));
		button_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> reader = LoginConfig.reader();
				String tle = reader.get(0);
				String Oldpwd = reader.get(1);
				String Newpwd = new String(pwd1.getPassword());
				String Newpwd2 = new String(pwd2.getPassword());
				if(StringUtil.isEmpty(Newpwd)||StringUtil.isEmpty(Newpwd2)) {
					JOptionPane.showMessageDialog(null, "密码不能为空");
					return;
				}
				if(Newpwd.equals(Newpwd2)) {
					if(Newpwd.equals(Oldpwd)) {//新密码和原密码相同
						JOptionPane.showMessageDialog(null, "新密码不能和原密码相同！");
						reset();
					}else {
						int i = userDao.updatePwd(Newpwd, tle, index);
						if (i > 0) {
							JOptionPane.showMessageDialog(null, "修改成功");
							dispose();
						}
					}
				}else {//两次输入密码不同
					JOptionPane.showMessageDialog(null, "第二次输入密码和第一次密码不一致！");
					pwd2.setText("");
				}
			}
		});
		button_submit.setBounds(199, 141, 93, 29);
		contentPane.add(button_submit);
	}

	protected void reset() {
		// TODO 自动生成的方法存根
		pwd1.setText("");
		pwd2.setText("");
	}
}
