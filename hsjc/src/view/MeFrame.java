package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.Docter;
import model.User;
import utils.LoginConfig;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * 个人信息界面
 * @author 86173
 *
 */
public class MeFrame extends JFrame {

	private JPanel contentPane;
	private UserDao userDao = new UserDao();
	private int index;
	private JLabel lbl_reset;
	private JLabel lbl_exit;
	private JLabel lbl_yhxx;
	private JLabel lbl_zx;
	private JLabel lbl_tel;
	private JLabel lbl_name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeFrame frame = new MeFrame();
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
	public MeFrame() {
		setTitle("\u4E2A\u4EBA\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 481);
		this.setLocationRelativeTo(null);//居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//获取当前用户的身份
		index = Integer.valueOf(LoginConfig.reader().get(2));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 360, 141);
		
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbl_icon = new JLabel("");
		lbl_icon.setIcon(new ImageIcon(MeFrame.class.getResource("/images/\u5706\u5F62\u7528\u6237.png")));
		lbl_icon.setBounds(146, 15, 50, 50);
		lbl_icon.setOpaque(false);
		panel.add(lbl_icon);
		
		lbl_tel = new JLabel("New label");
		lbl_tel.setFont(new Font("仿宋", Font.PLAIN, 20));
		lbl_tel.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_tel.setBounds(115, 80, 113, 21);
		String tle = LoginConfig.reader().get(0);//获得用户名
		lbl_tel.setText(tle);
		
		panel.add(lbl_tel);
		
		lbl_name = new JLabel("New label");
		lbl_name.setFont(new Font("仿宋", Font.PLAIN, 20));
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setBounds(131, 105, 81, 21);
		
		panel.add(lbl_name);
	
		
		lbl_yhxx = new JLabel("\u7528\u6237\u4FE1\u606F     >>>");
		lbl_yhxx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				YhxxActionPerformed(e);
			}
		});
		lbl_yhxx.setBackground(new Color(255, 255, 240));
		lbl_yhxx.setBounds(0, 142, 360, 31);
		lbl_yhxx.setOpaque(true);
		contentPane.add(lbl_yhxx);
		
		lbl_reset = new JLabel("\u91CD\u7F6E\u5BC6\u7801     >>>");
		lbl_reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PwdResetAction(e);
			}
		});
		lbl_reset.setBackground(new Color(255, 255, 240));
		lbl_reset.setBounds(0, 200, 360, 31);
		lbl_reset.setOpaque(true);
		contentPane.add(lbl_reset);
		
		lbl_exit = new JLabel("\u9000\u51FA\u767B\u5F55     >>>");
		lbl_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = JOptionPane.showConfirmDialog(null, "是否确认退出？");
				//是为0，否为1
				if(x==0) {
					dispose();
					JOptionPane.showMessageDialog(null, "退出成功，请重新登录！");
					LoginConfig.clean();
					new LoginFrame().setVisible(true);
				}
				
				
			}
		});
		lbl_exit.setBackground(new Color(255, 255, 240));
		lbl_exit.setBounds(0, 256, 360, 31);
		lbl_exit.setOpaque(true);
		contentPane.add(lbl_exit);
		
		lbl_zx = new JLabel("\u6CE8\u9500\u8D26\u6237     >>>");
		lbl_zx.addMouseListener(new MouseAdapter() {//由于外键的存在，这个注销账户的操作没有那么简单
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = JOptionPane.showConfirmDialog(null, "是否确认注销账户？");
				//是为0，否为1
				if(x==0) {
					int i = userDao.Cancel(tle,index);
					if(i>0) {
						dispose();
						JOptionPane.showMessageDialog(null, "注销成功，请重新登录");
						LoginConfig.clean();
						new LoginFrame().setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "注销失败！");
					}
				}
			}
		});
		lbl_zx.setBackground(new Color(255, 255, 240));
		lbl_zx.setBounds(0, 311, 360, 31);
		lbl_zx.setOpaque(true);
		contentPane.add(lbl_zx);
		
		JButton button_home = new JButton("\u9996\u9875");
		button_home.setHorizontalAlignment(SwingConstants.LEADING);
		button_home.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u9996\u9875.jpg")));
		button_home.setContentAreaFilled(false);
		button_home.setFocusPainted(false);
		button_home.setBackground(new Color(255, 239, 213));
		button_home.setOpaque(true);
		button_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeActionPerformed(e);
			}
		});
		button_home.setBounds(54, 396, 82, 29);
		contentPane.add(button_home);
		
		JButton button_me = new JButton("\u6211\u7684");

		button_me.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u6211\u7684.jpg")));
		button_me.setHorizontalAlignment(SwingConstants.LEADING);
		button_me.setContentAreaFilled(false);
		button_me.setFocusPainted(false);
		button_me.setBackground(new Color(255, 239, 213));
		button_me.setOpaque(true);
		button_me.setBounds(227, 396, 82, 29);
		contentPane.add(button_me);
		
		
		if(index==0) {
			User user = UserDao.userlist(tle).get(0);	
			String name = user.getName();
			lbl_name.setText(name);
		}else {
			Docter docter = UserDao.docterlist(tle).get(0);
			//System.out.println(docter);
			String name = docter.getDname();
			lbl_name.setText(name);
		}
		
	}

	protected void PwdResetAction(MouseEvent e) {
		// TODO 自动生成的方法存根
		new PwdResetFrame().setVisible(true);
	}

	protected void YhxxActionPerformed(MouseEvent e) {
		// TODO 自动生成的方法存根
		YhxxFrame YFrame = new YhxxFrame();
		YFrame.setVisible(true);
		if(YFrame.getFlag()==1) {//说明修改成功
			this.dispose();
		}
	}

	protected void HomeActionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		dispose();
		new MainFrame().setVisible(true);
	}
}
