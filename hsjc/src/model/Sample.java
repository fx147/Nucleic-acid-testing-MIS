package model;
/**
 * ���ģ�ͼ�¼����
 * ��CymFrame�в�������������ʱ Ҫʹ��
 * @author 86173
 *
 */
public class Sample {//��������
	private String sid;//������
	private String result;//�����
	private String uid;//�����
	private String tid;//����


	public Sample() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sample(String sid, String result, String uid, String tid) {
		super();
		this.sid = sid;
		this.result = result;
		this.uid = uid;
		this.tid = tid;
	}
	
	public Sample(String uid, String tid) {
		super();
		this.sid = tid + uid.substring(14);
		this.result = "δ����";
		this.uid = uid;
		this.tid = tid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	
	public String getResult() {
		return result;
	}

	public void setPoint(String result) {
		this.result = result;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {//�ṩ�˷��������ǲ��ṩʵ�ִ˷����Ľӿ�
		this.uid = uid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}
	
	@Override
	public String toString() {
		return "NATest [������=" + sid + ", ������=" + result + ", �����=" + uid + ", ����=" + tid +"]";
	}


}
