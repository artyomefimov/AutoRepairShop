package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Workshop;
import com.artyomefimov.database.repository.WorkshopRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    public void testReturnWorkshopList() throws Exception {
        String jsonList = objectMapper.writeValueAsString(workshops);
        when(workshopRepository.findAll()).thenReturn(workshops);
        mockMvc.perform(
                get("/workshops"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonList));
    }

    @Test
    public void testCreatingWorkshop() throws Exception {
        String jsonWorkshop = objectMapper.writeValueAsString(workshops.get(0));
        doReturn(workshops.get(0)).when(workshopRepository).save(any(Workshop.class));

        mockMvc.perform(
                post(
                        "/workshops")
                        .content(jsonWorkshop)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonWorkshop));
    }

    @Test
    public void testUpdatingWorkshop() throws Exception {
        String jsonWorkshop = objectMapper.writeValueAsString(workshops.get(0));
        doReturn(workshops.get(0)).when(workshopRepository).save(any(Workshop.class));

        mockMvc.perform(
                put(
                        "/workshops/123")
                        .content(jsonWorkshop)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonWorkshop));
    }

    @Test
    public void testGettingWorkshopById() throws Exception {
        String jsonWorkshop = objectMapper.writeValueAsString(workshops.get(0));
        Optional<Workshop> workshopById = Optional.of(workshops.get(0));
        doReturn(workshopById).when(workshopRepository).findById(eq(Long.valueOf(123L)));

        mockMvc.perform(
                get("/workshops/update/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonWorkshop));
    }

    @Test
    public void testDeletingWorkshopById() throws Exception {
        mockMvc.perform(
                delete("/workshops/123"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}