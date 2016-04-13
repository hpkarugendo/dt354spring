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
import ie.dit.dt354spring.repositories.ItemRepository;

@Controller
public class ItemController {
	@Autowired
	ItemRepository iRepo;
	
	@RequestMapping(value="item", method=RequestMethod.GET)
	public String getForm(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				return "redirect:/";
		} else {
			model.addAttribute("item", new Item());
			return "items_form";
		}
		
	}
	
	@RequestMapping(value="item", method=RequestMethod.POST)
	public String createItem(Item item){
		iRepo.save(item);
		return "redirect:/item/" + item.getId();
	}
	
	@RequestMapping("item/{id}")
	public String viewItem(@PathVariable Integer id, Model model){
		model.addAttribute("item", iRepo.findOne(id));
		return "items_view";
	}
	
	@RequestMapping(value="/items-by-name", method=RequestMethod.GET)
	public String listItemsByDept(Model model){
		model.addAttribute("items", iRepo.findAllByOrderByNameAsc());
		return "items_list";
	}
	
	@RequestMapping(value="/items", method=RequestMethod.GET)
	public String listItems(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				return "redirect:/";
		} else {
			model.addAttribute("items", iRepo.findAll());
			return "items_list";
		}
		
	}
	
	@RequestMapping("item/edit/{id}")
	public String updateItem(@PathVariable Integer id, Model model){
		model.addAttribute("item", iRepo.findOne(id));
		return "items_form";
	}
	
	@RequestMapping("item/delete/{id}")
	public String deleteItem(@PathVariable Integer id, Model model){
		iRepo.delete(id);;
		return "redirect:/items";
	}
	
}
