package ie.dit.dt354spring.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.entities.Enote;
import ie.dit.dt354spring.entities.Item;
import ie.dit.dt354spring.repositories.EmployeeRepository;
import ie.dit.dt354spring.repositories.EnoteRepository;
import ie.dit.dt354spring.repositories.ItemRepository;

@Controller
public class EnoteController {
	@Autowired
	private EnoteRepository enRepo;
	@Autowired
	private EmployeeRepository eRepo;
	
	@RequestMapping(value="enote", method=RequestMethod.GET)
	public String getForm(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else {
			model.addAttribute("enote", new Enote());
			List<Employee> es = new ArrayList<Employee>();
			for(Employee e: eRepo.findAll()){
				if(e.getId() != currentEmp.getId()){
					es.add(e);
				}
			}
			model.addAttribute("emps", es);
			model.addAttribute("emp", currentEmp);
			return "enotes_form";
		}
		
	}
	
	@RequestMapping(value="enote", method=RequestMethod.POST)
	public String createNote(Enote enote, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else {
			Enote note = enote;
			note.setNoteFrom(currentEmp);
			enRepo.save(note);
			return "redirect:/home";
		}
	}
	
	@RequestMapping(value="enote/reply/{id}", method=RequestMethod.GET)
	public String reply(@PathVariable Integer id, HttpServletRequest req, Model model){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else {
			Enote n = enRepo.findOne(id);
			Enote note = new Enote();
			note.setSubject(n.getSubject());
			note.setNoteTo(n.getNoteFrom());
			note.setNoteFrom(currentEmp);
			model.addAttribute("enote", note);
			List<Employee> es = new ArrayList<Employee>();
			for(Employee e: eRepo.findAll()){
				if(e.getId() != currentEmp.getId()){
					es.add(e);
				}
			}
			model.addAttribute("emps", es);
			model.addAttribute("emp", currentEmp);
			return "enotes_form";
		}
	}
	
	@RequestMapping("enote/{id}")
	public String viewReq(@PathVariable Integer id, Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		model.addAttribute("enote", enRepo.findOne(id));
		model.addAttribute("emp", currentEmp);
		return "enotes_view";
	}
	
	@RequestMapping(value="/enotes", method=RequestMethod.GET)
	public String listReqs(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else {
			model.addAttribute("enotes", enRepo.findByNoteTo(currentEmp));
			model.addAttribute("emp", currentEmp);
			return "enotes_list";
		}
		
	}
	
	@RequestMapping("enote/delete/{id}")
	public String deleteNote(@PathVariable Integer id, Model model, HttpServletRequest req){
		enRepo.delete(id);
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		model.addAttribute("emp", currentEmp);
		return "redirect:/enotes";
	}
	
}
