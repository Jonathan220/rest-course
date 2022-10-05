package br.com.jonathan.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.jonathan.model.Person;

@Service
public class PersonServices {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<Person> findAll(){
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}
	
	public Person create(Person person) {
		logger.info("Creating one Person");
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Updating one Person");
		return person;
	}
	
	public void delete(String id) {
		logger.info("Deleting one Person");
	}
	
	public Person findById() {
		logger.info("Finding one person!");
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("jonathan");
		person.setLastName("Silva");
		person.setAddress("Rio de janeiro");
		person.setGender("Male");
		
		
		return person;
	}

	private Person mockPerson(int i) {
		logger.info("Finding all people");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name "+i);
		person.setLastName("Last name "+i);
		person.setAddress("Some address in Brazil "+i);
		person.setGender("Male");
		return person;
	}
}
