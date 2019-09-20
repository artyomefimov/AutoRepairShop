package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Workshop;
import com.artyomefimov.database.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class WorkshopController {
    private WorkshopRepository workshopRepository;

    @Autowired
    public WorkshopController(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @GetMapping(value = "/workshops", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Workshop>> getAllWorkshops() {
        return new ResponseEntity<>(workshopRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(
            value = "/workshops/workshop",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Workshop> createWorkshop(@RequestBody @Valid Workshop workshop) {
        return new ResponseEntity<>(workshopRepository.save(workshop), HttpStatus.CREATED);
    }

    @PutMapping(value = "/workshops/workshop/{inn}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Workshop> updateWorkshop(@PathVariable Long inn,
                                                   @RequestBody @Valid Workshop workshop) {
        return new ResponseEntity<>(workshopRepository.save(workshop), HttpStatus.OK);
    }

    @GetMapping(value = "/workshops/workshop/{inn}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Workshop> getWorkshop(@PathVariable Long inn) {
        Optional<Workshop> workshop = workshopRepository.findById(inn);
        return workshop.
                map(workshop1 -> new ResponseEntity<>(workshop1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/workshops/workshop/{inn}")
    public ResponseEntity<Void> deleteWorkshop(@PathVariable Long inn) {
        try {
            workshopRepository.deleteById(inn);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
