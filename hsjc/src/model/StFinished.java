package model;
/**
 * �������ҽ���Ѽ����������
 * ��Ҫ����SDFinishedFrame(Sample-detection Finished)
 * @author 86173
 *
 */
public class StFinished {
	private String sid;//������
	private String result;//�����
	private String name;//��������
	private String no;//�������
	public StFinished() {
		super();
		// TODO �Զ����ɵĹ��캯�����
	}
	public StFinished(String sid, String result, String name, String no) {
		super();
		this.sid = sid;
		this.result = result;
		this.name = name;
		this.no = no;
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
	public void setResult(String result) {
		this.result = result;
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
	@Override
	public String toString() {
		return "StFinished [sid=" + sid + ", result=" + result + ", name=" + name + ", no=" + no + "]";
	}
	
	
}
