package com.hyh.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class initConfigration {

    @Autowired
    private SqlSessionFactory sessionFactory;

    @Bean("empNo")
    public Integer getEmpNo() {
        return 1;
    }

    @PostConstruct //配置interceptor
    public void addMyInterceptor() {
        MybatisPlusInterceptor interceptor = mybatisPlusInterceptor();
        sessionFactory.getConfiguration().addInterceptor(interceptor);
    }

    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.ORACLE));
        return interceptor;
    }

}
