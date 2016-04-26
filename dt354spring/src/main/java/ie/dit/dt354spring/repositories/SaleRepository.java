package ie.dit.dt354spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.dit.dt354spring.entities.Sale;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Integer>{
	List<Sale> findTop10ByOrderBySalesInPriceDesc();
	List<Sale> findTop10ByOrderBySalesInQuantityDesc();
	List<Sale> findAllByOrderBySalesInQuantityDesc();
	List<Sale> findAllByOrderBySalesInPriceDesc();
	List<Sale> findAllByOrderByNameAsc();
	Sale findByName(String name);
}
