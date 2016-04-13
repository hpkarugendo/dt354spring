package ie.dit.dt354spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.dit.dt354spring.entities.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer>{
    List<OrderItem> findAll();
}
