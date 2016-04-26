package ie.dit.dt354spring.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.entities.Sale;
import ie.dit.dt354spring.repositories.EmployeeRepository;
import ie.dit.dt354spring.repositories.SaleRepository;

@RestController
public class SaleService {
    @Autowired
    private SaleRepository sRepo;
    
    @RequestMapping(value="/rest/sales-by-quantity", method=RequestMethod.GET)
    public String getSalesQ( HttpServletResponse resp){
		List<Sale> sales = sRepo.findTop10ByOrderBySalesInQuantityDesc();
		Gson gson = new Gson();
		String jsonString = gson.toJson(sales);
		resp.setContentType("application/json");
		return jsonString;
    }
    
    @RequestMapping(value="/rest/sales-by-price", method=RequestMethod.GET)
    public String getSalesP( HttpServletResponse resp){
		List<Sale> sales = sRepo.findTop10ByOrderBySalesInPriceDesc();
		Gson gson = new Gson();
		String jsonString = gson.toJson(sales);
		resp.setContentType("application/json");
		return jsonString;
    }
}
