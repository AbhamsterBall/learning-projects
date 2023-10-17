package com.hyh.structAction;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyh.dao.ProductDAO;
import com.hyh.pojo.Dleft;
import com.hyh.pojo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("listProductMybatisActionBean")
public class ListProductMybatisAction extends ActionSupport {
    @Autowired
    private ProductDAO productDAO;
    private List<Dleft> productList;

    private int page = 1;

    private String url = "/mybatis?page=1";

    public String getUrl() {
        return "/mybatis?page=" + (page + 1);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Autowired
    public void setProductDAO(ProductDAO productDAO) { // spring通过setter注入
        this.productDAO = productDAO;
    }

    @Override
    public String execute() {
        System.out.println(page);
        PageHelper.startPage(page, 3);
        productList = productDAO.mybatisList();
        PageInfo pageInfo = new PageInfo(productList);
        System.out.println(pageInfo.getList());
        productList = pageInfo.getList();
        return SUCCESS;
    }

    public List<Dleft> getProductList() { // JSP通过get获取
        return productList;
    }
}
