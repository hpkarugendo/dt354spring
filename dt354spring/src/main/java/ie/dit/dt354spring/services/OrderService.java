package ie.dit.dt354spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ie.dit.dt354spring.entities.Order;
import ie.dit.dt354spring.repositories.OrderRepository;

@RestController
public class OrderService {
    @Autowired
    private OrderRepository oRepo;

    @RequestMapping(value="/rest/orders/add", method=RequestMethod.POST)
    public Order postOrder(@RequestBody Order order){
	return oRepo.save(order);
    }
}
