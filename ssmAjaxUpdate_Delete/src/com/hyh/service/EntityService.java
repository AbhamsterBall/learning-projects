package com.hyh.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hyh.model.Emp;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public interface EmpService<T> extends IService<T> {
    Map<String, Object> cQuery(HttpServletRequest rq, Object model, int page);
    Map<String, Object> cQueryById(int pk);
    String cUpdateById(HttpServletRequest rq, Object model, int pk);
}
