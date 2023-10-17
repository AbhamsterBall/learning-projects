package com.hyh.dao;

import com.hyh.pojo.Dleft;
import com.hyh.pojo.Product;
import com.hyh.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import java.util.List;

public interface ProductDAO {
    /*managed by strut2Action*/
    List<Product> list();
    List<Dleft> mybatisList();
    List<User> updateAge();

    /*managed by mybatis*/
    List<Dleft> findUserList(@Param("start") int start, @Param("size") int size);
    boolean update(@Param("id") int id, @Param("name") String name, @Param("sex") String sex, @Param("age") int age);
    @InsertProvider(type= SqlProviderAdapter.class, method="insert")
    void insert(InsertStatementProvider<Product> insertStatement);
    List<User> getAll();
}
