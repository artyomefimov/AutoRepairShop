package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Workshop;
import com.artyomefimov.database.repository.CustomerRepository;
import com.artyomefimov.database.repository.MasterRepository;
import com.artyomefimov.database.repository.WorkshopRepository;
import com.artyomefimov.web.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(WebConstants.WORKSHOPS_PAGE)
public class WorkshopController {
    private WorkshopRepository workshopRepository;
    private CustomerRepository customerRepository;
    private MasterRepository masterRepository;

    @Autowired
    public WorkshopController(WorkshopRepository workshopRepository,
                              CustomerRepository customerRepository,
                              MasterRepository masterRepository) {
        this.workshopRepository = workshopRepository;
        this.customerRepository = customerRepository;
        this.masterRepository = masterRepository;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Workshop addWorkshop(@Valid Workshop workshop,
                                BindingResult result,
                                HttpServletResponse response) throws BindException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }

        Workshop savedWorkshop = workshopRepository.save(workshop);

        response.setHeader("Location", "/" + WebConstants.WORKSHOPS_PAGE + "/" + savedWorkshop.getInn());
        return savedWorkshop;
    }

    @GetMapping(value = "/{inn}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody Workshop editWorkshop(@PathVariable("inn") Long inn,
                                 @Valid Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    @DeleteMapping(value = "/{inn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkshop(@PathVariable("inn") Long inn) {
        workshopRepository.deleteById(inn);
    }

    @GetMapping(value = "/{inn}/masters", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMasters(@PathVariable("inn") Long inn,
                             Model model) {
        model.addAttribute(WebConstants.MASTERS_ATTRIBUTE, masterRepository.findAllByWorkshop_Inn(inn));
        return WebConstants.MASTERS_PAGE;
    }

    @GetMapping(value = "/{inn}/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCustomers(@PathVariable("inn") Long inn,
                               Model model) {
        model.addAttribute(WebConstants.CUSTOMERS_ATTRIBUTE, customerRepository.findAllByWorkshop_Inn(inn));
        return WebConstants.CUSTOMERS_PAGE;
    }
}
