package model;
/**
 * ���ģ�ͼ�¼����������
 * ��JcFrame�в�ѯ���������ϢʱҪʹ��
 * �ں���ҽ���˲�����������Ϣʱ����ҲҪʹ��
 * @author 86173
 *
 */
public class NATest {//���������ж���
	private String testId;//�����
	private String point;//����
	private String date;//���ʱ��
	private String no;//�������


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
		return "NATest [���id=" + testId + ", ����=" + point + ", ���ʱ��=" + date + ", �������=" + no +"]";
	}


}
