package ie.dit.dt354spring;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import ie.dit.dt354spring.entities.Employee;

public class Testing extends Assert{
	
	@Test
	public void get_rest(){
		RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity<List> emps = restTemplate.getForEntity("http://localhost:8080/rest/employees", List.class);
        for(int i = 0; i < emps.getBody().size(); i++){
        	Object o = emps.getBody().get(i);
        	System.out.println(o.toString());
        	if(o instanceof Employee){
        		System.out.println("YES!");
        	} else{
        		System.out.println("NO!");
        	}
        }
	}
	
	@Test
	public void new_test(){
		final String url = "http://localhost:8080/rest/employees";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ParameterizedTypeReference<List<Employee>> typeRef = new ParameterizedTypeReference<List<Employee>>() {};
        ResponseEntity<List<Employee>> response = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
        Employee em = null;
		 for(Employee e: response.getBody()){
			 if(e.getCode().equalsIgnoreCase("2001")){
				 em = e;
			 }
		 }
		 
		 System.out.println(em.getName());
	}

}
