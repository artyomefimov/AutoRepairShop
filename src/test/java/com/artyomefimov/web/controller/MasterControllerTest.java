package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Master;
import com.artyomefimov.database.repository.MasterRepository;
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

public class MasterControllerTest extends AbstractTest {
    @MockBean
    private MasterRepository masterRepository;

    private String jsonListMasters;
    private String jsonSingleMaster;

    public MasterControllerTest() throws Exception {
        this.jsonListMasters = objectMapper.writeValueAsString(masterList);
        this.jsonSingleMaster = objectMapper.writeValueAsString(masterList.get(0));
    }

    @Test
    public void testGettingMastersByWorkshopInn() throws Exception {
        when(masterRepository.findAllByWorkshop_WorkshopId(any(Long.class)))
                .thenReturn(masterList);

        mockMvc.perform(
                get("/workshop/3645123456/masters"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonListMasters));
    }

    @Test
    public void testGettingMastersByLevelId() throws Exception {
        when(masterRepository.findAllByLevel_LevelId(any(Long.class)))
                .thenReturn(masterList);

        mockMvc.perform(
                get("/level/3645123456/masters"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonListMasters));
    }

    @Test
    public void testCreatingMaster() throws Exception {
        doReturn(masterList.get(0)).when(masterRepository).save(any(Master.class));

        mockMvc.perform(
                post(
                        "/masters/master")
                        .content(jsonSingleMaster)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleMaster));
    }

    @Test
    public void testUpdatingMaster() throws Exception {
        doReturn(masterList.get(0)).when(masterRepository).save(any(Master.class));

        mockMvc.perform(
                put(
                        "/masters/master/123")
                        .content(jsonSingleMaster)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleMaster));
    }

    @Test
    public void testGettingMasterById() throws Exception {
        Optional<Master> masterById = Optional.of(masterList.get(0));
        doReturn(masterById).when(masterRepository).findById(eq(Long.valueOf(123L)));

        mockMvc.perform(
                get("/masters/master/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(jsonSingleMaster));
    }

    @Test
    public void testDeletingMasterById() throws Exception {
        mockMvc.perform(
                delete("/masters/master/123"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}