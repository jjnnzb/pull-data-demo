package com.example.demo.controller;

import com.example.demo.entity.Response;
import com.example.demo.service.DataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Jiang Jining
 * @since 2020/12/31 19:38
 */
@RestController
@CrossOrigin
public class DataController {
    
    @Resource
    private DataService dataService;
    
    @PostMapping(value = "/api/v1/data/analyse")
    public Response pullDataController(@RequestParam(value = "keyword") String keyword) {
        return Response.success(dataService.analyseData(keyword));
    }
}
