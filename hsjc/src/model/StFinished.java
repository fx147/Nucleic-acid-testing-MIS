package model;
/**
 * 抽象的是医生已检测样本对象
 * 主要用于SDFinishedFrame(Sample-detection Finished)
 * @author 86173
 *
 */
public class StFinished {
	private String sid;//样本号
	private String result;//检测结果
	private String name;//居民姓名
	private String no;//检测序列
	public StFinished() {
		super();
		// TODO 自动生成的构造函数存根
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
