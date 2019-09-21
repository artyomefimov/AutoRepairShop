package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Level;
import com.artyomefimov.database.repository.LevelRepository;
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
public class LevelController {
    private LevelRepository levelRepository;

    @Autowired
    public LevelController(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @GetMapping(value = "**/levels", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Level>> getAllLevels() {
        return new ResponseEntity<>(
                levelRepository.findAll(),
                HttpStatus.OK);
    }

    @PostMapping(
            value = "**/levels/level",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Level> createLevel(@RequestBody @Valid Level level) {
        return new ResponseEntity<>(
                levelRepository.save(level),
                HttpStatus.CREATED);
    }

    @PutMapping(value = "**/levels/level/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Level> updateLevel(@PathVariable Long id,
                                             @RequestBody @Valid Level level) {
        return new ResponseEntity<>(
                levelRepository.save(level),
                HttpStatus.OK);
    }

    @GetMapping(value = "**/levels/level/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Level> getLevel(@PathVariable Long id) {
        Optional<Level> level = levelRepository.findById(id);
        return level
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "**/levels/level/{id}")
    public ResponseEntity<Void> deleteLevel(@PathVariable Long id) {
        try {
            levelRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
