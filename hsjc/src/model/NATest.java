package model;
/**
 * 这个模型记录核酸检测序列
 * 在JcFrame中查询检测序列信息时要使用
 * 在后续医生端插入检测序列信息时可能也要使用
 * @author 86173
 *
 */
public class NATest {//核酸检测序列对象
	private String testId;//检测编号
	private String point;//检测点
	private String date;//检测时间
	private String no;//检测序列


	public NATest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NATest(String testId, String point, String date, String no) {
		super();
		this.testId = testId;
		this.point = point;
		this.date = date;
		this.no = no;
	}

	public String getId() {
		return testId;
	}

	public void setId(String id) {
		this.testId = id;
	}
	
	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	@Override
	public String toString() {
		return "NATest [检测id=" + testId + ", 检测点=" + point + ", 检测时间=" + date + ", 检测序列=" + no +"]";
	}


}
