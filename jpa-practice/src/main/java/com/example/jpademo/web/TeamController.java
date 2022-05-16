package com.example.jpademo.web;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    @GetMapping
    public void findall(){
        


    }


}
