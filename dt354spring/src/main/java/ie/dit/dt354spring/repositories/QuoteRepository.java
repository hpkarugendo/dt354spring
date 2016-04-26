package ie.dit.dt354spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.dit.dt354spring.entities.Quote;

public interface QuoteRepository extends CrudRepository<Quote, Integer>{
    Quote findFirstByOrderByIdDesc();
}
