package ie.dit.dt354spring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Item item;
    private boolean served;
    private String note;
    
    public OrderItem() {
	super();
    }

    public OrderItem(Item item) {
	super();
	this.item = item;
	this.served = false;
	this.note = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isServed() {
        return served;
    }

    public void setServed(boolean served) {
        this.served = served;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    

}
