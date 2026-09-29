package br.com.erudio.service;

import br.com.erudio.controllers.BooksController;
import br.com.erudio.controllers.PersonController;
import br.com.erudio.controllers.TestLogController;
import br.com.erudio.data.dto.BooksDTO;
import br.com.erudio.data.dto.PersonDTO;
import br.com.erudio.excepition.RequiredObjectIsNullException;
import br.com.erudio.excepition.ResourceNotFoundException;
import br.com.erudio.model.Books;
import br.com.erudio.repository.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.erudio.mapper.ObjectMapper.parseListObjects;
import static br.com.erudio.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BooksServices {

    @Autowired
    private BooksRepository repository;

    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    public BooksDTO findById(Integer id) {
        logger.info("Finding one Book");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found"));

        var dto = parseObject(entity, BooksDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public List<BooksDTO> findAll() {
        logger.info("Finding Everyone");

        var books = parseListObjects(repository.findAll(), BooksDTO.class);

        books.forEach(b -> addHateoasLinks(b));

        return books;
    }

    public BooksDTO create(BooksDTO dto) {
        if (dto == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Creating a Book");

        var entity = parseObject(dto, Books.class);

        var newDTO = parseObject(
                repository.save(entity),
                BooksDTO.class
        );

        addHateoasLinks(newDTO);

        return newDTO;
    }

    public BooksDTO update(BooksDTO dto) {
        if (dto == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Updating a Book");

        Books entity = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found"));

        entity.setAuthor(dto.getAuthor());
        entity.setLaunchDate(dto.getLaunchDate());
        entity.setPrice(dto.getPrice());
        entity.setTitle(dto.getTitle());

        var newDTO = parseObject(
                repository.save(entity),
                BooksDTO.class
        );

        addHateoasLinks(newDTO);

        return newDTO;
    }

    public void delete(Integer id) {
        logger.info("Deleting a Book");

        Books entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found"));

        repository.delete(entity);
    }

    private static void addHateoasLinks(BooksDTO dto) {
        //GET - FindById
        dto.add(linkTo(methodOn(BooksController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        //GET - FindAll
        dto.add(linkTo(methodOn(BooksController.class).findAll()).withRel("findAll").withType("GET"));
        //Post - Create
        dto.add(linkTo(methodOn(BooksController.class).create(dto)).withRel("create").withType("POST"));
        //PUT - Create
        dto.add(linkTo(methodOn(BooksController.class).update(dto)).withRel("update").withType("PUT"));
        //DELETE
        dto.add(linkTo(methodOn(BooksController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}