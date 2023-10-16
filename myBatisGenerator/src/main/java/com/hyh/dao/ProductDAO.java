package com.hyh.dao;

import com.hyh.pojo.Product;
import com.hyh.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDAO {
    /*managed by strut2Action*/
    List<Product> list();
    List<Product> mybatisList();
    List<User> updateAge();

    /*managed by mybatis*/
    boolean update(@Param("id") int id, @Param("name") String name, @Param("sex") String sex, @Param("age") int age);
    List<User> getAll();
}
