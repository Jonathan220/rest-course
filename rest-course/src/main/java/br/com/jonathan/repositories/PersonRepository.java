package br.com.jonathan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jonathan.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
