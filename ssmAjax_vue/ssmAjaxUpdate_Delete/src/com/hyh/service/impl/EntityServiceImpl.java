package com.hyh.service.impl;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hyh.mapper.EmpMapper;
import com.hyh.model.Emp;
import com.hyh.service.EntityService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class EntityServiceImpl<T> implements EntityService {

    private T trueQuery;
    private String primaryKey = "id";
    private T updatedEntity;
    private T setedEntity;
    private Class<T> c;
    private ServiceImpl service;

    public EntityServiceImpl(Class<T> clazz, ServiceImpl service) {
        this.service = service;
        this.c = clazz;
        try {
            trueQuery = clazz.newInstance();
            updatedEntity = clazz.newInstance();
            setedEntity = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Object> cQuery(HttpServletRequest rq, int page) {

        deleteLog();

        QueryWrapper qr = new QueryWrapper<>();

        if (rq.getParameter("page") == null) { //request的地址永远不会变，变的是里面的数据
            trueQuery = setEntityByRequest(rq, null);
        }

        Arrays.asList(c.getDeclaredMethods()).forEach(i -> {
            try {
                if (isGetter(i) && i.invoke(trueQuery) != null && i.invoke(trueQuery) != "") {
                    if (i.getReturnType() == String.class)
                        qr.like(i.getName().substring(3), i.invoke(trueQuery));
                    else
                        qr.eq(i.getName().substring(3), i.invoke(trueQuery));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        AtomicReference<String> PK = new AtomicReference<>(""); //线程安全
        Arrays.asList(c.getDeclaredFields()).forEach(i -> {
            if (i.isAnnotationPresent(TableId.class)) PK.set(i.getName()); //自动检测主键
        });
        this.primaryKey = PK.get();
        qr.orderBy(true, true, PK.get());

        Page p = PageHelper.startPage(page, 15);

        List<T> list = service.list(qr);

        Map<String, Object> map = new HashMap<>();
        map.put("primaryKey", PK.get());
        map.put("pageLength", p.getPages());
        map.put("data", list);

        return map;
    }

    @Override
    public Map<String, Object> cQueryById(int pk) {
        Map<String, Object> map = new HashMap<>();
        map.put("primaryKey", this.primaryKey);
        this.updatedEntity = (T) service.getById(pk);
        map.put("entity", this.updatedEntity);
        return map;
    }

    @Override
    public String cUpdateById(HttpServletRequest rq, int pk) {
        String s;
        this.updatedEntity = setEntityByRequest(rq, this.updatedEntity);
        System.out.println(this.updatedEntity);
        if (service.updateById(this.updatedEntity))
            s = "SUCCESSFUL UPDATE";
        else
            s = "FAILED : check your input please";
        return s;
    }

    @Override
    public String cDeleteById(int pk) {
        String s;
        if (service.removeById(pk))
            s = "SUCCESSFUL DELETE";
        else
            s = "FAILED : refresh and try again";
        return s;
    }

    private T setEntityByRequest(HttpServletRequest rq, T entity) {
        AtomicReference<T> temp = new AtomicReference<>();
        temp.set(entity);
        if (entity == null)
            try {
                temp.set((T) c.newInstance());
            } catch (Exception e) {e.printStackTrace();}
        Arrays.asList(c.getDeclaredMethods()).forEach(i -> {
            try {
                String parameter = i.getName().substring(3).toLowerCase();
                if (isSetter(i) && rq.getParameter(parameter) != null && (rq.getParameter(parameter) != "" || entity != null)) {
                    i.invoke(temp.get(), trueType(rq.getParameter(parameter), i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return temp.get();
    }

    private boolean isGetter(Method method) {
        String name = method.getName();
        return name.startsWith("get") || (name.startsWith("is") && method.getReturnType() == boolean.class);
    }

    private boolean isSetter(Method method) {
        String name = method.getName();
        return name.startsWith("set");
    }

    private Object trueType(String in, Method i) {
        Class c = i.getReturnType() == void.class ? i.getParameterTypes()[0] : i.getReturnType();
        Object temp = in;
        if (in == "") temp = null;
        else {
            if (c == Long.class) temp = Long.valueOf(in);
            else if (c == Integer.class) temp = Integer.valueOf(in);
            else if (c == Date.class) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
                try {
                    temp = new Date(Integer.valueOf(in));
                } catch (Exception e) {
                    try {
                        temp = dateFormat.parse(in);
                        temp = new Date(dateFormat.parse(in).getTime());
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return temp;
    }

    private void deleteLog() {
        File f = new File("F:\\JAVA\\20231010\\ssmAjaxUpdate_Delete\\log\\history");
        if (f.exists())
            f.delete();
    }

}
