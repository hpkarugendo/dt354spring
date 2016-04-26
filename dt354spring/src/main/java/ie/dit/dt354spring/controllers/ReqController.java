package ie.dit.dt354spring.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.entities.Item;
import ie.dit.dt354spring.entities.Req;
import ie.dit.dt354spring.repositories.EmployeeRepository;
import ie.dit.dt354spring.repositories.ItemRepository;
import ie.dit.dt354spring.repositories.ReqRepository;

@Controller
public class ReqController {
	@Autowired
	private ReqRepository reRepo;
	@Autowired
	private EmployeeRepository eRepo;
	
	@RequestMapping(value="req", method=RequestMethod.GET)
	public String getForm(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else {
			model.addAttribute("req", new Req());
			model.addAttribute("emp", currentEmp);
			return "reqs_form";
		}
		
	}
	
	@RequestMapping(value="req", method=RequestMethod.POST)
	public String createItem(Req req, HttpServletRequest request){
		Employee currentEmp = (Employee) request.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else {
			Req r = req;
			r.setEmployee(currentEmp);
			reRepo.save(r);
		}
		return "redirect:/home";
	}
	
	@RequestMapping("req/{id}")
	public String viewItem(@PathVariable Integer id, Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		model.addAttribute("req", reRepo.findOne(id));
		model.addAttribute("emp", currentEmp);
		return "reqs_view";
	}
	
	@RequestMapping(value="/reqs", method=RequestMethod.GET)
	public String listItems(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
			model.addAttribute("reqs", reRepo.findByEmployee(currentEmp));
			model.addAttribute("emp", currentEmp);
		} else {
			model.addAttribute("reqs", reRepo.findAll());
			model.addAttribute("emp", currentEmp);
		}
		return "reqs_list";
	}
	
	@RequestMapping("req/okay/{id}")
	public String updateItem(@PathVariable Integer id, Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				return "redirect:/home";
		} else {
			Req r = reRepo.findOne(id);
			r.setStatus("Approved");
			reRepo.save(r);
			model.addAttribute("emp", currentEmp);
			return "redirect:/reqs";
		}
	}
	
	@RequestMapping("req/nokay/{id}")
	public String deleteItem(@PathVariable Integer id, Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				return "redirect:/home";
		} else {
			Req r = reRepo.findOne(id);
			r.setStatus("Denied");
			reRepo.save(r);
			model.addAttribute("emp", currentEmp);
			return "redirect:/reqs";
		}
	}
	
}
