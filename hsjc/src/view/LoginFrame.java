package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.User;
import utils.LoginConfig;
import utils.StringUtil;
import java.awt.Color;
/**
 * 登录界面
 * @author 86173
 *
 */
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameTxt;
	private JPasswordField pwd;
	private JComboBox identity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 634, 393);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u6838\u9178\u68C0\u6D4B\u7CFB\u7EDF\u767B\u5F55\u754C\u9762");
		label.setBounds(188, 63, 224, 24);
		label.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/\u68C0\u6D4B.png")));
		label.setFont(new Font("黑体", Font.PLAIN, 20));
		
		JLabel label_1 = new JLabel("\u8D26\u53F7");
		label_1.setBounds(163, 141, 56, 21);
		label_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/userName.png")));
		
		JLabel label_2 = new JLabel("\u5BC6\u7801");
		label_2.setBounds(163, 180, 56, 21);
		label_2.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/password.png")));
		
		UsernameTxt = new JTextField();
		UsernameTxt.setBounds(257, 138, 179, 27);
		UsernameTxt.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(257, 177, 179, 27);
		
		JList list = new JList();
		list.setBounds(401, 227, 1, 1);
		
		JLabel label_3 = new JLabel("\u8EAB\u4EFD");
		label_3.setBounds(163, 219, 56, 21);
		label_3.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/about.png")));
		
		identity = new JComboBox();
		identity.setBackground(new Color(240, 255, 240));
		identity.setOpaque(true);
		identity.setBounds(257, 216, 89, 27);
		identity.setModel(new DefaultComboBoxModel(new String[] {"\u5C45\u6C11", "\u533B\u751F"}));
		identity.setToolTipText("");
		
		JButton but_login = new JButton("\u767B\u5F55");
		but_login.setBackground(new Color(240, 255, 240));
		but_login.setBounds(261, 275, 89, 29);
		but_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginActionPerformed(e);
			}
		});
		but_login.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/login.png")));
		
		JButton button_reg = new JButton("\u6CE8\u518C");
		button_reg.setBackground(new Color(240, 255, 240));
		button_reg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegActionPerformed(e);
			}
		});
		button_reg.setBounds(436, 275, 89, 29);
		button_reg.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/reg.png")));
		
		JButton button_reset = new JButton("\u91CD\u7F6E");
		button_reset.setBackground(new Color(240, 255, 240));
		button_reset.setBounds(86, 275, 89, 29);
		button_reset.setIcon(new ImageIcon(LoginFrame.class.getResource("/images/reset.png")));
		button_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetActionPerformed(e);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(label);
		contentPane.add(but_login);
		contentPane.add(label_3);
		contentPane.add(label_1);
		contentPane.add(label_2);
		contentPane.add(pwd);
		contentPane.add(UsernameTxt);
		contentPane.add(identity);
		contentPane.add(list);
		contentPane.add(button_reset);
		contentPane.add(button_reg);
		this.setLocationRelativeTo(null);//居中
	}

	protected void RegActionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		this.dispose();//销毁当前窗体
		new RegFrame().setVisible(true);//显示新窗体
		
	}

	/**
	 * 对登录事件的处理
	 * @param e
	 */
	protected void LoginActionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		String name = this.UsernameTxt.getText();
		String pwd = new String(this.pwd.getPassword());
		int index = this.identity.getSelectedIndex();
		System.out.println(index);//获取当前下拉复选框被选中的索引，其中0为居民，1为医生
		if(StringUtil.isEmpty(name)||StringUtil.isEmpty(pwd)) {
			JOptionPane.showMessageDialog(null, "账号或密码不能为空！");
			return;
		}
		List<User> list = UserDao.login(name,pwd,index);
		if(list.size()!=0) {//登陆成功
			JOptionPane.showMessageDialog(null, "登录成功！");
			dispose();
			LoginConfig.save(name,pwd,index);
			new MainFrame().setVisible(true);
		}else {//登录失败
			JOptionPane.showMessageDialog(null, "账号或密码错误，登录失败！");
		}
	}
	/**
	 * 对重置事件的处理
	 * @param e
	 */
	protected void ResetActionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		reset();
	}
	public void reset() {
		this.UsernameTxt.setText("");
		this.pwd.setText("");
		//String pwd = new String(this.pwd.getPassword());
	}
	
}
