package ie.dit.dt354spring.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.entities.User;
import ie.dit.dt354spring.repositories.EmployeeRepository;

@Controller
public class IndexController {
    @Autowired
    private EmployeeRepository eRepo;
    private Employee currentEmp;

	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("user", new User());
		return "index";
	}
	
	@RequestMapping("/login")
	public ModelAndView index(User user, HttpServletRequest req){
		currentEmp = eRepo.findByEmail(user.getEmail());
		
		if(currentEmp != null){
		    Logger log = Logger.getLogger(IndexController.class);
		    log.info(user.getEmail() + " TOOK IN!");
		    req.getSession().setAttribute("currentEmp", currentEmp);
		    return new ModelAndView("redirect:/home");
		}
		return new ModelAndView("index");
	}
	
	@RequestMapping("/home")
	public String home(Model model, HttpServletRequest req){
		if(req.getSession(false) != null){
			currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
			if(currentEmp != null){
			    Logger log = Logger.getLogger(IndexController.class);
			    log.info(currentEmp.getEmail() + " LOGGED IN!");
			    
			    if(currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				model.addAttribute("emp", currentEmp);
				return "home_manager";
			    } else {
				model.addAttribute("emp", currentEmp);
				return "home_employee";
			    }
			} else {
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req){
	    Logger log = Logger.getLogger(IndexController.class);
	    log.info("EMPLOYEE LOGGED OUT!");
	    req.getSession(false).invalidate();
	    currentEmp = null;
	    return "redirect:/";
	}
}
