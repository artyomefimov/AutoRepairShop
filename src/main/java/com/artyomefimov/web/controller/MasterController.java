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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MasterController {
    private MasterRepository masterRepository;

    @Autowired
    public MasterController(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @GetMapping(value = "**/workshop/{workshopId}/masters", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Master>> getMastersByWorkshopInn(@PathVariable Long workshopId) {
        return new ResponseEntity<>(
                masterRepository.findAllByWorkshop_WorkshopId(workshopId),
                HttpStatus.OK);
    }

    @GetMapping(value = "**/level/{levelId}/masters", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Master>> getMastersByLevelId(@PathVariable Long levelId) {
        return new ResponseEntity<>(
                masterRepository.findAllByLevel_LevelId(levelId),
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

    @PutMapping(value = "**/masters/master/{masterId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Master> updateMaster(@PathVariable Long masterId,
                                               @RequestBody @Valid Master master) {
        return new ResponseEntity<>(
                masterRepository.save(master),
                HttpStatus.OK);
    }

    @GetMapping(value = "**/masters/master/{masterId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Master> getMaster(@PathVariable Long masterId) {
        Optional<Master> master = masterRepository.findById(masterId);
        return master
                .map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "**/masters/master/{masterId}")
    public ResponseEntity<Void> deleteMaster(@PathVariable Long masterId) {
        try {
            masterRepository.deleteById(masterId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
