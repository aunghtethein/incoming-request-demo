package com.jdc.incomingrequestdemo.controller;

import com.jdc.incomingrequestdemo.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @Autowired
    private ArticleDao articleDao;

    @GetMapping({"/index","/"})
    public String index(Model model){
        model.addAttribute("articles",articleDao.findAll());
        return "index";
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello spring";
    }
}
