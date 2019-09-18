package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Level;
import com.artyomefimov.database.repository.LevelRepository;
import com.artyomefimov.database.repository.MasterRepository;
import com.artyomefimov.database.repository.WorkshopRepository;
import com.artyomefimov.web.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(WebConstants.LEVELS_PAGE)
public class LevelController {
    private LevelRepository levelRepository;
    private WorkshopRepository workshopRepository;
    private MasterRepository masterRepository;

    @Autowired
    public LevelController(LevelRepository levelRepository,
                           WorkshopRepository workshopRepository,
                           MasterRepository masterRepository) {
        this.levelRepository = levelRepository;
        this.workshopRepository = workshopRepository;
        this.masterRepository = masterRepository;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Level createLevel(@RequestBody @Valid Level level) {
        return levelRepository.save(level);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Level updateLevel(@PathVariable Long id,
                      @RequestBody @Valid Level level) {
        return levelRepository.save(level);
    }

    @GetMapping(value = "/update/{id}")
    public ResponseEntity<Level> getLevel(@PathVariable Long id) {
        Optional<Level> level = levelRepository.findById(id);
        return level
                .map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevel(@PathVariable Long id) {
        try {
            levelRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}/masters")
    public String getMastersByLevelId(@PathVariable Long id,
                                      Model model) {
        model.addAttribute(
                WebConstants.MASTERS_ATTRIBUTE,
                masterRepository.findAllByLevel_Id(id));
        return WebConstants.MASTERS_PAGE;
    }

    @GetMapping(value = "/workshops")
    public String returnToWorkshopsPage(Model model) {
        model.addAttribute(
                WebConstants.WORKSHOPS_ATTRIBUTE,
                workshopRepository.findAll());
        return WebConstants.WORKSHOPS_PAGE;
    }
}
