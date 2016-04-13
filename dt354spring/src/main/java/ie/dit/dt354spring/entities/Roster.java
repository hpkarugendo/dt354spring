package ie.dit.dt354spring.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Roster {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Department dept;
    private String rosterDate, fileName;
    @Lob
    @Column(columnDefinition="mediumblob")
    private byte[] rosterFile;
    
    public Roster() {
	super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public String getRosterDate() {
        return rosterDate;
    }

    public void setRosterDate(String rosterDate) {
        this.rosterDate = rosterDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getRosterFile() {
        return rosterFile;
    }

    public void setRosterFile(byte[] rosterFile) {
        this.rosterFile = rosterFile;
    }
    
}
