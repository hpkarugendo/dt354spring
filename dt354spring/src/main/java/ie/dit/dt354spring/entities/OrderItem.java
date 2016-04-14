package ie.dit.dt354spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OrderItem implements Serializable{
    @Id
    private long id;
    @OneToOne
    private Item item;
    private boolean served;
    private String littleNote;
    
    public OrderItem() {
	super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getLittleNote() {
        return littleNote;
    }

    public void setLittleNote(String littleNote) {
        this.littleNote = littleNote;
    }
    
}
