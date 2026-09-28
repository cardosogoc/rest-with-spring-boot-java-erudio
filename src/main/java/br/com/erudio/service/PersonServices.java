package br.com.erudio.service;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.controllers.TestLogController;
import br.com.erudio.data.dto.PersonDTO;
import br.com.erudio.excepition.ResourceNotFoundException;

import static br.com.erudio.mapper.ObjectMapper.parseListObjects;
import static br.com.erudio.mapper.ObjectMapper.parseObject;

import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices {

    @Autowired
    private PersonRepository repository;

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());


    public PersonDTO findById(Long id) {
        logger.info("Finding one Person");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found"));
        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public List<PersonDTO> findAll() {
        logger.info("Finding Everyone");
        var persons = parseListObjects(repository.findAll(), PersonDTO.class);
        persons.forEach(p -> addHateoasLinks(p));
        return persons;
    }


    public PersonDTO create(PersonDTO dto) {
        logger.info("Creating a Person");
        var entity = parseObject(dto, Person.class);
        var newDTO = parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(newDTO);
        return newDTO;
    }

    public PersonDTO update(PersonDTO dto) {
        logger.info("Updating a Person");
        Person entity = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found"));

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());

        var newDTO = parseObject(repository.save(entity), PersonDTO.class);

        addHateoasLinks(newDTO);
        return newDTO;
    }

    public void delete(Long id) {
        logger.info("deleting a Person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found"));
        repository.delete(entity);
    }

    private static void addHateoasLinks(PersonDTO dto) {
        //GET - FindById
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        //GET - FindAll
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        //Post - Create
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        //PUT - Create
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        //DELETE
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
