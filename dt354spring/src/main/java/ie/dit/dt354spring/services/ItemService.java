package ie.dit.dt354spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.dit.dt354spring.entities.Item;
import ie.dit.dt354spring.repositories.ItemRepository;

@RestController
public class ItemService {
    @Autowired
    ItemRepository iRepo;
    
    @RequestMapping(value="/rest/items")
    public List<Item> getAllitems(){
	return iRepo.findAll();
    }

}
