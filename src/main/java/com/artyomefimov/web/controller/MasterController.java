package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Master;
import com.artyomefimov.database.repository.MasterRepository;
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
public class MasterController {
    private MasterRepository masterRepository;

    @Autowired
    public MasterController(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @GetMapping(value = "**/workshop/{inn}/masters", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Master>> getMastersByWorkshopInn(@PathVariable Long inn) {
        return new ResponseEntity<>(
                masterRepository.findAllByWorkshop_Inn(inn),
                HttpStatus.OK);
    }

    @GetMapping(value = "**/level/{id}/masters", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Master>> getMastersByLevelId(@PathVariable Long id) {
        return new ResponseEntity<>(
                masterRepository.findAllByLevel_Id(id),
                HttpStatus.OK);
    }

    @PostMapping(
            value = "**/masters/master",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Master> createMaster(@RequestBody @Valid Master master) {
        return new ResponseEntity<>(
                masterRepository.save(master),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "**/masters/master/{passportNum}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Master> updateMaster(@PathVariable Long passportNum,
                                               @RequestBody @Valid Master master) {
        return new ResponseEntity<>(
                masterRepository.save(master),
                HttpStatus.OK);
    }

    @GetMapping(value = "**/masters/master/{passportNum}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Master> getMaster(@PathVariable Long passportNum) {
        Optional<Master> master = masterRepository.findById(passportNum);
        return master
                .map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "**/masters/master/{passportNum}")
    public ResponseEntity<Void> deleteMaster(@PathVariable Long passportNum) {
        try {
            masterRepository.deleteById(passportNum);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
