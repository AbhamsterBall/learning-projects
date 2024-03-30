package com.hyh.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EmpDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.617+08:00", comments="Source Table: SCOTT.EMP")
    public static final Emp emp = new Emp();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.618+08:00", comments="Source field: SCOTT.EMP.EMPNO")
    public static final SqlColumn<Short> empEmpno = emp.empEmpno;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.62+08:00", comments="Source field: SCOTT.EMP.ENAME")
    public static final SqlColumn<String> empEname = emp.empEname;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.621+08:00", comments="Source field: SCOTT.EMP.JOB")
    public static final SqlColumn<String> empJob = emp.empJob;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.621+08:00", comments="Source field: SCOTT.EMP.MGR")
    public static final SqlColumn<Short> empMgr = emp.empMgr;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.622+08:00", comments="Source field: SCOTT.EMP.HIREDATE")
    public static final SqlColumn<Date> empHiredate = emp.empHiredate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.622+08:00", comments="Source field: SCOTT.EMP.SAL")
    public static final SqlColumn<Long> empSal = emp.empSal;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.623+08:00", comments="Source field: SCOTT.EMP.COMM")
    public static final SqlColumn<Long> empComm = emp.empComm;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.623+08:00", comments="Source field: SCOTT.EMP.DEPTNO")
    public static final SqlColumn<Short> empDeptno = emp.empDeptno;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2023-10-17T21:55:11.618+08:00", comments="Source Table: SCOTT.EMP")
    public static final class Emp extends SqlTable {
        public final SqlColumn<Short> empEmpno = column("EMPNO", JDBCType.NUMERIC);

        public final SqlColumn<String> empEname = column("ENAME", JDBCType.VARCHAR);

        public final SqlColumn<String> empJob = column("JOB", JDBCType.VARCHAR);

        public final SqlColumn<Short> empMgr = column("MGR", JDBCType.NUMERIC);

        public final SqlColumn<Date> empHiredate = column("HIREDATE", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> empSal = column("SAL", JDBCType.NUMERIC);

        public final SqlColumn<Long> empComm = column("COMM", JDBCType.NUMERIC);

        public final SqlColumn<Short> empDeptno = column("DEPTNO", JDBCType.NUMERIC);

        public Emp() {
            super("SCOTT.EMP");
        }
    }
}