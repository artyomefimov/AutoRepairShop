package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Workshop;
import com.artyomefimov.database.repository.WorkshopRepository;
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

public class WorkshopControllerTest extends AbstractTest {
    @MockBean
    private WorkshopRepository workshopRepository;

    private String jsonListWorkshops;
    private String jsonSingleWorkshop;

    public WorkshopControllerTest() throws Exception {
        this.jsonListWorkshops = objectMapper.writeValueAsString(workshopList);
        this.jsonSingleWorkshop = objectMapper.writeValueAsString(workshopList.get(0));
    }

    @Test
    public void testReturningWorkshopList() throws Exception {
        when(workshopRepository.findAll()).thenReturn(workshopList);

        mockMvc.perform(
                get("/workshops"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonListWorkshops));
    }

    @Test
    public void testCreatingWorkshop() throws Exception {
        doReturn(workshopList.get(0)).when(workshopRepository).save(any(Workshop.class));

        mockMvc.perform(
                post(
                        "/workshops/workshop")
                        .content(jsonSingleWorkshop)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleWorkshop));
    }

    @Test
    public void testUpdatingWorkshop() throws Exception {
        doReturn(workshopList.get(0)).when(workshopRepository).save(any(Workshop.class));

        mockMvc.perform(
                put(
                        "/workshops/workshop/123")
                        .content(jsonSingleWorkshop)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleWorkshop));
    }

    @Test
    public void testGettingWorkshopById() throws Exception {
        Optional<Workshop> workshopById = Optional.of(workshopList.get(0));
        doReturn(workshopById).when(workshopRepository).findById(eq(Long.valueOf(123L)));

        mockMvc.perform(
                get("/workshops/workshop/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleWorkshop));
    }

    @Test
    public void testDeletingWorkshopById() throws Exception {
        mockMvc.perform(
                delete("/workshops/workshop/123"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}