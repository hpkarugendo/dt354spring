package ie.dit.dt354spring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Quote {
    @Id
    @GeneratedValue
    private int id;
    @Column(columnDefinition="TEXT")
    private String dayQuote;
    private String dayQuoter;
    
    public Quote() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayQuote() {
        return dayQuote;
    }

    public void setDayQuote(String dayQuote) {
        this.dayQuote = dayQuote;
    }

    public String getDayQuoter() {
        return dayQuoter;
    }

    public void setDayQuoter(String dayQuoter) {
        this.dayQuoter = dayQuoter;
    }
}
