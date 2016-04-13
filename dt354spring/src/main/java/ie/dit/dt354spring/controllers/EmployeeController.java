package ie.dit.dt354spring.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.repositories.DepartmentRepository;
import ie.dit.dt354spring.repositories.EmployeeRepository;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeRepository eRepo;
	@Autowired
	DepartmentRepository dRepo;
	
	@RequestMapping(value="employee", method=RequestMethod.GET)
	public String getForm(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				return "redirect:/";
		} else {
			model.addAttribute("emp", new Employee());
			model.addAttribute("depts", dRepo.findAllByOrderByNameAsc());
			return "employees_form";
		}
	}
	
	@RequestMapping(value="employee", method=RequestMethod.POST)
	public String createEmployee(Employee emp){
		eRepo.save(emp);
		return "redirect:/employee/" + emp.getId();
	}
	
	@RequestMapping("employee/{id}")
	public String viewEmployee(@PathVariable Integer id, Model model){
		model.addAttribute("emp", eRepo.findOne(id));
		return "employees_view";
	}
	
	@RequestMapping(value="/employees-by-code", method=RequestMethod.GET)
	public String listEmployeesByCode(Model model){
		model.addAttribute("emps", eRepo.findAllByOrderByCodeAsc());
		return "employees_list";
	}
	
	@RequestMapping(value="/employees-by-dept", method=RequestMethod.GET)
	public String listEmployeesByDept(Model model){
		model.addAttribute("emps", eRepo.findAllByOrderByDeptNameAsc());
		return "employees_list";
	}
	
	@RequestMapping(value="/employees", method=RequestMethod.GET)
	public String listEmployees(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				return "redirect:/";
		} else {
			model.addAttribute("emps", eRepo.findAll());
			return "employees_list";
		}
	}
	
	@RequestMapping("employee/edit/{id}")
	public String updateEmployee(@PathVariable Integer id, Model model){
		model.addAttribute("emp", eRepo.findOne(id));
		model.addAttribute("depts", dRepo.findAllByOrderByNameAsc());
		return "employees_form";
	}
	
	@RequestMapping("employee/delete/{id}")
	public String deleteEmployee(@PathVariable Integer id, Model model){
		eRepo.delete(id);;
		return "redirect:/employees";
	}
	
}
