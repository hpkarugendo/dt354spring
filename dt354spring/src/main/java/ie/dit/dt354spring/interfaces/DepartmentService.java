package ie.dit.dt354spring.interfaces;

import java.util.List;

import ie.dit.dt354spring.entities.Department;

public interface DepartmentService {
	List<Department> findAll();
	Department addDepartment(Department dept);
	Department findById(int id);
	void deleteDepartment(int id);
	
}
