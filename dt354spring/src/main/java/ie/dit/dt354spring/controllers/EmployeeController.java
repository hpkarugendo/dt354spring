package ie.dit.dt354spring.controllers;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.dit.dt354spring.components.MailComponent;
import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.repositories.DepartmentRepository;
import ie.dit.dt354spring.repositories.EmployeeRepository;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeRepository eRepo;
	@Autowired
	private DepartmentRepository dRepo;
	@Autowired
	private MailComponent mail;
	
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
	public String createEmployee(Employee emp, Model model){
		Employee e = eRepo.findByEmail(emp.getEmail());
		Employee e2 = eRepo.findByCode(emp.getCode());
		if(e != null || e2 != null){
			model.addAttribute("errorm", "That employee exists");
			model.addAttribute("emp", new Employee());
			model.addAttribute("depts", dRepo.findAllByOrderByNameAsc());
			return "employees_form";
		}
		eRepo.save(emp);
		try {
			String to = emp.getEmail();
			String bodyT = "<p>Hi " + emp.getName() + ", </p>"
					+ "<p>Welcome to our company. Please visit http://localhost:8080. Log in with your email and password: <i>'pass'</i>.</p>"
					+ "<p>You can change your password on your home page later. You can also do these on the site:</p>"
					+ "<p>1. Roster <br /> "
					+ "2. Send and Recieve Messages from other employees <br />"
					+ "3. Apply for Time Off </p>"
					+ "<p>Hope you have a lovely time with us. </p>"
					+ "<p>Sincerely,<br />"
					+ "<b>Manager:</b> <i>Henry Patrick Karugendo.</i></p>";
			String bodyH = "<p>Hi " + emp.getName() + ", </p>"
					+ "<p>Welcome to our company. Please visit http://localhost:8080. Log in with your email and password: <i>'pass'</i>.</p>"
					+ "<p>You can change your password on your home page later. You can also do these on the site:</p>"
					+ "<p>1. Roster <br /> "
					+ "2. Send and Recieve Messages from other employees <br />"
					+ "3. Apply for Time Off </p>"
					+ "<p>Hope you have a lovely time with us. </p>"
					+ "<p>Sincerely,<br />"
					+ "<b>Manager:</b> <i>Henry Patrick Karugendo.</i></p>";
			mail.sendRegMail(to, bodyT, bodyH);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "redirect:/employees";
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
	
	@RequestMapping(value="/change-pass", method=RequestMethod.POST)
	public String changePass(HttpServletRequest req, Model model){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		}
		String pass1 = req.getParameter("pass1");
		String pass2 = req.getParameter("pass2");
		if(!pass1.equals(pass2)){
			model.addAttribute("errorm", "Passwords don't match");
			model.addAttribute("emp", currentEmp);
			return "pass_form";
		}
		
		Employee e = eRepo.findOne(currentEmp.getId());
		e.setPassword(pass1);
		eRepo.save(e);
		
		try {
			String to = currentEmp.getEmail();
			String bodyT = "<html><body><p>Hi "+ currentEmp.getName() +",</p>"
					+ "<p>You have successfully changed your password to <i>" + e.getPassword() +"</i></p>"
							+ "<p>Visit the site at http://localhost:8080 to test it.</p>"
							+ "<p><b>DT354 Bistro</b></p></body></html>";
			String bodyH = "<html><body><p>Hi "+ currentEmp.getName() +",</p>"
					+ "<p>You have successfully changed your password to <i>" + e.getPassword() +"</i></p>"
							+ "<p>Visit the site at http://localhost:8080 to test it.</p>"
							+ "<p><b>DT354 Bistro</b></p></body></html>";
			
			mail.sendPassMail(to, bodyT, bodyH);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "redirect:/logout";
	}
	
	@RequestMapping(value="/change-pass", method=RequestMethod.GET)
	public String changePassForm(HttpServletRequest req, Model model){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		}
		model.addAttribute("emp", currentEmp);
		return "pass_form";
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
