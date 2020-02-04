package com.galvanize.controllers;


import com.galvanize.entities.Person;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class HelloRestController {

    @GetMapping("/hello")
    public Person sayHello(@RequestParam String name,
                           @RequestParam Date dateRegistered,
                           @RequestParam String email,
                           @RequestParam String address){
        Person person = new Person(name, dateRegistered,email,address);
        System.out.println(person);
        return  person;

    }

//    Person person = new Person();
//        System.out.println(person);
//        return  person;
//
//}

    @PostMapping("/hello")
    public Person postPerson(@RequestBody Person person){
        System.out.println(person);
        return person;
    }



}
