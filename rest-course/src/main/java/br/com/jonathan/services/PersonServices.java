package br.com.jonathan.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonathan.data.vo.v1.PersonVO;
import br.com.jonathan.data.vo.v2.PersonVOV2;
import br.com.jonathan.exceptions.ResourceNotFoundException;
import br.com.jonathan.mapper.DozerMapper;
import br.com.jonathan.mapper.custom.PersonMapper;
import br.com.jonathan.model.Person;
import br.com.jonathan.repositories.PersonRepository;

@Service
public class PersonServices {
		
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PersonMapper mapper;
	
	public List<PersonVO> findAll(){
		logger.info("Finding all people");
		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one Person");
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating one Person with v2");
		var entity = mapper.convertVOToEntity(person);
		var vo = mapper.convertEntityToVO(personRepository.save(entity));
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating one Person");
		
		var entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
	}
	
	public void delete(long id) {
		logger.info("Deleting one Person");
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		personRepository.delete(entity);
	}
	
	public PersonVO findById(long id) {
		logger.info("Finding one person!");
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}

}
