package ie.dit.dt354spring.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Sale {
    @Id
    @GeneratedValue
    private int id;
    private Date orderDate;
    private int tableNo, roomNo, covers;
    @ManyToOne
    private Employee emp;
    @OneToMany
    private List<OrderItem> orderItems;
    private String orderStatus;
    private double orderTotal;
    private String payMethod;
    
    public Sale() {
	super();
    }

    public Sale(int tableNo, int covers, Employee emp) {
	super();
	this.orderDate = new Date();
	this.tableNo = tableNo;
	this.roomNo = 0;
	this.covers = covers;
	this.emp = emp;
	this.orderItems = new ArrayList<OrderItem>();
	this.orderStatus = "NEW";
	this.orderTotal = 0.0;
	this.payMethod = "NA";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getCovers() {
        return covers;
    }

    public void setCovers(int covers) {
        this.covers = covers;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
    
    
    
}
