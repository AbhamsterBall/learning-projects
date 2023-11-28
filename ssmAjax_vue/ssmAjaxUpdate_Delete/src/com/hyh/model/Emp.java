package com.hyh.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @TableName EMP
 */
@Data
@TableName("scott.emp")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Emp implements Serializable {
    @TableId
    private Integer empno;

    private String ename;

    private String job;

    private Integer mgr;

    private Date hiredate;

    private Long sal;

    private Long comm;

    private Integer deptno;

    private static final long serialVersionUID = 1L;

}