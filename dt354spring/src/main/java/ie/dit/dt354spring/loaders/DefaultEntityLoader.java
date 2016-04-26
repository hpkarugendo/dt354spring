package ie.dit.dt354spring.loaders;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import ie.dit.dt354spring.entities.Department;
import ie.dit.dt354spring.entities.Employee;
import ie.dit.dt354spring.entities.Item;
import ie.dit.dt354spring.entities.Quote;
import ie.dit.dt354spring.repositories.DepartmentRepository;
import ie.dit.dt354spring.repositories.EmployeeRepository;
import ie.dit.dt354spring.repositories.ItemRepository;
import ie.dit.dt354spring.repositories.QuoteRepository;

@Component
public class DefaultEntityLoader implements ApplicationListener<ContextRefreshedEvent>{
	
	private DepartmentRepository dRepo;
	private EmployeeRepository eRepo;
	private ItemRepository iRepo;
	private QuoteRepository qRepo;
	private Logger log = Logger.getLogger(DefaultEntityLoader.class);
	
	@Autowired
	public void setRepo(DepartmentRepository deptRepo, EmployeeRepository empRepo,
		ItemRepository itemRepo, QuoteRepository quoteRepo){
		this.dRepo = deptRepo;
		this.eRepo = empRepo;
		this.iRepo = itemRepo;
		this.qRepo = quoteRepo;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	    /*
	     * Departments
	     */
		if(dRepo.count() < 1){
			Department d = new Department("NA");
			dRepo.save(d);
			log.info("SAVED " + d.getName() + " DEPARTMENT!");
			
			Department d2 = new Department("ACCOUNTS");
			dRepo.save(d2);
			log.info("SAVED " + d.getName() + " DEPARTMENT!");
			
			Department d3 = new Department("HR");
			dRepo.save(d3);
			log.info("SAVED " + d.getName() + " DEPARTMENT!");
			
			Department d4 = new Department("MANAGEMENT");
			dRepo.save(d4);
			log.info("SAVED " + d.getName() + " DEPARTMENT!");
			
			Department d5 = new Department("RECEPTION");
			dRepo.save(d5);
			log.info("SAVED " + d.getName() + " DEPARTMENT!");
			
			Department d6 = new Department("RESTAURANT");
			dRepo.save(d6);
			log.info("SAVED " + d.getName() + " DEPARTMENT!");
			
			Department d7 = new Department("SALES");
			dRepo.save(d7);
			log.info("SAVED " + d.getName() + " DEPARTMENT!");
		}
		
		/*
		 * ------------------------------------------
		 * Employees
		 * ------------------------------------------
		 */
		if(eRepo.count() < 1 && dRepo.count() > 1){
			Employee e = new Employee("1001", "Henry Patrick Karugendo", "henry@dit.ie", "0774455931", "MALE");
			e.setDept(dRepo.findByName("MANAGEMENT"));
			eRepo.save(e);
			
			e = new Employee("2001", "Claudia Hennessy", "claudia@dit.ie", "0174455931", "FEMALE");
			e.setDept(dRepo.findByName("RESTAURANT"));
			eRepo.save(e);
			
			e = new Employee("3001", "Bena Trust", "bena@dit.ie", "0874455931", "FEMALE");
			e.setDept(dRepo.findByName("RESTAURANT"));
			eRepo.save(e);
			
			e = new Employee("4001", "Sergio Martinez", "sergio@dit.ie", "0834455931", "MALE");
			e.setDept(dRepo.findByName("RESTAURANT"));
			eRepo.save(e);
			
			e = new Employee("5001", "Kirsty Kuchir", "kirsty@dit.ie", "0854455931", "FEMALE");
			e.setDept(dRepo.findByName("SALES"));
			eRepo.save(e);
		}
		
		/*
		 * ------------------------------------------
		 * Items
		 * ------------------------------------------
		 */
		if(iRepo.count() < 1){
			Item i = new Item("Chicken Wings", 8.00, "FOOD", "STARTER", "NA", true, false);
			iRepo.save(i);
			i = new Item("Ceasar Salad", 8.00, "FOOD", "STARTER", "NA", false, false);
			iRepo.save(i);
			i = new Item("Vegetable Soup", 5.00, "FOOD", "STARTER", "NA", false, true);
			iRepo.save(i);
			i = new Item("Fried Brie", 7.00, "FOOD", "STARTER", "NA", false, true);
			iRepo.save(i);
			i = new Item("Chicken Tenders", 17.00, "FOOD", "MAIN", "NA", false, false);
			iRepo.save(i);
			i = new Item("Pasta Sorrentino", 14.70, "FOOD", "MAIN", "NA", false, true);
			iRepo.save(i);
			i = new Item("10oz Sirloin Steak", 22.00, "FOOD", "MAIN", "NA", false, false);
			iRepo.save(i);
			i = new Item("Angus Beef Burger", 17.00, "FOOD", "MAIN", "NA", false, false);
			iRepo.save(i);
			i = new Item("Ice Cream Selection", 6.00, "FOOD", "DESSERT", "NA", false, true);
			iRepo.save(i);
			i = new Item("Apple Pie", 6.00, "FOOD", "DESSERT", "NA", false, true);
			iRepo.save(i);
			i = new Item("Cheese Cake", 6.00, "FOOD", "DESSERT", "NA", false, true);
			iRepo.save(i);
			i = new Item("Fruit Salad", 6.00, "FOOD", "DESSERT", "NA", false, true);
			iRepo.save(i);
			i = new Item("Baked Baby Potatoes", 3.00, "FOOD", "SIDE", "NA", false, true);
			iRepo.save(i);
			i = new Item("Mash Potatoes", 3.00, "FOOD", "SIDE", "NA", false, true);
			iRepo.save(i);
			i = new Item("Hand-cut Chips", 3.00, "FOOD", "SIDE", "NA", false, true);
			iRepo.save(i);
			i = new Item("Sweet Potato Fries", 3.00, "FOOD", "SIDE", "NA", false, true);
			iRepo.save(i);
			i = new Item("Gravy Sauce", 1.95, "FOOD", "SIDE", "NA", false, true);
			iRepo.save(i);
			i = new Item("Mini Pasta", 4.95, "FOOD", "KID", "NA", false, true);
			iRepo.save(i);
			i = new Item("Sausage & Chips", 4.95, "FOOD", "KID", "NA", false, false);
			iRepo.save(i);
			i = new Item("Mini Burger", 4.95, "FOOD", "KID", "NA", false, false);
			iRepo.save(i);
			i = new Item("Ice Cream", 4.95, "FOOD", "KID", "NA", false, true);
			iRepo.save(i);
			i = new Item("Pint of Guinness", 5.30, "DRINK", "BEER", "NA", false, false);
			iRepo.save(i);
			i = new Item("Bottle of Budweiser", 5.60, "DRINK", "BEER", "NA", false, false);
			iRepo.save(i);
			i = new Item("Pint of Bulmers", 6.00, "DRINK", "BEER", "NA", false, false);
			iRepo.save(i);
			i = new Item("Glass of Heinkein", 3.60, "DRINK", "BEER", "NA", false, false);
			iRepo.save(i);
			i = new Item("Bottle of Chilensis Merlot", 24.95, "DRINK", "WINE", "RED", false, false);
			iRepo.save(i);
			i = new Item("Glass of Chandon", 10.35, "DRINK", "WINE", "CHAMPAGNE", false, false);
			iRepo.save(i);
			i = new Item("Glass of Chilensis Chadonnay", 6.50, "DRINK", "WINE", "WHITE", false, false);
			iRepo.save(i);
			i = new Item("Bottle of Brut", 7.95, "DRINK", "WINE", "SPARKLING", false, false);
			iRepo.save(i);
			i = new Item("Bailey's", 4.70, "DRINK", "SPIRIT", "NA", false, false);
			iRepo.save(i);
			i = new Item("Gin Bombay Sapphire", 5.70, "DRINK", "SPIRIT", "NA", false, false);
			iRepo.save(i);
			i = new Item("Jameson", 4.70, "DRINK", "SPIRIT", "NA", false, false);
			iRepo.save(i);
			i = new Item("Hennessey Brandy", 4.70, "DRINK", "SPIRIT", "NA", false, false);
			iRepo.save(i);
			i = new Item("7up", 3.00, "DRINK", "SOFT", "NA", false, false);
			iRepo.save(i);
			i = new Item("Pepsi Diet", 3.00, "DRINK", "SOFT", "NA", false, false);
			iRepo.save(i);
			i = new Item("Orange Juice", 3.10, "DRINK", "SOFT", "NA", false, false);
			iRepo.save(i);
			i = new Item("Sparkling Water LG", 4.50, "DRINK", "SOFT", "NA", false, false);
			iRepo.save(i);
			i = new Item("English Tea", 2.50, "DRINK", "OTHER", "NA", false, false);
			iRepo.save(i);
			i = new Item("Chamomile Tea", 2.90, "DRINK", "OTHER", "HERBAL", false, false);
			iRepo.save(i);
			i = new Item("Irish Coffee", 6.50, "DRINK", "OTHER", "SPECIAL", false, false);
			iRepo.save(i);
			i = new Item("Espresso", 3.00, "DRINK", "OTHER", "NA", false, false);
			iRepo.save(i);
		}
		
		/*
		 * Quotes
		 */
		if(qRepo.count() < 1){
			Quote q = new Quote();
			q.setDayQuote("Son of Krypton vs The Bat from Gotham. Fight Night!");
			q.setDayQuoter("Lex Luthor Jr (Batman v Superman)");
			qRepo.save(q);
			q = new Quote();
			q.setDayQuote("Tell me. Do you bleed?...You will!");
			q.setDayQuoter("Batman (Batman v Superman)");
			qRepo.save(q);
		}
	}

}
