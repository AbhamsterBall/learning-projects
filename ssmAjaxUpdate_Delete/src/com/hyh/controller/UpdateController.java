package com.hyh.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyh.model.Emp;
import com.hyh.service.EmpService;
import com.hyh.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UpdateController {

    @Autowired
    EmpService empService;

    private int pk = 1;

    private Gson gson = new Gson();

    private Emp updatedEmp = new Emp();

    @RequestMapping("/update/{pk}")
    public String handle(@PathVariable("pk") int pk) {
        this.pk = pk;
        return "update";
    }

    @RequestMapping(value = "/values/entity", produces = "application/json")
    public @ResponseBody String handle() {

        GsonBuilder gb = new GsonBuilder();
        gb.serializeNulls();
        Gson g = gb.create();
        this.gson = g;

        return g.toJson(empService.cQueryById(this.pk));
    }

    @RequestMapping(value = "/return/update")
    public @ResponseBody String update(HttpServletRequest rq) {
        return empService.cUpdateById(rq, this.pk);
    }
}

