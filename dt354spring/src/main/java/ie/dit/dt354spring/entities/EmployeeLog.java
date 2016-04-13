package ie.dit.dt354spring.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EmployeeLog {
    
    @Id
    @GeneratedValue
    private int id;
    private Date when;
    private String what;
    @ManyToOne
    private Employee who;
    
    public EmployeeLog() {
	super();
    }
    
    

    public EmployeeLog(String what, Employee who) {
	super();
	this.when = new Date();
	this.what = what;
	this.who = who;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public Employee getWho() {
        return who;
    }

    public void setWho(Employee who) {
        this.who = who;
    }
    
    

}
