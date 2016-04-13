package ie.dit.dt354spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.dit.dt354spring.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	Employee findByCode(String code);
	Employee findByEmail(String email);
	List<Employee> findAll();
	List<Employee> findAllByOrderByCodeAsc();
	List<Employee> findAllByOrderByDeptNameAsc();
}
