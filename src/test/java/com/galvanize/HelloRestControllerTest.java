package com.galvanize;

//import com.galvanize.controllers.HelloRestController;
//import com.galvanize.entities.Person;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//import java.util.Calendar;
//import java.util.Date;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.galvanize.controllers.HelloRestController;
import com.galvanize.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloRestControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void helloRestControllerTest(){
        HelloRestController hrc = new HelloRestController();
        Person p = hrc.sayHello("Koms", getTestDob(10),"koms@gmail.com", "Sandy");
        assertEquals(p.getAge(), 10);
    }

    private Date getTestDob(int years){
        LocalDate ld = LocalDate.now();
        ld.minusYears(1l);

        Calendar ci = Calendar.getInstance();
        ci.add(Calendar.YEAR, -years);
        return ci.getTime();
    }


    @Test
    void helloRegGetReturnsPersonTest() throws Exception {
        String url = "/hello?name=koms&dateRegistered=01/01/1985&email=koms.c1@gmail.com&address=seagullct";
        mvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("koms.c1@gmail.com")))
                .andExpect(jsonPath("$.age").value(35));
    }

    @Test
    void postRestControllerTest() throws Exception{
        String json = "{\"name\": \"koms\",\"dateRegistered\": \"01/01/1985\",\"email\": \"koms.c1@gmail.com\",\"address\": \"seagullct\"}";

                mvc.perform(post("/hello")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().string(containsString("koms.c1@gmail.com")))
                        .andExpect(jsonPath("$.age").value(35));


    }


}
