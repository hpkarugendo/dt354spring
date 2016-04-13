package ie.dit.dt354spring.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private int id;
	private String code;
	private String name;
	private String email;
	private String phone, gender;
	@ManyToOne
	private Department dept;
	@OneToMany
	private List<Req> reqs;
	private boolean loggedIn;
	
	public Employee() {
		super();
	}

	public Employee(String code, String name, String email, String phone, String gender) {
		super();
		this.code = code;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.dept = new Department();
		this.reqs = new ArrayList<Req>();
		this.loggedIn = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public List<Req> getReqs() {
		return reqs;
	}

	public void setReqs(List<Req> reqs) {
		this.reqs = reqs;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
}
