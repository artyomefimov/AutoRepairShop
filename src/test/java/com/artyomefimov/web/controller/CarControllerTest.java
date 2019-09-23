package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Car;
import com.artyomefimov.database.repository.CarRepository;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CarControllerTest extends AbstractTest {
    @MockBean
    private CarRepository carRepository;

    private String jsonListCars;
    private String jsonSingleCar;

    public CarControllerTest() throws Exception {
        this.jsonListCars = objectMapper.writeValueAsString(carList);
        this.jsonSingleCar = objectMapper.writeValueAsString(carList.get(0));
    }

    @Test
    public void testReturningCarsByMasterPassportNum() throws Exception {
        when(carRepository.findAllByMaster_MasterId(any(Long.class)))
                .thenReturn(carList);

        mockMvc.perform(
                get("/master/3645123456/cars"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonListCars));
    }

    @Test
    public void testReturningCarsByCustomerPassportNum() throws Exception {
        when(carRepository.findAllByCustomer_CustomerId(any(Long.class)))
                .thenReturn(carList);

        mockMvc.perform(
                get("/customer/3645123456/cars"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonListCars));
    }

    @Test
    public void testCreatingCar() throws Exception {
        doReturn(carList.get(0)).when(carRepository).save(any(Car.class));

        mockMvc.perform(
                post(
                        "/cars/car")
                        .content(jsonSingleCar)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleCar));
    }

    @Test
    public void testUpdatingCar() throws Exception {
        doReturn(carList.get(0)).when(carRepository).save(any(Car.class));

        mockMvc.perform(
                put(
                        "/cars/car/123")
                        .content(jsonSingleCar)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleCar));
    }

    @Test
    public void testGettingCarById() throws Exception {
        Optional<Car> carById = Optional.of(carList.get(0));
        doReturn(carById).when(carRepository).findById(eq(Long.valueOf(123L)));

        mockMvc.perform(
                get("/cars/car/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleCar));
    }

    @Test
    public void testDeletingCarById() throws Exception {
        mockMvc.perform(
                delete("/cars/car/123"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}