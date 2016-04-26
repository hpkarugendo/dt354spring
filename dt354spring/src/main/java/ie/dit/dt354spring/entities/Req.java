package ie.dit.dt354spring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Req {
	@Id
	@GeneratedValue
	private int id;
	private String fromDate, toDate;
	private String reason, status;
	@ManyToOne
	private Employee employee;
	
	public Req() {
		super();
		this.status = "New";
	}

	public Req(String fromDate, String toDate, String reason, Employee employee) {
	    super();
	    this.fromDate = fromDate;
	    this.toDate = toDate;
	    this.reason = reason;
	    this.status = "New";
	    this.employee = employee;
	}

	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	public String getFromDate() {
	    return fromDate;
	}

	public void setFromDate(String fromDate) {
	    this.fromDate = fromDate;
	}

	public String getToDate() {
	    return toDate;
	}

	public void setToDate(String toDate) {
	    this.toDate = toDate;
	}

	public String getReason() {
	    return reason;
	}

	public void setReason(String reason) {
	    this.reason = reason;
	}

	public String getStatus() {
	    return status;
	}

	public void setStatus(String status) {
	    this.status = status;
	}

	public Employee getEmployee() {
	    return employee;
	}

	public void setEmployee(Employee employee) {
	    this.employee = employee;
	}
	
	
}
