package com.hyh.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyh.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class EmpController {

    @Autowired
    EmpService empService;

//    @RequestMapping("/emp")
//    public String handle() {
//        return "emp";
//    }

    @RequestMapping(value = "/values/emp", produces = "application/json; charset=utf-8")
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody String handleSelect(@RequestParam(value = "page", defaultValue = "1") int page,
                                             HttpServletRequest rq, HttpServletResponse rs) {

        GsonBuilder gb = new GsonBuilder();
        gb.serializeNulls();
        Gson g = gb.create();

//        System.out.println((((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()).getSession().getId());
        rs.addCookie(new Cookie("rq", String.valueOf(rq))); //测试用没有传参
//        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().setAttribute("hello", "asdfasdf");
//        rq.getSession().setAttribute("hello", "awefawefa");
//        System.out.println(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getAttribute("hello"));
        System.out.println(rq.getSession().getId());
        rq.getSession().setAttribute("hello", "weefaefe");

        return g.toJson(empService.cQuery(rq, page));
    }

    @RequestMapping(value = "return/delete/{pk}", produces = "application/text; charset=utf-8")
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody String handleDelete(@PathVariable("pk") int pk) {
        return empService.cDeleteById(pk);
    }

    @RequestMapping(value = "/return/insert", produces = "application/text; charset=UTF-8")
    @CrossOrigin(origins = "http://localhost:8081")
    public @ResponseBody String hanleI(HttpServletRequest rq) {
        return empService.cInsert(rq);
    }
}
