package com.shubham.project.spring_network.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test Group", description = "A group of testing apis")
@RestController
@Log4j2
public class Test {

    private final Logger logger = LoggerFactory.getLogger(Test.class);

    @GetMapping("/test")
    public String getHello () {
        return "Hello There !!!";
    }

    @GetMapping("/lombok")
    public String index() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return "Howdy! Check out the Logs to see the output...";
    }
}
