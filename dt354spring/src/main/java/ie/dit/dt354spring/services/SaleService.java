package ie.dit.dt354spring.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ie.dit.dt354spring.entities.Sale;
import ie.dit.dt354spring.repositories.SaleRepository;

@RestController
public class SaleService {
    @Autowired
    SaleRepository sRepo;
    
    @RequestMapping(value="/rest/sales", method=RequestMethod.GET)
    public List<Sale> getOrders(){
	return sRepo.findAll();
    }
    
    @RequestMapping(value="/rest/sales/{tableNo}", method=RequestMethod.GET)
    public Sale getOrder(@PathVariable("tableNo") int tableNo){
	Logger log = Logger.getLogger(SaleService.class);
	log.info("REQUESTED ORDER WITH ID: " + tableNo);
	return sRepo.findByTableNo(tableNo);
    }
    
    @RequestMapping(value="/rest/sale", method=RequestMethod.POST)
    public ResponseEntity<String> saveOrder(@RequestBody Sale sale){
	sRepo.save(sale);
	return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
