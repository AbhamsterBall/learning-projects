package com.hyh.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyh.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UpdateController {

    @Autowired
    EmpService empService;

    private int pk;

//    @RequestMapping("/update/{pk}")
//    public String handle(@PathVariable("pk") int pk) {
//
//        this.pk = pk;
//
//        return "update";
//    }

    @RequestMapping(value = "/entity/{pk}", produces = "application/json; charset=utf-8")
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody String handleSelect(@PathVariable("pk") int pk) {
        this.pk = pk;

        GsonBuilder gb = new GsonBuilder();
        gb.serializeNulls();
        Gson g = gb.create();

        return g.toJson(empService.cQueryById(pk));
    }

    @RequestMapping(value = "/return/update", produces = "application/text; charset=utf-8")
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody String handleUpdate(HttpServletRequest rq) {
        return empService.cUpdateById(rq, pk);
    }

}
