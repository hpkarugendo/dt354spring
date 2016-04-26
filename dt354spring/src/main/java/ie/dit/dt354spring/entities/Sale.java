package ie.dit.dt354spring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sale {
	@Id
	@GeneratedValue
	private int id;
	@Column(unique=true)
	private String name;
	private double salesInPrice;
	private int salesInQuantity;
	
	public Sale() {
		super();
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

	public double getSalesInPrice() {
		return salesInPrice;
	}

	public void setSalesInPrice(double salesInPrice) {
		this.salesInPrice = salesInPrice;
	}

	public int getSalesInQuantity() {
		return salesInQuantity;
	}

	public void setSalesInQuantity(int salesInQuantity) {
		this.salesInQuantity = salesInQuantity;
	}
	
}
