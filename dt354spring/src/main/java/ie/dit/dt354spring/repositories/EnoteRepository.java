package ie.dit.dt354spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.dit.dt354spring.entities.Enote;

public interface EnoteRepository extends CrudRepository<Enote, Integer>{
    List<Enote> findAll();
    List<Enote> findByNoteFromId(int id);
    List<Enote> findByNoteToId(int id);
}
