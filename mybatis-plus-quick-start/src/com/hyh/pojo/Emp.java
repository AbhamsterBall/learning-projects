package com.hyh.pojo;

//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.context.annotation.Bean;
//import lombok.Data;

import java.util.Date;
import javax.annotation.Generated;

//@Data
@TableName(value="emp", schema="scott",
        keepGlobalPrefix = false, autoResultMap = true) //com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: com.hyh.pojo.Emp Not Found TableInfoCache.
public class Emp {
    @TableId("empno")
    @TableField("empno")
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.511+08:00", comments="Source field: SCOTT.EMP.EMPNO")
    private Short empEmpno;

    @TableField("ename")
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.524+08:00", comments="Source field: SCOTT.EMP.ENAME")
    private String empEname;

    @TableField("job")
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.525+08:00", comments="Source field: SCOTT.EMP.JOB")
    private String empJob;

    @TableField("mgr")
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.526+08:00", comments="Source field: SCOTT.EMP.MGR")
    private Short empMgr;

    @TableField("hiredate")
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.527+08:00", comments="Source field: SCOTT.EMP.HIREDATE")
    private Date empHiredate;

    @TableField("sal")
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.528+08:00", comments="Source field: SCOTT.EMP.SAL")
    private long empSal;

    @TableField("comm")
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.529+08:00", comments="Source field: SCOTT.EMP.COMM")
    private long empComm;

    @TableField("deptno")
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.529+08:00", comments="Source field: SCOTT.EMP.DEPTNO")
    private Short empDeptno;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.52+08:00", comments="Source field: SCOTT.EMP.EMPNO")
    public Short getEmpEmpno() {
        return empEmpno;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.523+08:00", comments="Source field: SCOTT.EMP.EMPNO")
    public void setEmpEmpno(Short empEmpno) {
        this.empEmpno = empEmpno;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.524+08:00", comments="Source field: SCOTT.EMP.ENAME")
    public String getEmpEname() {
        return empEname;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.525+08:00", comments="Source field: SCOTT.EMP.ENAME")
    public void setEmpEname(String empEname) {
        this.empEname = empEname;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.525+08:00", comments="Source field: SCOTT.EMP.JOB")
    public String getEmpJob() {
        return empJob;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.526+08:00", comments="Source field: SCOTT.EMP.JOB")
    public void setEmpJob(String empJob) {
        this.empJob = empJob;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.526+08:00", comments="Source field: SCOTT.EMP.MGR")
    public Short getEmpMgr() {
        return empMgr;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.527+08:00", comments="Source field: SCOTT.EMP.MGR")
    public void setEmpMgr(Short empMgr) {
        this.empMgr = empMgr;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.527+08:00", comments="Source field: SCOTT.EMP.HIREDATE")
    public Date getEmpHiredate() {
        return empHiredate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.528+08:00", comments="Source field: SCOTT.EMP.HIREDATE")
    public void setEmpHiredate(Date empHiredate) {
        this.empHiredate = empHiredate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.528+08:00", comments="Source field: SCOTT.EMP.SAL")
    public long getEmpSal() {
        return empSal;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.529+08:00", comments="Source field: SCOTT.EMP.SAL")
    public void setEmpSal(long empSal) {
        this.empSal = empSal;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.529+08:00", comments="Source field: SCOTT.EMP.COMM")
    public long getEmpComm() {
        return empComm;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.529+08:00", comments="Source field: SCOTT.EMP.COMM")
    public void setEmpComm(long empComm) {
        this.empComm = empComm;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.531+08:00", comments="Source field: SCOTT.EMP.DEPTNO")
    public Short getEmpDeptno() {
        return empDeptno;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-18T15:58:43.531+08:00", comments="Source field: SCOTT.EMP.DEPTNO")
    public void setEmpDeptno(Short empDeptno) {
        this.empDeptno = empDeptno;
    }

    public Emp(Short empEmpno, String empEname, String empJob, Short empMgr, Date empHiredate, long empSal, long empComm, Short empDeptno) {
        this.empEmpno = empEmpno;
        this.empEname = empEname;
        this.empJob = empJob;
        this.empMgr = empMgr;
        this.empHiredate = empHiredate;
        this.empSal = empSal;
        this.empComm = empComm;
        this.empDeptno = empDeptno;
    }
}