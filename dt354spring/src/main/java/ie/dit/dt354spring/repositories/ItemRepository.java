package ie.dit.dt354spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.dit.dt354spring.entities.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    List<Item> findAll();
    List<Item> findAllByOrderByNameAsc();
    List<Item> findTop3ByOrderByIdDesc();
}
