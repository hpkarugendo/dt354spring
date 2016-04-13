package ie.dit.dt354spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.dit.dt354spring.entities.Sale;

public interface SaleRepository extends CrudRepository<Sale, Integer>{
    List<Sale> findAll();
    List<Sale> findByOrderStatus(String orderStatus);
    List<Sale> findAllByOrderByPayMethodAsc();
    List<Sale> findAllByOrderByOrderDateAsc();
    Sale findByTableNo(int tableNo);
}
