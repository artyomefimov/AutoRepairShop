package com.artyomefimov.web.service;

import com.artyomefimov.Utils;
import com.artyomefimov.database.model.Master;
import com.artyomefimov.database.repository.LevelRepository;
import com.artyomefimov.database.repository.WorkshopRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterService {
    private static final String WORKSHOP_ID = "workshopId";
    private static final String LEVEL_ID = "levelId";

    private WorkshopRepository workshopRepository;
    private LevelRepository levelRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public MasterService(WorkshopRepository workshopRepository, LevelRepository levelRepository, ObjectMapper objectMapper) {
        this.workshopRepository = workshopRepository;
        this.levelRepository = levelRepository;
        this.objectMapper = objectMapper;
    }

    public Master resolveMasterFromJson(String masterJson) throws Exception {
        Master master = objectMapper.readValue(masterJson, Master.class);
        Long workshopId = Utils.resolveJsonNodeValue(objectMapper, masterJson, WORKSHOP_ID);
        if (workshopId != null)
            workshopRepository.findById(workshopId).ifPresent(master::setWorkshop);
        Long levelId = Utils.resolveJsonNodeValue(objectMapper, masterJson, LEVEL_ID);
        if (levelId != null)
            levelRepository.findById(levelId).ifPresent(master::setLevel);
        return master;
    }
}
