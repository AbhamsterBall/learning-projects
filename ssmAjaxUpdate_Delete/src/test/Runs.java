package test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyh.mapper.EmpMapper;
import com.hyh.model.Emp;
import com.hyh.service.EmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.SqlColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
public class Runs {

    @Autowired
    private EmpService empService;

    @Autowired
    private EmpMapper empMapper;

    private static boolean isGetter(Method method) {
        String name = method.getName();
        return name.startsWith("get") || (name.startsWith("is") && method.getReturnType() == boolean.class);
    }

//    List<Object> allInfo =

    @Test
    public void test0() {
        Emp emp = new Emp();
        emp.setEmpno(1); emp.setComm((long)2000);

        LambdaQueryWrapper<Emp> qr = new LambdaQueryWrapper<>();
        qr.like(Emp::getEname, "de")
                .like(Emp::getJob, "new");
//        PageHelper.startPage(1, 15);
        List<Emp> list = empService.list(qr);

        invokeMethods(list.get(1), true);
        list.forEach(i -> invokeMethods(i, false));

    }

    private void invokeMethods(Emp e, Boolean printNames) {
        Class<Emp> c = Emp.class;

        Arrays.asList(c.getDeclaredMethods()).forEach(i -> {
            try {
                if (isGetter(i)) {
                    if (printNames) System.out.println(i.getName().substring(3));
                    else i.invoke(e);
                }
            } catch (IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Test
    public void testMethod() {
//        test0();

        Class c = Emp.class;
        QueryWrapper<Emp> qr = new QueryWrapper<>();

        Emp e = new Emp();
        e.setComm((long)100);
        e.setSal((long)1000);
        e.setEname("de");
        Arrays.asList(c.getDeclaredMethods()).forEach(i -> {
            try {
                if (isGetter(i) && i.invoke(e) != null) {
                    qr.like(i.getName().substring(3), i.invoke(e));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        PageHelper.startPage(1, 15);

        empService.list(qr);

    }

    @Test
    public void testNew() {
        MockHttpServletRequest request = new MockHttpServletRequest();
//        request.setParameter("ename", "a");
        request.setParameter("sal", "5000");
        request.setParameter("job", "Manager");
        request.setParameter("comm", "");
        GsonBuilder gsonbuilder = new GsonBuilder();
        gsonbuilder.serializeNulls();
        Gson gson = gsonbuilder.create();
//        empService.cQuery(request, new Emp(), 1);
        System.out.println(gson.toJson(empService.cQuery(request, new Emp(), 1)));
    }
}
