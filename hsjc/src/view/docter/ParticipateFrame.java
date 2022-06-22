package view.docter;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.LoginConfig;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.DocterDao;
import model.Participate;
import model.StFinished;
/**
 * 用于展示居民参与检测程度界面
 * @author 86173
 *
 */
public class ParticipateFrame extends JFrame {

	private JPanel contentPane;
	private int index;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParticipateFrame frame = new ParticipateFrame();
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
	public ParticipateFrame() {
		setTitle("\u5C45\u6C11\u68C0\u6D4B\u53C2\u4E0E\u5EA6");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 453);
		this.setLocationRelativeTo(null);//居中
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl1 = new JLabel("\u5C45\u6C11\u68C0\u6D4B\u53C2\u4E0E\u5EA6");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setBackground(new Color(255, 192, 203));
		lbl1.setBounds(0, 0, 332, 33);
		lbl1.setOpaque(true);
		contentPane.add(lbl1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 48, 281, 320);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(240, 255, 240));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"\u5E8F\u53F7", "\u5C45\u6C11\u53F7", "\u53C2\u4E0E\u68C0\u6D4B\u603B\u6570"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(123);
		table.setRowHeight(26);
		List<Participate> plist = DocterDao.ParticipateList();
		fillTable(table,plist);
		scrollPane.setViewportView(table);
		
		//获取当前用户的身份
		index = Integer.valueOf(LoginConfig.reader().get(2));
	}
	public void fillTable(JTable t,List<Participate> tlist) {
		DefaultTableModel dtm = (DefaultTableModel) t.getModel();
		DefaultTableCellRenderer r   = new DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		t.setDefaultRenderer(Object.class, r);
		dtm.setRowCount(0);// 设置成0行
		
		for (int i=0;i<tlist.size();i++) {
			Vector v = new Vector<>();
			Participate test = tlist.get(i);
			v.add(i+1);
			v.add(test.getUid());
			v.add(test.getCount());
			
			dtm.addRow(v);
		}
	}
}
