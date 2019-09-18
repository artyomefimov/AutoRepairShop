package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.Workshop;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Time;
import java.util.List;

import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractTest {
    @Autowired
    protected MockMvc mockMvc;

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected String incorrectWorkshopJson =
            "{\"inn\":null," +
                    "\"name\":\"1\"," +
                    "\"address\":\"1\"," +
                    "\"openHours\":\"STRINGVALUE\"," +
                    "\"closeHours\":\"18:33:09\"," +
                    "\"ownerName\":\"1\"," +
                    "\"masters\":[]," +
                    "\"customers\":[]}";

    protected List<Workshop> workshops = asList(
            new Workshop("1", "1", new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis()), "1"),
            new Workshop("2", "2", new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis()), "1"),
            new Workshop("3", "3", new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis()), "1"));
}
