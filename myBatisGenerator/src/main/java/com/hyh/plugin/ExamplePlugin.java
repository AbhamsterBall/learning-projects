package com.hyh.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

// ExamplePlugin.java
@Intercepts({@Signature(
        type= Executor.class,
        method = "update",
        args = {MappedStatement.class,Object.class}),
        @Signature(type = Executor.class,
                method = "query",
                args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
        })
public class ExamplePlugin implements Interceptor {
    private Properties properties = new Properties();
    public Object intercept(Invocation invocation) throws Throwable {
//        Calendar c = Calendar.getInstance();
//        c.setTime(invocation.getArgs()[0]);
        System.out.println("plugin");
        System.out.println(invocation.getTarget());
        System.out.println(((MappedStatement)invocation.getArgs()[0]).getId());
        System.out.println();
//        invocation.getArgs()[0] = invocation.getArgs()[0] + "beforeUpdate";
        Object returnObject = invocation.proceed();
        // implement post processing if need
        return returnObject;
    }
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
