package exampleDetail.model0.scott;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;

public class employer {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.313+08:00", comments="Source field: SCOTT.EMP.EMPNO")
    private Short empno;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.319+08:00", comments="Source field: SCOTT.EMP.ENAME")
    private String ename;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.32+08:00", comments="Source field: SCOTT.EMP.JOB")
    private String job;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.321+08:00", comments="Source field: SCOTT.EMP.MGR")
    private Short mgr;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.321+08:00", comments="Source field: SCOTT.EMP.HIREDATE")
    private Date startDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.322+08:00", comments="Source field: SCOTT.EMP.SAL")
    private BigDecimal sal;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.322+08:00", comments="Source field: SCOTT.EMP.DEPTNO")
    private Short deptno;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.318+08:00", comments="Source field: SCOTT.EMP.EMPNO")
    public Short getEmpno() {
        return empno;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.319+08:00", comments="Source field: SCOTT.EMP.EMPNO")
    public void setEmpno(Short empno) {
        this.empno = empno;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.32+08:00", comments="Source field: SCOTT.EMP.ENAME")
    public String getEname() {
        return ename;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.32+08:00", comments="Source field: SCOTT.EMP.ENAME")
    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.32+08:00", comments="Source field: SCOTT.EMP.JOB")
    public String getJob() {
        return job;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.32+08:00", comments="Source field: SCOTT.EMP.JOB")
    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.321+08:00", comments="Source field: SCOTT.EMP.MGR")
    public Short getMgr() {
        return mgr;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.321+08:00", comments="Source field: SCOTT.EMP.MGR")
    public void setMgr(Short mgr) {
        this.mgr = mgr;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.321+08:00", comments="Source field: SCOTT.EMP.HIREDATE")
    public Date getStartDate() {
        return startDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.321+08:00", comments="Source field: SCOTT.EMP.HIREDATE")
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.322+08:00", comments="Source field: SCOTT.EMP.SAL")
    public BigDecimal getSal() {
        return sal;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.322+08:00", comments="Source field: SCOTT.EMP.SAL")
    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.323+08:00", comments="Source field: SCOTT.EMP.DEPTNO")
    public Short getDeptno() {
        return deptno;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-12T16:58:46.323+08:00", comments="Source field: SCOTT.EMP.DEPTNO")
    public void setDeptno(Short deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "employer{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", startDate=" + startDate +
                ", sal=" + sal +
                ", deptno=" + deptno +
                '}';
    }
}