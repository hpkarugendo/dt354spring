package ie.dit.dt354spring.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.entities.Quote;
import ie.dit.dt354spring.entities.User;
import ie.dit.dt354spring.repositories.EmployeeRepository;
import ie.dit.dt354spring.repositories.EnoteRepository;
import ie.dit.dt354spring.repositories.ItemRepository;
import ie.dit.dt354spring.repositories.OrderRepository;
import ie.dit.dt354spring.repositories.QuoteRepository;
import ie.dit.dt354spring.repositories.ReqRepository;
import ie.dit.dt354spring.repositories.RosterRepository;
import ie.dit.dt354spring.repositories.SaleRepository;

@Controller
public class IndexController {
	@Autowired
	private EmployeeRepository eRepo;
	@Autowired
	private QuoteRepository qRepo;
	@Autowired
	private RosterRepository rRepo;
	@Autowired
	private EnoteRepository enRepo;
	@Autowired
	private ItemRepository iRepo;
	@Autowired
	private ReqRepository reRepo;
	@Autowired
	private OrderRepository oRepo;
	@Autowired
	private SaleRepository sRepo;
	
	private Employee currentEmp;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}

	@RequestMapping("/login")
	public ModelAndView index(User user, HttpServletRequest req) {
		currentEmp = eRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());

		if (currentEmp != null) {
			Logger log = Logger.getLogger(IndexController.class);
			log.info(user.getEmail() + " TOOK IN!");
			req.getSession().setAttribute("currentEmp", currentEmp);
			return new ModelAndView("redirect:/home");
		}
		return new ModelAndView("index");
	}

	@RequestMapping("/home")
	public String home(Model model, HttpServletRequest req) {
		if (req.getSession(false) != null) {
			currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
			if (currentEmp != null) {
				Logger log = Logger.getLogger(IndexController.class);
				log.info(currentEmp.getEmail() + " LOGGED IN!");

				if (currentEmp.getDept().getName().equalsIgnoreCase("Management")) {
					model.addAttribute("emp", currentEmp);
					model.addAttribute("qotd", qRepo.findFirstByOrderByIdDesc());
					model.addAttribute("emps", eRepo.findTop3ByOrderByIdDesc());
					model.addAttribute("rosters", rRepo.findTop3ByOrderByIdDesc());
					model.addAttribute("reqs", reRepo.findTop3ByStatusOrderByIdDesc("New"));
					model.addAttribute("nsales", sRepo.findTop5ByOrderBySalesInQuantityDesc());
					model.addAttribute("psales", sRepo.findTop5ByOrderBySalesInPriceDesc());
					model.addAttribute("items", iRepo.findTop3ByOrderByIdDesc());
					model.addAttribute("orders", oRepo.findTop3ByOrderByIdDesc());
					model.addAttribute("enotes", enRepo.findTop3ByNoteToOrderByIdDesc(currentEmp));
					model.addAttribute("newQuote", new Quote());
					return "home_manager";
				} else {
					model.addAttribute("rosters", rRepo.findTop3ByDeptNameOrderByIdDesc(currentEmp.getDept().getName()));
					model.addAttribute("reqs", reRepo.findTop3ByEmployeeOrderByFromDateDesc(currentEmp));
					model.addAttribute("enotes", enRepo.findTop3ByNoteToOrderByIdDesc(currentEmp));
					model.addAttribute("emp", currentEmp);
					model.addAttribute("qotd", qRepo.findFirstByOrderByIdDesc());
					return "home_employee";
				}
			} else {
				return "redirect:/";
			}
		}
		
		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		Logger log = Logger.getLogger(IndexController.class);
		log.info("EMPLOYEE LOGGED OUT!");
		req.getSession(false).invalidate();
		currentEmp = null;
		return "redirect:/";
	}
}
