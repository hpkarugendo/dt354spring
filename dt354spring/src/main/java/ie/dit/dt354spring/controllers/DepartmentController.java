package ie.dit.dt354spring.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.dit.dt354spring.entities.Department;
import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.repositories.DepartmentRepository;

@Controller
public class DepartmentController {
	@Autowired
	DepartmentRepository dRepo;
	
	@RequestMapping(value="department", method=RequestMethod.GET)
	public String getForm(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				return "redirect:/";
		} else {
			model.addAttribute("dept", new Department());
			return "departments_form";
		}
		
	}
	
	@RequestMapping(value="department", method=RequestMethod.POST)
	public String createDepartment(Department dept){
		dRepo.save(dept);
		return "redirect:/department/" + dept.getId();
	}
	
	@RequestMapping("department/{id}")
	public String viewDepartment(@PathVariable Integer id, Model model){
		model.addAttribute("dept", dRepo.findOne(id));
		return "departments_view";
	}
	
	@RequestMapping(value="/departments-by-name", method=RequestMethod.GET)
	public String listDepartmentsByName(Model model){
		model.addAttribute("depts", dRepo.findAllByOrderByNameAsc());
		return "departments_list";
	}
	
	@RequestMapping(value="/departments", method=RequestMethod.GET)
	public String listDepartments(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				return "redirect:/";
		} else {
			model.addAttribute("depts", dRepo.findAll());
			return "departments_list";
		}
		
	}
	
	@RequestMapping("department/edit/{id}")
	public String updateDepartment(@PathVariable Integer id, Model model){
		model.addAttribute("dept", dRepo.findOne(id));
		return "departments_form";
	}
	
	@RequestMapping("department/delete/{id}")
	public String deleteDepartment(@PathVariable Integer id, Model model){
		dRepo.delete(id);;
		return "redirect:/departments";
	}
	
}
