package com.jdc.incomingrequestdemo.controller;

import com.jdc.incomingrequestdemo.dao.ResourceDao;
import com.jdc.incomingrequestdemo.ds.Article;
import com.jdc.incomingrequestdemo.ds.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class Test {
    @Autowired
    private ResourceDao resourceDao;

    //http://localhost:8080/api/
    @GetMapping
    public ResponseEntity<Iterable<Resource>> listArticles(){
        return ResponseEntity.ok().body(resourceDao.findAll());
    }

    //curl -v -X PUT localhost:8080/api -H 'Content-Type: application/json' -d '{"id":"11","name":"New article content"}'
    @PutMapping
    public ResponseEntity<?> saveResource(@RequestBody Resource resource){
        resourceDao.save(resource);
        return ResponseEntity.ok().build();
    }
}
