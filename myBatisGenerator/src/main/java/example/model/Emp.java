package example.model;

import jakarta.annotation.Generated;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Supplier;

public class Emp {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6466808+08:00", comments="Source field: SCOTT.EMP.EMPNO")
    private Short empno;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.648648+08:00", comments="Source field: SCOTT.EMP.ENAME")
    private String ename;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6496078+08:00", comments="Source field: SCOTT.EMP.JOB")
    private String job;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6496078+08:00", comments="Source field: SCOTT.EMP.MGR")
    private Short mgr;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6496078+08:00", comments="Source field: SCOTT.EMP.HIREDATE")
    private Date hiredate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.650552+08:00", comments="Source field: SCOTT.EMP.SAL")
    private BigDecimal sal;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.650552+08:00", comments="Source field: SCOTT.EMP.COMM")
    private BigDecimal comm;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6515282+08:00", comments="Source field: SCOTT.EMP.DEPTNO")
    private Short deptno;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.648648+08:00", comments="Source field: SCOTT.EMP.EMPNO")
    public Short getEmpno() {
        return empno;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.648648+08:00", comments="Source field: SCOTT.EMP.EMPNO")
    public void setEmpno(Short empno) {
        this.empno = empno;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.648648+08:00", comments="Source field: SCOTT.EMP.ENAME")
    public String getEname() {
        return ename;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.648648+08:00", comments="Source field: SCOTT.EMP.ENAME")
    public void setEname(String ename) {
        this.ename = ename;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6496078+08:00", comments="Source field: SCOTT.EMP.JOB")
    public String getJob() {
        return job;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6496078+08:00", comments="Source field: SCOTT.EMP.JOB")
    public void setJob(String job) {
        this.job = job;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6496078+08:00", comments="Source field: SCOTT.EMP.MGR")
    public Short getMgr() {
        return mgr;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6496078+08:00", comments="Source field: SCOTT.EMP.MGR")
    public void setMgr(Short mgr) {
        this.mgr = mgr;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.650552+08:00", comments="Source field: SCOTT.EMP.HIREDATE")
    public Date getHiredate() {
        return hiredate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.650552+08:00", comments="Source field: SCOTT.EMP.HIREDATE")
    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.650552+08:00", comments="Source field: SCOTT.EMP.SAL")
    public BigDecimal getSal() {
        return sal;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.650552+08:00", comments="Source field: SCOTT.EMP.SAL")
    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.650552+08:00", comments="Source field: SCOTT.EMP.COMM")
    public BigDecimal getComm() {
        return comm;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6515282+08:00", comments="Source field: SCOTT.EMP.COMM")
    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6515282+08:00", comments="Source field: SCOTT.EMP.DEPTNO")
    public Short getDeptno() {
        return deptno;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-10T11:48:19.6515282+08:00", comments="Source field: SCOTT.EMP.DEPTNO")
    public void setDeptno(Short deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                '}';
    }
}