package ie.dit.dt354spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.entities.Req;

public interface ReqRepository extends CrudRepository<Req, Integer>{
	List<Req> findTop3ByStatusOrderByIdDesc(String status);
	List<Req> findByEmployee(Employee e);
	List<Req> findTop3ByEmployeeOrderByFromDateDesc(Employee emp);
}
