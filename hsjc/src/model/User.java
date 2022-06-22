package model;
/**
 * 返回居民对象
 * @author 86173
 *
 */
public class User {
	private String id1;
	private String username;//联系方式就是username
	private String password;
	private String address;// 地址
	private String name;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String id1, String address ,String name) {
		super();
		this.id1 = id1;
		this.username = username;
		this.password = password;
		this.address = address;
		this.name = name;
	}

	public String getId() {
		return id1;
	}

	public void setId(String id) {
		this.id1 = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User(String username, String password ) {
		super();
		this.username = username;
		this.password = password;
		//this.id1 = id1;
	}
	
	/*public User(String username, String password , String id1) {
		super();
		this.username = username;
		this.password = password;
		this.id1 = id1;
	}*/

	/*public User(String username, String password, String id1, String address) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.id1 = id1;
	}*/

	

	public User(String username2) {
		this.username = username;
	}

	

	@Override
	public String toString() {
		return "User [uid=" + id1 + ", username=" + username + ", password=" + password + ", name=" + name + ", address=" + address+"]";
	}

}

