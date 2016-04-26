package ie.dit.dt354spring.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.webresources.FileResource;
import org.apache.tomcat.util.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ie.dit.dt354spring.entities.Department;
import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.entities.Roster;
import ie.dit.dt354spring.repositories.DepartmentRepository;
import ie.dit.dt354spring.repositories.RosterRepository;

@Controller
public class RosterController {
	@Autowired
	RosterRepository rRepo;
	@Autowired
	DepartmentRepository dRepo;
	
	@RequestMapping(value="roster", method=RequestMethod.GET)
	public String getForm(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				return "redirect:/";
		} else {
			model.addAttribute("depts", dRepo.findAll());
			model.addAttribute("dept", new Department());
			return "rosters_form";
		}
		
	}
	
	@RequestMapping(value="roster", method=RequestMethod.POST)
	public String createDepartment(@RequestParam("dept") int dept, 
		@RequestParam("rosterFile") MultipartFile file,
		@RequestParam("rosterDate") String rosterDate){
		Roster r = new Roster();
		Department dt = dRepo.findOne(dept);
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		r.setDept(dt);
		r.setRosterDate(rosterDate);
		try {
		    byte[] b = file.getBytes();
		    String ext = "";
		    if(file.getContentType().contains("pdf")){
			ext = ".pdf";
		    } else if(file.getContentType().contains("xls")){
			ext = ".xls";
		    } else if(file.getContentType().contains("xlsx")) {
			ext = ".xlsx";
		    }
		    FileOutputStream fos = new FileOutputStream("src/main/resources/static/rosters/" + 
		    	    r.getRosterDate() + r.getDept().getName() + ext);
		    r.setFileName(r.getRosterDate() + r.getDept().getName() + ext);
		    fos.write(b);
		    fos.close();
		} catch (FileNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		System.out.println(r.getDept().getName());
		rRepo.save(r);
		return "redirect:/rosters";
	}
	
	@RequestMapping(value="/rosters", method=RequestMethod.GET)
	public String listDepartments(Model model, HttpServletRequest req){
		Employee currentEmp = (Employee) req.getSession(false).getAttribute("currentEmp");
		if(currentEmp == null){
			return "redirect:/";
		} else if(!currentEmp.getDept().getName().equalsIgnoreCase("Management")){
				return "redirect:/";
		} else {
			model.addAttribute("rosters", rRepo.findAll());
			return "rosters_list";
		}
		
	}
	
	@RequestMapping("roster/delete/{id}")
	public String deleteDepartment(@PathVariable Integer id, Model model){
		rRepo.delete(id);;
		return "redirect:/rosters";
	}
	
}
