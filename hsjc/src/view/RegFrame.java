package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.User;
import utils.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
/**
 * 注册界面
 * @author 86173
 *
 */
public class RegFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txt_tle;
	private JTextField txt_name;
	private JTextField txt_id;
	private JPasswordField txt_pwd;
	private UserDao userDao = new UserDao();//没看懂
	private JTextField txt_add;
	private JLabel lbl_id;
	//private boolean flag = true;
	private JLabel lbl_add;
	private JComboBox cBox;
	private int index;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegFrame frame = new RegFrame();
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
	public RegFrame() {
		setTitle("\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 634, 393);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u6CE8\u518C");
		label.setFont(new Font("新宋体", Font.BOLD, 20));
		label.setBounds(268, 15, 84, 21);
		contentPane.add(label);
		
		JLabel lbl_tle = new JLabel("\u7535\u8BDD\u53F7\u7801:");
		lbl_tle.setBounds(177, 54, 81, 21);
		contentPane.add(lbl_tle);
		
		JLabel lbl_pwd = new JLabel("\u5BC6\u7801:");
		lbl_pwd.setBounds(177, 91, 81, 21);
		contentPane.add(lbl_pwd);
		
		JLabel lbl_name = new JLabel("\u59D3\u540D:");
		lbl_name.setBounds(177, 127, 81, 21);
		contentPane.add(lbl_name);
		
		lbl_id = new JLabel("\u5C45\u6C11\u53F7:");
		lbl_id.setBounds(177, 163, 81, 21);
		contentPane.add(lbl_id);
		
		JButton button = new JButton("\u91CD\u7F6E");
		button.setBackground(new Color(250, 240, 230));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResetActionPerformed(e);
			}
		});
		button.setBounds(177, 293, 123, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u63D0\u4EA4");
		button_1.setBackground(new Color(250, 240, 230));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubmitActionPerformed(e);
			}
		});
		button_1.setBounds(346, 293, 123, 29);
		contentPane.add(button_1);
		
		txt_tle = new JTextField();
		txt_tle.setBounds(300, 51, 204, 27);
		contentPane.add(txt_tle);
		txt_tle.setColumns(10);
		
		txt_name = new JTextField();
		txt_name.setBounds(300, 124, 204, 27);
		contentPane.add(txt_name);
		txt_name.setColumns(10);
		
		txt_id = new JTextField();
		txt_id.setBounds(300, 160, 204, 27);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
		
		txt_pwd = new JPasswordField();
		txt_pwd.setBounds(300, 88, 204, 27);
		contentPane.add(txt_pwd);
		
		JLabel lbl_identify = new JLabel("\u8EAB\u4EFD:");
		lbl_identify.setBounds(177, 257, 81, 21);
		contentPane.add(lbl_identify);
		
		cBox = new JComboBox();
		cBox.setBackground(new Color(250, 240, 230));
		cBox.setOpaque(true);
		cBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IndexChanged(e);
			}
		});
		cBox.setModel(new DefaultComboBoxModel(new String[] {"\u5C45\u6C11", "\u533B\u751F"}));
		cBox.setBounds(300, 251, 112, 27);
		contentPane.add(cBox);
		
		lbl_add = new JLabel("\u4F4F\u5740:");
		lbl_add.setBounds(177, 199, 81, 21);
		contentPane.add(lbl_add);
		
		txt_add = new JTextField();
		txt_add.setBounds(300, 196, 204, 27);
		contentPane.add(txt_add);
		txt_add.setColumns(10);
		this.setLocationRelativeTo(null);//居中
		
		
	}

	protected void IndexChanged(ActionEvent e) {
		// TODO 自动生成的方法存根
		String[] id = {"居民号","医生号"};
		String[] add = {"地址","化验中心"};
		index = cBox.getSelectedIndex();
		this.lbl_id.setText(id[index]);
		this.lbl_add.setText(add[index]);
		/*if(flag) {
			this.lbl_id.setText(id[1]);
			this.lbl_add.setText(add[1]);
		}
		else {
			this.lbl_id.setText(id[0]);
			this.lbl_add.setText(add[0]);
		}
		flag = !flag;*/
	}

	/**
	 * 域的完整性：电话号码为11位纯数字  居民号为18位纯数字或17位数字+X 且居民号后四位不能与其他相同
	 * 			    医生号为10位纯数字
	 * 实体完整性：居民号、医生号非空且不能相同
	 * 参照完整性：外键化验中心非空且必须存在于化验中心表中
	 * @param e
	 */
	protected void SubmitActionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		String tle = this.txt_tle.getText();
		String pwd = new String(this.txt_pwd.getPassword());
		String name = this.txt_name.getText();
		String id = this.txt_id.getText();
		String add = this.txt_add.getText();
		index = cBox.getSelectedIndex();
		int i = 0; 
		
		//限制电话号码必须是11位纯数字
		if(!StringUtil.isCorrectTle(tle)) {
			JOptionPane.showMessageDialog(null, "请检查电话号码是否输入正确！");
			return;
		}
		//限制密码和姓名不能为空
		if(StringUtil.isEmpty(pwd) || StringUtil.isEmpty(name)) {
			JOptionPane.showMessageDialog(null, "密码或姓名不能为空！");
			return;
		}
		if(index==0) {
			//限制居民号为18位纯数字或者17位数字+X
			if(!StringUtil.isCorrectUid(id)) {
				JOptionPane.showMessageDialog(null, "请正确输入居民号！");
				return;
			}
			i = userDao.reg(tle,pwd,name,id,add,index);//插入,index表示当前进行的是哪种账户的注册 插入必须在判断条件后插入
			if(i>0) {//注册成功
				this.dispose();
				new LoginFrame().setVisible(true);
			}else if(i==-1){//电话号码重复导致的注册失败
				JOptionPane.showMessageDialog(null, "该电话号码已被注册！");
			}else if(i==-2){//电话号码重复导致的注册失败
				JOptionPane.showMessageDialog(null, "该居民号后四位与其他居民号相同！");
			}else {//居民号重复导致的注册失败
				JOptionPane.showMessageDialog(null, "注册失败！请检查居民号是否输入正确");
			}
		}
		else {
			if(!StringUtil.isCorrectDid(id)) {
				JOptionPane.showMessageDialog(null, "请正确输入医生号！");
				return;
			}
			i = userDao.reg(tle,pwd,name,id,add,index);//插入,index表示当前进行的是哪种账户的注册 插入必须在判断条件后插入
			if(i>0) {//注册成功
				this.dispose();
				new LoginFrame().setVisible(true);
			}else if(i==-1){
				JOptionPane.showMessageDialog(null, "该电话号码已被注册！");
			}else {//注册失败
				JOptionPane.showMessageDialog(null, "注册失败！请检查医生号或检测中心是否输入正确");
			}
		}
		
		
	}

	protected void ResetActionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		this.txt_tle.setText("");
		this.txt_pwd.setText("");
		this.txt_name.setText("");
		this.txt_id.setText("");
		this.txt_add.setText("");
	}
}
