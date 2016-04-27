package ie.dit.dt354spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.dit.dt354spring.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	Employee findByCode(String code);
	Employee findByEmail(String code);
	Employee findByEmailAndPassword(String email, String password);
	List<Employee> findAll();
	List<Employee> findAllByOrderByCodeAsc();
	List<Employee> findAllByOrderByDeptNameAsc();
	List<Employee> findTop3ByOrderByIdDesc();
}
