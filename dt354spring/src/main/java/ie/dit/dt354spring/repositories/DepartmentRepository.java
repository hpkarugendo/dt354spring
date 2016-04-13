package ie.dit.dt354spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.dit.dt354spring.entities.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer>{
	Department findByName(String name);
	List<Department> findAll();
	List<Department> findAllByOrderByNameAsc();
}
