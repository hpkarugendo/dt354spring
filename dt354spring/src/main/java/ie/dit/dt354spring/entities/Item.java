package ie.dit.dt354spring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique=true)
    private String name;
    private int quantity;
    private double price;
    private String itemCat, itemType, itemSubType;
    private boolean foodSpicy, foodVeg;
    
    public Item() {
	super();
    }
    

    public Item(String name, double price, String itemCat, String itemType, String itemSubType,
	    boolean foodSpicy, boolean foodVeg) {
	super();
	this.name = name;
	this.quantity = 1000;
	this.price = price;
	this.itemCat = itemCat;
	this.itemType = itemType;
	this.itemSubType = itemSubType;
	this.foodSpicy = foodSpicy;
	this.foodVeg = foodVeg;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemCat() {
        return itemCat;
    }

    public void setItemCat(String itemCat) {
        this.itemCat = itemCat;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemSubType() {
        return itemSubType;
    }

    public void setItemSubType(String itemSubType) {
        this.itemSubType = itemSubType;
    }

    public boolean isFoodSpicy() {
        return foodSpicy;
    }

    public void setFoodSpicy(boolean foodSpicy) {
        this.foodSpicy = foodSpicy;
    }

    public boolean isFoodVeg() {
        return foodVeg;
    }

    public void setFoodVeg(boolean foodVeg) {
        this.foodVeg = foodVeg;
    }
    
    
    
    
}
