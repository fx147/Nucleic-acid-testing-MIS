package model;
/**
 * 这个模型记录样本
 * 在CymFrame中插入新样本数据时 要使用
 * @author 86173
 *
 */
public class Sample {//样本对象
	private String sid;//样本号
	private String result;//检测结果
	private String uid;//居民号
	private String tid;//检测号


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
		this.result = "未化验";
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

	public void setUid(String uid) {//提供此方法，但是不提供实现此方法的接口
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
		return "NATest [样本号=" + sid + ", 化验结果=" + result + ", 居民号=" + uid + ", 检测号=" + tid +"]";
	}


}
