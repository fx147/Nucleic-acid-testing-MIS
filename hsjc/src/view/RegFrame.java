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
 * ע�����
 * @author 86173
 *
 */
public class RegFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txt_tle;
	private JTextField txt_name;
	private JTextField txt_id;
	private JPasswordField txt_pwd;
	private UserDao userDao = new UserDao();//û����
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
		label.setFont(new Font("������", Font.BOLD, 20));
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
		this.setLocationRelativeTo(null);//����
		
		
	}

	protected void IndexChanged(ActionEvent e) {
		// TODO �Զ����ɵķ������
		String[] id = {"�����","ҽ����"};
		String[] add = {"��ַ","��������"};
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
	 * ��������ԣ��绰����Ϊ11λ������  �����Ϊ18λ�����ֻ�17λ����+X �Ҿ���ź���λ������������ͬ
	 * 			    ҽ����Ϊ10λ������
	 * ʵ�������ԣ�����š�ҽ���ŷǿ��Ҳ�����ͬ
	 * ���������ԣ�����������ķǿ��ұ�������ڻ������ı���
	 * @param e
	 */
	protected void SubmitActionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		String tle = this.txt_tle.getText();
		String pwd = new String(this.txt_pwd.getPassword());
		String name = this.txt_name.getText();
		String id = this.txt_id.getText();
		String add = this.txt_add.getText();
		index = cBox.getSelectedIndex();
		int i = 0; 
		
		//���Ƶ绰���������11λ������
		if(!StringUtil.isCorrectTle(tle)) {
			JOptionPane.showMessageDialog(null, "����绰�����Ƿ�������ȷ��");
			return;
		}
		//�����������������Ϊ��
		if(StringUtil.isEmpty(pwd) || StringUtil.isEmpty(name)) {
			JOptionPane.showMessageDialog(null, "�������������Ϊ�գ�");
			return;
		}
		if(index==0) {
			//���ƾ����Ϊ18λ�����ֻ���17λ����+X
			if(!StringUtil.isCorrectUid(id)) {
				JOptionPane.showMessageDialog(null, "����ȷ�������ţ�");
				return;
			}
			i = userDao.reg(tle,pwd,name,id,add,index);//����,index��ʾ��ǰ���е��������˻���ע�� ����������ж����������
			if(i>0) {//ע��ɹ�
				this.dispose();
				new LoginFrame().setVisible(true);
			}else if(i==-1){//�绰�����ظ����µ�ע��ʧ��
				JOptionPane.showMessageDialog(null, "�õ绰�����ѱ�ע�ᣡ");
			}else if(i==-2){//�绰�����ظ����µ�ע��ʧ��
				JOptionPane.showMessageDialog(null, "�þ���ź���λ�������������ͬ��");
			}else {//������ظ����µ�ע��ʧ��
				JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ����������Ƿ�������ȷ");
			}
		}
		else {
			if(!StringUtil.isCorrectDid(id)) {
				JOptionPane.showMessageDialog(null, "����ȷ����ҽ���ţ�");
				return;
			}
			i = userDao.reg(tle,pwd,name,id,add,index);//����,index��ʾ��ǰ���е��������˻���ע�� ����������ж����������
			if(i>0) {//ע��ɹ�
				this.dispose();
				new LoginFrame().setVisible(true);
			}else if(i==-1){
				JOptionPane.showMessageDialog(null, "�õ绰�����ѱ�ע�ᣡ");
			}else {//ע��ʧ��
				JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ�����ҽ���Ż��������Ƿ�������ȷ");
			}
		}
		
		
	}

	protected void ResetActionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		this.txt_tle.setText("");
		this.txt_pwd.setText("");
		this.txt_name.setText("");
		this.txt_id.setText("");
		this.txt_add.setText("");
	}
}
