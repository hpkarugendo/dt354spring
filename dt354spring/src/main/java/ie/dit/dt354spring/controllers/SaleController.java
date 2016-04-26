package ie.dit.dt354spring.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.repositories.SaleRepository;

@Controller
public class SaleController {
	@Autowired
	private SaleRepository sRepo;
	
	@RequestMapping("/sales-by-price-all")
	public String listPriceAll(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				return "redirect:/";
		} else {
			model.addAttribute("sales", sRepo.findAllByOrderBySalesInPriceDesc());
			model.addAttribute("emp", currentEmp);
			return "sales_list";
		}
	}
	
	@RequestMapping("/sales-by-quantity-all")
	public String listQuantityAll(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				return "redirect:/";
		} else {
			model.addAttribute("sales", sRepo.findAllByOrderBySalesInQuantityDesc());
			model.addAttribute("emp", currentEmp);
			return "sales_list";
		}
	}
	
	@RequestMapping("/sales-by-name-all")
	public String listNameAll(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				return "redirect:/";
		} else {
			model.addAttribute("sales", sRepo.findAllByOrderByNameAsc());
			model.addAttribute("emp", currentEmp);
			return "sales_list";
		}
	}
	
	@RequestMapping("/sales")
	public String listAll(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				return "redirect:/";
		} else {
			model.addAttribute("sales", sRepo.findAll());
			model.addAttribute("emp", currentEmp);
			return "sales_list";
		}
	}
}
