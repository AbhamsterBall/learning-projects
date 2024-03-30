package com.hyh.structAction;

import com.hyh.dao.ProductDAO;
import com.hyh.pojo.User;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userAction")
public class UserAction extends ActionSupport {
    @Autowired
    private ProductDAO productDAO;
    private List<User> UserList;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) { // spring通过setter注入
        this.productDAO = productDAO;
    }

    @Override
    public String execute() {
        UserList = productDAO.updateAge();
        return SUCCESS;
    }

    public List<User> getUserList() { // JSP通过get获取
        return UserList;
    }
}
