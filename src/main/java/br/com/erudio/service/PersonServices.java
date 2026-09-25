package br.com.erudio.service;

import br.com.erudio.controllers.TestLogController;
import br.com.erudio.data.dto.PersonDTO;
import br.com.erudio.excepition.ResourceNotFoundException;
import static br.com.erudio.mapper.ObjectMapper.parseListObjects;
import static br.com.erudio.mapper.ObjectMapper.parseObject;

import br.com.erudio.mapper.ObjectMapper;
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


    public PersonDTO findById(Long id){
        logger.info("Finding one Person");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found"));
        return parseObject(entity, PersonDTO.class);
    }

    public List<PersonDTO> findAll(){
        logger.info("Finding Everyone");
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }


    public PersonDTO create(PersonDTO dto){
        logger.info("Creating a Person");
        var entity = parseObject(dto, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO dto){
        logger.info("Updating a Person");
        Person entity = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found"));

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("deleting a Person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found"));
        repository.delete(entity);
    }

}
