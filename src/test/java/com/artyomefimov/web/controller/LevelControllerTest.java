package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Level;
import com.artyomefimov.database.repository.LevelRepository;
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

public class LevelControllerTest extends AbstractTest {
    @MockBean
    private LevelRepository levelRepository;

    private String jsonListLevels;
    private String jsonSingleLevel;

    public LevelControllerTest() throws Exception {
        this.jsonListLevels = objectMapper.writeValueAsString(levelList);
        this.jsonSingleLevel = objectMapper.writeValueAsString(levelList.get(0));
    }

    @Test
    public void testReturningLevelList() throws Exception {
        when(levelRepository.findAll()).thenReturn(levelList);

        mockMvc.perform(
                get("/levels"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonListLevels));
    }

    @Test
    public void testCreatingLevel() throws Exception {
        doReturn(levelList.get(0)).when(levelRepository).save(any(Level.class));

        mockMvc.perform(
                post(
                        "/levels/level")
                        .content(jsonSingleLevel)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleLevel));
    }

    @Test
    public void testUpdatingLevel() throws Exception {
        doReturn(levelList.get(0)).when(levelRepository).save(any(Level.class));

        mockMvc.perform(
                put(
                        "/levels/level/123")
                        .content(jsonSingleLevel)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleLevel));
    }

    @Test
    public void testGettingLevelById() throws Exception {
        Optional<Level> levelById = Optional.of(levelList.get(0));
        doReturn(levelById).when(levelRepository).findById(eq(Long.valueOf(123L)));

        mockMvc.perform(
                get("/levels/level/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleLevel));
    }

    @Test
    public void testDeletingLevelById() throws Exception {
        mockMvc.perform(
                delete("/levels/level/123"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}