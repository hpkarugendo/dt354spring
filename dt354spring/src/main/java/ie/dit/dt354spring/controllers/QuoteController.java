package ie.dit.dt354spring.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.dit.dt354spring.entities.Quote;
import ie.dit.dt354spring.repositories.QuoteRepository;

@Controller
public class QuoteController {
    @Autowired
    private QuoteRepository qRepo;
    
    @RequestMapping(value="/quote", method=RequestMethod.POST)
    public String addQuote(Quote quote, Model model){
	qRepo.save(quote);
	return "redirect:/home";
    }
    
    @RequestMapping(value="/quote", method=RequestMethod.GET)
    public String getForm(Model model, HttpServletRequest req){
	if(req.getSession(false) == null){
	    return "redirect:/";
	} else {
	    model.addAttribute("newQuote", new Quote());
	    return "quotes_form";
	}
    }
}
