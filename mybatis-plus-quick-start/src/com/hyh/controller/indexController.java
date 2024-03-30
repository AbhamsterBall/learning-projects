package com.hyh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyh.pojo.Emp;
import com.hyh.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class indexController {
    @Autowired
    EmpService empService;

    private int page = 1;

    @RequestMapping("/index")
    public String handle(@RequestParam("page") Integer page, HttpServletRequest request, HttpServletResponse response) {
        if (page != null) this.page = page;
        List<Emp> emps = empService.testIPage(page);
        request.getSession().setAttribute("emps", emps);
        return "index";
    }

}
