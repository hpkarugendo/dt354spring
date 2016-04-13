package ie.dit.dt354spring.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.repositories.EmployeeRepository;

@RestController
public class EmployeeService {
    @Autowired
    EmployeeRepository eRepo;
    
    @RequestMapping(value="/rest/employees", method=RequestMethod.GET)
    public List<Employee> getEmps(){
	return eRepo.findAll();
    }
    
    @RequestMapping(value="/rest/employees/{code}", method=RequestMethod.GET)
    public Employee getEmp(@PathVariable("code") String code){
	Logger log = Logger.getLogger(EmployeeService.class);
	log.info("REQUESTED EMPLOYEE WITH CODE: " + code);
	return eRepo.findByCode(code);
    }
}
