package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Workshop;
import com.artyomefimov.database.repository.CustomerRepository;
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
@RequestMapping(WebConstants.WORKSHOPS_PAGE)
public class WorkshopController {
    private WorkshopRepository workshopRepository;
    private CustomerRepository customerRepository;
    private MasterRepository masterRepository;
    private LevelRepository levelRepository;

    @Autowired
    public WorkshopController(WorkshopRepository workshopRepository,
                              CustomerRepository customerRepository,
                              MasterRepository masterRepository,
                              LevelRepository levelRepository) {
        this.workshopRepository = workshopRepository;
        this.customerRepository = customerRepository;
        this.masterRepository = masterRepository;
        this.levelRepository = levelRepository;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Workshop createWorkshop(@RequestBody @Valid Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    @PutMapping(value = "/{inn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Workshop updateWorkshop(@PathVariable Long inn,
                            @RequestBody @Valid Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    @GetMapping(value = "/update/{inn}")
    public ResponseEntity<Workshop> getWorkshop(@PathVariable Long inn) {
        Optional<Workshop> workshop = workshopRepository.findById(inn);
        return workshop.
                map(workshop1 -> new ResponseEntity<>(workshop1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{inn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkshop(@PathVariable Long inn) {
        try {
            workshopRepository.deleteById(inn);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping(value = "/{inn}/masters", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMastersByWorkshopInn(@PathVariable Long inn,
                                          Model model) {
        model.addAttribute(
                WebConstants.MASTERS_ATTRIBUTE,
                masterRepository.findAllByWorkshop_Inn(inn));
        return WebConstants.MASTERS_PAGE;
    }

    @GetMapping(value = "/{inn}/customers")
    public String getCustomersByWorkshopInn(@PathVariable Long inn,
                                            Model model) {
        model.addAttribute(
                WebConstants.CUSTOMERS_ATTRIBUTE,
                customerRepository.findAllByWorkshop_Inn(inn));
        return WebConstants.CUSTOMERS_PAGE;
    }

    @GetMapping(value = "/levels")
    public String goToLevelsPage(Model model) {
        model.addAttribute(
                WebConstants.LEVELS_ATTRIBUTE,
                levelRepository.findAll());
        return WebConstants.LEVELS_PAGE;
    }
}
