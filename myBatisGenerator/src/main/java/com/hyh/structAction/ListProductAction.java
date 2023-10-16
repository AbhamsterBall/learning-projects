package com.hyh.structAction;

import com.hyh.dao.ProductDAO;
import com.hyh.pojo.Product;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("listProductActionBean")
public class ListProductAction extends ActionSupport {
    @Autowired
    private ProductDAO productDAO;
    private List<Product> listProduct;

    @Autowired
    public void setProductDAO(ProductDAO productDAO) { // spring通过setter注入
        this.productDAO = productDAO;
    }

    @Override
    public String execute() {
        listProduct = productDAO.list();
        return SUCCESS;
    }

    public List<Product> getListProduct() { // JSP通过get获取
        return listProduct;
    }
}
