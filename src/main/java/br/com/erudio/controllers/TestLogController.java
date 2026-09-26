package br.com.erudio.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController("/api/test/v1")
public class TestLogController {

    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @GetMapping
    public String testLog(){
        logger.info("This is an INFO Log!");
        logger.debug("This is an DEBUG Log!");
        logger.warn("This is an WARN Log!");
        logger.error("This is an ERROR Log!");
        return "Logs generated successfully!";
    }
}
