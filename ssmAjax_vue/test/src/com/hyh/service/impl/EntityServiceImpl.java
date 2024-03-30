package com.hyh.service.impl;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hyh.service.EntityService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EntityServiceImpl<M extends BaseMapper<T>, T> implements EntityService<T> {

    private ServiceImpl<M, T> service;
    private Class<T> clazz;

    private T trueQuery;
    private T updatedEntity;
    private String pkStr;
    private T insertedEntity;

    public EntityServiceImpl(ServiceImpl<M, T> service, Class<T> clazz) {
        this.service = service;
        this.clazz = clazz;

        try {
            this.trueQuery = clazz.newInstance();
            this.updatedEntity = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Object> cQuery(HttpServletRequest rq, int page) {
        if (rq.getParameter("page") == null)//url传值？？？ .。。><
            trueQuery = setEntity(rq, null);
        System.out.println(trueQuery);

        for (Field f : clazz.getDeclaredFields()) {
            if (f.isAnnotationPresent(TableId.class))
                pkStr = f.getName();
        }

        QueryWrapper<T> qr = new QueryWrapper<>();
        try {
            qr = new QueryWrapper<>();
            for (Method i : clazz.getDeclaredMethods()) {
                String parm = i.getName().substring(3).toLowerCase();
                if (i.getName().startsWith("get") && i.invoke(trueQuery) != null && (i.invoke(trueQuery) != "")) {
                    Object re = i.invoke(trueQuery);
                    if (re.getClass() == String.class)
                        qr.like(parm, re);
                    else
                        qr.eq(parm, re);
                }
            }
            qr.orderBy(true, true, pkStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(qr.getSqlSelect());
        Page p = PageHelper.startPage(page, 15);
        List<T> list = service.list(qr);
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        map.put("pkStr", pkStr);
        map.put("end", p.getPages());

        return map;
    }

    private T setEntity(HttpServletRequest rq, T entity) {
        T temp = null;
        try {
            temp = clazz.newInstance();
            if (entity != null)
                temp = entity;

            for (Method i : clazz.getDeclaredMethods()) {
                String parm = i.getName().substring(3).toLowerCase();
                if (i.getName().startsWith("set") && rq.getParameter(parm) != null && (rq.getParameter(parm) != "" || entity == null))
                    i.invoke(temp, trueType(rq.getParameter(parm), i));
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return temp;
    }

    private Object trueType(String value, Method i) {
        Object o = value;
        Class c = i.getReturnType() == void.class ? i.getParameterTypes()[0] : i.getReturnType();
        if (value == "") o = null;
        else {
            if (c == Long.class || c == long.class) o = Long.parseLong(value);
            else if (c == Integer.class || c == int.class) o = Integer.parseInt(value);
            else if (c == Date.class) {
                System.out.println("in");
                try {
                    o = new Date(Integer.parseInt(value));
                } catch (Exception e) {
                    SimpleDateFormat simple = new SimpleDateFormat("MMM dd, yyyy");
                    try {
                        o = new Date(simple.parse(value).getTime());
                    } catch (ParseException ex) {
                        re = "格式错误，请改变格式，例如：Oct 23, 2023";
                        o = new Date(0);
                    }
                }
                System.out.println(o);
            }
        }
        return o;
    }

    private String re = "";

    @Override
    public Map<String, Object> cQueryById(int pk) {
        updatedEntity = service.getById(pk);
        Map<String, Object> map = new HashMap<>();
        map.put("data", updatedEntity);
        map.put("pkStr", pkStr);
        map.put("pk", pk);
        return map;
    }

    @Override
    public String cUpdateById(HttpServletRequest rq, int pk) {
        updatedEntity = setEntity(rq, updatedEntity);
        String actualStr = "";
        try {
            if (re == "") {
                if (service.updateById(updatedEntity)) {
                    actualStr = "更新成功";
                } else {
                    actualStr = "更新失败：请刷新或检查后重试";
                }
            }
        } catch (Exception e) {
            actualStr = "更新失败：" + e.getCause().getMessage();
        }
        System.out.println(actualStr);
        return re == "" ? actualStr : re;
    }

    @Override
    public String cDeleteById(int pk) {
        String actualStr = "";
        if (service.removeById(pk)) {
            actualStr = "删除成功";
        } else {
            actualStr = "删除失败：请刷新或检查后重试";
        }
        return actualStr;
    }

    @Override
    public String cInsert(HttpServletRequest rq) {
        insertedEntity = setEntity(rq, null);
        System.out.println(insertedEntity);
        String actualStr = "";
        try {
            if (re == "") {
                if (service.save(insertedEntity))
                    actualStr = "插入成功";
                else
                    actualStr = "FAILED: refresh and try again";
            }
        } catch (Exception e) {
            actualStr = "插入失败：" + e.getCause().getMessage();
        }

        System.out.println(re == "" ? actualStr : re);
        return re == "" ? actualStr : re;
    }
}
