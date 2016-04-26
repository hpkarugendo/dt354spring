package ie.dit.dt354spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.dit.dt354spring.entities.Roster;

public interface RosterRepository extends CrudRepository<Roster, Integer>{
    List<Roster> findByDeptName(String name);
    List<Roster> findAll();
    List<Roster> findTop3ByOrderByIdDesc();
    List<Roster> findTop3ByDeptNameOrderByIdDesc(String deptName);
}
