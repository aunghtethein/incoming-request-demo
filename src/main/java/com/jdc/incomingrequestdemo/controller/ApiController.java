package com.jdc.incomingrequestdemo.controller;

import com.jdc.incomingrequestdemo.dao.ArticleDao;
import com.jdc.incomingrequestdemo.ds.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/articles",produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {
    @Autowired
    private ArticleDao articleDao;

    //http://localhost:8080/api/articles
    @GetMapping
    public ResponseEntity<Iterable<Article>> listArticles(){
        return ResponseEntity.ok().body(articleDao.findAll());
    }

    //http://localhost:8080/api/articles/2
    @GetMapping("{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable int id){
        return articleDao.findById(id)
                .map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.notFound().build());
    }
    //curl -I localhost:8080/api/articles
    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Iterable<Article>> getArticlesCount(){
        return ResponseEntity.ok()
                .header("Articles-Count",String.valueOf(articleDao.count()))
                .body(articleDao.findAll());
    }
    //curl -v -X PUT localhost:8080/api/articles -H 'Content-Type:application/json' -d '{"title":"New article","body":"New article content"}'
    //@RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)//second part is content negotiation
    @PutMapping
    public ResponseEntity addArticle(@RequestBody Article article){
        articleDao.save(article);
        return ResponseEntity.ok().build();
    }


    //curl -v -X PATCH localhost:8080/api/articles/1 -H 'Content-Type:application/json' -d '{"title":"Updated article","body":"Updated article content"}'
    @PatchMapping(value = "{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateArticle(@PathVariable int id, @RequestBody Article article){
        if(articleDao.existsById(id)){
            article.setId(id);
            articleDao.save(article);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    //curl -v -X DELETE localhost:8080/api/articles/3
    @DeleteMapping("{id}")
    public ResponseEntity deleteArticleById(@PathVariable int id){
        if (articleDao.existsById(id)){
            articleDao.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
