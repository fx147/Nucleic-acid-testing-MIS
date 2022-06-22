package model;
/**
 * 主要用来记录内层，第二层核酸检测结果详情的模型 
 * InnerResaultFrame中使用
 * @author 86173
 *
 */
public class ST2 {
	private String resault;
	private String uname;
	private String uid;
	private String sid;
	private String no;
	private String date;
	private String point;//采样点
	private String method;//采样方式
	private String dname;//医生名
	
	public ST2() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public ST2(String resault, String uname, String uid, String sid, String no, String date, String point,
			String method, String dname) {
		super();
		this.resault = resault;
		this.uname = uname;
		this.uid = uid;
		this.sid = sid;
		this.no = no;
		this.date = date;
		this.point = point;
		this.method = method;
		this.dname = dname;
	}
	
	public String getResault() {
		return resault;
	}
	public void setResault(String resault) {
		this.resault = resault;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	@Override
	public String toString() {
		return "ST2 [resault=" + resault + ", uname=" + uname + ", uid=" + uid + ", sid=" + sid + ", no=" + no
				+ ", date=" + date + ", point=" + point + ", method=" + method + ", dname=" + dname + "]";
	}
	
}
