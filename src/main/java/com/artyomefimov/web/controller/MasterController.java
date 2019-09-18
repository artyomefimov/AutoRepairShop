package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Master;
import com.artyomefimov.database.repository.CarRepository;
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
@RequestMapping(WebConstants.MASTERS_PAGE)
public class MasterController {
    private MasterRepository masterRepository;
    private CarRepository carRepository;
    private WorkshopRepository workshopRepository;

    @Autowired
    public MasterController(MasterRepository masterRepository,
                            CarRepository carRepository,
                            WorkshopRepository workshopRepository) {
        this.masterRepository = masterRepository;
        this.carRepository = carRepository;
        this.workshopRepository = workshopRepository;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    Master createMaster(@RequestBody @Valid Master master) {
        return masterRepository.save(master);
    }

    @PutMapping(value = "/{passportNum}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Master updateMaster(@PathVariable Long passportNum,
                        @RequestBody @Valid Master master) {
        return masterRepository.save(master);
    }

    @GetMapping(value = "/update/{passportNum}")
    public ResponseEntity<Master> getMaster(@PathVariable Long passportNum) {
        Optional<Master> master = masterRepository.findById(passportNum);
        return master
                .map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{passportNum}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaster(@PathVariable Long passportNum) {
        try {
            masterRepository.deleteById(passportNum);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping(value = "/{passportNum}/cars")
    public String getCarsByMasterPassportNum(@PathVariable Long passportNum,
                                             Model model) {
        model.addAttribute(
                WebConstants.CARS_ATTRIBUTE,
                carRepository.findAllByMaster_MasterPassportNum(passportNum));
        return WebConstants.CARS_PAGE;
    }

    @GetMapping(value = "/workshops")
    public String returnToWorkshopsPage(Model model) {
        model.addAttribute(
                WebConstants.WORKSHOPS_ATTRIBUTE,
                workshopRepository.findAll());
        return WebConstants.WORKSHOPS_PAGE;
    }
}
