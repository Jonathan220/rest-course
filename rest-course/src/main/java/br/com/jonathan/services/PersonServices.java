package br.com.jonathan.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonathan.exceptions.ResourceNotFoundException;
import br.com.jonathan.model.Person;
import br.com.jonathan.repositories.PersonRepository;

@Service
public class PersonServices {
		
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository personRepository;
	
	public List<Person> findAll(){
		return personRepository.findAll();
	}
	
	public Person create(Person person) {
		logger.info("Creating one Person");
		return personRepository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating one Person");
		
		Person entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return personRepository.save(entity);
	}
	
	public void delete(long id) {
		logger.info("Deleting one Person");
		Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		personRepository.delete(entity);
	}
	
	public Person findById(long id) {
		logger.info("Finding one person!");
		return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}

}
