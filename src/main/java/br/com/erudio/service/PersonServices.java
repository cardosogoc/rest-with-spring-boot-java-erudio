package br.com.erudio.service;

import br.com.erudio.controllers.TestLogController;
import br.com.erudio.excepition.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices {

    @Autowired
    private PersonRepository repository;

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());


    public Person findById(Long id){
        logger.info("Finding one Person");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found"));
    }

    public List<Person> findAll(){
        logger.info("Finding Everyone");
        return repository.findAll();
    }


    public Person create(Person person){
        logger.info("Creating a Person");
        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating a Person");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("deleting a Person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found"));
        repository.delete(entity);
    }

}
