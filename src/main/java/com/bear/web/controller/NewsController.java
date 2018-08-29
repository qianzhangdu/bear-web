package com.bear.web.controller;

import com.bear.web.model.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public WebApiResponse test() {
        logger.info("===========info=============");
        logger.warn("===========warn=============");
        logger.error("===========error=============");
        return WebApiResponse.success("小熊科技!");
    }
}
