package com.example.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PersonDao;
//import com.example.demo.dao.PersonListRepository;
import com.example.demo.entity.Person;


/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/persons")
public class PersonsController {

	@Autowired
	private PersonDao personDao;

//	@Autowired
	public PersonsController(PersonDao personDao) {
	//	super();
		this.personDao = personDao;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getPerson(
					@PathVariable("id") String id,
					Model model) {
		Person person;
		try {
			person = personDao.getById(id);
			if( person != null ) {
				model.addAttribute("person", person);
			}else {
				return "prerson not found";
			}
		}catch(Exception ex) {
			return "person not found : " + ex.toString(); 
		}
		
		//return "personList";
		return person.toString();
	}
	
	@RequestMapping(value="/create/{id}")
	public String addToPersonList(
					@PathVariable("id") String id) {
		Person person = new Person();
		//person.setId(id);
		person.setName("hl");
		person.setAge(13);
		person.setSex(0);
		personDao.create(person);
		return person.getId();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String addToPersonList(
					@PathVariable("id") String id,
					Person person) {
		person.setName(id);
		personDao.create(person);
		return "redirect:/{id}";
	}
}
