package ie.dit.dt354spring.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Enote {
    
    @Id
    @GeneratedValue
    private int id;
    private Date dateSent;
    private String subject, body;
    @OneToOne
    private Employee noteFrom;
    @OneToOne
    private Employee noteTo;
    private boolean seen;
    
    public Enote() {
	super();
	this.dateSent = new Date();
    }

    public Enote(String subject, String body, Employee noteFrom, Employee noteTo) {
	super();
	this.dateSent = new Date();
	this.subject = subject;
	this.body = body;
	this.noteFrom = noteFrom;
	this.noteTo = noteTo;
	this.seen = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Employee getNoteFrom() {
        return noteFrom;
    }

    public void setNoteFrom(Employee noteFrom) {
        this.noteFrom = noteFrom;
    }

    public Employee getNoteTo() {
        return noteTo;
    }

    public void setNoteTo(Employee noteTo) {
        this.noteTo = noteTo;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
    
    

}
