package model;
/**
 * 返回医生对象
 * 注册医生信息时要使用，查询医生个人信息时也要使用
 * @author 86173
 *
 */
public class Docter {
	private String dname;//医生名字
	private String tle;//医生电话号码
	private String pwd;//医生密码
	private String did;//医生号
	private String testCenter;//检测中心名
	
	
	
	public Docter() {
		super();
		// TODO 自动生成的构造函数存根
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
