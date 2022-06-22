package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.UserDao;
import model.NATest;
import model.ST1;
import utils.LoginConfig;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import java.awt.SystemColor;
/**
 * 展示居民检测结果的第一层界面
 * @author 86173
 *
 */
public class ResultFrame extends JFrame {

	private JPanel contentPane;
	private UserDao userdao = new UserDao();
	private int n;
	private JScrollPane scrollPane;

	//private String[] sid = {};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultFrame frame = new ResultFrame();
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
	public ResultFrame() {
		setTitle("\u91C7\u6837\u7ED3\u679C\u67E5\u8BE2");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 481);
		this.setLocationRelativeTo(null);// 居中
		contentPane = new JPanel();
		//contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl1 = new JLabel("\u68C0\u6D4B\u7ED3\u679C");
		lbl1.setFont(new Font("宋体", Font.PLAIN, 16));
		lbl1.setBackground(new Color(0, 255, 0));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setBounds(0, 0, 364, 33);
		lbl1.setOpaque(true);
		contentPane.add(lbl1);

		List<String> reader = LoginConfig.reader();
		String tle = reader.get(0);
		List<ST1> st1list = UserDao.st1list(tle);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 48, 364, 296);
		contentPane.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		//panel_1.setBackground(new Color(249, 243, 223));
		panel_1.setLayout(null);
		panel_1.setPreferredSize(new Dimension(345, 400));
		n = fillLabel(panel_1, st1list);

		scrollPane.setViewportView(panel_1);
		
		JLabel lbl2 = new JLabel("New label");
		//lbl2.setBackground(new Color(249, 243, 223));
		lbl2.setOpaque(true);
		lbl2.setBounds(0, 30, 364, 21);
		lbl2.setText("检测结果共有"+n+"条");
		contentPane.add(lbl2);

	}

	public int fillLabel(JPanel panel_1, List<ST1> st1list) {
		int i = 0;
		for (i = 0; i < st1list.size(); i++) {
			JLabel lbl = new JLabel();
			ST1 st1 = st1list.get(i);
			String name = st1.getName();
			String sid = st1.getSid();
			String no = st1.getNo();
			String strMsg1 = "&nbsp&nbsp姓名：&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + name;
			String strMsg2 = "&nbsp&nbsp样本号： &nbsp&nbsp&nbsp&nbsp " + sid;
			String strMsg3 = "&nbsp&nbsp检测序列：&nbsp&nbsp&nbsp&nbsp " + no;
			String strMsg = "<html><body>" + strMsg1 + "<br>" + strMsg2 + "<br>" + strMsg3 + "<body></html>";

			lbl.setText(strMsg);
			lbl.setName(sid);
			
			lbl.setFont(new Font("宋体", Font.PLAIN, 14));
			lbl.setBackground(Color.WHITE);
			lbl.setOpaque(true);
			lbl.setHorizontalAlignment(SwingConstants.LEFT);
			lbl.setBounds(0, 61 * i, 350, 55);
			lbl.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String msg = lbl.getName();
					System.out.println(msg);
					InnerAccess(e,msg);
				}
			});

			panel_1.add(lbl);
			scrollPane.setViewportView(panel_1);
		}
		return i;
	}

	protected void InnerAccess(MouseEvent e,String msg) {
		// TODO 自动生成的方法存根
		// 用于进入内层窗口，显示详细的核酸检测信息
		InnerResultFrame irFrame = new InnerResultFrame();
		/**
		 * 为什么会出错呢？
		 * 这是因为线程的前后顺序导致的，应该先设置sid的值，但是sid的值被设置之前
		 * 已经在new inFrame时被调用了，
		 * 我们把这个调用写成函数，让第一层窗口调用这个函数
		 */
		irFrame.setVisible(true);
		irFrame.setSid(msg);
		/**
		 * 又出现了bug了，sid穿进去的都是一个，这和实训的时候老师讲的问题感觉上是一个问题
		 * 应该是先执行完放置标签，再处理的点击事件
		 * 闭包问题
		 * 
		 * 解决了闭包问题终于又解决了一个问题，有的样本还没有检测呢
		 */
		//System.out.println(sid);
		irFrame.fillTable();
		System.out.println("2");
	}
}
