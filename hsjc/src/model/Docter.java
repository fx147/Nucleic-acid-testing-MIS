package model;
/**
 * ����ҽ������
 * ע��ҽ����ϢʱҪʹ�ã���ѯҽ��������ϢʱҲҪʹ��
 * @author 86173
 *
 */
public class Docter {
	private String dname;//ҽ������
	private String tle;//ҽ���绰����
	private String pwd;//ҽ������
	private String did;//ҽ����
	private String testCenter;//���������
	
	
	
	public Docter() {
		super();
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public Docter(String dname, String tle, String pwd, String did, String testCenter) {
		super();
		this.dname = dname;
		this.tle = tle;
		this.pwd = pwd;
		this.did = did;
		this.testCenter = testCenter;
	}

	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getTle() {
		return tle;
	}
	public void setTle(String tle) {
		this.tle = tle;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getTestCenter() {
		return testCenter;
	}
	public void setTestCenter(String testCenter) {
		this.testCenter = testCenter;
	}

	@Override
	public String toString() {
		return "Docter [dname=" + dname + ", tle=" + tle + ", pwd=" + pwd + ", did=" + did + ", testCenter="
				+ testCenter + "]";
	}
	
	
}
