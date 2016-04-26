package ie.dit.dt354spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.entities.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
    List<Order> findAll();
    Order findById(long id);
    List<Order> findAllByOrderByEmpNameAsc();
    List<Order> findAllByOrderStatus(String stat);
    List<Order> findAllByOrderByOrderTotalDesc();
    List<Order> findAllByOrderByOrderDate();
    List<Order> findTop3ByOrderByIdDesc();
}
