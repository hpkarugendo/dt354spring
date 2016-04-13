package ie.dit.dt354spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.dit.dt354spring.entities.EmployeeLog;

public interface EmployeeLogRepository extends CrudRepository<EmployeeLog, Integer>{
    List<EmployeeLog> findAll();
}
