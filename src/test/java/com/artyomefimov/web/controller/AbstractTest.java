package com.artyomefimov.web.controller;

import com.artyomefimov.database.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
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

    protected List<Workshop> workshopList = asList(
            new Workshop(1, "1", "1", new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis()), "1"),
            new Workshop(2, "2", "2", new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis()), "1"),
            new Workshop(3,"3", "3", new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis()), "1")
    );

    protected List<Car> carList = asList(
            new Car("num1", "mark1", "model1", "crashType1", 11, new Customer(), new Master()),
            new Car("num2", "mark2", "model2", "crashType2", 12, new Customer(), new Master()),
            new Car("num3", "mark3", "model3", "crashType3", 13, new Customer(), new Master())
    );

    protected List<Customer> customerList = asList(
            new Customer(1,"name1", "phone1", "address1", new Date(System.currentTimeMillis()), new Workshop()),
            new Customer(2,"name2", "phone2", "address2", new Date(System.currentTimeMillis()), new Workshop()),
            new Customer(3,"name3", "phone3", "address3", new Date(System.currentTimeMillis()), new Workshop())
    );

    protected List<Level> levelList = asList(
            new Level("level1"),
            new Level("level2"),
            new Level("level3")
    );

    protected List<Master> masterList = asList(
            new Master(1,"name1", "phone1", new Level(), new Workshop()),
            new Master(2,"name2", "phone2", new Level(), new Workshop()),
            new Master(3,"name3", "phone3", new Level(), new Workshop())
    );
}
