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
 * չʾ���������ĵ�һ�����
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
		this.setLocationRelativeTo(null);// ����
		contentPane = new JPanel();
		//contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl1 = new JLabel("\u68C0\u6D4B\u7ED3\u679C");
		lbl1.setFont(new Font("����", Font.PLAIN, 16));
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
		lbl2.setText("���������"+n+"��");
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
			String strMsg1 = "&nbsp&nbsp������&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + name;
			String strMsg2 = "&nbsp&nbsp�����ţ� &nbsp&nbsp&nbsp&nbsp " + sid;
			String strMsg3 = "&nbsp&nbsp������У�&nbsp&nbsp&nbsp&nbsp " + no;
			String strMsg = "<html><body>" + strMsg1 + "<br>" + strMsg2 + "<br>" + strMsg3 + "<body></html>";

			lbl.setText(strMsg);
			lbl.setName(sid);
			
			lbl.setFont(new Font("����", Font.PLAIN, 14));
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
		// TODO �Զ����ɵķ������
		// ���ڽ����ڲ㴰�ڣ���ʾ��ϸ�ĺ�������Ϣ
		InnerResultFrame irFrame = new InnerResultFrame();
		/**
		 * Ϊʲô������أ�
		 * ������Ϊ�̵߳�ǰ��˳���µģ�Ӧ��������sid��ֵ������sid��ֵ������֮ǰ
		 * �Ѿ���new inFrameʱ�������ˣ�
		 * ���ǰ��������д�ɺ������õ�һ�㴰�ڵ����������
		 */
		irFrame.setVisible(true);
		irFrame.setSid(msg);
		/**
		 * �ֳ�����bug�ˣ�sid����ȥ�Ķ���һ�������ʵѵ��ʱ����ʦ��������о�����һ������
		 * Ӧ������ִ������ñ�ǩ���ٴ���ĵ���¼�
		 * �հ�����
		 * 
		 * ����˱հ����������ֽ����һ�����⣬�е�������û�м����
		 */
		//System.out.println(sid);
		irFrame.fillTable();
		System.out.println("2");
	}
}
