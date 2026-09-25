package br.com.erudio.controllers;

import br.com.erudio.data.dto.PersonDTO;
import br.com.erudio.service.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
