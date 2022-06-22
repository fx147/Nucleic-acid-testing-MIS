package model;
/**
 * 居民参与检测程度模型
 * @author 86173
 *
 */
public class Participate {
	private String uid;
	private int count;
	
	public Participate() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public Participate(String uid, int count) {
		super();
		this.uid = uid;
		this.count = count;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Participate [uid=" + uid + ", count=" + count + "]";
	}
	
	
}
