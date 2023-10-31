package com.hyh.service.impl;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hyh.mapper.EmpMapper;
import com.hyh.model.Emp;
import com.hyh.service.EmpService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class EmpServiceImpl<T> extends ServiceImpl<EmpMapper<T>, T> implements EmpService<T> {

    private T trueQuery = new T();
    private String primaryKey = "id";
    private T updatedEntity;
    private T setedEntity;

//    public EmpServiceImpl(T model) {
//        trueQuery = model;
//    }

    @Override
    public Map<String, Object> cQuery(HttpServletRequest rq, Object model, int page) {

        deleteLog();

        Class c = model.getClass();
        QueryWrapper qr = new QueryWrapper<>();

        if (rq.getParameter("page") == null) { //request的地址永远不会变，变的是里面的数据
            trueQuery = setEntityByRequest(rq, c, null);
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

        List<Emp> list = this.list(qr);

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
        this.updatedEntity = this.getById(pk);
        map.put("entity", this.updatedEntity);
        return map;
    }

    @Override
    public String cUpdateById(HttpServletRequest rq, Object model, int pk) {
        String s;
        this.updatedEntity = setEntityByRequest(rq, model.getClass(), this.updatedEntity);
        System.out.println(this.updatedEntity);
        if (this.updateById(this.updatedEntity))
            s = "SUCCESSFUL UPDATE";
        else
            s = "FAILED : check your input please";
        return s;
    }

    private T setEntityByRequest(HttpServletRequest rq, Class c, T entity) {
        AtomicReference<T> temp = new AtomicReference<>();
        temp.set(entity);
        if (entity == null)
            temp.set((T) new Object());
        Arrays.asList(c.getDeclaredMethods()).forEach(i -> {
            try {
                String parameter = i.getName().substring(3).toLowerCase();
                if (isSetter(i) && rq.getParameter(parameter) != null && rq.getParameter(parameter) != "") {
                    System.out.println(trueType(rq.getParameter(parameter), i));
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
        if (c == Long.class) temp = Long.valueOf(in);
        else if (c == Integer.class) temp = Integer.valueOf(in);
        else if (c == Date.class) temp = new Date(Integer.valueOf(in));
        return temp;
    }

    private void deleteLog() {
        File f = new File("F:\\JAVA\\20231010\\ssmAjaxUpdate_Delete\\log\\history");
        if (f.exists())
            f.delete();
    }

}
