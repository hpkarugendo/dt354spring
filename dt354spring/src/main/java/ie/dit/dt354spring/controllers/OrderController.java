package ie.dit.dt354spring.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.repositories.OrderRepository;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository oRepo;

    @RequestMapping("order/{id}")
    public String viewOrder(@PathVariable long id, Model model) {
	System.out.println(String.valueOf(id));
	model.addAttribute("order", oRepo.findOne(id));
	return "orders_view";
    }

    @RequestMapping(value = "/orders-by-date", method = RequestMethod.GET)
    public String listOrdersByDate(Model model) {
	model.addAttribute("orders", oRepo.findAllByOrderByOrderDate());
	return "orders_list";
    }

    @RequestMapping(value = "/orders-by-employee", method = RequestMethod.GET)
    public String listOrdersByEmp(Model model) {
	model.addAttribute("orders", oRepo.findAllByOrderByEmpNameAsc());
	return "orders_list";
    }

    @RequestMapping(value = "/orders-by-total", method = RequestMethod.GET)
    public String listOrdersByTotal(Model model) {
	model.addAttribute("orders", oRepo.findAllByOrderByOrderTotalDesc());
	return "orders_list";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String listOrders(Model model, HttpServletRequest req) {
	Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
	if (currentEmp == null) {
	    return "redirect:/";
	} else if (!currentEmp.getDept().getName().equalsIgnoreCase("Management")) {
	    return "redirect:/";
	} else {
	    model.addAttribute("orders", oRepo.findAll());
	    return "orders_list";
	}
    }
}
