package ie.dit.dt354spring.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.entities.Item;
import ie.dit.dt354spring.entities.Order;
import ie.dit.dt354spring.repositories.EmployeeRepository;
import ie.dit.dt354spring.repositories.ItemRepository;
import ie.dit.dt354spring.repositories.OrderRepository;

@Component
public class GetData implements CommandLineRunner{
    @Autowired
    private OrderRepository oRepo;
    @Autowired
    private EmployeeRepository eRepo;
    @Autowired
    private ItemRepository iRepo;

    @Override
    public void run(String... arg0) throws Exception {
	Firebase root = new Firebase("https://sizzling-heat-8093.firebaseio.com/dt354spring");
	Firebase ordersRef = root.child("orders");
	Firebase itemsRef = root.child("items");
	Firebase employeesRef = root.child("employees");
	    ordersRef.addValueEventListener(new ValueEventListener() {
	        
	        @Override
	        public void onDataChange(DataSnapshot snapshot) {
	            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
	                Order post = postSnapshot.getValue(Order.class);
	                oRepo.save(post);
	            }
	            System.out.println("ORDERS SAVED");
	        }

	        @Override
	        public void onCancelled(FirebaseError firebaseError) {
	            System.out.println("READ FAILED!!");
	        }
	    });
	    
	    for(Employee e: eRepo.findAll()){
		Firebase empRef = employeesRef.child(e.getCode());
		empRef.setValue(e);
	    }
	    
	    for(Item i: iRepo.findAll()){
		Firebase itemRef = itemsRef.child(String.valueOf(i.getId()));
		itemRef.setValue(i);
	    }
	
    }

}
