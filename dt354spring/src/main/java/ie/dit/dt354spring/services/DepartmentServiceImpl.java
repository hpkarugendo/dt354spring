package ie.dit.dt354spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ie.dit.dt354spring.entities.Department;
import ie.dit.dt354spring.interfaces.DepartmentService;
import ie.dit.dt354spring.repositories.DepartmentRepository;

public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	private DepartmentRepository dRepo;

	@Override
	public List<Department> findAll() {
		return dRepo.findAll();
	}

	@Override
	public Department addDepartment(Department dept) {
		return dRepo.save(dept);
	}

	@Override
	public Department findById(int id) {
		return dRepo.findOne(id);
	}

	@Override
	public void deleteDepartment(int id) {
		dRepo.delete(id);
	}
}
