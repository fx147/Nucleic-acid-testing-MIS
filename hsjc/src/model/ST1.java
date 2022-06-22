package model;
/**
 * 主要记录第一层结果查询界面
 * ResaultFrame使用
 * @author 86173
 *
 */
public class ST1 {
	private String sid;//样本号
	private String name;//姓名
	private String no;//检测序列
	public ST1() {
		super();
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
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
	//第一层界面只展示姓名，样本号，检测序列
	/*select 姓名,样本号,检测序列 
	from 居民,样本信息表,检测序列
	where 电话号码=17335357019 
	and 居民.居民号=样本信息表.居民号 
	and 样本信息表.检测号 = 检测序列.检测号 ;*/
	//第二层展示检测结果，化验医生号，采样方式，姓名，居民号，检测号，样本号，检测点，检测时间
	public ST1(String sid, String name, String no) {
		super();
		this.sid = sid;
		this.name = name;
		this.no = no;
	}
	
	@Override
	public String toString() {
		return "ST1 [sid=" + sid + ", name=" + name + ", no=" + no + "]";
	}
}
