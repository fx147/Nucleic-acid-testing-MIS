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
import utils.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
/**
 * �޸ĸ�����Ϣ����
 * @author 86173
 *
 */
public class ModifyFrame extends JFrame {

	private UserDao userDao = new UserDao();
	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_add;
	private int index;
	int flag=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyFrame frame = new ModifyFrame();
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
	public ModifyFrame() {
		setTitle("\u4E2A\u4EBA\u4FE1\u606F\u4FEE\u6539");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 150, 375, 241);
		this.setLocationRelativeTo(null);//����
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//��ȡ��ǰ�û������
		index = Integer.valueOf(LoginConfig.reader().get(2));
		
		JLabel label = new JLabel("\u59D3  \u540D:");
		label.setFont(new Font("����", Font.PLAIN, 17));
		label.setIcon(new ImageIcon(ModifyFrame.class.getResource("/images/userName.png")));
		label.setBounds(45, 40, 93, 21);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("\u4F4F  \u5740:");
		label_2.setFont(new Font("����", Font.PLAIN, 17));
		label_2.setIcon(new ImageIcon(ModifyFrame.class.getResource("/images/\u5BB6\u5EAD\u4F4F\u5740.png")));
		label_2.setBounds(45, 97, 93, 21);
		contentPane.add(label_2);
		
		txt_name = new JTextField();
		txt_name.setBounds(155, 37, 148, 27);
		contentPane.add(txt_name);
		txt_name.setColumns(10);
		
		txt_add = new JTextField();
		txt_add.setBounds(155, 94, 148, 27);
		contentPane.add(txt_add);
		txt_add.setColumns(10);
		
		JButton button_reset = new JButton("\u91CD\u7F6E");
		button_reset.setBackground(new Color(253, 245, 230));
		button_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_name.setText("");
				//txt_id.setText("");
				txt_add.setText("");
			}
		});
		button_reset.setBounds(69, 152, 69, 21);
		contentPane.add(button_reset);
		
		JButton button_confirm = new JButton("\u786E\u5B9A");
		button_confirm.setBackground(new Color(253, 245, 230));
		button_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmActionPeformed(e);
			}
		});
		button_confirm.setBounds(219, 152, 69, 21);
		contentPane.add(button_confirm);
		
		if(index==1) {
			label_2.setText("��������");
		}
	}

	protected void ConfirmActionPeformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		List<String> reader = LoginConfig.reader();
		String tle = reader.get(0);
	
		String name = txt_name.getText();
		String add = txt_add.getText();
		
		System.out.println(name+" "+add+" "+tle+" "+index);
		int i = userDao.Mondify(name,add,tle,index);//����
		if(StringUtil.isEmpty(name)) {
			JOptionPane.showMessageDialog(null, "��������Ϊ�գ�");
			return;
		}
		
		if(i>0) {//�޸ĳɹ�
			this.dispose();
			
			JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
			//System.exit(0);
			//��ô������ǰ�漸�����ڶ��ر��أ�
			flag=1;
			new MainFrame().setVisible(true);
		}else {//�޸�ʧ��
			if(index==0) {
				JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
			}else {
				JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ������Ƿ���������ȷ�ļ��������");
			}
		}
	}
	public int getFlag() {
		return flag;
	}
}
