package com.hyh.dao.impl;

import com.hyh.dao.ProductDAO;
import com.hyh.pojo.Product;
import com.hyh.pojo.User;
import com.hyh.transaction.impl.TransactionImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDAO.class);

    private SessionFactory sessionFactory;

    @Autowired
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Product> list() {
        @SuppressWarnings("unchecked") //unchecked是对编译器来说不可预知的错误 checked对编译器来说可以预知所以会强制捕获
        List<Product> listProduct = (List<Product>)
                sessionFactory.getCurrentSession().createCriteria(Product.class) // hibernate数据库操作会话通过元数据了解表结构后生成相应SQL
                        .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY) // DISTINCT_ROOT_ENTITY代表了数据库中的DISTINCT
                        .list(); // 返回类型
        System.out.println(listProduct);
        return listProduct;
    }

    @Override
    @Transactional
    public List<Product> mybatisList() {
        SqlSessionFactory factory = giveFactory();
        SqlSession sqlSession = factory.openSession();
        List<Product> productList = sqlSession.getMapper(ProductDAO.class).mybatisList();
        return productList;
    }

    @Override
    @Transactional
    public List<User> updateAge() {
        SqlSessionFactory factory = giveFactory();
        SqlSession sqlSession = factory.openSession();
        List<User> users = sqlSession.getMapper(ProductDAO.class).getAll();
        for (User u : users) {
            boolean re = sqlSession.getMapper(ProductDAO.class).update(u.getId(), u.getName(), u.getSex(), u.getAge() + 1);
            if (!re) {
                LOGGER.warn(() -> {
                    return "update fail UserId = " + u.getId();
                });
            }
        }
        sqlSession.commit();
        users = sqlSession.getMapper(ProductDAO.class).getAll();
        return users;
    }

    @Override
    public boolean update(int id, String name, String sex, int age) {
        //do nothing for mybatis processed method
        return false;
    }

    @Override
    public List<User> getAll() {
        //do nothing for mybatis processed method
        return null;
    }

    public SqlSessionFactory giveFactory() { //启用mybatis配置文件
        SqlSessionFactory factory = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); //使用
            factory = new SqlSessionFactoryBuilder().build(inputStream);
            inputStream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        factory.getConfiguration().setDatabaseId("oracle"); //set database type
        System.out.println(factory.getConfiguration().getDatabaseId());

        return factory;
    }


}
