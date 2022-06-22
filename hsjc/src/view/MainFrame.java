package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.LoginConfig;
import view.docter.DetectionFrame;
import view.docter.ParticipateFrame;
import view.docter.SDFinishedFrame;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
/**
 * 主界面，居民和医生的界面会有差别
 * @author 86173
 *
 */
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private int index;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setTitle("\u4E3B\u9875\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 367, 532);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);//居中
		contentPane.setLayout(null);
		
		//获取当前用户的身份
		index = Integer.valueOf(LoginConfig.reader().get(2));
		//System.out.println(index);
		JButton button_home = new JButton("\u9996\u9875");//..........
		button_home.setHorizontalAlignment(SwingConstants.LEADING);
		button_home.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u9996\u9875.jpg")));
		button_home.setContentAreaFilled(false);
		button_home.setFocusPainted(false);
		button_home.setBackground(new Color(255, 239, 213));
		button_home.setOpaque(true);
		button_home.setBounds(30, 447, 83, 29);
		contentPane.add(button_home);
		
		JButton button_me = new JButton("\u6211\u7684");//..........
		button_me.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u6211\u7684.jpg")));
		button_me.setHorizontalAlignment(SwingConstants.LEADING);
		button_me.setContentAreaFilled(false);
		button_me.setFocusPainted(false);
		button_me.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MeActionPerformed(e);//..........
			}
		});
		button_me.setBackground(new Color(255, 239, 213));
		button_me.setOpaque(true);
		button_me.setBounds(225, 447, 82, 29);
		contentPane.add(button_me);
		
		JLabel lblNewLabel = new JLabel("111");//..........
		lblNewLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/images/main-2.jpg")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, -11, 363, 158);
		contentPane.add(lblNewLabel);
		
		JPanel panel_resault = new JPanel();
		panel_resault.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelResaultAction(e);
			}
		});
		panel_resault.setBackground(new Color(244, 164, 96));
		panel_resault.setForeground(Color.BLACK);
		panel_resault.setBounds(15, 185, 142, 82);
		contentPane.add(panel_resault);
		panel_resault.setLayout(null);
		
		JLabel lbl_resault = new JLabel("检测结果查询");//..........
		lbl_resault.setFont(new Font("小米兰亭", Font.BOLD, 18));
		lbl_resault.setBounds(0, 0, 116, 21);
		lbl_resault.setForeground(new Color(255, 255, 255));
		panel_resault.add(lbl_resault);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u68C0\u6D4B\u7ED3\u679C\u67E5\u8BE2 (2).png")));
		label.setBounds(87, 36, 40, 40);
		panel_resault.add(label);
		
		JPanel panel_jc = new JPanel();
		panel_jc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelJcAction(e);
			}
		});
		panel_jc.setBackground(new Color(70, 130, 180));
		panel_jc.setBounds(196, 185, 142, 82);
		contentPane.add(panel_jc);
		panel_jc.setLayout(null);
		
		JLabel lbl_jc = new JLabel("检测信息查询");//..........
		lbl_jc.setFont(new Font("小米兰亭", Font.BOLD, 18));
		lbl_jc.setBounds(0, 0, 116, 21);
		lbl_jc.setForeground(new Color(255, 255, 255));
		panel_jc.add(lbl_jc);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u68C0\u6D4B\u70B9\u4FE1\u606F.png")));
		label_1.setBounds(87, 36, 40, 40);
		panel_jc.add(label_1);
		
		JPanel panel_cym = new JPanel();
		panel_cym.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelCymAction(e);
			}
		});
		panel_cym.setBackground(new Color(240, 128, 128));
		panel_cym.setBounds(15, 320, 142, 82);
		contentPane.add(panel_cym);
		panel_cym.setLayout(null);
		
		JLabel lbl_cym = new JLabel("\u91C7\u6837");//..........
		lbl_cym.setFont(new Font("小米兰亭", Font.BOLD, 18));
		lbl_cym.setBounds(0, 0, 116, 21);
		lbl_cym.setForeground(new Color(255, 255, 255));
		panel_cym.add(lbl_cym);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u5316\u9A8C.jpg")));
		label_2.setBounds(87, 36, 40, 40);
		panel_cym.add(label_2);
		
		JPanel panel_par = new JPanel();
		panel_par.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelParticipate(e);
			}
		});
		panel_par.setLayout(null);
		panel_par.setForeground(Color.BLACK);
		panel_par.setBackground(new Color(144, 238, 144));
		panel_par.setBounds(196, 320, 142, 82);
		if(index==0) {
			panel_par.setVisible(false);
		}
		contentPane.add(panel_par);
		
		JLabel label_participate = new JLabel();
		label_participate.setForeground(new Color(255, 255, 255));
		label_participate.setFont(new Font("小米兰亭", Font.BOLD, 18));
		label_participate.setBounds(0, 5, 127, 41);
		String text="<html><body>居民检测参与度查询<body></html>";
		label_participate.setText(text);
		panel_par.add(label_participate);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/\u5C45\u6C11\u53C2\u4E0E\u5EA6.jpg")));
		label_3.setBounds(87, 42, 40, 40);
		panel_par.add(label_3);
		
		if(index==1) {
			lbl_resault.setText("已化验样本");
			lbl_jc.setText("管理检测点");
			lbl_cym.setText("化验");
		}
	}
	
	protected void PanelParticipate(MouseEvent e) {
		// TODO 自动生成的方法存根
		new ParticipateFrame().setVisible(true);
	}

	public void ResidentView() {
		
	}

	protected void PanelCymAction(MouseEvent e) {
		// TODO 自动生成的方法存根
		System.out.println(index);
		if(index == 0) {
			new CymFrame().setVisible(true);
		}else {
			new DetectionFrame().setVisible(true);
		}
		
	}

	protected void PanelJcAction(MouseEvent e) {
		// TODO 自动生成的方法存根
		//dispose();
		new JcFrame().setVisible(true);
	}

	protected void PanelResaultAction(MouseEvent e) {
		// TODO 自动生成的方法存根
		//dispose();
		if(index == 0) {
			new ResultFrame().setVisible(true);
		}
		else {
			new SDFinishedFrame().setVisible(true);
		}
	}

	protected void MeActionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		dispose();
		new MeFrame().setVisible(true);
	}
}
