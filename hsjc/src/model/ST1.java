package model;
/**
 * ��Ҫ��¼��һ������ѯ����
 * ResaultFrameʹ��
 * @author 86173
 *
 */
public class ST1 {
	private String sid;//������
	private String name;//����
	private String no;//�������
	public ST1() {
		super();
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	//��һ�����ֻչʾ�����������ţ��������
	/*select ����,������,������� 
	from ����,������Ϣ��,�������
	where �绰����=17335357019 
	and ����.�����=������Ϣ��.����� 
	and ������Ϣ��.���� = �������.���� ;*/
	//�ڶ���չʾ�����������ҽ���ţ�������ʽ������������ţ����ţ������ţ����㣬���ʱ��
	public ST1(String sid, String name, String no) {
		super();
		this.sid = sid;
		this.name = name;
		this.no = no;
	}
	
	@Override
	public String toString() {
		return "ST1 [sid=" + sid + ", name=" + name + ", no=" + no + "]";
	}
}
