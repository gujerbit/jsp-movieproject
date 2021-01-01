package vo;

import java.sql.Timestamp;

public class MemberVO {
	private String id;
	private String pw;
	private String email;
	private String tel;
	private Timestamp birth;
	
	public MemberVO() {}

	public MemberVO(String id, String pw, String email, String tel, Timestamp birth) {
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.tel = tel;
		this.birth = birth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Timestamp getBirth() {
		return birth;
	}

	public void setBirth(Timestamp birth) {
		this.birth = birth;
	}
	
	@Override
	public String toString() {
		return "[id : " + id +"] [pw : " + pw + "] [email : " + email + "] [tel : " + tel + "] [birth : " + birth + "]";
	}
}