package ie.dit.dt354spring.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Req {
	@Id
	@GeneratedValue
	private int id;
	private Date fromDate, toDate;
	private String reason, status;
	
	public Req() {
		super();
	}

	public Req(Date fromDate, Date toDate, String reason) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.reason = reason;
		this.status = "PENDING";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
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

}
